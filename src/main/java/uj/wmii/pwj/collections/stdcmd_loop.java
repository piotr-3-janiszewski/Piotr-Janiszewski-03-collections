package uj.wmii.pwj.collections;

import java.util.*;

public class stdcmd_loop implements Command {
	private List<Command> loop_commands;

	public stdcmd_loop() {
		this.loop_commands = null;
	}

	public stdcmd_loop(List<Command> loop_commands) {
		this.loop_commands = loop_commands;
	}

	@Override
	public float getPriority() {
		return 2.0f;
	}

	@Override
	public PartialParseState parse(String code, Parser parser) {
		if (code.charAt(0) != '[')
			return null;

		int index = 1;
		int open_brackets = 1;

		while (index < code.length() && open_brackets > 0) {
			char c = code.charAt(index);
			if (c == '[') open_brackets++;
			else if (c == ']') open_brackets--;
			index++;
		}

		if (open_brackets != 0) {
			return null;
		}

		String loop_body = code.substring(1, index - 1);

		List<Command> parsed_loop_body = parser.parse(loop_body);

		PartialParseState result = new PartialParseState();
		result.commands.add(new stdcmd_loop(parsed_loop_body));
		result.remainingCode = code.substring(index);

		return result;
	}

	@Override
	public void execute(Interpreter state) {
		while (state.memory[state.memory_index] != 0) {
			for (Command command : loop_commands)
				command.execute(state);
		}
	}

	@Override
	public String toString() {
		return "[" + loop_commands.toString() + "]";
	}
}

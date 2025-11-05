package uj.wmii.pwj.collections;

import java.util.*;

public class Parser {
	private List<Command> commands = new ArrayList<>();

	public void addCommand(Command command) {
		int index = 0;

		while (index < commands.size() && commands.get(index).getPriority() <= command.getPriority())
			index++;

		commands.add(index, command);
	}

	public List<Command> parse(String code) {

		List<Command> parsed_code = new ArrayList<>();

		boolean pass_unresolved = false;
		while (code.length() > 0 && !pass_unresolved) {
			pass_unresolved = true;
			
			PartialParseState partial_result = null;

			int command_index = 0;
			while (partial_result == null && command_index < commands.size()) {
				partial_result = commands.get(command_index).parse(code, this);
				
				command_index++;
			}

			if (partial_result == null) {
				System.err.println("ERROR during parsing ~ no matching command found:\n" + code);
				break;
			}

			pass_unresolved = false;

			code = partial_result.remainingCode;
			parsed_code.addAll(partial_result.commands);
		}

		return parsed_code;
	}

	public static Parser getDefault() {
		Parser result = new Parser();
		result.addCommand(new stdcmd_right_arrow());
		result.addCommand(new stdcmd_left_arrow());
		result.addCommand(new stdcmd_plus());
		result.addCommand(new stdcmd_minus());
		result.addCommand(new stdcmd_dot());
		result.addCommand(new stdcmd_comma());
		result.addCommand(new stdcmd_loop());
		return result;
	}
}

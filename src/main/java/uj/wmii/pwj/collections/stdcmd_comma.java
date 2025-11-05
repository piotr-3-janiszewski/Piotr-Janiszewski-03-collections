package uj.wmii.pwj.collections;

import java.io.IOException;

public class stdcmd_comma implements Command {
	@Override
	public float getPriority() {
		return 1.0f;
	}

	@Override
	public PartialParseState parse(String code, Parser parser) {
		if (code.charAt(0) != ',')
			return null;

		PartialParseState result = new PartialParseState();
		result.commands.add(new stdcmd_comma());
		result.remainingCode = code.substring(1);
		return result;
	}

	@Override
	public void execute(Interpreter state) {
		try {
			int input = state.in_stream.read();
			state.memory[state.memory_index] = (byte)input;
		} catch (IOException e) {
			throw new RuntimeException("Error reading input", e);
		}
	}

	@Override
	public String toString() {
		return ",";
	}
}

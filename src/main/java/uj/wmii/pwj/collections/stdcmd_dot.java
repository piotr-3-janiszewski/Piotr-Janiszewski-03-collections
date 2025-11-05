package uj.wmii.pwj.collections;

public class stdcmd_dot implements Command {
	@Override
	public float getPriority() {
		return 1.0f;
	}

	@Override
	public PartialParseState parse(String code, Parser parser) {
		if (code.charAt(0) != '.')
			return null;

		PartialParseState result = new PartialParseState();
		result.commands.add(new stdcmd_dot());
		result.remainingCode = code.substring(1);
		return result;
	}

	@Override
	public void execute(Interpreter state) {
		state.out_stream.print((char) (state.memory[state.memory_index] & 0xFF));
		System.err.println((char) (state.memory[state.memory_index] & 0xFF));
	}

	@Override
	public String toString() {
		return ".";
	}
}

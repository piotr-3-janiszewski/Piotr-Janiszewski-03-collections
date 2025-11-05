package uj.wmii.pwj.collections;

public class stdcmd_left_arrow implements Command {
	@Override
	public float getPriority() {
		return 1.0f;
	}

	@Override
	public PartialParseState parse(String code, Parser parser) {
		if (code.charAt(0) != '<')
			return null;

		PartialParseState result = new PartialParseState();
		result.commands.add(new stdcmd_left_arrow());
		result.remainingCode = code.substring(1);
		return result;
	}

	@Override
	public void execute(Interpreter state) {
		state.memory_index--;

		if (state.memory_index < 0)
			System.err.println("memory_index < 0");
	}

	@Override
	public String toString() {
		return "<";
	}
}

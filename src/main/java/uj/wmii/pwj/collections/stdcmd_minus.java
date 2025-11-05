package uj.wmii.pwj.collections;

public class stdcmd_minus implements Command {
	@Override
	public float getPriority() {
		return 1.0f;
	}

	@Override
	public PartialParseState parse(String code, Parser parser) {
		if (code.charAt(0) != '-')
			return null;

		PartialParseState result = new PartialParseState();
		result.commands.add(new stdcmd_minus());
		result.remainingCode = code.substring(1);
		return result;
	}

	@Override
	public void execute(Interpreter state) {
		state.memory[state.memory_index]--;
	}

	@Override
	public String toString() {
		return "-";
	}
}

package uj.wmii.pwj.collections;

public interface Command {
	public void execute(Interpreter state);

	public PartialParseState parse(String code, Parser parser);

	public float getPriority();
}

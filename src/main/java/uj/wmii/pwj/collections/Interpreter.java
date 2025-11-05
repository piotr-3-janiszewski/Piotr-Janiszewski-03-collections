package uj.wmii.pwj.collections;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class Interpreter implements Brainfuck {
	List<Command> instructions;
	Parser parser;
	protected PrintStream out_stream;
	protected InputStream in_stream;
	protected int[] memory;
	protected int memory_index;

	public void execute() {
		Arrays.fill(memory, (int)0);
		memory_index = 0;

		for (Command instruction : instructions)
			instruction.execute(this);
	}

	public Interpreter(String program, PrintStream out, InputStream in, int stackSize) {
		this(program, out, in, stackSize, Parser.getDefault());
	}

	public Interpreter(String program, PrintStream out, InputStream in, int stackSize, Parser parser) {
		this.parser = parser;
		out_stream = out;
		in_stream = in;
		memory = new int[stackSize];

		instructions = parser.parse(program.replaceAll("[^\\[\\],\\.\\+\\-<>]", ""));
	}
}

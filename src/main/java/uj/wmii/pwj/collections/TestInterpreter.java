package uj.wmii.pwj.collections;

import java.io.*;

public class TestInterpreter {
	public static void main(String[] args) {
		String code = "+++++>,<[->.<]";
		Interpreter interpreter = new Interpreter(code, System.out, System.in, 16);

		System.out.println("Parsed instructions:");
		for (Command c : interpreter.instructions) {
			System.out.print(c + " ");
		}

		System.out.println("\nExecuting...");
		interpreter.execute();

		System.out.println("\nMemory index after execution: " + interpreter.memory_index);
		System.out.println("Memory contents:");
		for (int i = 0; i < interpreter.memory.length; i++) {
			System.out.print(interpreter.memory[i] + " ");
		}
	}
}

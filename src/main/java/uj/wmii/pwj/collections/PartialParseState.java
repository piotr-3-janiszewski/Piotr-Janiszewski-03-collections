package uj.wmii.pwj.collections;

import java.util.*;

public class PartialParseState {
	public List<Command> commands;
	public String remainingCode;

	public PartialParseState() {
		commands = new ArrayList<>();
		remainingCode = new String();
	}
}

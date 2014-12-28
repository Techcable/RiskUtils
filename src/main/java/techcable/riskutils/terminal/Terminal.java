package techcable.riskutils.terminal;

public class Terminal {
	private Terminal() {}
	
	public static ITerminal getTerminal() {
		if (System.console() != null) {
			return new ConsoleTerminal(System.console());
		} else {
			return new InOutTerminal(System.out, System.in);
		}
	}

	//Static methods
	public static String readLine() {
		return getTerminal().readLine();
	}
	public static String readLine(String format, Object... args) {
		return getTerminal().readLine();
	}
	public static String readPassword() {
		return getTerminal().readPassword();
	}
	public static String readPassword(String format, Object... args) {
		return getTerminal().readPassword(format, args);
	}
	
	public static void printf(String format, Object... args) {
		getTerminal().printf(format, args);
	}
	public static void nextLine() {
		getTerminal().nextLine();
	}
}

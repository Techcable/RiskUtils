package techcable.riskutils.terminal;

public interface ITerminal {
	public abstract String readLine();
	public abstract String readLine(String format, Object... args);
	public abstract String readPassword();
	public abstract String readPassword(String format, Object... args);
	
	public abstract void printf(String format, Object... args);
	public abstract void nextLine();
}

package techcable.riskutils;

import java.util.Arrays;

import com.google.common.base.Preconditions;

public class RiskUtils {
	public static void main(String[] args) {
		Preconditions.checkNotNull(RunMode.getRunMode(args[0]), "%s is not a valid run mode", args[0]);
		RunMode mode = RunMode.getRunMode(args[0]);
		mode.run(Arrays.copyOfRange(args, 1, args.length));
	}
	
	public static enum RunMode {
		ATTACK {
			@Override
			public void run(String[] args) {
				AttackMode.getInstance().run(args);
			}
		};
		public static RunMode getRunMode(String mode) {
			switch (mode) {
			case "attack" : return ATTACK;
			default : return null;
			}
		}
		public void run(String[] args) {
			throw new UnsupportedOperationException("This run mode isn't supported");
		}
	}
}
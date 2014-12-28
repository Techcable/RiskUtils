package techcable.riskutils;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.base.Preconditions;

import static techcable.riskutils.Utils.reverseSort;
import static techcable.riskutils.terminal.Terminal.nextLine;
import static techcable.riskutils.terminal.Terminal.printf;

import lombok.*;

@Getter
@Setter
public class Territory {
	public static final Pattern TERRITORY_PATTERN = Pattern.compile("([^:]*):?(\\d+)?");
	private final String name;
	private int troops;
	private static final Random rng = new Random();
	
	public Territory(String name, int troops) {
		this.name = name;
		this.troops = troops;
	}
	
	public boolean canAttack(Territory defender) {
		return getTroops() > 1 && defender.getTroops() > 0;
	}
	
	public static Territory fromString(String s) {
		Matcher m = TERRITORY_PATTERN.matcher(s);
		Preconditions.checkArgument(m.matches(), "%s is not a valid territory string", s);
		String name = m.group(1);
		int troops = 0;
		if (m.group(2) != null) {
			troops = Integer.parseInt(m.group(2));
		}
		return new Territory(name, troops);
	}
	
	@Override
	public String toString() {
		if (troops != 0) {
			return String.format("%s:%s", getName(), Integer.toString(getTroops()));
		} else {
			return getName();
		}
	}

	public void attack(Territory defender) {
		Preconditions.checkArgument(canAttack(defender), "%s can't attack attack %s", getName(), defender.getName());
		
		int[] attackDice = roll(getAttackRolls());
		int[] defendDice = roll(defender.getDefenceRolls());
		reverseSort(attackDice);
		reverseSort(defendDice);
		
		int toCompare = Math.min(getAttackRolls(), defender.getDefenceRolls());
		
		for (int i = 0; i < toCompare; i++) {
			if (attackDice[i] > defendDice[i]) {
				defender.setTroops(defender.getTroops() - 1);
			} else {
				setTroops(getTroops() - 1);
			}
		}
	}
	
	public static int[] roll(int times) {
		int[] rolls = new int[times];
		for (int i = 0; i < times; i++) {
			rolls[i] = rng.nextInt(6) + 1;
		}
		return rolls;
	}
	
	public int getAttackRolls() {
		if (getTroops() > 3) {
			return 3;
		} else if (getTroops() == 3) {
			return 2;
		} else {
			return 1;
		}
	}
	
	public int getDefenceRolls() {
		if (getTroops() > 1) {
			return 2;
		} else {
			return 1;
		}
	}
}

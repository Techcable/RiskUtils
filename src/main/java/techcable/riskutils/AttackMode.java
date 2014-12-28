package techcable.riskutils;

import java.util.logging.Logger;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;

import static techcable.riskutils.terminal.Terminal.nextLine;
import static techcable.riskutils.terminal.Terminal.printf;
import static techcable.riskutils.terminal.Terminal.readLine;

import lombok.*;

public class AttackMode {
	private static AttackMode instance;
	public static AttackMode getInstance() {
		if (instance == null) {
			instance = new AttackMode();
		}
		return instance;
	}
	public void run(String[] args) {
		String attackerName = readLine("Please input the territory of the attacker:");
		int attackTroops = Integer.parseInt(readLine("How many troops are in %s?:"));
		String defenderName = readLine("Please input the territory of the defender:");
		int defendTroops = Integer.parseInt(readLine("How many troops are in %s?:"));
		Territory attacker = new Territory(attackerName, attackTroops);
		Territory defender = new Territory(defenderName, defendTroops);
		run(attacker, defender);
	}
	
	public void run(Territory attacker, Territory defender) {
		for (int i = 0; true; i++) {
			if (!attacker.canAttack(defender)) { //We can't attack
				break;
			}
			attacker.attack(defender);
			printf("Attack #%s:", i + 1);
			nextLine();
			printf("\t%s now has %s", attacker.getName(), attacker.getTroops());
			nextLine();
			printf("\t%s now has %s", defender.getName(), defender.getTroops());
			nextLine();
		}
		boolean win = defender.getTroops() == 0;
		if (win) {
			printf("%s beats %s with %s troops remaining", attacker.getName(), defender.getName(), attacker.getTroops());
			nextLine();
		} else {
			printf("%s survives an attack by %s with %s troops remaining", defender.getName(), attacker.getName(), defender.getTroops());
			nextLine();
			printf("%s now has %s troops", attacker.getName(), attacker.getTroops());
			nextLine();
		}
	}
}


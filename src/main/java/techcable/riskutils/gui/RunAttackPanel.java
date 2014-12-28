package techcable.riskutils.gui;

import static techcable.riskutils.terminal.Terminal.nextLine;
import static techcable.riskutils.terminal.Terminal.printf;

import javax.swing.JPanel;

import lombok.*;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import techcable.riskutils.Territory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RunAttackPanel extends JPanel {
	private JPanel buttonBox;
	private JButton goButton;
	private JPanel inputPanel;
	private JLabel attackingTerritoryLabel;
	private JTextField attackingTerritoryField;
	private JLabel attackingTroopsLabel;
	private JTextField attackingTroopsField;
	private JLabel defendingTerritoryLabel;
	private JTextField defendingTerritoryField;
	private JLabel defendingTroopsLabel;
	private JTextField defendingTroopsField;
	private JLabel attackUntilLabel;
	private JTextField attackUntilField;
	private JButton clearButton;
	public RunAttackPanel() {
		setLayout(new BorderLayout(0, 0));
		add(getButtonBox(), BorderLayout.SOUTH);
		add(getInputPanel(), BorderLayout.CENTER);
	}
	
	private JPanel getButtonBox() {
		if (buttonBox == null) {
			buttonBox = new JPanel();
			buttonBox.add(getGoButton());
			buttonBox.add(getClearButton());
		}
		return buttonBox;
	}
	private JButton getGoButton() {
		if (goButton == null) {
			goButton = new JButton("Go!");
			goButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!isValidInt(getAttackingTroopsField().getText())) {
						JOptionPane.showMessageDialog(null, getAttackingTroopsField().getText() + " is not a valid number of troops", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else if (!isValidInt(getDefendingTroopsField().getText())) {
						JOptionPane.showMessageDialog(null, getDefendingTroopsField().getText() + " is not a valid number of troops", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else if (!isValidInt(getAttackUntilField().getText())) {
						JOptionPane.showMessageDialog(null, getAttackUntilField().getText() + " is not a valid number of troops", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					Territory attacker = new Territory(getAttackingTerritoryField().getText(), Integer.parseInt(getAttackingTroopsField().getText()));
					Territory defender = new Territory(getDefendingTerritoryField().getText(), Integer.parseInt(getDefendingTroopsField().getText()));
					int attackUntil = Integer.parseInt(getAttackUntilField().getText());
					for (int i = 0; true; i++) {
						if (!attacker.canAttack(defender)) { //We can't attack
							break;
						}
						if (attacker.getTroops() <= attackUntil) { //Don't want to attack 
							break;
						}	
						attacker.attack(defender);
					}
					boolean win = defender.getTroops() == 0;
					if (win) {
						StringBuilder msg = new StringBuilder();
						msg.append(attacker.getName());
						msg.append(" beats ");
						msg.append(defender.getName());
						msg.append(" and now has ");
						msg.append(attacker.getTroops());
						msg.append(" troops.");
						JOptionPane.showMessageDialog(null, msg, "Attacker Wins", JOptionPane.INFORMATION_MESSAGE);
					} else {
						StringBuilder msg = new StringBuilder();
						msg.append(defender.getName());
						msg.append(" survives an attack by ");
						msg.append(attacker.getName());
						msg.append(" with ");
						msg.append(defender.getTroops());
						msg.append(" troops left.");
						msg.append(System.lineSeparator());
						msg.append(attacker.getName());
						msg.append(" now has ");
						msg.append(attacker.getTroops());
						msg.append(" troops left.");
						JOptionPane.showMessageDialog(null, msg, "Defender Wins", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
		}
		return goButton;
	}
	private JPanel getInputPanel() {
		if (inputPanel == null) {
			inputPanel = new JPanel();
			inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
			inputPanel.add(getAttackingTerritoryLabel());
			inputPanel.add(getAttackingTerritoryField());
			inputPanel.add(getAttackingTroopsLabel());
			inputPanel.add(getAttackingTroopsField());
			inputPanel.add(getDefendingTerritoryLabel());
			inputPanel.add(getDefendingTerritoryField());
			inputPanel.add(getDefendingTroopsLabel());
			inputPanel.add(getDefendingTroopsField());
			inputPanel.add(getAttackUntilLabel());
			inputPanel.add(getAttackUntilField());
		}
		return inputPanel;
	}
	private JLabel getAttackingTerritoryLabel() {
		if (attackingTerritoryLabel == null) {
			attackingTerritoryLabel = new JLabel("Attacking Territory:");
		}
		return attackingTerritoryLabel;
	}
	private JTextField getAttackingTerritoryField() {
		if (attackingTerritoryField == null) {
			attackingTerritoryField = new JTextField();
			attackingTerritoryField.setColumns(10);
		}
		return attackingTerritoryField;
	}
	private JLabel getAttackingTroopsLabel() {
		if (attackingTroopsLabel == null) {
			attackingTroopsLabel = new JLabel("Attacking Troops:");
		}
		return attackingTroopsLabel;
	}
	private JTextField getAttackingTroopsField() {
		if (attackingTroopsField == null) {
			attackingTroopsField = new JTextField();
			attackingTroopsField.setColumns(10);
		}
		return attackingTroopsField;
	}
	private JLabel getDefendingTerritoryLabel() {
		if (defendingTerritoryLabel == null) {
			defendingTerritoryLabel = new JLabel("Defending Territory:");
		}
		return defendingTerritoryLabel;
	}
	private JTextField getDefendingTerritoryField() {
		if (defendingTerritoryField == null) {
			defendingTerritoryField = new JTextField();
			defendingTerritoryField.setColumns(10);
		}
		return defendingTerritoryField;
	}
	private JLabel getDefendingTroopsLabel() {
		if (defendingTroopsLabel == null) {
			defendingTroopsLabel = new JLabel("Defending Troops:");
		}
		return defendingTroopsLabel;
	}
	private JTextField getDefendingTroopsField() {
		if (defendingTroopsField == null) {
			defendingTroopsField = new JTextField();
			defendingTroopsField.setColumns(10);
		}
		return defendingTroopsField;
	}
	private JLabel getAttackUntilLabel() {
		if (attackUntilLabel == null) {
			attackUntilLabel = new JLabel("Attack Until:");
		}
		return attackUntilLabel;
	}
	private JTextField getAttackUntilField() {
		if (attackUntilField == null) {
			attackUntilField = new JTextField();
			attackUntilField.setText("0");
			attackUntilField.setColumns(10);
		}
		return attackUntilField;
	}
	
	public static boolean isValidInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	private JButton getClearButton() {
		if (clearButton == null) {
			clearButton = new JButton("Clear");
			clearButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					getAttackingTerritoryField().setText("");
					getAttackingTroopsField().setText("");
					getDefendingTerritoryField().setText("");
					getDefendingTroopsField().setText("");
					getAttackUntilField().setText("0");
				}
			});
		}
		return clearButton;
	}
}
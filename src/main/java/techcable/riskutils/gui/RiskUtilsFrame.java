package techcable.riskutils.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.google.common.base.Throwables;

import techcable.riskutils.RiskUtils;

import lombok.*;

@Getter
public class RiskUtilsFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		if (GraphicsEnvironment.isHeadless()) {
			RiskUtils.main(args);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setSystemUI();
					RiskUtilsFrame frame = new RiskUtilsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RiskUtilsFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new RunAttackPanel();
		setContentPane(contentPane);
	}
	
	public static void setSystemUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			throw Throwables.propagate(e);
		}
	}
}

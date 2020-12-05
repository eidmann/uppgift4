package soa;

//import java.awt.BorderLayout; 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
//import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;

public class JmsConsumerClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMeddelande;
	private JTextField textQueue;
	private JPanel panel;
	private JButton btnStart;
	private JButton btnStop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JmsConsumerClient frame = new JmsConsumerClient();
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
	public JmsConsumerClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMeddelande = new JLabel("Meddelande:");
		lblMeddelande.setBounds(10, 11, 81, 14);
		contentPane.add(lblMeddelande);
		
		txtMeddelande = new JTextField();
		txtMeddelande.setBounds(10, 28, 315, 20);
		contentPane.add(txtMeddelande);
		txtMeddelande.setColumns(10);
		
		JButton btnHmta = new JButton("Hämta");
		btnHmta.setBounds(335, 27, 89, 23);
		contentPane.add(btnHmta);
		
		JLabel lblQueue = new JLabel("Queue:");
		lblQueue.setBounds(10, 59, 48, 14);
		contentPane.add(lblQueue);
		
		textQueue = new JTextField();
		textQueue.setBounds(56, 56, 114, 20);
		contentPane.add(textQueue);
		textQueue.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 84, 315, 166);
		contentPane.add(panel);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(335, 84, 89, 23);
		contentPane.add(btnStart);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(335, 113, 89, 23);
		contentPane.add(btnStop);
	}
}

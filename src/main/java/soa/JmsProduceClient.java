package soa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.jms.JMSException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JmsProduceClient {

	private JFrame frame;
	private JTextField textMeddelande;
	private JTextField textQueue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JmsProduceClient window = new JmsProduceClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JmsProduceClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 140);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMeddelande = new JLabel("Meddelande:");
		lblMeddelande.setBounds(10, 11, 62, 14);
		frame.getContentPane().add(lblMeddelande);
		
		textMeddelande = new JTextField();
		textMeddelande.setBounds(10, 36, 315, 20);
		frame.getContentPane().add(textMeddelande);
		textMeddelande.setColumns(10);
		
		JButton btnNewButton = new JButton("Skicka");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMessageActionPerformed(e);
			}
		});
		btnNewButton.setBounds(335, 35, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblQueue = new JLabel("Queue:");
		lblQueue.setBounds(10, 67, 36, 14);
		frame.getContentPane().add(lblQueue);
		
		textQueue = new JTextField();
		textQueue.setBounds(49, 64, 86, 20);
		frame.getContentPane().add(textQueue);
		textQueue.setColumns(10);
	}
	
	private void btnMessageActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			new JmsAmqManager().produce(textQueue.getText(), textMeddelande.getText());
		} catch (JMSException ex) {
			JOptionPane.showMessageDialog(frame, this, ex.getMessage(), 0);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, this, ex.getMessage(),0);
		}
		textMeddelande.setText(null);
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		btnMessageActionPerformed(e);
//		
//	}

}

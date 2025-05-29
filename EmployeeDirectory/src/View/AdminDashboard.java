package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminDashboard extends JPanel {

	
	
	private JTextField txtNAndD;
	private JButton btndelete;

	public AdminDashboard() {
		setLayout(null);

		JLabel lbAD = new JLabel("Admin Dashboard");
		lbAD.setFont(new Font("Arial", Font.BOLD, 22));
		lbAD.setBounds(23, 20, 282, 32);
		add(lbAD);

		txtNAndD = new JTextField();
		txtNAndD.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNAndD.setBounds(61, 68, 448, 32);
		add(txtNAndD);
		txtNAndD.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBackground(Color.RED);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Arial", Font.BOLD, 14));
		btnSearch.setBounds(551, 67, 91, 32);
		add(btnSearch);

		setPreferredSize(new Dimension(1000, 700));
		setBackground(new Color(245, 245, 245));

		
		
		//gridpanel
		JPanel gridpanel = new JPanel(new GridLayout(5, 1, 10, 10));
		gridpanel.setBackground(new Color(245, 245, 245));
		
		gridpanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		gridpanel.setBounds(23, 125, 700, 500);
		
		for(int i=1;i<=5;i++) {
			JPanel card=new JPanel(new BorderLayout());
			card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			card.setBackground(new Color(245,245,245));
			card.setBackground(Color.WHITE);
			card.setPreferredSize(new Dimension(300,60));
			
			JLabel name=new JLabel ("Admin" +i);
			name.setFont(new Font("Arial",Font.PLAIN,16));
			name.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			card.add(name,BorderLayout.CENTER);
			
			btndelete=new JButton("Delete");
			btndelete.setBackground(Color.red);
			btndelete.setFocusPainted(false);
			btndelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btndelete.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			btndelete.addActionListener(e->{
				JOptionPane.showMessageDialog(card, "Delete clicked for" + name.getText());
			});
			card.add(btndelete,BorderLayout.EAST);
			gridpanel.add(card);
				
		}

		add(gridpanel);
		
//		gridpanel.setBounds(23, 149, 767, 569);
//		add(gridpanel);
//		gridpanel.setLayout(new GridLayout(5, 1, 10, 10));

		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("AdminDashboard");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(new AdminDashboard());
			frame.setSize(1000, 730);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}

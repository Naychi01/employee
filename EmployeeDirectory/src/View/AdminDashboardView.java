package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminDashboardView extends JFrame {

	private static JPanel contentPanel;
	private CardLayout cardLayout;
	private Map<String, JPanel> panels = new LinkedHashMap<>();
	private Map<String, JLabel> navLabels = new LinkedHashMap<>();
	private Color normalColor = Color.black;
	private Color hoverColor = new Color(0, 0, 255); // Blue
	private Color activeColor = new Color(34, 139, 34); // Forest Green

	private JLabel activeLabel = null;
	private static JTextField textField;
	private static JButton btnNewButton;
	private static JTextField txtNAndD;
	

	public AdminDashboardView() {
		setTitle("Employee Directory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(817, 700);
		setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel menuBar = createMenuBar();
		mainPanel.add(menuBar, BorderLayout.NORTH);

		cardLayout = new CardLayout();
		contentPanel = new JPanel(cardLayout);
		contentPanel.setBackground(Color.WHITE);
		createPanels();

		for (Map.Entry<String, JPanel> entry : panels.entrySet()) {
			contentPanel.add(entry.getValue(), entry.getKey());
		}

		cardLayout.show(contentPanel, "AdminDashboard");
		setActiveNav("Admin Dashboard");

		mainPanel.add(contentPanel, BorderLayout.CENTER);
		
		
		

		
		getContentPane().add(mainPanel);
	}

	private JPanel createMenuBar() {
		JPanel menuBar = new JPanel(new BorderLayout());
		menuBar.setBackground(new Color(230, 230, 250));
		menuBar.setPreferredSize(new Dimension(getWidth(), 50));
		menuBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

		// Left: logo + system name
		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		leftPanel.setOpaque(false);

		JLabel menuIcon = new JLabel("\u2630");
		menuIcon.setFont(new Font("Arial", Font.PLAIN, 20));
		menuIcon.setForeground(Color.WHITE);

		JLabel systemName = new JLabel("Employee Directory");
		systemName.setFont(new Font("Arial", Font.PLAIN, 15));
		systemName.setForeground(Color.BLACK);

		leftPanel.add(menuIcon);
		leftPanel.add(systemName);

		// Center: navigation labels
		JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
		navPanel.setOpaque(false);

		String[] menuItems = { "Employee Dashboard", "Admin Dashboard", "Add Admin" };

		for (String item : menuItems) {
			JLabel navLabel = createNavLabel(item);
			navLabels.put(item, navLabel);
			navPanel.add(navLabel);
		}

		// Right: logout button
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		rightPanel.setOpaque(false);

		JButton logoutButton = new JButton("Logout");
		logoutButton.setFocusPainted(false);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBackground(new Color(220, 53, 69)); // Bootstrap Danger color
		logoutButton.setFont(new Font("Arial", Font.PLAIN, 14));
		logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

		// Button hover effect
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				logoutButton.setBackground(new Color(200, 35, 50));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				logoutButton.setBackground(new Color(220, 53, 69));
			}
		});

		logoutButton.addActionListener(e -> {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				dispose(); // or redirect to login form
				System.exit(0);
			}
		});

		rightPanel.add(logoutButton);

		menuBar.add(leftPanel, BorderLayout.WEST);
		menuBar.add(navPanel, BorderLayout.CENTER);
		menuBar.add(rightPanel, BorderLayout.EAST);

		return menuBar;
	}

	private JLabel createNavLabel(String name) {
		JLabel label = new JLabel(name);
		label.setFont(new Font("Arial", Font.PLAIN, 14));
		label.setForeground(normalColor);
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		label.setOpaque(false);
		label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (label != activeLabel) {
					label.setForeground(hoverColor);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (label != activeLabel) {
					label.setForeground(normalColor);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setActiveNav(name);
				cardLayout.show(contentPanel, name.replace(" ", ""));
			}
		});

		return label;
	}

	private void setActiveNav(String name) {
		if (activeLabel != null) {
			activeLabel.setForeground(normalColor);
		}
		activeLabel = navLabels.get(name);
		if (activeLabel != null) {
			activeLabel.setForeground(activeColor);
		}
	}

	private void createPanels() {
		panels.put("AdminDashboard", createAdminDashboard());
		panels.put("EmployeeDashboard", panelWithLabel("Employee Dashboard"));
		panels.put("AddAdmin", panelWithLabel("Add Admin"));
	}

	private JPanel panelWithLabel(String text) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(text));
		return panel;
	}

	static JPanel createAdminDashboard() {
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBox.background"));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Dashboard");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 23));
		lblNewLabel.setBounds(56, 22, 292, 36);
		panel.add(lblNewLabel);
		
		txtNAndD = new JTextField();
		txtNAndD.setBounds(104, 79, 504, 29);
		panel.add(txtNAndD);
		txtNAndD.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.RED);
		btnSearch.setFont(new Font("Arial", Font.BOLD, 14));
		btnSearch.setBounds(624, 79, 91, 29);
		panel.add(btnSearch);
		
		return panel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			AdminDashboardView view = new AdminDashboardView();
			view.setVisible(true);
		});
	}
}

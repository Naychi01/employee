package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import View.EmployeeDetailAdminView;
public class AdminDetailsView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private CardLayout cardlayout;
	private Map<String, JPanel> panels = new LinkedHashMap<>();
	private Map<String, JLabel> navLabels = new LinkedHashMap<>();
	private JPanel mainPanel;
	 
	private JLabel activeLabel = null;
	
	  private JPanel contentPane;
	    private JTextField textFieldName;
	    private JTextField textFieldPhone;
	    private JTextField textFieldEmail;
	    private JTextField textFieldDepartment;
	    private JTextField textFieldJobTitle;

	    private JTextField nameField, phoneField, emailField, hiringDateField, jobTitleField;
	    private JCheckBox isManagerCheckBox, isActiveCheckBox, isAcceptedCheckBox;

	   
	private Color normalColor = Color.WHITE;
	private Color hoverColor = Color.BLACK;
	private Color activeColor = new Color(34,139,34);
	 
	public AdminDetailsView() {
		setTitle("Employee Deo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
			 
		JPanel mainPanel = new JPanel(new BorderLayout());   
		JPanel menuBar = createMenuBar();   
		mainPanel.add(menuBar, BorderLayout.NORTH);   
		cardlayout = new CardLayout();
		contentPanel = new JPanel(cardlayout);
		createPanels();
		
		// Add the panels to contentPanel with their names as keys
		for (Map.Entry<String, JPanel> entry : panels.entrySet()) {
			contentPanel.add(entry.getValue(), entry.getKey());
		}
		
		cardlayout.show(contentPanel, "Admin Details");
		setActiveNav("Admin Details");
		mainPanel.add(contentPanel, BorderLayout.CENTER);  
		
		// Add mainPanel to the content pane with BorderLayout
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	private JPanel createMenuBar() {
		 JPanel menuBar = new JPanel(new BorderLayout());
	        menuBar.setBackground(new Color(64, 224, 208));
	        menuBar.setPreferredSize(new Dimension(getWidth(), 50));
	        menuBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

	        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
	        leftPanel.setOpaque(false);

		try {
			ImageIcon homeIcon = new ImageIcon("C:\\OJTProject\\EmployeeDirectotry\\image\\home.png");

			Image img = homeIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		JLabel iconLabel = new JLabel(new ImageIcon(img));
		iconLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		leftPanel.add(iconLabel);
		}catch(Exception e) {
			System.out.println("File not found");
		} 
		
		JLabel systemName = new JLabel("Employee Directory");
		systemName.setFont(new Font("Arial", Font.BOLD, 16));
		systemName.setForeground(Color.WHITE);
		leftPanel.add(systemName);

		JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 8));  
		 
		
		navPanel.setOpaque(false);

		 
		
		String[] menuItems = {
			"Employee Dashboard",
			"Employee Detail",
			"Admin Details",
			"Admin Dashboard",
			"Add Admin"
		};

		for (String item : menuItems) {
			JLabel navLabel = createNavLabel(item);
			navLabels.put(item, navLabel);
			navPanel.add(navLabel);
		}

		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		rightPanel.setOpaque(false);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirm = JOptionPane.showConfirmDialog(null, "Do You want to Logout?","Logout",JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					dispose();
					System.exit(0);
				}
				
				
			}
		});
		btnLogout.setFocusPainted(false);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(new Color(220, 53, 69));   
		btnLogout.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogout.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

		rightPanel.add(btnLogout);

		menuBar.add(leftPanel, BorderLayout.WEST);
		menuBar.add(navPanel, BorderLayout.CENTER);   
		menuBar.add(rightPanel, BorderLayout.EAST);   

		return menuBar;
	}

	private JLabel createNavLabel(String name) {
		JLabel label = new JLabel(name);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setForeground(normalColor);
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(label != activeLabel) {
						label.setForeground(hoverColor);
			}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(label != activeLabel) {
					label.setForeground(normalColor);
			}
			}
 			@Override
			public void mouseClicked(MouseEvent e) {
				setActiveNav(name);
				cardlayout.show(contentPanel, name.replace("",""));
				
			}
		});		


		return label;
	}

//	private void setActiveNav(String name) {
//		if (activeLabel != null) {
//			activeLabel.setForeground(normalColor);   
//		}
//		activeLabel = navLabels.get(name);
//		if (activeLabel != null) {
//			activeLabel.setForeground(Color.GREEN);   
//		}
//	}

	private void createPanels() {
		panels.put("Employee Dashboard", panelWithLabel("Employee Dashboard"));
		panels.put("Employee Detail", EmployeeDetailAdminView.createEmpDetail());
		panels.put("Admin Details",createAdminDetailsPanel());
		panels.put("Admin Dashboard", AdminDashboardView.createAdminDashboard());
		panels.put("Add Admin", panelWithLabel("Add Admin"));
	}
	
	 

private JPanel createEmpDetail() {
	// TODO Auto-generated method stub
	return null;
}

//	private JPanel createAdminDetailsPanel() {
//		JPanel panel = new JPanel(new BorderLayout());
//		  setLayout(new BorderLayout());
//	      panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
//
//	      JLabel titleLabel = new JLabel("Admin Details",SwingConstants.CENTER);
//	      
//	      JPanel formContainer = new JPanel(new GridBagLayout());
//	      formContainer.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
//	        
//	        return panel;
//	    }

 

//	private JPanel createAdminDetailsPanel() {
//       
//        return panel;
//    }
	
	 public JPanel createAdminDetailsPanel() {
	        JPanel mainPanel = new JPanel(new BorderLayout());
	        mainPanel.setBackground(Color.WHITE);

	        JLabel titleLabel = new JLabel("Admin Details", SwingConstants.CENTER);
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
	        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
	        mainPanel.add(titleLabel, BorderLayout.NORTH);

	        // Form panel with 50% width and border
	        JPanel formPanel = new JPanel();
	        formPanel.setLayout(null);
	        formPanel.setBackground(Color.WHITE);
	        formPanel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
	            BorderFactory.createEmptyBorder(20, 30, 20, 30)
	        ));
	        
	        int formWidth = (int)(getWidth() * 0.5);
	        int fieldWidth = formWidth - 60; // Account for padding
	        formPanel.setPreferredSize(new Dimension(formWidth, 700));

	        // Field positioning
	        int labelHeight = 20;
	        int fieldHeight = 30;
	        int labelYSpacing = 10;
	        int groupSpacing = 25;
	        int currentY = 20;
	        int leftMargin = 30;

	        // Name Field
	        addFormField(formPanel, "Name:", leftMargin, currentY, fieldWidth, labelHeight, labelYSpacing);
	        nameField = createTextField();
	        nameField.setBounds(leftMargin, currentY + labelHeight + labelYSpacing, fieldWidth, fieldHeight);
	        formPanel.add(nameField);
	        currentY += labelHeight + labelYSpacing + fieldHeight + groupSpacing;

	        // Phone Field
	        addFormField(formPanel, "Phone Number:", leftMargin, currentY, fieldWidth, labelHeight, labelYSpacing);
	        phoneField = createTextField();
	        phoneField.setBounds(leftMargin, currentY + labelHeight + labelYSpacing, fieldWidth, fieldHeight);
	        formPanel.add(phoneField);
	        currentY += labelHeight + labelYSpacing + fieldHeight + groupSpacing;

	        // Email Field
	        addFormField(formPanel, "Email:", leftMargin, currentY, fieldWidth, labelHeight, labelYSpacing);
	        emailField = createTextField();
	        emailField.setBounds(leftMargin, currentY + labelHeight + labelYSpacing, fieldWidth, fieldHeight);
	        formPanel.add(emailField);
	        currentY += labelHeight + labelYSpacing + fieldHeight + groupSpacing;

	        // Hiring Date Field
	        addFormField(formPanel, "Hiring Date:", leftMargin, currentY, fieldWidth, labelHeight, labelYSpacing);
	        hiringDateField = createTextField();
	        hiringDateField.setBounds(leftMargin, currentY + labelHeight + labelYSpacing, fieldWidth, fieldHeight);
	        formPanel.add(hiringDateField);
	        currentY += labelHeight + labelYSpacing + fieldHeight + groupSpacing;

	        // Checkbox Fields
	        addFormField(formPanel, "Is Manager:", leftMargin, currentY, fieldWidth, labelHeight, labelYSpacing);
	        isManagerCheckBox = createCheckBox();
	        isManagerCheckBox.setBounds(leftMargin, currentY + labelHeight + labelYSpacing, fieldWidth, fieldHeight);
	        formPanel.add(isManagerCheckBox);
	        currentY += labelHeight + labelYSpacing + fieldHeight + groupSpacing;

	        addFormField(formPanel, "Is Active:", leftMargin, currentY, fieldWidth, labelHeight, labelYSpacing);
	        isActiveCheckBox = createCheckBox();
	        isActiveCheckBox.setBounds(leftMargin, currentY + labelHeight + labelYSpacing, fieldWidth, fieldHeight);
	        formPanel.add(isActiveCheckBox);
	        currentY += labelHeight + labelYSpacing + fieldHeight + groupSpacing;

	        addFormField(formPanel, "Is Accepted:", leftMargin, currentY, fieldWidth, labelHeight, labelYSpacing);
	        isAcceptedCheckBox = createCheckBox();
	        isAcceptedCheckBox.setBounds(leftMargin, currentY + labelHeight + labelYSpacing, fieldWidth, fieldHeight);
	        formPanel.add(isAcceptedCheckBox);
	        currentY += labelHeight + labelYSpacing + fieldHeight + groupSpacing;

	        // Job Title Field
	        addFormField(formPanel, "Job Title:", leftMargin, currentY, fieldWidth, labelHeight, labelYSpacing);
	        jobTitleField = createTextField();
	        jobTitleField.setBounds(leftMargin, currentY + labelHeight + labelYSpacing, fieldWidth, fieldHeight);
	        formPanel.add(jobTitleField);
	        currentY += labelHeight + labelYSpacing + fieldHeight + groupSpacing;

	        // Update form height
	        formPanel.setPreferredSize(new Dimension(formWidth, currentY));

	        // Center the form
	        JPanel formContainer = new JPanel(new GridBagLayout());
	        formContainer.setBackground(Color.WHITE);
	        formContainer.add(formPanel);

	        // Scroll pane
	        JScrollPane scrollPane = new JScrollPane(formContainer);
	        scrollPane.setBorder(BorderFactory.createEmptyBorder());
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollPane.getVerticalScrollBar().setUnitIncrement(30);

	        mainPanel.add(scrollPane, BorderLayout.CENTER);
	        return mainPanel;
	    }

	    private void addFormField(JPanel panel, String labelText, int x, int y, int width, int height, int spacing) {
	        JLabel label = new JLabel(labelText);
	        label.setBounds(x, y, width, height);
	        panel.add(label);
	    }

	    private JTextField createTextField() {
	        JTextField field = new JTextField();
	        field.setEditable(true);
	        field.setBackground(Color.WHITE);
	        field.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
	            BorderFactory.createEmptyBorder(5, 8, 5, 8)
	        ));
	        field.setFont(new Font("Arial", Font.PLAIN, 14));
	        return field;
	    }

	    private JCheckBox createCheckBox() {
	        JCheckBox checkBox = new JCheckBox();
	        checkBox.setEnabled(true);
	        checkBox.setBackground(Color.WHITE);
	        return checkBox;
	    }

	private JPanel panelWithLabel(String text) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(text));
		return panel;
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

	    public void setAdminDetails(String name, String phone, String email, String hiringDate,
	                              boolean isManager, boolean isActive, boolean isAccepted,
	                              String jobTitle) {
	        nameField.setText(name);
	        phoneField.setText(phone);
	        emailField.setText(email);
	        hiringDateField.setText(hiringDate);
	        isManagerCheckBox.setSelected(isManager);
	        isActiveCheckBox.setSelected(isActive);
	        isAcceptedCheckBox.setSelected(isAccepted);
	        jobTitleField.setText(jobTitle);
	    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AdminDetailsView frame = new AdminDetailsView();
				frame.setVisible(true); 
			}
		});
	}
}


 
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import Config.DBConfig;
  
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
        setPlaceholder(txtNAndD,"Enter Admin name or Department ");

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Search functionality can be added here
            }
        });
        btnSearch.setBackground(Color.RED);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Arial", Font.BOLD, 14));
        btnSearch.setBounds(551, 67, 91, 32);
        add(btnSearch);

        setPreferredSize(new Dimension(800, 700));
        setBackground(new Color(245, 245, 245));

        // Create fixed grid panel for 5 admins
        JPanel gridpanel = new JPanel();
        gridpanel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 rows, 1 column, with gaps
        gridpanel.setBackground(new Color(245, 245, 245));
        gridpanel.setBounds(23, 125, 700, 500);
        gridpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        try {
            // Get admin data from database
            List<Map<String, String>> admins = DBConfig.getAdminData();
            
            if (admins.isEmpty()) {
                JLabel noDataLabel = new JLabel("No admin data found");
                noDataLabel.setFont(new Font("Arial", Font.ITALIC, 16));
                gridpanel.add(noDataLabel);
            } else {
                // Show only first 5 admins
                int count = Math.min(admins.size(), 5);
                for (int i = 0; i < count; i++) {
                    Map<String, String> admin = admins.get(i);
                    
                    JPanel card = new JPanel(new BorderLayout());
                    card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    card.setBackground(Color.WHITE);
                    card.setPreferredSize(new Dimension(680, 80)); // Fixed size for each card

                    // Info panel
                    JPanel infoPanel = new JPanel();
                    infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                    infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    
                    JLabel nameLabel = new JLabel(admin.get("name"));
                    nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    
                    JLabel deptLabel = new JLabel(admin.get("department"));
                    deptLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    deptLabel.setForeground(Color.GRAY);
                    
                    infoPanel.add(nameLabel);
                    infoPanel.add(deptLabel);
                    card.add(infoPanel, BorderLayout.CENTER);

                    // Delete button
                    JButton btndelete = new JButton("Delete");
                    btndelete.setBackground(Color.RED);
                    btndelete.setForeground(Color.WHITE);
                    btndelete.addActionListener(e -> {
                        // Remove the card from the gridpanel without deleting from database
                        int confirm = JOptionPane.showConfirmDialog(
                            card, 
                            "Delete " + admin.get("name") + "?", 
                            "Confirm Delete", 
                            JOptionPane.YES_NO_OPTION
                        );
                        if (confirm == JOptionPane.YES_OPTION) {
                            // Remove card from UI only (no database delete)
                            gridpanel.remove(card);
                            gridpanel.revalidate();
                            gridpanel.repaint();
                        }
                    });
                    card.add(btndelete, BorderLayout.EAST);

                    gridpanel.add(card);
                }
                
                // If there are fewer than 5 admins, add empty panels to maintain layout
                for (int i = count; i < 5; i++) {
                    JPanel emptyCard = new JPanel();
                    emptyCard.setBackground(new Color(245, 245, 245));
                    emptyCard.setPreferredSize(new Dimension(680, 80));
                    gridpanel.add(emptyCard);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error loading admin data: " + ex.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }

        add(gridpanel);
    }
    
    private void setPlaceholder(JTextField textField, String placeholder) {
        // Set initial placeholder
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
 
        // To keep track of whether placeholder is currently shown
        final boolean[] showingPlaceholder = {true};
 
        // Focus behavior
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder[0]) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                    showingPlaceholder[0] = false;
                }
            }
 
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                    showingPlaceholder[0] = true;
                }
            }
        });
 
        // Key listener — placeholder gets removed on first key press
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (showingPlaceholder[0]) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                    showingPlaceholder[0] = false;
                }
            }
        });
 
        // Document listener — placeholder reappears if text is cleared
        textField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void update() {
                SwingUtilities.invokeLater(() -> {
                    if (!showingPlaceholder[0] && textField.getText().isEmpty()) {
                        textField.setForeground(Color.GRAY);
                        textField.setText(placeholder);
                        showingPlaceholder[0] = true;
                        // Move caret to start to simulate placeholder behavior
                        textField.setCaretPosition(0);
                    }
                });
            }
 
            public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("AdminDashboard");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new AdminDashboard());
            frame.setSize(800, 730);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
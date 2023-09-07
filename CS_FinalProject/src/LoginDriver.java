
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.MatteBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LoginDriver extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField txtUsername;
    private JTextField fieldforUser;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginDriver frame = new LoginDriver();
                    frame.setLocationRelativeTo(null);

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public LoginDriver() {

    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

       
        JButton loginbtn = new JButton("Login");
        loginbtn.setFocusPainted(false);
        loginbtn.setBounds(73, 283, 190, 35);
        contentPane.add(loginbtn);
        loginbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String url = "jdbc:mysql://localhost:3306/university";
                String username = "root";
                String password = "Commute123"; 
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    
                    String dr_id = txtUsername.getText();
                    String dr_name = fieldforUser.getText();
                    try (Connection con = DriverManager.getConnection(url, username, password);
                            Statement st = con.createStatement()) {

                        String query = "SELECT * FROM driver WHERE DriverPlate = '" + dr_id + "' AND First_Name = '" + dr_name + "'";
                        ResultSet rs = st.executeQuery(query);
                        
                        if (rs.next()) {
                            String driverData = "Driver ID: " + rs.getString("DriverPlate")
                                              + "\nDriver Name: " + rs.getString("First_Name");

                            JOptionPane.showMessageDialog(LoginDriver.this, driverData, "Student Information", JOptionPane.INFORMATION_MESSAGE);

                            DriverAccount studentAccountFrame2 = new DriverAccount(dr_id, dr_name);
                            studentAccountFrame2.frame.setVisible(true);

                            dispose();
                        }
           }

                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                
                
                
                
                
        		
            }
        });
        loginbtn.setBounds(73, 283, 190, 35);
        contentPane.add(loginbtn);

        txtUsername = new JTextField();
        txtUsername.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
        txtUsername.setText("ID");
        txtUsername.setForeground(Color.BLACK);
        txtUsername.setEditable(false);
        txtUsername.setColumns(10);
        txtUsername.setBackground(SystemColor.menu);
        txtUsername.setBounds(48, 92, 110, 20);
        contentPane.add(txtUsername);

        txtUsername = new JTextField();
        txtUsername.setColumns(10);
        txtUsername.setBounds(48, 123, 230, 25);
        contentPane.add(txtUsername);

        fieldforUser = new JTextField();
        fieldforUser.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
        fieldforUser.setText("Name:");
        fieldforUser.setForeground(new Color(0, 0, 0));
        fieldforUser.setEditable(false);
        fieldforUser.setColumns(10);
        fieldforUser.setBackground(SystemColor.menu);
        fieldforUser.setBounds(48, 159, 110, 20);
        contentPane.add(fieldforUser);

        fieldforUser = new JTextField();
        fieldforUser.setBounds(48, 190, 230, 25);
        contentPane.add(fieldforUser);
       
     
        JLabel lblNewDriver = new JLabel("New Driver?");
        lblNewDriver.setVerticalAlignment(SwingConstants.TOP);
        lblNewDriver.setBounds(73, 385, 91, 14);
        contentPane.add(lblNewDriver);
        
        JButton btnNewButton_1 = new JButton("Register");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
         		
               	Signup_Driver new_account2 = new Signup_Driver();
               	
               	new_account2.setLocationRelativeTo(null);
               	new_account2.setVisible(true);
               	dispose();
        		
        	}
        });
        btnNewButton_1.setBounds(174, 381, 89, 23);
        contentPane.add(btnNewButton_1);
    }
}



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class Signup_Driver extends JFrame {

	private JPanel contentPane;
	private JTextField DriverPlate;
	private JTextField Driver_Name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup_Driver frame = new Signup_Driver();
					frame.setLocationRelativeTo(null);
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
	public Signup_Driver() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			Login l = new Login();
				l.setLocationRelativeTo(null);
				l.setVisible(true);
			}
		});

		setBounds(100, 100, 350, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DriverPlate = new JTextField();
		DriverPlate.setCaretColor(SystemColor.menu);
		DriverPlate.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		DriverPlate.setText("Driver Plate");
		DriverPlate.setForeground(Color.BLACK);
		DriverPlate.setEditable(false);
		DriverPlate.setColumns(10);
		DriverPlate.setBackground(SystemColor.menu);
		DriverPlate.setBounds(49, 73, 110, 20);
		contentPane.add(DriverPlate);
		
		DriverPlate = new JTextField();
		DriverPlate.setColumns(10);
		DriverPlate.setBounds(49, 104, 230, 25);
		contentPane.add(DriverPlate);
		
		Driver_Name = new JTextField();
		Driver_Name.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		Driver_Name.setText("Driver name");
		Driver_Name.setForeground(new Color(0, 0, 0));
		Driver_Name.setEditable(false);
		Driver_Name.setColumns(10);
		Driver_Name.setBackground(SystemColor.menu);
		Driver_Name.setBounds(49, 140, 110, 20);
		contentPane.add(Driver_Name);
		
		Driver_Name = new JTextField();
		Driver_Name.setBounds(49, 171, 230, 25);
		contentPane.add(Driver_Name);
		
		JButton signupbtn = new JButton("Create Account");
        signupbtn.setFocusPainted(false);
        signupbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String url = "jdbc:mysql://localhost:3306/university";
                   String username = "root";
                   String password = "Commute123"; 

                   try {
                       Class.forName("com.mysql.cj.jdbc.Driver");
                       
                       String dr_id = DriverPlate.getText();
                       String dr_name = Driver_Name.getText();

                       try (Connection con = DriverManager.getConnection(url, username, password);
                               Statement st = con.createStatement()) {

                          	String query = "INSERT INTO driver (DriverPlate, First_Name, INCOME, Students) VALUES ('" + dr_id + "', '" + dr_name + "', 0, '')";
                           int rowsAffected = st.executeUpdate(query);
                           dispose();

                           if (rowsAffected > 0) {
                              JOptionPane.showMessageDialog(Signup_Driver.this, "Account created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                           
                              LoginDriver accs = new LoginDriver();
                              accs.setLocationRelativeTo(null);
                              accs.setVisible(true);
                           
                           } else {
                               JOptionPane.showMessageDialog(Signup_Driver.this, "Failed to create account.", "Error", JOptionPane.ERROR_MESSAGE);
                           }

                       } catch (SQLException ex) {
                           ex.printStackTrace();
                       }
                   } catch (ClassNotFoundException ex) {
                       ex.printStackTrace();
                   }
               }
           });
		signupbtn.setBounds(74, 352, 190, 35);
		contentPane.add(signupbtn);
		
		
	}

}

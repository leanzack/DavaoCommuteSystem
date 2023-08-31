

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
public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField Student_ID;
	private JTextField Student_Name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
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
		
		Student_ID = new JTextField();
		Student_ID.setCaretColor(SystemColor.menu);
		Student_ID.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		Student_ID.setText("Student ID");
		Student_ID.setForeground(Color.BLACK);
		Student_ID.setEditable(false);
		Student_ID.setColumns(10);
		Student_ID.setBackground(SystemColor.menu);
		Student_ID.setBounds(49, 73, 110, 20);
		contentPane.add(Student_ID);
		
		Student_ID = new JTextField();
		Student_ID.setColumns(10);
		Student_ID.setBounds(49, 104, 230, 25);
		contentPane.add(Student_ID);
		
		Student_Name = new JTextField();
		Student_Name.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		Student_Name.setText("Student name");
		Student_Name.setForeground(new Color(0, 0, 0));
		Student_Name.setEditable(false);
		Student_Name.setColumns(10);
		Student_Name.setBackground(SystemColor.menu);
		Student_Name.setBounds(49, 140, 110, 20);
		contentPane.add(Student_Name);
		
		Student_Name = new JTextField();
		Student_Name.setBounds(49, 171, 230, 25);
		contentPane.add(Student_Name);
		
		JButton signupbtn = new JButton("Create Account");
        signupbtn.setFocusPainted(false);
        signupbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	   String url = "jdbc:mysql://localhost:3306/University";
                   String username = "root";
                   String password = "Commute123"; 

                   try {
                       Class.forName("com.mysql.cj.jdbc.Driver");
                       
                       String st_id = Student_ID.getText();
                       String st_name = Student_Name.getText();

                       try (Connection con = DriverManager.getConnection(url, username, password);
                               Statement st = con.createStatement()) {

                    	   String query = "INSERT INTO ComputerScienceStudents (Student_ID, First_Name, FARE, LANDMARK1, LANDMARK2) VALUES ('" + st_id + "', '" + st_name + "', 0, '', '')";
                           int rowsAffected = st.executeUpdate(query);
                           dispose();

                           if (rowsAffected > 0) {
                              JOptionPane.showMessageDialog(Signup.this, "Account created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                           
                              Login l = new Login();
              				l.setLocationRelativeTo(null);
              				l.setVisible(true);
                           
                           } else {
                               JOptionPane.showMessageDialog(Signup.this, "Failed to create account.", "Error", JOptionPane.ERROR_MESSAGE);
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

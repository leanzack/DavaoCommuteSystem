

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;



public class DriverAccount {

    /**
	 * 
	 */
	JFrame frame;
	private String DriverPlate;
    private String Driver_Name;


    public DriverAccount(String dr_id, String dr_name) {
        this.DriverPlate = dr_id;
        this.Driver_Name = dr_name;
       

        initialize();
    }

    void initialize() {
    	   frame = new JFrame("Student Account");
           frame.setBounds(100, 100, 1000, 700);
           frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Driver Plate: " + DriverPlate);
        lblNewLabel.setBounds(20, 20, 200, 20);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Driver Name: " + Driver_Name);
        lblNewLabel_1.setBounds(20, 50, 200, 20);
        frame.getContentPane().add(lblNewLabel_1);


        
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String url = "jdbc:mysql://localhost:3306/university";
                String username = "root";
                String password = "Commute123"; 

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
    

                    try (Connection connection = DriverManager.getConnection(url, username, password)) {
                    

                    	String updateQuery = "UPDATE driver SET income = ?, students = ?, WHERE DriverPlate = ?";

                    	try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    	 
                    	    preparedStatement.setString(3, DriverPlate);
                    	    preparedStatement.executeUpdate();

                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                } catch (ClassNotFoundException | NumberFormatException ex) {
                    ex.printStackTrace();
                }
     
        
            }
          });
      
        
      

          
            }
    }
        
     
   

      
            	
        
    


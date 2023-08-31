
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;



public class StudentAccount {

    /**
	 * 
	 */
	JFrame frame;
	private String studentId;
    private String studentName;
    private ArrayList<String> landmarkList;
    private String selectedPointA = "";
    private String selectedPointB = "";
    private DefaultListModel<String> resultListModel;

    public StudentAccount(String st_id, String st_name) {
        this.studentId = st_id;
        this.studentName = st_name;
        landmarkList = new ArrayList<>();
        landmarkList.add("GMALL Toril");
        landmarkList.add("Crossing Bayabas");
        landmarkList.add("Vales JCT");
        landmarkList.add("Pepsi Cola");
        landmarkList.add("Better Living");
        landmarkList.add("Bago Crossing");
        landmarkList.add("Puan Crossing");
        landmarkList.add("Ulas Crossing");
        landmarkList.add("Royal Valley Bangkal");
        landmarkList.add("Boundary Matina-Talomo");
        landmarkList.add("Matina Brgy. Hall");
        landmarkList.add("La Suerte Gallera");
        landmarkList.add("Tulip Drive");
        landmarkList.add("Ma-a Crossing");
        landmarkList.add("Quirino Avenue");
        landmarkList.add("Bonifacio St.");
        landmarkList.add("Ecoland Terminal");
        landmarkList.add("SM City");
        landmarkList.add("Royal Valley Bangkal reverse");
        landmarkList.add("Ulas Crossing reverse");
        landmarkList.add("Puan Crossing reverse");
        landmarkList.add("Bago Crossing reverse");
        landmarkList.add("Better Living reverse");
        landmarkList.add("Pepsi Cola reverse");
        landmarkList.add("Vales JCT reverse");
        landmarkList.add("Crossing Bayabas reverse");
        landmarkList.add("Gaisano Grand Toril reverse");
        landmarkList.add("");


        initialize();
    }

    void initialize() {
    	   frame = new JFrame("Student Account");
           frame.setBounds(100, 100, 1058, 786);
           frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Student ID: " + studentId);
        lblNewLabel.setBounds(187, 11, 200, 20);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Student Name: " + studentName);
        lblNewLabel_1.setBounds(338, 11, 200, 20);
        frame.getContentPane().add(lblNewLabel_1);


        JLabel txtlabel = new JLabel("Succeeding kilometers: ");
        txtlabel.setBounds(250, 164, 265, 105);
       frame.getContentPane().add(txtlabel);

        JLabel textlabel2 = new JLabel("TOTAL FARE");
        textlabel2.setBounds(250, 164, 265, 50);
        frame.getContentPane().add(textlabel2);

        JButton calculateButton = new JButton("Calculate Fare");
        calculateButton.setBounds(250, 83, 150, 30);
        frame.getContentPane().add(calculateButton);
        calculateButton.setBackground(Color.BLACK);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        
        JButton pointAButton = new JButton("Select Starting Landmark:");
        pointAButton.setBounds(22, 159, 218, 30);
        frame.getContentPane().add(pointAButton);
        pointAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                selectedPointA = (String) JOptionPane.showInputDialog(frame, "Select Starting Landmark:", "Start Selection", JOptionPane.PLAIN_MESSAGE, null, landmarkList.toArray(), null);
    
            }
          });
            

        JButton pointBButton = new JButton("Select Ending Landmark");
        pointBButton.setBounds(22, 200, 218, 30);
        frame.getContentPane().add(pointBButton);
        
        pointBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPointB = (String) JOptionPane.showInputDialog(frame, "Select Starting Landmark:", "Start Selection", JOptionPane.PLAIN_MESSAGE, null, landmarkList.toArray(), null);
  
            }
          });

        JList<String> resultList =  new JList<>();
        resultListModel = new DefaultListModel<>();
        resultList.setModel(resultListModel);
        resultList.setBounds(22, 280, 428, 456);
        frame.getContentPane().add(resultList);
        JScrollPane scrollPane = new JScrollPane(resultList);
        scrollPane.setBounds(22, 280, 428, 456);

        frame.getContentPane().add(scrollPane);


        JLabel lblNewLabel_2 = new JLabel("LANDMARK:");
        lblNewLabel_2.setBounds(250, 124, 200, 14);
        frame.getContentPane().add(lblNewLabel_2);
        
       
   
   

        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/University";
                String username = "root";
                String password = "Commute123";

                try {
                    Class.forName("com.mysql.jdbc.Driver");

  
                   
                    
                    String route = "Route: " + selectedPointA + " to " + selectedPointB;

                    int startIndex = landmarkList.indexOf(selectedPointA);
                    int endIndex = landmarkList.indexOf(selectedPointB);

                    resultListModel.clear();

                    if (startIndex != -1 && endIndex != -1 ) {
                        resultListModel.addElement(route);
                        for (int i = startIndex; i <= endIndex; i++) {
                            resultListModel.addElement((": " ) + landmarkList.get(i + 1));
                        }  
               
                    }
                    
                    int size = resultListModel.getSize() + -1;
                    
                    JLabel lblNewLabel_3 = new JLabel("New label");
                    lblNewLabel_3.setBounds(250, 137, 150, 40);
                    frame.getContentPane().add(lblNewLabel_3);
                    
                    lblNewLabel_3.setText(" " + size);  
                    
                    
                    
                    double firstFour = 12.00;
                    double succeeding = 1.80;
                    double totalFareValue;

                    if (size >= 4) {
                        int succeedingKilometers = size - 4;
                        totalFareValue = firstFour + (succeedingKilometers * succeeding);
                    } else if (size > 0) {
                        totalFareValue = firstFour;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                        return;
                    }
                                
                 resultListModel.addElement("Total fare: P " + totalFareValue);
                    
                    
                    

                    try (Connection connection = DriverManager.getConnection(url, username, password)) {
                    

                    	String updateQuery = "UPDATE ComputerScienceStudents SET FARE = ?, LANDMARK1 = ?, LANDMARK2 = ? WHERE Student_ID = ?";

                    	try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    	    preparedStatement.setDouble(1, totalFareValue);
                    	    preparedStatement.setString(2, selectedPointA);
                    	    preparedStatement.setString(3, selectedPointB);
                    	    preparedStatement.setString(4, studentId);
                    	    preparedStatement.executeUpdate();

                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    txtlabel.setText("Succeeding kilometers: " + (size - 4) + "");
                    textlabel2.setText("Total fare: P " + totalFareValue);

                } catch (ClassNotFoundException | NumberFormatException ex) {
                    ex.printStackTrace();
                }
     
        
            }
          });
      
        
      

          
            }
    }
        
     
        
    


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CustomerDetails extends JFrame implements ActionListener{
	private JButton Logoutbtn,AddCustbtn,Backbtn;
	private JLabel CompanyName,Tin_no,Retailername,Place,Discount;
	private JTextField Tin_noField,RetailerField,PlaceField,DiscountField;
	
	public CustomerDetails(){
		super("Stock and Invoice Management System");
		setLayout(new GridBagLayout());
		 CompanyName = new JLabel("                Shree Renuka Tyre");
		 Tin_no = new JLabel("Tin_No                            ");
		 Retailername = new JLabel("Retailer Name               ");
		 Place = new JLabel("Place                            ");
		 Discount = new JLabel("Discount                     ");
		 Tin_noField = new JTextField(20);
		 RetailerField = new JTextField(50);
		 PlaceField = new JTextField(50);
		 DiscountField = new JTextField(20);
		 Logoutbtn = new JButton("LOGOUT");
		 Logoutbtn.setForeground(Color.BLUE);
		 AddCustbtn = new JButton("ADD CUSTOMER");
		 AddCustbtn.setForeground(Color.BLUE);
		 Backbtn = new JButton("BACK");
		 Backbtn.setForeground(Color.BLUE);
		 
		 Logoutbtn.addActionListener(this);
		 AddCustbtn.addActionListener(this);
		 Backbtn.addActionListener(this);
		 GridBagConstraints gc = new GridBagConstraints();
			//final Insets insets = new Insets(4, 4, 4, 4);
	        //final Insets buttonInsets = new Insets(4, 4, 4, 8);
			CompanyName.setFont(new Font("Serif", Font.BOLD, 18));
	        CompanyName.setForeground(Color.BLACK);
	        
	        gc.insets = new Insets(4,6,4,6);
	        gc.gridx = 0;
			gc.gridy = 0;
			gc.weightx = 1;
			gc.weighty = 1;
			gc.anchor = GridBagConstraints.EAST;
			gc.fill = GridBagConstraints.NONE;
			add(CompanyName, gc);                   //////////////need to center alignment of company name
			setFont(new Font("Serif", Font.BOLD, 16));
			gc.gridx = 0;
			gc.gridy = 1;
			gc.weightx = 1;
			gc.weighty = 1;
		
			gc.anchor = GridBagConstraints.CENTER;
			gc.fill = GridBagConstraints.NONE;
			add(Tin_no, gc);
			
			
			gc.gridx = 1;
			gc.gridy = 1;
			gc.fill = GridBagConstraints.HORIZONTAL;
			add(Tin_noField, gc);
			gc.fill = GridBagConstraints.NONE;
			
			gc.gridx = 0;
			gc.gridy = 2;
			add(Retailername, gc);
			
			gc.gridx = 1;
			gc.gridy = 2;
			gc.fill = GridBagConstraints.HORIZONTAL;
			add(RetailerField, gc);
			gc.anchor = GridBagConstraints.CENTER;
			gc.gridx = 0;
			gc.gridy = 3;
			gc.fill = GridBagConstraints.NONE;
			add(Place, gc);
			
			gc.gridx = 1;
			gc.gridy = 3;
			gc.fill = GridBagConstraints.HORIZONTAL;
			add(PlaceField, gc);
			gc.gridx=0;
			gc.gridy=4;
			gc.fill = GridBagConstraints.NONE;
			add(Discount,gc);
			gc.gridx = 1;
			gc.gridy = 4;
			gc.fill = GridBagConstraints.HORIZONTAL;
			add(DiscountField, gc);
			
			gc.fill = GridBagConstraints.NONE;
			gc.gridx=1;
			gc.gridy=5;
			add(AddCustbtn,gc);
			gc.gridy=6;
			gc.gridx=0;
			gc.anchor=GridBagConstraints.LAST_LINE_START;
			add(Backbtn,gc);
			gc.gridx=1;
			gc.anchor=GridBagConstraints.LAST_LINE_END;
			add(Logoutbtn,gc);
	
			setSize(500, 500);
			//setLocation(200,200);
			setLocationByPlatform(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);

	}
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();
		if(btnClicked == Backbtn){
			StockMainPage page = new StockMainPage();
			this.setVisible(false);
		}
		else if(btnClicked == Logoutbtn){
			SigninPage page = new SigninPage();
		}else if(btnClicked == AddCustbtn){
			if(Tin_noField.getText().equals("")){
			    JOptionPane.showMessageDialog(null, "Tin no. not entered");
			}
			else if(RetailerField.getText().equals("")){
			    JOptionPane.showMessageDialog(null, "Retailer name not entered");
			}
			else if(PlaceField.getText().equals("")){
			    JOptionPane.showMessageDialog(null, "Place not entered");

			}else if(DiscountField.getText().equals("")){
			    JOptionPane.showMessageDialog(null, "Discount not entered");
			}else{
				
			String tin_no = Tin_noField.getText();
			String retailer= RetailerField.getText();
			String place = PlaceField.getText();
			Float Discount= Float.parseFloat(DiscountField.getText());
			Database db = new Database();
			try {
				db.connect();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		    try {
		    	boolean rtnresult =db.saveCust(tin_no, retailer, place,Discount);
		    	if(rtnresult == true){
		    		StockMainPage page = new StockMainPage();
		    		this.setVisible(false);
		    	}else{
				    JOptionPane.showMessageDialog(null, "Tin_no already exist");
				    this.setVisible(true);
		    	}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}			
			
		}
	}
}

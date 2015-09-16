import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


public class KeyEntry extends JFrame implements ActionListener{
	private JLabel CompanyName;
	private JLabel Key;
	private JPasswordField KeyField;
	private JButton Submitbtn;
	private boolean valid = false;
	
	public KeyEntry(){
		super("Stock and Invoice Management System");
		setLayout(new GridBagLayout());
		CompanyName = new JLabel("SHREE RENUKA TYRES");
		Key = new JLabel("Enter Key");
		KeyField = new JPasswordField(20);
		Submitbtn = new JButton("SUBMIT"); 
		Submitbtn.setForeground(Color.BLUE);
		
		Submitbtn.addActionListener(this);
		GridBagConstraints gc = new GridBagConstraints();
		final Insets insets = new Insets(4, 4, 4, 4);
        final Insets buttonInsets = new Insets(4, 4, 4, 8);
		
        CompanyName.setFont(new Font("Serif", Font.BOLD, 18));
        CompanyName.setForeground(Color.BLACK);
        Key.setFont(new Font("Serif", Font.BOLD, 16));

        gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 3;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(CompanyName, gc);
		
		gc.gridx=0;
		gc.gridy=1;
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(Key,gc);
		
		gc.gridx=0;
		gc.gridy=2;
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		
		add(KeyField,gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.weightx = 4;
		gc.weighty = 4;
		gc.insets = insets;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(Submitbtn, gc);
		
		
		
		getRootPane().setDefaultButton(Submitbtn);
		setSize(500, 500);
		//setLocation(200,200);
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();
		if(btnClicked == Submitbtn){
			//boolean validKey = false;
		/*	try {
				SendMail p = new SendMail();
			} catch (MessagingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}*/
			char[] Password = KeyField.getPassword();
			

			System.out.println("submit button clicked");
			
			System.out.println("Password: " + Password);
			String passwd = new String(Password);
			if (passwd.equals("")) {
			    JOptionPane.showMessageDialog(null, "Key not entered");
			}else{
			Database db = new Database();
			try {
				db.connect();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				valid = db.verifyKey(Password);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(valid)
			{
			InetAddress ip;
			String strMac = "";
			
			try {
					
				ip = InetAddress.getLocalHost();
				System.out.println("Current IP address : " + ip.getHostAddress());
				
				NetworkInterface network = NetworkInterface.getByInetAddress(ip);
					
				byte[] mac = network.getHardwareAddress();
					
				System.out.print("Current MAC address : ");
					
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
				}
				
				strMac = sb.toString();
				
				System.out.println(strMac);
				
					
			} catch (UnknownHostException e1) {
				
				e1.printStackTrace();
				
			} catch (SocketException e1){
					
				e1.printStackTrace();
					
			}
		    try {
		       boolean rtnResult = db.ValidateSoftwareLic(strMac);
		       if (rtnResult == true) {
		    	   System.out.println("Valid MAC ADDRESS");
		    	   LoginFrame page = new LoginFrame();
		    	   this.setVisible(false);
		    	   Submitbtn.setEnabled(false);
		       }
		       else {
		    	   System.out.println("Invalid MAC ADDRESS");
		    	   JOptionPane.showMessageDialog(null, "Not a valid key");
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
}
////EC-F4-BB-74-C9-0F
//FC-F8-AE-27-9A-ED
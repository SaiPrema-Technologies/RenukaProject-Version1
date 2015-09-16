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
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class ResetpwdVerifyPage extends JFrame implements ActionListener {

	private JLabel CompanyName;
	private JLabel NewpwdLabel;
	private JLabel VerifypwdLabel;
	private JPasswordField NewpwdField;
	private JPasswordField VerifypwdField;
	private JButton Submitbtn;
	private JButton BackToLoginPage;
	//private BackToLoginPageListener bktoLoginListener;
	String Username;
	
	public ResetpwdVerifyPage(String UserName){
		
		
		super("Stock and Invoice Management System");
		Username =UserName;

		setLayout(new GridBagLayout());

		CompanyName = new JLabel("      Renuka Tyres    \n ");
		NewpwdLabel = new JLabel("Enter New Password");
		NewpwdField = new JPasswordField(20);
		VerifypwdLabel = new JLabel("Verify New Password");
		VerifypwdField = new JPasswordField(20);
		Submitbtn = new JButton("Submit");
		Submitbtn.setForeground(Color.BLUE);
		BackToLoginPage = new JButton("Back To Login Page");
		BackToLoginPage.setForeground(Color.BLUE);

		Submitbtn.addActionListener(this);
		BackToLoginPage.addActionListener(this);

		GridBagConstraints gc = new GridBagConstraints();

		final Insets insets = new Insets(4, 4, 4, 4);
		final Insets buttonInsets = new Insets(4, 4, 4, 8);
		CompanyName.setFont(new Font("Serif", Font.BOLD, 18));
		CompanyName.setForeground(Color.BLACK);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.CENTER;
		add(CompanyName, gc); // ////////////need to center alignment of company
								// name

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(NewpwdLabel, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		add(NewpwdField, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		add(VerifypwdLabel, gc);

		gc.gridx = 1;
		gc.gridy = 2;
		add(VerifypwdField, gc);

		gc.gridx = 0;
		gc.gridy = 3;
		add(BackToLoginPage, gc);

		gc.gridx = 1;
		gc.gridy = 3;
		add(Submitbtn, gc);
		getRootPane().setDefaultButton(Submitbtn);

		setSize(500, 500);
		//setLocation(200, 200);
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	/*public ResetpwdVerifyPage(String UserName){
		Username =UserName;
	}*/

	/*public void setBackToLoginPageListener(BackToLoginPageListener listener) {
		this.bktoLoginListener = listener;
	}*/
	public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();  
        if (btnClicked == BackToLoginPage) {
        
        	LoginFrame page = new LoginFrame();
        	this.setVisible(false);
        	NewpwdField.setText("");
		    VerifypwdField.setText("");
        	
        	/*if (bktoLoginListener != null) {
				System.out.println("Back to login page button clicked");
				bktoLoginListener.FrameInitiated(this);
			}*/
        }
        else if(btnClicked == Submitbtn){
        	char[] NewPasswd = NewpwdField.getPassword();
        	char[] VerifyPasswd = VerifypwdField.getPassword();
        	System.out.println("New Password : "+NewPasswd);
        	System.out.println("Verify Password :"+VerifyPasswd);
        	
        	String NpassWd = new String(NewPasswd);
        	String VpassWd = new String(VerifyPasswd);
        	
        	if(NpassWd.equals("")) {
			    JOptionPane.showMessageDialog(null, "Password not entered");
			}else if(VpassWd.equals("")){
				JOptionPane.showMessageDialog(null, "Password not entered");
			}
			else if(NpassWd.equals(VpassWd))
        	{
        	
        		Database db = new Database();
    			try {
    				db.connect();
    			} catch (Exception e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    			
    		    try {
    		    	db.resetpwd(Username,NewPasswd);
    			} catch (SQLException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    		    
    		    new LoginFrame().setVisible(true);
    		    this.setVisible(false);
    		    NewpwdField.setText("");
    		    VerifypwdField.setText("");
        	}
        	else{
        		JOptionPane.showMessageDialog(null, "Verify password not matching entered password" );
        		System.out.println("Verify password not matching entered password");
        	}
        	
        }
	}
}

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

public class RegisterPage extends JFrame implements ActionListener {
	private JLabel CompanyName;
	private JLabel EmployeeNameLabel;
	private JLabel UserNameLabel;
	private JLabel PasswordLabel;
	private JTextField EmployeeNameField;
	private JTextField UserNameField;
	private JPasswordField PasswordField;
	private JButton SubmitToRegister;
	private JButton BackToLoginPage;
	//private BackToLoginPageListener bktoLoginListener;
	//private GoToSigninPageListener gotoLoginListener;
	
	
	public RegisterPage() {
		super("Stock and Invoice Management System");
		
		setLayout(new GridBagLayout());
		CompanyName = new JLabel("Renuka Tyres\n");
		EmployeeNameLabel = new JLabel("Employee Name");
		EmployeeNameField = new JTextField(20);
		UserNameLabel = new JLabel("User Name");
		UserNameField = new JTextField(20);
		PasswordLabel = new JLabel("Password");
		PasswordField = new JPasswordField(20);
		SubmitToRegister = new JButton("  Submit  ");
		SubmitToRegister.setForeground(Color.BLUE);
		BackToLoginPage = new JButton("Go Back To Login Page");
		BackToLoginPage.setForeground(Color.BLUE);
		SubmitToRegister.addActionListener(this);
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
		gc.fill = GridBagConstraints.NONE;
		add(CompanyName, gc);                   //////////////need to center alignment of company name
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(EmployeeNameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(EmployeeNameField, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		add(UserNameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		add(UserNameField, gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		add(PasswordLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		add(PasswordField, gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		add(BackToLoginPage, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		add(SubmitToRegister, gc);
		getRootPane().setDefaultButton(SubmitToRegister);

		setSize(500, 500);
		//setLocation(200,200);
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
/*	public void setBackToLoginPageListener(BackToLoginPageListener listener) {
		this.bktoLoginListener = listener;
	}

	public void setGoToSigninPageListener(GoToSigninPageListener listener) {
		this.gotoLoginListener = listener;
	}
	*/
	@Override
	public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
		
		if (btnClicked == SubmitToRegister) {
			
			String EmplName = EmployeeNameField.getText();
			String UserName = UserNameField.getText();
			char[] Password = PasswordField.getPassword();
			
			System.out.println("Submit to Register button clicked");
			System.out.println("EmployeeName: " + EmplName);
			System.out.println("User Name: " + UserName);
			System.out.println("Password: " + Password);
			String passwd = new String(Password);
			
			//if (EmplName == "") {
				//return;
			//}
			if (EmplName.equals("")) {
			    JOptionPane.showMessageDialog(null, "Employee name not  entered");
			} else if (UserName.equals("")) {
			    JOptionPane.showMessageDialog(null, "User name not entered");
			} else if (passwd.equals("")) {
			    JOptionPane.showMessageDialog(null, "Password not entered");
			}else{
			Database db = new Database();
			try {
				db.connect();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		    try {
		    	boolean rtnresult = db.saveUser(EmplName, UserName, Password);
		    	if(rtnresult== true){
				    System.out.println("User entered");
				    SigninPage page = new  SigninPage();
				    this.setVisible(false);
		    	}else{
		    		
				    JOptionPane.showMessageDialog(null, "User name already exist");
				    this.setVisible(true);
		    	}
		    	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    //gotoLoginListener.FrameInitiated(this);
		    
		    EmployeeNameField.setText("");
			UserNameField.setText("");
			PasswordField.setText("");
			}
		}
		else if (btnClicked == BackToLoginPage) {
			//if (bktoLoginListener != null) {
				System.out.println("Back to login page button clicked");
				//bktoLoginListener.FrameInitiated(this);
				LoginFrame page = new LoginFrame();
				this.setVisible(false);
				EmployeeNameField.setText("");
				UserNameField.setText("");
				PasswordField.setText("");
			//}			
			
		}
	}

}

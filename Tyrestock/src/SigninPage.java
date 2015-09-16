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

public class SigninPage extends JFrame implements ActionListener {

	private JLabel CompanyName;
	private JLabel UserNameLabel;
	private JLabel PasswordLabel;
	private JTextField UserNameField;
	private JPasswordField PasswordField;
	private JButton SignInBtn;
	private JButton BackToLoginPage;
	//private BackToLoginPageListener bktoLoginListener;
	private StockMainPage StockMain;


	public SigninPage() {
		super("Stock and Invoice Management System");

		setLayout(new GridBagLayout());
	/*	this.StockMain = new StockMainPage();
		StockMain.setStockmainpageListener(new StockmainpageListener() {			
	    	@Override
			public void FrameInitiated(Object frame) {
				if (frame instanceof StockMainPage) {
					setVisible(true);												
					StockMain.setVisible(false);												
				}					
			}				
		});
		*/
		
		CompanyName = new JLabel("      Renuka Tyres     ");
		UserNameLabel = new JLabel("User Name");
		UserNameField = new JTextField(20);
		PasswordLabel = new JLabel("Password");
		PasswordField = new JPasswordField(20);
		SignInBtn = new JButton("SignIn");
		SignInBtn.setForeground(Color.BLUE);
		BackToLoginPage = new JButton("Back To Login Page");
		BackToLoginPage.setForeground(Color.BLUE);

		SignInBtn.addActionListener(this);
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
		add(UserNameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		add(UserNameField, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		add(PasswordLabel, gc);

		gc.gridx = 1;
		gc.gridy = 2;
		add(PasswordField, gc);

		gc.gridx = 0;
		gc.gridy = 3;
		add(BackToLoginPage, gc);

		gc.gridx = 1;
		gc.gridy = 3;
		add(SignInBtn, gc);
		getRootPane().setDefaultButton(SignInBtn);
		setSize(500, 500);
		//setLocation(200, 200);
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
/*
	public void setBackToLoginPageListener(BackToLoginPageListener listener) {
		this.bktoLoginListener = listener;
	}
*/
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();

		if (btnClicked == SignInBtn) {

			String UserName = UserNameField.getText();
			char[] Password = PasswordField.getPassword();
			boolean validUser = false;

			System.out.println("SignIn button clicked");
			System.out.println("User Name: " + UserName);
			System.out.println("Password: " + Password);
			String passwd = new String(Password);

			
			
			if (UserName.equals("")) {
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
				validUser = db.verifyUser(UserName, Password);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (validUser) {
				System.out.println("User name and password has been verified");
				if (this.StockMain == null)
				{	
				  this.StockMain = new StockMainPage();
				}
				
				this.setVisible(false);
				
				if (this.StockMain != null) {
					this.StockMain.setVisible(true);
				}
				
				UserNameField.setText("");
				PasswordField.setText("");
				//StockMainPage page = new StockMainPage();
			} else {
				System.out.println("Failed to verify user name");
				JOptionPane.showMessageDialog(null, "Failed to verify user name");
			}
			}

		} else if (btnClicked == BackToLoginPage) {
			//if (bktoLoginListener != null) {
				System.out.println("Back to login page button clicked");
				//bktoLoginListener.FrameInitiated(this);
				LoginFrame page = new LoginFrame();
				this.setVisible(false);
				UserNameField.setText("");
				PasswordField.setText("");
		//	}

		}

	}

}

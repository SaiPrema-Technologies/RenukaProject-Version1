import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LoginFrame extends JFrame implements ActionListener {
	
	private JLabel CompanyName;
	private JButton RegisterBtn;
	private JButton SigninBtn;
	private JButton ResetpwdBtn;
	private RegisterPage Registration;
	private SigninPage Signin;
	private ResetpwdPage Resetpwd;
	//private ResetpwdVerifyPage ResetpwdVerify;
	
	public LoginFrame() {
		super("Stock and Invoice Management System");
		
		setLayout(new GridBagLayout());
		/*this.Registration = new RegisterPage();
		
		Registration.setBackToLoginPageListener(new BackToLoginPageListener() {			
	    	@Override
			public void FrameInitiated(Object frame) {
				if (frame instanceof RegisterPage) {
					setVisible(true);												
					Registration.setVisible(false);												
				}					
			}				
		});
		
		Registration.setGoToSigninPageListener(new GoToSigninPageListener() {			
	    	@Override
			public void FrameInitiated(Object frame1) {
				if (frame1 instanceof RegisterPage) {
													
					//setVisible(true);
					Signin.setVisible(true);

					Registration.setVisible(false);												
				}					
			}				
		});
		
		setLayout(new GridBagLayout());
		this.Signin = new SigninPage();
		Signin.setBackToLoginPageListener(new BackToLoginPageListener() {			
	    	@Override
			public void FrameInitiated(Object frame) {
				if (frame instanceof SigninPage) {
					setVisible(true);												
					Signin.setVisible(false);												
				}					
			}				
		});
		
		
		setLayout(new GridBagLayout());
		this.Resetpwd = new ResetpwdPage();
		Resetpwd.setBackToLoginPageListener(new BackToLoginPageListener() {			
	    	@Override
			public void FrameInitiated(Object frame) {
				if (frame instanceof ResetpwdPage) {
					setVisible(true);												
					Resetpwd.setVisible(false);												
				}					
			}				
		}); */
		/*
		setLayout(new GridBagLayout());
		this.ResetpwdVerify = new ResetpwdVerifyPage();
		ResetpwdVerify.setBackToLoginPageListener(new BackToLoginPageListener(){
			public void FrameInitiated(Object frame){
				if(frame instanceof ResetpwdVerifyPage){
					setVisible(true);
					ResetpwdVerify.setVisible(false);
				}
			}
		});
		*/
		
		CompanyName = new JLabel("<html>SHREE RENUKA TYRES</html>\n");
		RegisterBtn = new JButton("     Register     ");
		RegisterBtn.setSize(150, 150);
		RegisterBtn.setForeground(Color.BLUE);
		SigninBtn = new JButton("     Sign In     ");
		SigninBtn.setForeground(Color.BLUE);
		ResetpwdBtn = new JButton("Reset Password");
		ResetpwdBtn.setForeground(Color.BLUE);
		
		RegisterBtn.addActionListener(this);
		SigninBtn.addActionListener(this);
		ResetpwdBtn.addActionListener(this);
		
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
		add(CompanyName, gc);
        
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 4;
		gc.weighty = 4;
		gc.insets = insets;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(RegisterBtn, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		//gc.insets = buttonInsets;
		add(SigninBtn,gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		add(ResetpwdBtn, gc);
		
		
		setSize(500, 500);
		//setLocation(200,200);
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();
		
		if (btnClicked == RegisterBtn) {
			System.out.println("Regsiter button clicked");
			
			if (this.Registration == null)
			{	
			  this.Registration = new RegisterPage();
			}
			
			this.setVisible(false);
			
			if (this.Registration != null) {
				this.Registration.setVisible(true);
			}
			
			if (this.Signin != null) {
				this.Signin.setVisible(false);
			}
			
			if (this.Resetpwd != null) {
				this.Resetpwd.setVisible(false);
			}						
		}
		else if (btnClicked == SigninBtn) {
			System.out.println("Signin button clicked");
			
			if (this.Signin == null)
			{	
			  this.Signin = new SigninPage();
			}
			
			this.setVisible(false);						
			
			if (this.Registration != null) {
				this.Registration.setVisible(false);
			}
			
			if (this.Signin != null) {
				this.Signin.setVisible(true);
			}
            
			if (this.Resetpwd != null) {
				this.Resetpwd.setVisible(false);
			}
			
		}
		else if (btnClicked == ResetpwdBtn){
			System.out.println("Reset password button clicked");
			
			if (this.Resetpwd == null)
			{	
			  this.Resetpwd = new ResetpwdPage();
			}
			
			this.setVisible(false);						
			
			if (this.Registration != null) {
				this.Registration.setVisible(false);
			}
			
			if (this.Signin != null) {
				this.Signin.setVisible(false);
			}
            
			if (this.Resetpwd != null) {
				this.Resetpwd.setVisible(true);
			}
		}
		
	}
	
	

}

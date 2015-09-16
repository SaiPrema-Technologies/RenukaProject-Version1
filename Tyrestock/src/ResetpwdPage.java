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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ResetpwdPage extends JFrame implements ActionListener {

	private JLabel CompanyName;
	private JLabel EmployeeNameLabel;
	private JLabel UserNameLabel;
	private JTextField EmployeeNameField;
	private JTextField UserNameField;
	private JButton ClickToVerify;
	private JButton BackToLoginPage;
	//private BackToLoginPageListener bktoLoginListener;

	public ResetpwdPage() {
		super("Stock and Invoice Management System");

		setLayout(new GridBagLayout());
		CompanyName = new JLabel("Renuka Tyres");
		EmployeeNameLabel = new JLabel("Employee Name");
		EmployeeNameField = new JTextField(20);
		UserNameLabel = new JLabel("User Name");
		UserNameField = new JTextField(20);
		ClickToVerify = new JButton("Click To Verify");
		ClickToVerify.setForeground(Color.BLUE);
		BackToLoginPage = new JButton("Back To Login Page");
		BackToLoginPage.setForeground(Color.BLUE);

		ClickToVerify.addActionListener(this);
		BackToLoginPage.addActionListener(this);

		GridBagConstraints gc = new GridBagConstraints();
		final Insets insets = new Insets(4, 4, 4, 4);
		final Insets buttonInsets = new Insets(4, 4, 4, 8);

		// label = new JLabel("A label");
		CompanyName.setFont(new Font("Serif", Font.BOLD, 18));
		CompanyName.setForeground(Color.BLACK);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(CompanyName, gc); // ////////////need to center alignment of company
								// name

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
		gc.gridy = 4;
		add(BackToLoginPage, gc);
		gc.gridx = 1;
		gc.gridy = 4;
		add(ClickToVerify, gc);
		getRootPane().setDefaultButton(ClickToVerify);

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
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();

		if (btnClicked == BackToLoginPage) {
			//if (bktoLoginListener != null) {
				System.out.println("Back to login page button clicked");
				LoginFrame page = new LoginFrame();
				this.setVisible(false);
				//bktoLoginListener.FrameInitiated(this);
				EmployeeNameField.setText("");
				UserNameField.setText("");
			//}
		} else if (btnClicked == ClickToVerify) {
			String EmpName = EmployeeNameField.getText();
			String UserName = UserNameField.getText();
			boolean validUser = false;

			System.out.println("ClickToVerify button clicked");
			System.out.println("Employee Name: " + EmpName);
			System.out.println("User Name: " + UserName);

			if (EmpName.equals("")) {
				JOptionPane.showMessageDialog(null,
						"Employee name not  entered");
			} else if (UserName.equals("")) {
				JOptionPane.showMessageDialog(null, "User name not entered");
			} else {
				Database db = new Database();
				try {
					db.connect();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					validUser = db.verifytoResetpwd(EmpName, UserName);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (validUser) {
					System.out
							.println("User name and password has been verified");
					new ResetpwdVerifyPage(UserName).setVisible(true);
					this.setVisible(false);
					EmployeeNameField.setText("");
					UserNameField.setText("");
					// ResetpwdVerifyPage page = new ResetpwdVerifyPage();
				} else {
					System.out.println("Failed to verify user name");
				}
			}
		}
	}
}

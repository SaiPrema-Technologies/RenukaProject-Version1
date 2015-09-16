import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class StockMainPage extends JFrame implements ActionListener {

	private JButton Logoutbtn;
	private JLabel CompanyName;
	private JButton StockInfobtn;
	private JButton GenerateInvoicebtn,Customerdetailsbtn;
	private StockInfoPage StockInfo;
	private GenerateInvoicePage GenerateInvoice;
//	private StockmainpageListener bktoSignin;
	public StockMainPage() {
		super("Stock and Invoice Management System");
		
		setLayout(new GridBagLayout());
	
		CompanyName = new JLabel("Renuka Tyres\n");
		Logoutbtn = new JButton("Logout");
		Logoutbtn.setForeground(Color.BLUE);
		StockInfobtn = new JButton("     Stock Information     ");
		StockInfobtn.setSize(150, 150);
		StockInfobtn.setForeground(Color.BLUE);
		GenerateInvoicebtn = new JButton("     Generate Invoice     ");
		GenerateInvoicebtn.setForeground(Color.BLUE);
		Customerdetailsbtn = new JButton("Add Customer Details");
		Customerdetailsbtn.setForeground(Color.BLUE);

		StockInfobtn.addActionListener(this);
		GenerateInvoicebtn.addActionListener(this);
		Logoutbtn.addActionListener(this);
		Customerdetailsbtn.addActionListener(this);
		
		GridBagConstraints gc = new GridBagConstraints();
		final Insets insets = new Insets(4, 4, 4, 4);
        final Insets buttonInsets = new Insets(4, 4, 4, 8);
        CompanyName.setFont(new Font("Serif", Font.BOLD, 18));
        CompanyName.setForeground(Color.BLACK);
        
        gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.NORTHEAST;
		gc.weightx=1;
		gc.weighty=1;
		gc.ipadx=6;
		//gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(Logoutbtn,gc);
		
        
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
		add(StockInfobtn, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		//gc.insets = buttonInsets;
		add(GenerateInvoicebtn,gc);
		gc.gridx=0;
		gc.gridy = 3;
		add(Customerdetailsbtn,gc);
		setSize(500, 500);
		//setLocation(200,200);
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
	}
	/*
	public void setStockmainpageListener(StockmainpageListener listener){
		this.bktoSignin=listener;
	}*/
	
	
	
	
	public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();  
	        
        if(btnClicked == StockInfobtn){
        	if (this.StockInfo == null)
			{	
			  this.StockInfo = new StockInfoPage();
			}
			
			this.setVisible(false);
			
			if (this.StockInfo != null) {
				this.StockInfo.setVisible(true);
			}
			
			if (this.GenerateInvoice != null) {
				this.GenerateInvoice.setVisible(false);
        	
			}
        	
        	//StockInfoPage page = new StockInfoPage();
        }
        else if(btnClicked == GenerateInvoicebtn){
        	if (this.GenerateInvoice == null)
			{	
			  this.GenerateInvoice = new GenerateInvoicePage();
			}
			
			this.setVisible(false);
			
			if (this.GenerateInvoice != null) {
				this.GenerateInvoice.setVisible(true);
			}
			
			if (this.StockInfo != null) {
				this.StockInfo.setVisible(false);
				//GenerateInvoicePage page1 = new GenerateInvoicePage();
			}
        }
        	
        else if(btnClicked == Logoutbtn){
        	//if (bktoSignin != null) {
				System.out.println("Back to login page button clicked");
				SigninPage page = new SigninPage();
				this.setVisible(false);
				//bktoSignin.FrameInitiated(this);
        	//}
        	
        } else if(btnClicked == Customerdetailsbtn){
        	CustomerDetails page= new CustomerDetails();
        	this.setVisible(false);
        }
	}
}


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class StockInfoPage extends JFrame implements ActionListener {
	//private Connection con;
	private JButton Logoutbtn;
	private JButton Backbtn;
	private JLabel CompanyName;
	private JButton StockInfobtn;
	private JButton UpdateStockbtn;
	private JButton AddNewProductbtn,InvStockInfobtn;
	private SigninPage Sigin;
	private GetStockInfoPage GetStockInfo;
	private UpdatePurchasePage UpdatePurchase;
	private AddNewProdPage AddNewProd;
	private JComboBox ProductCombo;
	
	
	
	public StockInfoPage() {
		super("Stock and Invoice Management System");

		setLayout(new GridBagLayout());

		CompanyName = new JLabel("Renuka Tyres\n");
		Logoutbtn = new JButton("Logout");
		Logoutbtn.setForeground(Color.BLUE);
		Backbtn = new JButton("Back");
		Backbtn.setForeground(Color.BLUE);
		ProductCombo = new JComboBox();
		StockInfobtn = new JButton(" Product Wise Stock Information ");
		StockInfobtn.setSize(150, 150);
		StockInfobtn.setForeground(Color.BLUE);
		UpdateStockbtn = new JButton("         Update Purchase Stock        ");
		UpdateStockbtn.setSize(150, 150);
		UpdateStockbtn.setForeground(Color.BLUE);
		AddNewProductbtn = new JButton("               Add New Product              ");
		AddNewProductbtn.setSize(150, 150);
		AddNewProductbtn.setForeground(Color.BLUE);
		InvStockInfobtn = new JButton("        Invoice Stock Information      ");
		InvStockInfobtn.setSize(150, 150);
		InvStockInfobtn.setForeground(Color.BLUE);
		// /this is trial need to get product from database
		//StringSearchable searchable = new StringSearchable(myWords);

		//AutocompleteJComboBox product = new AutocompleteJComboBox(searchable);
		
		DefaultComboBoxModel product = new DefaultComboBoxModel();
		product.addElement("Select Product");
		String url = "jdbc:mysql://localhost:8889/renuka";
		String userid = "root";
		String password = "root";
		String sql = "SELECT DISTINCT  Product_name FROM stock";
		Connection con = null;
		 ArrayList data = new ArrayList();
		try {
			Connection connection = (Connection) DriverManager.getConnection(
					url, userid, password);
			// PreparedStatement check = (PreparedStatement)
			// con.prepareStatement(sql);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				data.add(rs.getString("Product_name"));
				product.addElement(rs.getString("Product_name"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// throw e;
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				;
			}
		}

		ProductCombo.setModel(product);
		//String value=ProductCombo.getSelectedItem().toString();
		
		
		

		
		StockInfobtn.addActionListener(this);
		UpdateStockbtn.addActionListener(this);
		AddNewProductbtn.addActionListener(this);
		Logoutbtn.addActionListener(this);
		Backbtn.addActionListener(this);
		InvStockInfobtn.addActionListener(this);
		GridBagConstraints gc = new GridBagConstraints();
		final Insets insets = new Insets(4, 4, 4, 4);
		final Insets buttonInsets = new Insets(4, 4, 4, 8);
		CompanyName.setFont(new Font("Serif", Font.BOLD, 18));
		CompanyName.setForeground(Color.BLACK);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.ipadx = 6;
		// gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(Logoutbtn, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.anchor = GridBagConstraints.CENTER;

		gc.fill = GridBagConstraints.NONE;
		add(CompanyName, gc);

		// /Select product comboBox
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;

		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Select Product"), gc);

		gc.gridx++;

		gc.anchor = GridBagConstraints.CENTER;

		gc.insets = new Insets(0, 0, 0, 0);
		add(ProductCombo, gc);

		// Stockbtn
		
		gc.insets = new Insets(4, 4, 4, 8);
		gc.ipadx=10;
		gc.gridy++;
		gc.gridx = 0;

		gc.weightx = 4;
		gc.weighty = 4;
		gc.insets = buttonInsets;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(StockInfobtn, gc);
		// Updatebtn
		gc.gridy++;
		gc.gridx = 0;
		gc.insets = buttonInsets;
		add(UpdateStockbtn, gc);

		// /Add product btn
		gc.gridy++;
		gc.gridx = 0;
		gc.insets = buttonInsets;
		add(AddNewProductbtn, gc);
		
		gc.gridy++;
		gc.gridx=0;
		gc.insets = buttonInsets;
		add(InvStockInfobtn,gc);
		
		gc.gridy++;
		gc.anchor =  GridBagConstraints.SOUTHWEST;
		add(Backbtn,gc);
		
		setSize(500, 500);
		//setLocation(200, 200);
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();
		if (btnClicked == StockInfobtn) {
			String value=ProductCombo.getSelectedItem().toString();
			if(value.equals("Select Product")){
				JOptionPane.showMessageDialog(null, "Select Product");
			}else{
			String value1=ProductCombo.getSelectedItem().toString();
			System.out.println(value1);
			if (this.GetStockInfo == null)
			{	
			  this.GetStockInfo = new GetStockInfoPage(value1);
			}
			
			this.setVisible(false);
			
			if (this.GetStockInfo != null) {
				this.GetStockInfo.setVisible(true);
			}
			
			if (this.UpdatePurchase != null) {
				this.UpdatePurchase.setVisible(false);
        	
			}
			if(this.AddNewProd != null){
				this.AddNewProd.setVisible(false);
			}
			}
			
			//GetStockInfoPage page = new GetStockInfoPage(value);
		} else if (btnClicked == UpdateStockbtn) {
			String value=ProductCombo.getSelectedItem().toString();
			if(value.equals("Select Product")){
				JOptionPane.showMessageDialog(null, "Select Product");
			}else{
			String value1=ProductCombo.getSelectedItem().toString();
			if (this.UpdatePurchase == null)
			{	
			  this.UpdatePurchase = new UpdatePurchasePage(value1);
			}
			
			this.setVisible(false);
			
			if (this.UpdatePurchase != null) {
				this.UpdatePurchase.setVisible(true);
			}
			
			if (this.GetStockInfo != null) {
				this.GetStockInfo.setVisible(false);
        	
			}
			if(this.AddNewProd != null){
				this.AddNewProd.setVisible(false);
			}
			}
			
			
			//UpdatePurchasePage page = new UpdatePurchasePage(value);
		} else if (btnClicked == AddNewProductbtn) {
			if (this.AddNewProd == null)
			{	
			  this.AddNewProd = new AddNewProdPage();
			}
			
			this.setVisible(false);
			
			if (this.AddNewProd != null) {
				this.AddNewProd.setVisible(true);
			}
			
			if (this.GetStockInfo != null) {
				this.GetStockInfo.setVisible(false);
        	
			}
			if(this.UpdatePurchase != null){
				this.UpdatePurchase.setVisible(false);
			}
			
			
			//AddNewProdPage page = new AddNewProdPage();
		}else if(btnClicked == Logoutbtn){
			
			
			if(this.Sigin == null){
				this.Sigin = new SigninPage();
			}
			this.setVisible(false);
			if(this.Sigin != null){
				this.Sigin.setVisible(true);
			}
			//SigninPage page = new SigninPage();
		}else if(btnClicked == Backbtn){
			StockMainPage page = new StockMainPage();
			this.setVisible(false);
		}else if(btnClicked == InvStockInfobtn){
			InvStockInfo page = new InvStockInfo();
			this.setVisible(false);
		}
	}

}

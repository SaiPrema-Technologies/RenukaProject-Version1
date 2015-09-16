import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdesktop.xswingx.PromptSupport;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class GenerateInvoicePage extends JFrame implements ActionListener{
	
	HashMap<Price, String> hm = new HashMap<Price, String>();
	//private Float count_tot=0f; 
	//private JTable table;
	private String tin_no="",Retailername;
	private JButton Logoutbtn,MainMenubtn,GenInvoicebtn;
	private JLabel CompanyName;
	
	//private InvoiceReportPage invreport;
	
	private JLabel BillNoLabel,Date,Label1,Label2,Label3;
	private JTextField BillNoField;
	private JLabel DeliveryToLabel,DiscountLabel,VatLabel,TinLabel;
	private JTextField DeliveryToField,DiscountField;
	private JTextField Quantity,nextQuantity,nextPrice;
	private JTextField Price,PricePerUnit,nextPricePerUnit;
	private JComboBox ProductCombo,nextProductCombo,RetailerCombo;
	private int count;
	private ActionListener actionListener;
	private int invoice = 0;
	private Float vat=14.5f;
	java.sql.Date selectedDate;
	public GenerateInvoicePage(){
		super("Stock and Invoice Management System");
		setLayout(new GridBagLayout());
	
		
		
		
		CompanyName = new JLabel("      Renuka Tyres    ");
		Logoutbtn = new JButton("Logout");
		Logoutbtn.setForeground(Color.BLUE);
		MainMenubtn = new JButton("MAIN MENU");
		MainMenubtn.setForeground(Color.BLUE);
		GenInvoicebtn = new JButton("GENERATE INVOICE");
		GenInvoicebtn.setForeground(Color.BLUE);

		TinLabel = new JLabel("Tin no");
		BillNoLabel = new JLabel("Bill or Invoice No.");
		BillNoField = new JTextField(20);
		Date = new JLabel("Date");
	
		DeliveryToLabel = new JLabel("Delivery To");
		//DeliveryToField = new JTextField(20);
		DiscountLabel = new JLabel("Discount");
		DiscountField = new JTextField(20);
		VatLabel = new JLabel("                           VAT  :        14.5 %");
		//VatField = new JTextField(20);
		
		Label1 = new JLabel("Price/Unit");
		Label2 = new JLabel("Qty");
		Label3 = new JLabel("Price");
		Quantity = new JTextField(20);
		Price = new JTextField(20);
		PricePerUnit = new JTextField(20);
		
		ProductCombo = new JComboBox();
		RetailerCombo = new JComboBox();
		
		///this is trial. need to get product from database
		DefaultComboBoxModel retailer = new DefaultComboBoxModel();
		DefaultComboBoxModel product = new DefaultComboBoxModel();
		product.addElement("Select Product");
		retailer.addElement("Select Retailer");
		String url = "jdbc:mysql://localhost:8889/renuka";
		String userid = "root";
		String password = "root";
		String sql = "SELECT DISTINCT  Product_name FROM stock";
		String sql1 = "SELECT Invoice_no FROM sales  ORDER BY Sl_no DESC  LIMIT 1";
		String cnt="select count(*) as count from Sales";
		String sql2 ="SELECT Retailer_name FROM customer";
		Connection con = null;
		// ArrayList data = new ArrayList();
		try {
			Connection connection = (Connection) DriverManager.getConnection(
					url, userid, password);
			// PreparedStatement check = (PreparedStatement)
			// con.prepareStatement(sql);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				product.addElement(rs.getString("Product_name"));
			}
			rs.close();
			stmt.close();
			Statement cust =connection.createStatement();
			ResultSet rs0= cust.executeQuery(sql2);
			while(rs0.next()){
				retailer.addElement(rs0.getString("Retailer_name"));
			}
			rs0.close();
			cust.close();
			Statement stmt1 = connection.createStatement();
			ResultSet rs1 = stmt1.executeQuery(cnt);
			int count =0;
			while(rs1.next()){
				count=rs1.getInt(1);
				System.out.println("count: " +count);
			}
			if(count==0){
				invoice =1;
			}else{
			Statement stmt2 = connection.createStatement();
			ResultSet rs2 = stmt2.executeQuery(sql1);
			
			while (rs2.next()) {
				invoice = rs2.getInt("Invoice_no");
				//date = checkResult.getString("Date");
				System.out.println("invoice "+invoice);
				
			}
			invoice++;
			rs1.close();
			stmt1.close();
			rs2.close();
			stmt2.close();
			}
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

		String inv =Integer.toString(invoice);
		ProductCombo.setModel(product);
		RetailerCombo.setModel(retailer);
		
		Logoutbtn.addActionListener(this);
		MainMenubtn.addActionListener(this);
		GenInvoicebtn.addActionListener(this);
		
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
		gc.weightx=0;
		gc.gridx = 1;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.NORTHEAST;
		gc.insets = new Insets(0,0,0,5);
		add(MainMenubtn,gc);
		gc.weightx=0;
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.NORTHEAST;
		add(GenInvoicebtn,gc);

		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(CompanyName, gc);
		
		gc.gridy++;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(BillNoLabel, gc);
	
		gc.gridx = 1;
		gc.gridy = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(new JLabel(inv), gc);
		
		gc.weightx = 0.1;
		gc.weighty = 1;
		gc.gridx=0;
		gc.gridy=3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.fill =GridBagConstraints.NONE;
		gc.gridwidth=1;
		add(Date,gc);
		gc.gridx++;
		
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		//JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		model.setSelected(true);
		gc.fill =GridBagConstraints.HORIZONTAL;
		add(datePicker,gc);
		selectedDate = (java.sql.Date) datePicker.getModel().getValue();

		gc.gridx = 0;
		gc.gridy = 4;
		gc.fill = GridBagConstraints.NONE;
		add(DeliveryToLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(RetailerCombo, gc);
		gc.gridx++;
		add(new JLabel("                          Tin no :"),gc);
		gc.gridx++;
		add(TinLabel,gc);
		gc.gridx=0;
		gc.gridy=5;
		gc.fill = GridBagConstraints.NONE;
		add(DiscountLabel,gc);
		
		gc.gridx++;
		gc.gridy = 5;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(DiscountField, gc);
		
		gc.gridx++;
		gc.gridy=5;
		gc.fill = GridBagConstraints.NONE;
		add(VatLabel,gc);
		/*
		gc.gridx++;
		gc.gridy = 5;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(VatField, gc);
		*/
		
		gc.gridy = 6;
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		
		//gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(4, 4, 4, 8);
		add(new JLabel(" Product"), gc);
		
		gc.gridx=1;
	
		gc.anchor = GridBagConstraints.WEST;
	
		gc.insets = new Insets(0,0,0,0);
		add(ProductCombo, gc);
		
		
			
			
		//product.addElement("Tyre1");
		//product.addElement("Tyre2");
		//product.addElement("Tyre3");
		
		gc.gridx=2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST;
		//add(new JLabel("Price/unit"),gc);
		add(Label1,gc);
		PricePerUnit.setEditable(false);
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx=3;
		add(PricePerUnit,gc);
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST;
		//add(new JLabel("Qty"),gc);
		add(Label2,gc);
		
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx++;
		add(Quantity,gc);
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST;
		//add(new JLabel("Price"),gc);
		add(Label3,gc);

		Price.setEditable(false);
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx++;
		add(Price,gc);
		
		ProductCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String combovalue = (String) ProductCombo.getSelectedItem();
				Float pr = 0f;
				String url = "jdbc:mysql://localhost:8889/renuka";
				String userid = "root";
				String password = "root";
				String sql2 = "SELECT Price_per_unit FROM stock WHERE Product_name=? ORDER BY Price_per_unit DESC LIMIT 1";
				try {
					Connection connection = (Connection) DriverManager
							.getConnection(url, userid, password);
					PreparedStatement getprice = (PreparedStatement) connection
							.prepareStatement(sql2);

					getprice.setString(1, combovalue);

					ResultSet checkResult = getprice.executeQuery();

					while (checkResult.next()) {
						pr = checkResult.getFloat(1);
					}
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
				if (combovalue.equals("Select Product")) {
					JOptionPane.showMessageDialog(null, "Select Product");
				} else {
					PricePerUnit.setText(Float.toString(pr));
				}

			}

		});
		RetailerCombo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Retailername= (String) RetailerCombo.getSelectedItem();
				String url = "jdbc:mysql://localhost:8889/renuka";
		        String userid = "root";
		        String password = "root";
				String sql="Select Tin_no,Discount from customer where Retailer_name =?";
				Float dis=0f; ;
				try{
					Connection connection = (Connection) DriverManager.getConnection(
							url, userid, password);
					PreparedStatement gettin_no = (PreparedStatement) connection.prepareStatement(sql);
					
					gettin_no.setString(1, Retailername);

					ResultSet checkResult = gettin_no.executeQuery();
					while(checkResult.next()){
						tin_no=checkResult.getString(1);
						 dis = checkResult.getFloat(2);
						System.out.println(dis);
					}
				}catch (SQLException e1)
			        {
			            System.out.println( e1.getMessage() );
			        }
				if(Retailername.equals("Select Retailer")){
					JOptionPane.showMessageDialog(null, "Select Retailer");
				}else{
					TinLabel.setText(tin_no);
					DiscountField.setText(Float.toString(dis));
					//TinLabel = new JLabel(tin_no);
				}
				}
		});
		
		System.out.println("Entering while loop\n");
		
		count = 7;
		
		Quantity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String combovalue = (String) ProductCombo.getSelectedItem();
				String url = "jdbc:mysql://localhost:8889/renuka";
		        String userid = "root";
		        String password = "root";
				String sql ="SELECT Closing_stock,Product_name FROM stock where Product_name=? ORDER BY Sl_no DESC  LIMIT 1";
				int closestock=0;
				try {Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
		        //Statement stmt = connection.createStatement();
				PreparedStatement verifyclosing = (PreparedStatement) connection.prepareStatement(sql);
				verifyclosing.setString(1, combovalue);
				ResultSet checkResult = verifyclosing.executeQuery();
				
				// String passWd = new String(pwd);

				while (checkResult.next()) {
					closestock = Integer.parseInt(checkResult.getString("Closing_stock"));
					//date = checkResult.getString("Date");
					System.out.println(closestock);
					
				}
				
				}catch (SQLException e1)
		        {
		            System.out.println( e1.getMessage() );
		        }
				//String combovalue = (String) ProductCombo.getSelectedItem();
				if(combovalue.equals("Select Product")){
					JOptionPane.showMessageDialog(null, "Select Product");
				}else if(closestock<(Integer.parseInt(Quantity.getText()))){
					JOptionPane.showMessageDialog(null, "Required quantity not available");
				}
					
				
				else
				{
		    	System.out.println("Selected: " + ProductCombo.getSelectedItem());
		    	System.out.println(", Position: " + ProductCombo.getSelectedIndex());
				Float Priceperunit1 = Float.parseFloat(PricePerUnit.getText());
		    	System.out.println("qtity:"+Priceperunit1);
		    	int Qty1 = Integer.parseInt(Quantity.getText());
		    	System.out.println("qtity:"+Qty1);
		    	Float Price1 = Qty1*Priceperunit1;
		    	String pr =Float.toString(Price1);
		    	Price.setText(pr);
		    	
		    	//Float isFloat= Float.parseFloat(DiscountField.getText());
				//System.out.println("discount :"+isFloat);
		    	
		    	hm.put(new Price(combovalue, Priceperunit1,Qty1,Price1), combovalue);
		    	printMap(hm);
		    	addNewProduct();}
			}
		});
		
		
		
		
		setSize(800, 500);
		//setLocation(200,200);
		setLocationByPlatform(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
	public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();
        if(btnClicked == Logoutbtn){
        	SigninPage page = new SigninPage();
			this.setVisible(false);
        }
        else if(btnClicked == MainMenubtn){
        	StockMainPage page = new StockMainPage();
			this.setVisible(false);
        }else if(btnClicked ==GenInvoicebtn){
        	
        	
        	 if(((String)RetailerCombo.getSelectedItem()).equals("Select Retailer")){
        		JOptionPane.showMessageDialog(null, "Select Retailer");
        	 }else if(DiscountField.getText().equals("")){
        		JOptionPane.showMessageDialog(null, "Enter Discount");
        	 }
        	 else{
        		//String delyr = DeliveryToField.getText();
            	//int invoice =Integer.parseInt( BillNoField.getText());
            	Float Discount= Float.parseFloat(DiscountField.getText());
        	
        	//Float vat = Float.parseFloat(VatField.getText());
        	
        	
        	
        	
        	Set<Price> keys = hm.keySet();
            for(Price p:keys){
            	Float pr=p.getPrice();
            	String product =p.getProductname();
            	Float pr_per_unit = p.getPrice_perunit();
            	int qtity = p.getQty();
            	System.out.println("pr: "+pr);
                System.out.println(p+"==>"+hm.get(p));
            
        	
        	
        	Database db = new Database();
			try {
				db.connect();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		    try {
		    	int retval=db.GenInvoice(invoice,selectedDate,Retailername,Discount,vat,product, pr_per_unit, qtity,pr);
		    	//if(retval==0){
		    		//JOptionPane.showMessageDialog(null, "Required Stock not available check your stocks");
		    		//this.setVisible(true);
		    		//GetStockInfoPage page = new GetStockInfoPage();
		    	//}else if(retval==1){
		    		// String inv =Integer.toString(invoice);
		    		 //JTable table = createTable(inv);
		         	//InvoiceReportPage page = new InvoiceReportPage(inv,Discount,vat,date,delyr,table);
		         	
		         	//this.setVisible(false);
		    	//}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
            }
        	//String inv = BillNoField.getText();
           
        	
        	 String inv =Integer.toString(invoice);
        	 
    		// JTable table = createTable(inv);
        	 DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     		String text = df.format(selectedDate);
     		
     		 
     		System.out.println("The date is: " + text);
         	InvoiceReportPage page = new InvoiceReportPage(inv,Discount,vat,text,Retailername,tin_no,hm);
         	/*this.invreport = new InvoiceReportPage(inv,Discount,vat,date,Retailername,tin_no);
    		invreport.setStockmainpageListener(new StockmainpageListener() {			
    	    	@Override
    			public void FrameInitiated(Object frame) {
    				if (frame instanceof InvoiceReportPage) {
    					setVisible(true);												
    					invreport.setVisible(false);												
    				}					
    			}				
    		});*/
         	this.setVisible(false);
        	}
        	
        }
     
	}
	public void addNewProduct(){
		System.out.println("Entered while loop\n");
        //JComboBox nextProductCombo = new JComboBox(); 
		
		//printMap(hm);

        nextProductCombo = new JComboBox();
       // JTextField nextQuantity = new JTextField(20);
		//JTextField nextPrice = new JTextField(10);
        nextQuantity = new JTextField(20);
        nextPrice = new JTextField(10);
        nextPricePerUnit = new JTextField(10);
        GridBagConstraints Gc = new GridBagConstraints();
        DefaultComboBoxModel nextProduct = new DefaultComboBoxModel();
        nextProduct.addElement("Select Product");
        //
        String url = "jdbc:mysql://localhost:8889/renuka";
		String userid = "root";
		String password = "root";
		String sql = "SELECT DISTINCT  Product_name FROM stock";
		Connection con = null;
		// ArrayList data = new ArrayList();
		try {
			Connection connection = (Connection) DriverManager.getConnection(
					url, userid, password);
			// PreparedStatement check = (PreparedStatement)
			// con.prepareStatement(sql);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				nextProduct.addElement(rs.getString("Product_name"));
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
        
        
		
		
		
		Gc.gridy = count++;
		Gc.weightx = 1;
		Gc.weighty = 0.2;
		Gc.fill = GridBagConstraints.NONE;
		Gc.gridx = 0;		
		
		Gc.insets = new Insets(4, 4, 4, 8);
		add(new JLabel(" Product"), Gc);
		
		Gc.gridx++;	
		Gc.anchor = GridBagConstraints.WEST;
	
		Gc.insets = new Insets(0,0,0,0);
		String value=ProductCombo.getSelectedItem().toString();
		
		//add(new JLabel(value),Gc);
		add(nextProductCombo, Gc);
		
		nextProductCombo.setModel(nextProduct);
		//nextProduct.addElement(value);
		//nextProduct.addElement("Tyre1");
		//nextProduct.addElement("Tyre2");
		//nextProduct.addElement("Tyre3");
		
		//System.out.println("Selected: " + ProductCombo.getSelectedItem());
        //System.out.println(", Position: " + ProductCombo.getSelectedIndex());
		
		Gc.gridx++;
		//Gc.anchor = GridBagConstraints.LINE_START;
		Gc.fill = GridBagConstraints.NONE;
		Gc.anchor = GridBagConstraints.EAST;
		add(new JLabel("Price/unit"),Gc);
		
		nextPricePerUnit.setEditable(false);

		Gc.anchor = GridBagConstraints.WEST;
		Gc.fill = GridBagConstraints.HORIZONTAL;
		Gc.gridx++;
		add(nextPricePerUnit,Gc);
		
		Gc.gridx++; 
		Gc.fill = GridBagConstraints.NONE;
		Gc.anchor = GridBagConstraints.EAST;
		add(new JLabel("Qty"),Gc);
		
		
		//Gc.anchor = GridBagConstraints.LINE_START;
		//Gc.fill = GridBagConstraints.HORIZONTAL;
		Gc.anchor = GridBagConstraints.WEST;
		Gc.fill = GridBagConstraints.HORIZONTAL;
		Gc.gridx++;
		add(nextQuantity,Gc);
		
		Gc.gridx++;
		//Gc.anchor = GridBagConstraints.LINE_START;
		Gc.fill = GridBagConstraints.NONE;
		Gc.anchor = GridBagConstraints.EAST;
		add(new JLabel("Price"),Gc);
		
		//Gc.anchor = GridBagConstraints.LINE_START;
		//Gc.fill = GridBagConstraints.HORIZONTAL;
		nextPrice.setEditable(false);
		Gc.anchor = GridBagConstraints.WEST;
		Gc.fill = GridBagConstraints.HORIZONTAL;
		Gc.gridx++;
		add(nextPrice,Gc);
		
		/*
		gc.gridy = 6;
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		
		//gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets(4, 4, 4, 8);
		add(new JLabel(" Product"), gc);
		
		gc.gridx=1;
	
		gc.anchor = GridBagConstraints.WEST;
	
		gc.insets = new Insets(0,0,0,0);
		add(ProductCombo, gc);
		
		
		
		
		//product.addElement("Tyre1");
		//product.addElement("Tyre2");
		//product.addElement("Tyre3");
		
		gc.gridx=2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST;
		//add(new JLabel("Price/unit"),gc);
		add(Label1,gc);
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx=3;
		add(PricePerUnit,gc);
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST;
		//add(new JLabel("Qty"),gc);
		add(Label2,gc);
		
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx++;
		add(Quantity,gc);
		
		gc.gridx++;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST;
		//add(new JLabel("Price"),gc);
		add(Label3,gc);
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx++;
		add(Price,gc);
		
		
		
		
		
		
		*/
		/*int Qty = Integer.parseInt(nextQuantity.getText());
		System.out.println("qtity:"+Qty);
		
		int Price = Integer.parseInt(nextPrice.getText());
		System.out.println("Price:"+Price);
		*/
		
		
		nextProductCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		
				
				
		String combovalue = (String) nextProductCombo.getSelectedItem();
		Float pr = 0f;
		String url = "jdbc:mysql://localhost:8889/renuka";
        String userid = "root";
        String password = "root";
		String sql2 ="SELECT Price_per_unit FROM stock WHERE Product_name=? ORDER BY Price_per_unit DESC LIMIT 1";
		try {
			Connection connection = (Connection) DriverManager.getConnection(
					url, userid, password);
			PreparedStatement verifyclosing = (PreparedStatement) connection.prepareStatement(sql2);
			
			verifyclosing.setString(1, combovalue);

			ResultSet checkResult = verifyclosing.executeQuery();
			
			while(checkResult.next()){
				pr=checkResult.getFloat(1);
			}
		}catch (SQLException e1)
	        {
	            System.out.println( e1.getMessage() );
	        }
		if(combovalue.equals("Select Product")){
			JOptionPane.showMessageDialog(null, "Select Product");
		}else{
			nextPricePerUnit.setText(Float.toString(pr));
		}
			
			}
			
		});
		
		nextQuantity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//JComboBox Pnext = (JComboBox) e.getSource();
				//HashMap<Price, String> hm = new HashMap<Price, String>();

				String combovalue = (String) nextProductCombo.getSelectedItem();
				
				String url = "jdbc:mysql://localhost:8889/renuka";
		        String userid = "root";
		        String password = "root";
				String sql ="SELECT Closing_stock,Product_name FROM stock where Product_name=? ORDER BY Sl_no DESC  LIMIT 1";
				int closestock=0;
				try {Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
		        //Statement stmt = connection.createStatement();
				PreparedStatement verifyclosing = (PreparedStatement) connection.prepareStatement(sql);
				verifyclosing.setString(1, combovalue);
				ResultSet checkResult = verifyclosing.executeQuery();
				
				// String passWd = new String(pwd);

				while (checkResult.next()) {
					closestock = Integer.parseInt(checkResult.getString("Closing_stock"));
					//date = checkResult.getString("Date");
					System.out.println(closestock);
					
				}
				
				}catch (SQLException e1)
		        {
		            System.out.println( e1.getMessage() );
		        }
				if(combovalue.equals("Select Product")){
					JOptionPane.showMessageDialog(null, " Select Product");
				}else if(closestock<(Integer.parseInt(nextQuantity.getText()))){
					JOptionPane.showMessageDialog(null, "Required quantity not available");
				}
				else
				{
		    	System.out.println("Selected: " + nextProductCombo.getSelectedItem());
		    	System.out.println(", Position: " + nextProductCombo.getSelectedIndex());
				Float Priceperunit1 = Float.parseFloat(nextPricePerUnit.getText());
		    	System.out.println("qtity:"+Priceperunit1);
		    	int Qty1 = Integer.parseInt(nextQuantity.getText());
		    	System.out.println("qtity:"+Qty1);
		    	Float Price1 = Qty1*Priceperunit1;
		    	String pr =Float.toString(Price1);
		    	nextPrice.setText(pr);
		    	
		    	hm.put(new Price(combovalue, Priceperunit1,Qty1,Price1), combovalue);
		    	printMap(hm);
				addNewProduct();
				}
			}
	});
	}
	public static void printMap(HashMap<Price, String> map){
        
        Set<Price> keys = map.keySet();
        for(Price p:keys){
        	Float pr=p.getPrice();
        	System.out.println("pr: "+pr);
            System.out.println(p+"==>"+map.get(p));
        }
    }
	
}
class Price{
    
    private String Productname;
    private Float price,price_perunit;
    private int qty;
     
    public Price(String itm, Float priceper,int qtity,Float pr){
        this.Productname = itm;
        this.price_perunit = priceper;
        this.price = pr;
        this.qty = qtity;
    }
     
   

	public String getProductname() {
		return Productname;
	}



	public void setProductname(String productname) {
		Productname = productname;
	}



	public Float getPrice() {
		return price;
	}



	public void setPrice(Float price) {
		this.price = price;
	}



	public Float getPrice_perunit() {
		return price_perunit;
	}



	public void setPrice_perunit(Float price_perunit) {
		this.price_perunit = price_perunit;
	}



	public int getQty() {
		return qty;
	}



	public void setQty(int qty) {
		this.qty = qty;
	}



    
     
    public String toString(){
        return "item: "+Productname+" price/unit : "+price_perunit+" Qty : "+qty+"  price: "+price;
    }



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((Productname == null) ? 0 : Productname.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((price_perunit == null) ? 0 : price_perunit.hashCode());
		result = prime * result + qty;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (Productname == null) {
			if (other.Productname != null)
				return false;
		} else if (!Productname.equals(other.Productname))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (price_perunit == null) {
			if (other.price_perunit != null)
				return false;
		} else if (!price_perunit.equals(other.price_perunit))
			return false;
		if (qty != other.qty)
			return false;
		return true;
	}

	
}


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdesktop.xswingx.PromptSupport;


public class AddNewProdPage extends JFrame implements ActionListener{
	private JButton Logoutbtn,AddProd,MainMenubtn,Backbtn;
	private JLabel CompanyName;
	private JLabel ProductName;
	private JLabel PurchaseInvoiceNo;
	private JLabel PurchaseDate;
	private JLabel Quantity,Price;
	private JTextField ProductNameField;
	private JTextField PurchaseInvoiceNoField;
// JTextField PurchaseDateField;
	private JTextField QtyField,PriceField;
	java.sql.Date selectedDate;
	//private JButton AddProd;
	//private JButton MainMenubtn;
	//private JButton Backbtn;

	public AddNewProdPage(){
		super("Stock and Invoice Management System");
		
		setLayout(new GridBagLayout());
		CompanyName = new JLabel("      Renuka Tyres    ");
		Logoutbtn = new JButton("Logout");
		ProductName = new JLabel("New Product Name");
		ProductNameField= new JTextField(20);
		PurchaseInvoiceNo = new JLabel("Purchase Invoice No.");
		PurchaseInvoiceNoField = new JTextField(20);
		PurchaseDate = new JLabel("Purchase Date");
	//	PurchaseDateField = new JTextField(20);
		Quantity = new JLabel("Quantity");
		QtyField = new JTextField(20);
		Price =new JLabel("Price/unit");
		PriceField = new JTextField(20);
		
		AddProd = new JButton("ADD PRODUCT");
		AddProd.setForeground(Color.BLUE);
		MainMenubtn = new JButton("MAIN MENU");
		MainMenubtn.setForeground(Color.BLUE);
		Backbtn = new JButton("BACK");
		Backbtn.setForeground(Color.BLUE);
		
		AddProd.addActionListener(this);
		Logoutbtn.addActionListener(this);
		MainMenubtn.addActionListener(this);
		Backbtn.addActionListener(this);
		
		GridBagConstraints gc = new GridBagConstraints();
		
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
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.fill = GridBagConstraints.NONE;
		add(CompanyName, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(new JLabel("ADD NEW PRODUCT"), gc);

		gc.gridx = 0;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(ProductName, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		add(ProductNameField,gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(PurchaseInvoiceNo, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		add(PurchaseInvoiceNoField, gc);
		
		
		gc.gridx = 0;
		gc.gridy = 5;
		add(PurchaseDate, gc);
		
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		//JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		//model.setDate(1990, 8, 24);

		model.setSelected(true);

		gc.gridx = 1;
		gc.gridy = 5;
		add(datePicker, gc);
		//PromptSupport.setPrompt("dd/mm/yyyy", PurchaseDateField);
		selectedDate = (java.sql.Date) datePicker.getModel().getValue();

		
		gc.gridx = 0;
		gc.gridy = 6;
		add(Quantity, gc);
		
		gc.gridx = 1;
		gc.gridy = 6;
		add(QtyField, gc);
		
		gc.gridx=0;
		gc.gridy=7;
		add(Price,gc);
		gc.gridx=1;
		gc.gridy=7;
		add(PriceField,gc);
		
		gc.gridx = 0;
		gc.gridy = 8;
		gc.anchor = GridBagConstraints.LINE_END;
		add(AddProd, gc);
		
		gc.weightx=0;
		gc.gridy = 9;
		gc.anchor = GridBagConstraints.PAGE_END;
		add(MainMenubtn,gc);
		gc.weightx=0;
		gc.gridx++;
		gc.anchor = GridBagConstraints.PAGE_END;
		add(Backbtn,gc);
		
		setSize(500, 500);
		//setLocation(200, 200);
		setLocationByPlatform(true);
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
		if(btnClicked == AddProd){
			String ProdName = ProductNameField.getText();
			String Pur_Invoice_no = PurchaseInvoiceNoField.getText();
			//String date = PurchaseDateField.getParameter("date");
			String Qty_pur = QtyField.getText();
			String Price = PriceField.getText();
			 System.out.println("Add product button clicked");
			 System.out.println("Product :"+ProdName);
			 System.out.println("Purchase Invoice no :"+Pur_Invoice_no);
			 System.out.println("Quantity :" +Qty_pur);
			 System.out.println("Price:");
			// Date date = PurchaseDateField.getDate("date");
			 
			 
			 
			// String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(PurchaseDateField.getParameter("date")));
			 
			 if (ProdName.equals("")) {
				    JOptionPane.showMessageDialog(null, "Enter Product name");
				} else if (Pur_Invoice_no.equals("")) {
				    JOptionPane.showMessageDialog(null, "Enter Invoice number");
				    
				}  else if (Qty_pur.equals("")) {
				   JOptionPane.showMessageDialog(null, "Enter Quantity");
				} else {
					long invoice = Long.parseLong(PurchaseInvoiceNoField.getText());
					System.out.println("invoice:" +invoice);
					Database db = new Database();
					try {
						db.connect();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				    try {
				    	db.AddProd(ProdName, Pur_Invoice_no, selectedDate,Qty_pur,Price);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}StockInfoPage page = new StockInfoPage();
				    this.setVisible(false);
				}
			 
		}else if(btnClicked == MainMenubtn){
			StockMainPage page = new StockMainPage();
			this.setVisible(false);
			
		}else if(btnClicked == Backbtn){
			StockInfoPage page = new StockInfoPage();
			this.setVisible(false);
		}else if(btnClicked == Logoutbtn){
			SigninPage page = new SigninPage();
			this.setVisible(false);
		}
	}
}

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class InvStockInfo extends JFrame implements ActionListener{
	private JLabel CompanyName,DateLabel,InvoiceLabel;
	private JTextField DateField;
	private JComboBox InvoiceCombo;
	private JButton Logoutbtn,Backbtn,MainMenubtn,Showbtn,Okbtn;
	private int count;
	private JTable table;
	java.sql.Date selectedDate;

	DefaultComboBoxModel invoice_no;
	public InvStockInfo(){
		super("Stock and Invoice Management System");
		setLayout(new GridBagLayout());
		
		CompanyName = new JLabel("Renuka Tyres\n");
		Logoutbtn = new JButton("LOGOUT");
		Logoutbtn.setForeground(Color.BLUE);
		Backbtn = new JButton("BACK");
		Backbtn.setForeground(Color.BLUE);
		MainMenubtn = new JButton("MAIN MENU");
		MainMenubtn.setForeground(Color.BLUE);
		Showbtn = new JButton("Show");
		Showbtn.setForeground(Color.BLUE);
		Okbtn = new JButton("OK");
		DateLabel = new JLabel("Date : ");
		InvoiceLabel = new JLabel("Invoice no : ");
		DateField = new JTextField(10);
		InvoiceCombo = new JComboBox();
		 invoice_no = new DefaultComboBoxModel();
		 /*invoice_no.addElement("Select Invoice_no");
		String url = "jdbc:mysql://localhost:8889/renuka";
		String userid = "root";
		String password = "";
		String sql = "SELECT DISTINCT  Invoice_no FROM stock";
		Connection con = null;
		 //ArrayList data = new ArrayList();
		try {
			Connection connection = (Connection) DriverManager.getConnection(
					url, userid, password);
			// PreparedStatement check = (PreparedStatement)
			// con.prepareStatement(sql);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				//data.add(rs.getString("Invoice_no"));
				invoice_no.addElement(rs.getString("Invoice_no"));
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

		InvoiceCombo.setModel(invoice_no);*/
		Logoutbtn.addActionListener(this);
		Backbtn.addActionListener(this);
		MainMenubtn.addActionListener(this);
		Showbtn.addActionListener(this);
		GridBagConstraints gc = new GridBagConstraints();
		
		//final Insets insets = new Insets(4, 4, 4, 4);
		//final Insets buttonInsets = new Insets(4, 4, 4, 8);
		CompanyName.setFont(new Font("Serif", Font.BOLD, 18));
		CompanyName.setForeground(Color.BLACK);
		
		gc.gridy=0;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(CompanyName, gc);
		
		gc.gridy=1;
		gc.gridx=0;
		add(DateLabel,gc);
	
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		//JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, p);
		final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		//model.setDate(1990, 8, 24);

		model.setSelected(true);
		
		
		gc.gridx++;
		add(datePicker,gc);
		//selectedDate = (java.sql.Date) datePicker.getModel().getValue();

		gc.gridx++;
		add(Okbtn,gc);
		gc.gridy=2;
		gc.gridx=0;
		add(InvoiceLabel,gc);
		gc.gridx++;
		add(InvoiceCombo,gc);
		gc.gridy=3;
		gc.gridx=1;
		add(Showbtn,gc);
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.SOUTHEAST;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.ipadx = 6;
		// gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(Logoutbtn, gc);
		gc.weightx=0;
		gc.gridx = 1;
		gc.gridy = 4;
		
		gc.fill = GridBagConstraints.NONE;
		//gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0,0,0,5);
		add(MainMenubtn,gc);
		gc.weightx=0;
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.SOUTHWEST;
		add(Backbtn,gc);


		
				Okbtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//String input = DateField.getText();
				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				//LocalDate localDate = LocalDate.parse(input, formatter);
				//java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
				//DefaultComboBoxModel invoice_no = new DefaultComboBoxModel();
				selectedDate = (java.sql.Date) datePicker.getModel().getValue();
				invoice_no.addElement("Select Invoice_no");
				String url = "jdbc:mysql://localhost:8889/renuka";
				String userid = "root";
				String password = "root";
				String sql = "SELECT DISTINCT  Invoice_no FROM stock where Date='"+selectedDate+"'";
				String sql1 ="select count(*) as count from stock where Date ='"+selectedDate+"'";
				Connection con = null;
				 //ArrayList data = new ArrayList();
				try {
					Connection connection = (Connection) DriverManager.getConnection(
							url, userid, password);
					// PreparedStatement check = (PreparedStatement)
					// con.prepareStatement(sql);
					PreparedStatement verifydate = (PreparedStatement) connection.prepareStatement(sql1);
					ResultSet rs1 = verifydate.executeQuery(sql1);
					rs1.next();
					int count = rs1.getInt(1);
					if(count==0){
						JOptionPane.showMessageDialog(null, "Invoice not present on required date");

					}else{
					
					PreparedStatement verifyinv = (PreparedStatement) connection.prepareStatement(sql);
					//Statement stmt = connection.createStatement();
					
					ResultSet rs = verifyinv.executeQuery(sql);
						
					//verifyinv.setDate(1,sqlDate);
					while (rs.next()) {
						//data.add(rs.getString("Invoice_no"));
						System.out.println(rs.getString("Invoice_no"));
						invoice_no.addElement(rs.getString("Invoice_no"));
					}
					rs.close();
					}
				} catch (Exception e1) {
					// throw e;
					e1.printStackTrace();
				} finally {
					try {
						con.close();
					} catch (Exception e1) {
						;
					}
				}

				InvoiceCombo.setModel(invoice_no);
				
				
			}
		});
		
				
		//	this.setVisible(true);
		setLocationByPlatform(true);
		setSize(500, 500);
		//setLocation(200,200);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);

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
		}else if(btnClicked == Backbtn){
			StockInfoPage page = new StockInfoPage();
			this.setVisible(false);
		}else if(btnClicked == MainMenubtn){
			StockMainPage page = new StockMainPage();
			this.setVisible(false);
		}else if(btnClicked == Showbtn){
			String combovalue = (String) InvoiceCombo.getSelectedItem();
			if(combovalue.equals("Select Invoice_no")){
				JOptionPane.showMessageDialog(null, " Select Invoice_no");
			}else{
			System.out.println("combu"+combovalue);
			InvStockDisplay page= new InvStockDisplay(combovalue);
			this.setVisible(false);
			}
		}
	}
	
}

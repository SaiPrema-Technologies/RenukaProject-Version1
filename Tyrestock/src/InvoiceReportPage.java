import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class InvoiceReportPage extends JFrame implements ActionListener,Printable{
	//private GenerateInvoicePage gen;
	private JButton NewInvoicebtn,Printbtn,MainMenu,Logout,Save,Reset;
	private JLabel CompanyName,Tin_no,Invoice,Bill_no,Date,Deliveryto,Phno,label1,label2,label3,label4,label5,counttot,discountval,discountamt;
	private JLabel Vatval,totalamt,RTin_no;
	private Float count_tot=0f,Disc_val,Vat_val,Total;
	private JPanel contentPane;
	private JPanel panel,panel1,panel0;
	private JTable table;
	private JFrame frame;
	private String Invoice_No,delyr;
	//private StockmainpageListener bktoSignin;
	public InvoiceReportPage(String Invoice_no,Float disc,Float vat,String date,String deliver,String tin_no,HashMap<Price, String> map) {
		super("Stock and Invoice Management System");
		//panel = new JPanel(new GridBagLayout());
		//this.getContentPane().add(panel);

		//setLayout(new GridBagLayout());
		//this.getContentPane().add(panel);
		//JTable t = new JTable(null);
		Invoice_No=Invoice_no;
		delyr=deliver;
		Tin_no = new JLabel("TIN NO : 29351264069");
		Invoice = new JLabel("                                                      INVOICE  REPORT");
		CompanyName= new JLabel("                                     SHREE RENUKA TYRES");
		//CompanyName = new JLabel("<html><pr>   SHREE  RENUKA  TYRES<br>     TVS  DISTRIBUTORS<br> SHREENAGAR  , NH4 .BELGAUM-590016</pr></html>");
		
		Bill_no = new JLabel("Invoice No :  "+Invoice_no);
		Date = new JLabel("Date :  "+date);
		Deliveryto = new JLabel("Delivery To :    "+deliver);
		Phno = new JLabel("Ph no. : 9742202777");
		RTin_no = new JLabel("TIN NO  :  "+tin_no);
		NewInvoicebtn = new JButton("New Invoice");
		NewInvoicebtn.setForeground(Color.BLUE);
		Printbtn = new JButton("       Print       ");
		Printbtn.setForeground(Color.BLUE);
		MainMenu = new JButton(" Main Menu ");
		MainMenu.setForeground( Color.BLUE);
		Logout = new JButton("     Logout     ");
		Logout.setForeground(Color.BLUE);
		Save = new JButton("       Save       ");
		Save.setForeground(Color.BLUE);
		Reset = new JButton("Reset");
		NewInvoicebtn.addActionListener(this);
		MainMenu.addActionListener(this);
		Logout.addActionListener(this);
		Printbtn.addActionListener(this);
		Save.addActionListener(this);
		Reset.addActionListener(this);
		ArrayList columnName = new ArrayList();
		columnName.add("Particulars");
		columnName.add("Per_unit");
		columnName.add("Qty");
		columnName.add("Amount");
		
        ArrayList data = new ArrayList();

        String url = "jdbc:mysql://localhost:8889/renuka";
        String userid = "root";
        String password = "root";
        String sql1= "SELECT value FROM sales where Invoice_no=?";
        String sql = "SELECT Product_name,Price_per_unit,Quantity,value FROM sales where Invoice_no=?";
        try {Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
        //Statement stmt = connection.createStatement();
		PreparedStatement verifyinv = (PreparedStatement) connection.prepareStatement(sql);
		verifyinv.setString(1, Invoice_no);
		
		PreparedStatement totval = (PreparedStatement) connection.prepareStatement(sql1);
		totval.setString(1, Invoice_no);
		
		ResultSet rs1 = verifyinv.executeQuery();
		{
			ResultSetMetaData md = rs1.getMetaData();
			int columns = md.getColumnCount();
			while (rs1.next())
			{
				ArrayList row = new ArrayList(columns);

				for (int i = 1; i <= columns; i++)
				{
					row.add( rs1.getObject(i) );
				}
				
				data.add( row );
				
			}
			
		}
		 
		ResultSet checkResult1 = totval.executeQuery();
		while(checkResult1.next()){
		
		 count_tot = checkResult1.getFloat(1)+count_tot;
		 
		 
		}
		//System.out.println(countid);
   
        }  catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i <data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
          
            dataVector.add(subVector);
          
        }

        for (int i = 0; i < columnName.size(); i++ )
            columnNamesVector.add(columnName.get(i));
            
         table = new JTable(dataVector, columnNamesVector)
        {
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        }; 
        resizeColumnWidth(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //table.setRowHeight(1, 10);
		//table.setSize( 300,600 );

        System.out.println(count_tot);
        Disc_val=(float)Math.ceil((disc*count_tot)/100);
        System.out.println(Disc_val);
        Float disc_amt=(float)Math.ceil(count_tot-Disc_val);
        Vat_val= (float)Math.ceil((vat*disc_amt)/100);
        Total =(float)Math.ceil(disc_amt+Vat_val);
        String tot =Float.toString(count_tot);
        
    	//String tot = String.format("%2f", count_tot);
        String disc_val =Float.toString(Disc_val);
    	String dis_amt = Float.toString(disc_amt);
    	String Vat_amt = Float.toString(Vat_val);
    	String tot_amt = Float.toString(Total);
    	
    	label1 = new JLabel("Total Value :");
    	label2 = new JLabel("Discount "+disc+"% :");
    	label3 = new JLabel("Discount amt :");
    	label4 = new JLabel("VAT Value :");
    	label5 = new JLabel("Total :");
    	counttot = new JLabel(tot);
    	discountamt = new JLabel(dis_amt);
    	discountval = new JLabel(disc_val);
    	Vatval = new JLabel(Vat_amt);
    	totalamt = new JLabel(tot_amt);
    	
        
        frame =new JFrame();
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
    	GridBagConstraints gcb  = new GridBagConstraints();
    	
    	gcb.gridx=0;
    	gcb.gridy=0;
    	//gcb.fill=GridBagConstraints.BOTH;
    	panel0 = new JPanel(new GridBagLayout());
    	GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;
		//gc.gridheight=2;
		final Insets insets = new Insets(4, 4, 4, 4);
		final Insets buttonInsets = new Insets(4, 4, 4, 8);
		gc.insets = new Insets(5,5,8,5);

		gc.gridy=0;
		gc.ipadx = 6;
		gc.gridx=0;
		gc.anchor = GridBagConstraints.NORTH;
    	panel0.add(Save,gc);
    	gc.gridy=2;
    	panel0.add(Printbtn,gc);
    	gc.gridy=4;
    	panel0.add(NewInvoicebtn,gc);
    	gc.gridy=6;
    	panel0.add(MainMenu,gc);
    	gc.gridy=8;
    	panel0.add(Logout,gc);
    	//gc.gridy=10;
    	//panel0.add(Reset,gc);
    	contentPane.add(panel0, gcb);
    	gcb.gridx=1;
    	gcb.gridy=0;
    	gcb.fill=GridBagConstraints.BOTH;
    	panel = new JPanel(new GridBagLayout());
    	
		gc.insets = new Insets(0,4,0,4);

		
		gc.gridx=0;
		gc.gridy=0;
		gc.ipadx = 6;
//		gc.fill = GridBagConstraints.HORIZONTAL;

		gc.anchor = GridBagConstraints.NORTH;
		panel.add(Invoice,gc);
		
		//gc.gridx=2;
		//gc.anchor = GridBagConstraints.FIRST_LINE_END;
		//panel.add(Logout,gc);
		
		gc.gridx=0;
		gc.gridy=2;
		gc.anchor = GridBagConstraints.WEST;
		panel.add(Tin_no,gc);
		
		gc.gridx=2;
		gc.anchor = GridBagConstraints.EAST;
		panel.add(Phno,gc);
		
		gc.gridx=0;
		gc.gridy=3;
		gc.gridwidth=2;
		gc.anchor = GridBagConstraints.CENTER;
		panel.add(CompanyName,gc);
		gc.gridx=0;
		gc.gridy++;
		gc.gridwidth=2;
		gc.anchor = GridBagConstraints.CENTER;
		panel.add(new JLabel("                                    TVS  TYRE  DISTRIBUTORS"),gc);
		
		gc.gridx=0;
		gc.gridy++;
		gc.gridwidth=2;
		gc.anchor = GridBagConstraints.CENTER;
		panel.add(new JLabel("                                   SHREENAGAR , NH4 .BELGAUM-590016"),gc);
		 
		gc.gridy++;
		gc.gridx=0;
		gc.gridwidth=1;
		gc.anchor = GridBagConstraints.WEST;
		panel.add(Bill_no,gc);
		/*gc.gridx=1;
		gc.anchor = GridBagConstraints.WEST;
		panel.add(new JLabel(Invoice_no),gc);
		*/
		gc.gridx=2;
		gc.anchor = GridBagConstraints.EAST;
		panel.add(Date,gc);
		/*
		gc.gridx=3;
		gc.anchor = GridBagConstraints.WEST;
		panel.add(new JLabel(date),gc);
		*/
		gc.gridy++;
		gc.gridx=0;
		gc.anchor = GridBagConstraints.WEST;

		panel.add(Deliveryto,gc);
		gc.gridx=2;
		gc.anchor = GridBagConstraints.EAST;
		panel.add(RTin_no,gc);
		/*gc.gridx++;
		panel.add(new JLabel(deliver),gc);
		
		*/
		//table.setPreferredScrollableViewportSize(table.getPreferredSize());
		//table.setFillsViewportHeight(true);
		gc.gridx=0;
		gc.gridy++;
		gc.gridwidth=3;
		
		//gc.gridheight=2;
		panel.add( new JScrollPane(table),gc);
		
		gc.gridy++;
		gc.gridx=0;
		gc.anchor = GridBagConstraints.WEST;
		panel.add(label1,gc);
		gc.gridx++;
		panel.add(counttot,gc);
		
		//gc.gridy++;
		gc.gridx++;
		panel.add(label2,gc);
		gc.gridx++;
		panel.add(discountval,gc);
		
		gc.gridx=0;
		gc.gridy++;
		panel.add(label3,gc);
		gc.gridx++;
		panel.add(discountamt,gc);
		//gc.gridy++;
		gc.gridx++;
		panel.add(label4,gc);
		gc.gridx++;
		panel.add(Vatval,gc);
		gc.gridy++;
		gc.gridx=0;
		//gc.anchor=GridBagConstraints.CENTER;
		panel.add(label5,gc);
		gc.gridx++;
		panel.add(totalamt,gc);
		contentPane.add(panel, gcb);
	
		frame.setContentPane(contentPane);
		
       // frame.pack();
        frame.setSize(800, 800);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
		
		//this.pack();

		//this.setVisible(true);
		
		
		//setSize(800, 800);
		//setLocation(200,200);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setVisible(true);
		//this.PrintFrameToPDF();
		
		
		
	}
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 10; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}

	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();
		if(btnClicked == NewInvoicebtn){
			GenerateInvoicePage page = new GenerateInvoicePage();
			this.setVisible(false);
		}//else if(btnClicked == Reset){
			//if (bktoSignin != null) {
				//bktoSignin.FrameInitiated(this);
			//}}
			
		 else if(btnClicked == MainMenu){
			StockMainPage page = new StockMainPage();
			this.setVisible(false);
		}else if(btnClicked == Logout){
			SigninPage page = new SigninPage();
			this.setVisible(false);
		}else if(btnClicked == Printbtn){
			
			PrinterJob pjob = PrinterJob.getPrinterJob();
			PageFormat preformat = pjob.defaultPage();
			preformat.setOrientation(PageFormat.LANDSCAPE);
			PageFormat postformat = pjob.pageDialog(preformat);
			//If user does not hit cancel then print.
			if (preformat != postformat) {
			    //Set print component
			    pjob.setPrintable(new Printer(panel), postformat);
			    
			    if (pjob.printDialog()) {
			        try {
						pjob.print();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			}
			
			
			
			this.setVisible(false);
		}if(btnClicked == Save){
			
			try {
				this.pdf();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (DocumentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}
	}
	
	
	private void pdf() throws FileNotFoundException, DocumentException{
		Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/Users/ashokmagadum/Desktop/untitled folder/"+delyr+Invoice_No+".pdf"));
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        PdfTemplate tp = cb.createTemplate(panel.getWidth()+100, panel.getHeight());
        Graphics2D g2 = tp.createGraphics(panel.getWidth()+100, panel.getHeight());
        g2.scale(0.8, 1.0);
        panel.print(g2);
        g2.dispose();
        cb.addTemplate(tp, 250, 0);
        document.close();


	}
	
	@Override
	public int print(Graphics arg0, PageFormat arg1, int arg2)
			throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
}
//12.34.56.78:80
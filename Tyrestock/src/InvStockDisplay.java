import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.ResultSetMetaData;


public class InvStockDisplay extends JFrame implements ActionListener{
	private JLabel CompanyName;
	private JButton Logoutbtn,Backbtn,MainMenubtn;
	private JTable table;
	public InvStockDisplay(String inv){
		super("Stock and Invoice Management System");
		setLayout(new GridBagLayout());
		
		CompanyName = new JLabel("Renuka Tyres\n");
		Logoutbtn = new JButton("LOGOUT");
		Logoutbtn.setForeground(Color.BLUE);
		Backbtn = new JButton("BACK");
		Backbtn.setForeground(Color.BLUE);
		MainMenubtn = new JButton("MAIN MENU");
		MainMenubtn.setForeground(Color.BLUE);
		
		
		
		Logoutbtn.addActionListener(this);
		Backbtn.addActionListener(this);
		MainMenubtn.addActionListener(this);
		GridBagConstraints gc = new GridBagConstraints();
		
		//final Insets insets = new Insets(4, 4, 4, 4);
		//final Insets buttonInsets = new Insets(4, 4, 4, 8);
		CompanyName.setFont(new Font("Serif", Font.BOLD, 18));
		CompanyName.setForeground(Color.BLACK);
		
		table = new JTable();
		
		
		 ArrayList columnNames = new ArrayList();
	        ArrayList data = new ArrayList();
	        String url = "jdbc:mysql://localhost:8889/renuka";
	        String userid = "root";
	        String password = "root";
	        String sql = "SELECT Product_name,Date,Opening_stock,Purchase,Sale,Closing_stock,Price_per_unit FROM stock ";
	        String sql1 = "SELECT Product_name,Date,Opening_stock,Purchase,Sale,Closing_stock,Price_per_unit FROM stock where Invoice_no=?";
	        ////////////
	       // System.out.println(combovalue);
	        GridBagConstraints Gc = new GridBagConstraints();
	        try {Connection connection = (Connection) DriverManager.getConnection( url, userid, password );
	                //Statement stmt = connection.createStatement();
	        		PreparedStatement verifyUsr = (PreparedStatement) connection.prepareStatement(sql);
	        		
	        		
	        		
	        		ResultSet rs = (ResultSet) verifyUsr.executeQuery(sql);
	        		{
	        			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
	        			int columns = md.getColumnCount();

	                //  Get column names
	        			for (int i = 1; i <= columns; i++)
	        			{
	        				columnNames.add( md.getColumnName(i) );
	        			}
	        		}
	                //  Get row data
	        		PreparedStatement verifyProd = (PreparedStatement) connection.prepareStatement(sql1);
	        		verifyProd.setString(1, inv);
	        		
	        		ResultSet rs1 = (ResultSet) verifyProd.executeQuery();
	        		{
	        			ResultSetMetaData md = (ResultSetMetaData) rs1.getMetaData();
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
	        }
	            catch (SQLException e1)
	            {
	                System.out.println( e1.getMessage() );
	            }	

	            // Create Vectors and copy over elements from ArrayLists to them
	            // Vector is deprecated but I am using them in this example to keep 
	            // things simple - the best practice would be to create a custom defined
	            // class which inherits from the AbstractTableModel class
	            Vector columnNamesVector = new Vector();
	            Vector dataVector = new Vector();

	            for (int i = 0; i < data.size(); i++)
	            {
	                ArrayList subArray = (ArrayList)data.get(i);
	                Vector subVector = new Vector();
	                for (int j = 0; j < subArray.size(); j++)
	                {
	                    subVector.add(subArray.get(j));
	                }
	                dataVector.add(subVector);
	            }

	            for (int i = 0; i < columnNames.size(); i++ )
	                columnNamesVector.add(columnNames.get(i));

	            //  Create table with database data    
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

		gc.gridy=0;
		gc.gridx = 0;
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		add(CompanyName, gc);
		
		
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 1;	
		gc.insets = new Insets(4, 4, 4, 8);
		add( new JScrollPane(table),gc);
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 2;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.SOUTHEAST;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.ipadx = 6;
		// gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(Logoutbtn, gc);
		gc.weightx=0;
		gc.gridx = 1;
		//gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		//gc.anchor = GridBagConstraints.CENTER;
		//gc.insets = new Insets(0,0,0,5);
		add(MainMenubtn,gc);
		gc.weightx=0;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.SOUTHWEST;
		add(Backbtn,gc);
		setLocationByPlatform(true);
		setSize(600, 600);
		//setLocation(200,200);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

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
		if(btnClicked == Logoutbtn){
			SigninPage page = new SigninPage();
			this.setVisible(false);
		}else if(btnClicked == MainMenubtn){
			StockMainPage page = new StockMainPage();
			this.setVisible(false);
		}else if(btnClicked == Backbtn){
			InvStockInfo page = new InvStockInfo();
			this.setVisible(false);
		}
}
}

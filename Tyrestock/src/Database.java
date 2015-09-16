import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Database {

	private Connection con;

	public Database() {
		System.out.println("StockInfo DB");
	}

	public void connect() throws Exception {
		if (con != null) {
			return;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		// jdbc:mysql://hostname/ databaseName
		String url = "jdbc:mysql://localhost:8889/renuka";
		con = (Connection) DriverManager.getConnection(url, "root", "root");

	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean saveUser(String emplName, String userName, char[] pwd)
			throws SQLException {
		String checkSql = "select count(*) as count from Login where UserName=?";
		PreparedStatement checkEmployee = (PreparedStatement) con
				.prepareStatement(checkSql);

		String insertSql = "insert into Login (EmployeeName, UserName, Password) values(?,?,?)";
		PreparedStatement insertStatement = (PreparedStatement) con
				.prepareStatement(insertSql);

		checkEmployee.setString(1, userName);

		ResultSet checkResult = checkEmployee.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);
		checkEmployee.close();

		System.out.println("Count for employee name " + count);

		if (count == 0) {
			System.out.println("Register Employee: " + emplName
					+ " to Stock and Invoice Info system");
			String passWd = new String(pwd);
			insertStatement.setString(1, emplName);
			insertStatement.setString(2, userName);
			insertStatement.setString(3, passWd);

			insertStatement.executeUpdate();
			return true;
		} else if (count >= 1) {
			System.out.println("Employee " + emplName
					+ " already registered to Stock and Invoice Info system");
			return false;
		}

		insertStatement.close();
		return false;
	}
	public boolean saveCust(String tin_no,String retailername,String place,Float discount)throws SQLException{
		String checksql ="select count(*) as count from customer where Tin_no =?";
		PreparedStatement checkTin = (PreparedStatement) con
				.prepareStatement(checksql);
		String insertSql ="insert into customer (Tin_no,Retailer_name,Place,Discount) values(?,?,?,?)";
		PreparedStatement insertStatement = (PreparedStatement) con
				.prepareStatement(insertSql);

		checkTin.setString(1, tin_no);

		ResultSet checkResult = checkTin.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);
		checkTin.close();
		System.out.println("Count for Tin number " + count);

		if (count == 0) {
			System.out.println("Register Tin_no: " + tin_no
					+ " to Stock and Invoice Info system");
			
			insertStatement.setString(1, tin_no);
			insertStatement.setString(2, retailername);
			insertStatement.setString(3, place);
			insertStatement.setFloat(4,discount);

			insertStatement.executeUpdate();
			return true;

		} else if (count >= 1) {
			System.out.println("Tin_no  " + tin_no
					+ " already registered to Stock and Invoice Info system");
			return false;
		}

		insertStatement.close();
		return false;
	}
	public boolean verifyKey(char[] pwd)throws SQLException{
		String checksql = "SELECT `Key` FROM `softwarelicence` WHERE Slno=1";
		PreparedStatement verifyKey = (PreparedStatement) con
				.prepareStatement(checksql);
		//verifyKey.setInt(1, 1);

		ResultSet checkResult = verifyKey.executeQuery();
		checkResult.next();
		String PassWord = "";
		String passWd = new String(pwd);
		PassWord = checkResult.getString("Key");
		if ( passWd.equals(PassWord)) {
			return true;
		}

		return false;
	}
	
	public boolean ValidateSoftwareLic(String Mac) throws SQLException {
		String checkSql = "select count(*) as count from softwarelicence where MAC=?";
		PreparedStatement checkMac = (PreparedStatement) con
				.prepareStatement(checkSql);
		
		checkMac.setString(1, Mac);
		
		ResultSet checkResult = checkMac.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);
		checkMac.close();

		System.out.println("Count for MAC " + count);

		if (count == 0) {
			System.out.println("No match for MAC address");
		    return(false);
		}
		else if(count == 1) {
			System.out.println("There is a match for MAC address");
		    return(true);
			
		}
		return(false);
	}

	
	
	public boolean verifyUser(String userName, char[] pwd) throws SQLException {
		String checkSql = "select userName, Password from Login where userName=?";
		PreparedStatement verifyUsr = (PreparedStatement) con
				.prepareStatement(checkSql);

		verifyUsr.setString(1, userName);

		ResultSet checkResult = verifyUsr.executeQuery();

		String UserName = "";
		String PassWord = "";
		String passWd = new String(pwd);

		while (checkResult.next()) {
			UserName = checkResult.getString("userName");
			PassWord = checkResult.getString("Password");

			System.out.println("USER NAME: " + UserName);
			System.out.println("PASSWORD: " + PassWord);
		}

		if (userName.equals(UserName) && passWd.equals(PassWord)) {
			return true;
		}

		return false;

	}

	public boolean verifytoResetpwd(String empname, String username)
			throws SQLException {
		String checkSql = "select username, EmployeeName from Login where userName=?";
		PreparedStatement verifyUsr = (PreparedStatement) con
				.prepareStatement(checkSql);

		verifyUsr.setString(1, username);

		ResultSet checkResult = verifyUsr.executeQuery();

		String Empname = "";
		String Username = "";
		// String passWd = new String(pwd);

		while (checkResult.next()) {
			Empname = checkResult.getString("userName");
			Username = checkResult.getString("EmployeeName");

			System.out.println("USER NAME: " + Username);
			System.out.println("PASSWORD: " + Empname);
		}

		if (username.equals(Username) && Empname.equals(Empname)) {
			return true;
		}

		return false;

	}

	public void resetpwd(String username, char[] pwd) throws SQLException {
		String query = "update Login set Password = ? where UserName = ?";
		PreparedStatement UpdateStmt = (PreparedStatement) con
				.prepareStatement(query);
		String passWd = new String(pwd);
		UpdateStmt.setString(1, passWd);
		UpdateStmt.setString(2, username);
		UpdateStmt.executeUpdate();
		System.out.println("Password executed");
		UpdateStmt.close();
	}

	public void AddProd(String Prodname, String Invoice_no, java.sql.Date Pur_date,
			String Pur_qty,String price) throws SQLException {
		String Checksql = "select count(*) as count from stock where Product_name=? ";
		PreparedStatement verifyproduct = (PreparedStatement) con
				.prepareStatement(Checksql);

		String checkid ="select count(*) as countid from stock";
		PreparedStatement checkidno = (PreparedStatement) con
				.prepareStatement(checkid);
		
		String insertsql = "insert into stock(Sl_no,Product_name,Date,Invoice_no,Opening_stock,Purchase,Sale,Closing_stock,Price_per_unit) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertStatement = (PreparedStatement) con
				.prepareStatement(insertsql);

		verifyproduct.setString(1, Prodname);

		ResultSet checkResult = verifyproduct.executeQuery();
		checkResult.next();

		int count = checkResult.getInt(1);
	
		int countid=0; 
		ResultSet checkResult1 = checkidno.executeQuery();
		while(checkResult1.next()){
		
		 countid = checkResult1.getInt(1);
		 
		}
		verifyproduct.close();
		checkidno.close();
		
		
		// // date format conversion from string
		//String input = Pur_date;
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//LocalDate localDate = LocalDate.parse(input, formatter);

		// //inserting values if product not present
		if (count == 0) {
			insertStatement.setInt(1, countid+1);
			insertStatement.setString(2, Prodname);

			//java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
			insertStatement.setDate(3, Pur_date);
			insertStatement.setString(4, Invoice_no);
			insertStatement.setString(5, "0");
			insertStatement.setString(6, Pur_qty);
			insertStatement.setString(7, "0");
			insertStatement.setString(8, Pur_qty);
			insertStatement.setString(9, price);
			System.out.println("Product " + Prodname + " added");
			insertStatement.executeUpdate();
		} else if (count >= 1) {
			System.out.println("Product already exist");
		}
		insertStatement.close();
	}

	public void UpdateProd(String Prodname, int Invoice_no, java.sql.Date Pur_date,int Pur_qty,int Price) throws SQLException {
		String closingstock = "SELECT Closing_stock,Product_name,Date FROM stock where Product_name=? ORDER BY Sl_no DESC  LIMIT 1";
		PreparedStatement verifyclosing = (PreparedStatement) con.prepareStatement(closingstock);

		String checkid ="select count(*) as countid from stock";
		PreparedStatement checkidno = (PreparedStatement) con
				.prepareStatement(checkid);
		
		String insertsql = "insert into stock(Sl_no,Product_name,Date,Invoice_no,Opening_stock,Purchase,Sale,Closing_stock,Price_per_unit) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertStatement = (PreparedStatement) con.prepareStatement(insertsql);

		verifyclosing.setString(1, Prodname);

		ResultSet checkResult = verifyclosing.executeQuery();
		
		int countid=0; 
		ResultSet checkResult1 = checkidno.executeQuery();
		while(checkResult1.next()){
		
		 countid = checkResult1.getInt(1);
		 
		}
		
		
		
		//String date ="";
		int closestock=0;
		// String passWd = new String(pwd);

		while (checkResult.next()) {
			closestock = Integer.parseInt(checkResult.getString("Closing_stock"));
			//date = checkResult.getString("Date");
			System.out.println(closestock);
			
		}
		
		//String input = Pur_date;
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//LocalDate localDate = LocalDate.parse(input, formatter);
		
		/*String input1 = date;
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dbDate = LocalDate.parse(input1, formatter1);
		
		System.out.println(dbDate);
		*/
		insertStatement.setInt(1, countid+1);
		insertStatement.setString(2, Prodname);
		//java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
		insertStatement.setDate(3, Pur_date);
		insertStatement.setInt(4, Invoice_no);
		insertStatement.setInt(5, closestock);
		insertStatement.setInt(6, Pur_qty);
		insertStatement.setInt(7, 0);
		insertStatement.setInt(8, Pur_qty+closestock);
		insertStatement.setInt(9, Price);

		insertStatement.executeUpdate();
		
		insertStatement.close();
	}
	public int GenInvoice(int Invoice_no,java.sql.Date sale_date,String Deliveryto,float Discount,float Vat,String Product_name,Float Price_per_unit,int Quantity,Float Value)throws SQLException {
		String closingstock = "SELECT Closing_stock,Product_name FROM stock where Product_name=? ORDER BY Sl_no DESC  LIMIT 1";
		PreparedStatement verifyclosing = (PreparedStatement) con.prepareStatement(closingstock);
		
		String checkid ="select count(*) as countid from stock";
		PreparedStatement checkidno = (PreparedStatement) con
				.prepareStatement(checkid);
		
		String checkno ="select count(*) as count from Sales";
		PreparedStatement checkno1 = (PreparedStatement) con
				.prepareStatement(checkno);
		
		String insertsql = "insert into stock(Sl_no,Product_name,Date,Invoice_no,Opening_stock,Purchase,Sale,Closing_stock,Price_per_unit) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertStatement = (PreparedStatement) con.prepareStatement(insertsql);
		
		String insertsql1 = "insert into Sales(Sl_no,Product_name,Date,Invoice_no,Deliveryto,Price_per_unit,Quantity,value,Discount,Vat) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertsales =(PreparedStatement)con.prepareStatement(insertsql1);
		verifyclosing.setString(1, Product_name);

		int count = 0;
		ResultSet checkResult2 = checkno1.executeQuery();
		while(checkResult2.next()){
			count = checkResult2.getInt(1);
		}
		
		int countid=0; 
		ResultSet checkResult1 = checkidno.executeQuery();
		while(checkResult1.next()){
		 countid = checkResult1.getInt(1);
		}
		ResultSet checkResult = verifyclosing.executeQuery();
		int closestock=0;
		// String passWd = new String(pwd);

		while (checkResult.next()) {
			closestock = Integer.parseInt(checkResult.getString("Closing_stock"));
			//date = checkResult.getString("Date");
			System.out.println(closestock);
			
		}
		//String input = sale_date;
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//LocalDate localDate = LocalDate.parse(input, formatter);
	
		
		
		if(closestock>0 && !((closestock-Quantity) <0)){
			insertStatement.setInt(1, countid+1);
			insertStatement.setString(2, Product_name);
			//java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
			insertStatement.setDate(3, sale_date);
			insertStatement.setInt(4, Invoice_no);
			insertStatement.setInt(5, closestock);
			insertStatement.setInt(6,0);
			insertStatement.setInt(7, Quantity);
			insertStatement.setInt(8, closestock-Quantity);
			insertStatement.setFloat(9, Price_per_unit);
			insertStatement.executeUpdate();
			insertStatement.close();
			
			insertsales.setInt(1,count+1 );
			insertsales.setString(2,Product_name);
			//java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
			insertsales.setDate(3, sale_date);
			insertsales.setInt(4, Invoice_no);
			insertsales.setString(5, Deliveryto);
			insertsales.setFloat(6, Price_per_unit);
			insertsales.setInt(7, Quantity);
			insertsales.setFloat(8,Value);
			insertsales.setFloat(9, Discount);
			insertsales.setFloat(10, Vat);
			insertsales.executeUpdate();
			insertsales.close();
		
				return 1;
		}
		else{
			return 0;
		}
	}
}

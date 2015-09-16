import javax.swing.SwingUtilities;

public class StockInvoiceMgmt {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				//LoginFrame frame = new LoginFrame();
				KeyEntry frame = new KeyEntry();
			}
			
		});              

	}

}

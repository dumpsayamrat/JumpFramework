package jump.view.main;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.TextField;


import java.awt.event.InputMethodListener;

import javax.swing.JButton;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import databases.MySQL;

import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.SelectionAdapter;

public class Databases extends ViewPart {

	private Label lblConfiguration,lblInfo;
	private Panel panel;
	private Button button;
	private TextField text;
	private Combo comboTB;
	public static int i = 0;
	private String db,user,pass;
	private Text txtDatabase;
	private Text txtUser;
	private Text txtPass;
	private MySQL mysql;
	
	public Databases() {
		
	}

	public void setFocus() {
		lblConfiguration.setFocus();

	}

	/*public void dbcon() {
		Connector conn = new Connector("cdcol", "root", "");
		conn.connection();
		conn.showTableName();
			
	}
	*/
	public void createPartControl(Composite parent) {
		panel = new Panel();
		parent.setLayout(null);
		lblConfiguration = new Label(parent, 0);
		lblConfiguration.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblConfiguration.setText("Configuration");
		lblConfiguration.setBounds(22, 32, 121, 24);
		lblConfiguration.setAlignment(SWT.CENTER);
		text = new TextField();
		Shell shell = new Shell();
		Color color = new Color(shell.getDisplay(), new RGB(255, 255, 0));
		lblConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		
		Label lblTable = new Label(parent, SWT.NONE);
		lblTable.setText("Table");
		lblTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblTable.setAlignment(SWT.CENTER);
		lblTable.setBounds(34, 174, 87, 24);
		
		comboTB = new Combo(parent, SWT.NONE);
		comboTB.setItems(new String[] {});
		comboTB.setBounds(149, 171, 262, 23);
		comboTB.setText("Select Table");
		
		Button btnConnect = new Button(parent, SWT.NONE);
		btnConnect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mysql = new MySQL(txtDatabase.getText(), txtUser.getText(), txtPass.getText());
				if (mysql.getConnection()) lblInfo.setText("Connection successful.");
				else lblInfo.setText("Connection failed.");
			}
		});
		btnConnect.setBounds(414, 125, 75, 25);
		btnConnect.setText("Connect");
		
		txtDatabase = new Text(parent, SWT.BORDER);
		txtDatabase.setBounds(149, 64, 252, 21);
		
		Label lblDatabaseName = new Label(parent, SWT.NONE);
		lblDatabaseName.setText("Database Name");
		lblDatabaseName.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblDatabaseName.setBounds(22, 61, 121, 24);
		
		txtUser = new Text(parent, SWT.BORDER);
		txtUser.setBounds(149, 94, 252, 21);
		
		Label lblDatabaseUser = new Label(parent, SWT.NONE);
		lblDatabaseUser.setText("Database User");
		lblDatabaseUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblDatabaseUser.setBounds(22, 91, 121, 24);
		
		txtPass = new Text(parent, SWT.BORDER);
		txtPass.setBounds(149, 127, 252, 21);
		
		Label lblDatabasePassword = new Label(parent, SWT.NONE);
		lblDatabasePassword.setText("Database Password");
		lblDatabasePassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblDatabasePassword.setBounds(22, 124, 121, 24);
		
		lblInfo = new Label(parent, SWT.NONE);
		lblInfo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblInfo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblInfo.setBounds(69, 375, 386, 70);
		
	

	}
}

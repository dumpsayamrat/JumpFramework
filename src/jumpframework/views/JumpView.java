package jumpframework.views;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.TextField;



import java.awt.event.InputMethodListener;

import javax.swing.JButton;

import jumpframework.createproject.CreateProject;
import jumpframework.createproject.FileUtil;
import jumpframework.createproject.MySQL;
import jumpframework.createproject.Writer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.custom.ScrolledComposite;

public class JumpView extends ViewPart {

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
	private Text txtLocation;
	Writer writer;
	
	public JumpView() {
		
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
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		panel = new Panel();
		parent.setLayout(null);
		lblConfiguration = new Label(parent, 0);
		lblConfiguration.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblConfiguration.setText("JUMP Task");
		lblConfiguration.setBounds(22, 20, 121, 24);
		lblConfiguration.setAlignment(SWT.CENTER);
		text = new TextField();
		Shell shell = new Shell();
		Color color = new Color(shell.getDisplay(), new RGB(255, 255, 0));
		lblConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnConnect = new Button(parent, SWT.NONE);
		btnConnect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mysql = new MySQL(txtDatabase.getText(), txtUser.getText(), txtPass.getText());
				if (mysql.getConnection()){
					String[] tables = mysql.getTablesName();
					comboTB.setItems(tables);
					comboTB.setText("Select Table");
					lblInfo.setText("Connection Success.");
				}
				else lblInfo.setText("Connection failed.");
			}
		});
		btnConnect.setBounds(416, 125, 75, 25);
		btnConnect.setText("Connect");
		
		txtDatabase = new Text(parent, SWT.BORDER);
		txtDatabase.setText("jdbc:mysql://<host>:3306/<database>");
		txtDatabase.setBounds(149, 64, 252, 21);
		
		Label lblDatabaseName = new Label(parent, SWT.NONE);
		lblDatabaseName.setText("Connecttion String");
		lblDatabaseName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDatabaseName.setBounds(22, 67, 101, 18);
		
		txtUser = new Text(parent, SWT.BORDER);
		txtUser.setBounds(149, 94, 252, 21);
		
		Label lblDatabaseUser = new Label(parent, SWT.NONE);
		lblDatabaseUser.setText("Database User");
		lblDatabaseUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDatabaseUser.setBounds(22, 97, 75, 15);
		
		txtPass = new Text(parent, SWT.BORDER | SWT.PASSWORD);
		txtPass.setTouchEnabled(true);
		txtPass.setBounds(149, 127, 252, 21);
		
		Label lblDatabasePassword = new Label(parent, SWT.NONE);
		lblDatabasePassword.setText("Database Password");
		lblDatabasePassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDatabasePassword.setBounds(22, 130, 101, 15);
		
		lblInfo = new Label(parent, SWT.BORDER);
		lblInfo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblInfo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
		lblInfo.setBounds(68, 289, 386, 70);
		
		comboTB = new Combo(parent, SWT.NONE);
		comboTB.setItems(new String[] {});
		comboTB.setBounds(149, 209, 252, 23);
		
		Button btnGenerate = new Button(parent, SWT.NONE);
		btnGenerate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(FileUtil.checkLocation(txtLocation.getText())){
					if(comboTB.getText().equals("")){
						lblInfo.setText("Please select table.");
					}
					else{
						if(CreateProject.createProject(txtLocation.getText(),txtDatabase.getText(), txtUser.getText(), txtPass.getText() )){
							
							mysql = new MySQL(txtDatabase.getText(), txtUser.getText(), txtPass.getText());
							if (mysql.getConnection()){
								writer = new Writer(comboTB.getText(), "model", txtLocation.getText(), mysql);
								writer = new Writer(comboTB.getText(), "dao", txtLocation.getText(), mysql);
								writer = new Writer(comboTB.getText(), "service", txtLocation.getText(), mysql);
								writer = new Writer(comboTB.getText(), "repository", txtLocation.getText(), mysql);
								writer = new Writer(comboTB.getText(), "controller", txtLocation.getText(), mysql);
								writer = new Writer(comboTB.getText(), "view", txtLocation.getText(), mysql);
								
								lblInfo.setText("Generation Success.");
							}
							
						}else{
							
						}
						
					}
				}
				else lblInfo.setText(txtLocation.getText()+"Wrong project location.");
			}
		});
		btnGenerate.setBounds(416, 207, 75, 25);
		btnGenerate.setText("Generate");
		
		txtLocation = new Text(parent, SWT.BORDER);
		txtLocation.setBounds(149, 181, 252, 21);
		
		Label lblTable = new Label(parent, SWT.NONE);
		lblTable.setText("Select Table");
		lblTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTable.setAlignment(SWT.CENTER);
		lblTable.setBounds(22, 212, 68, 15);
		
		Label lblProjectLocation = new Label(parent, SWT.NONE);
		lblProjectLocation.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblProjectLocation.setBounds(22, 184, 100, 15);
		lblProjectLocation.setText("Project Location");
		
		
		
	

	}
}

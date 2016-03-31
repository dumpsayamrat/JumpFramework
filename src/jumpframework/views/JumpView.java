package jumpframework.views;

import java.awt.Panel;
import java.awt.TextField;

import jumpframework.createproject.CreateProject;
import jumpframework.createproject.FileUtil;
import jumpframework.createproject.InputSteamToFileApp;
import jumpframework.createproject.MySQL;
import jumpframework.createproject.Writer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;

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

	String selectedDir;
	private Text TextTable;
	private Composite composite2;
	private Button[] btnCheck;
	private Button btnNewButton;
	
	public JumpView() {
		
	}

	public void setFocus() {
		lblConfiguration.setFocus();

	}

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
				lblInfo.setText("Waiting....");
				mysql = new MySQL(txtDatabase.getText(), txtUser.getText(), txtPass.getText());
				if (mysql.getConnection()){
					String[] tables = mysql.getTablesName();
					TextTable.setText("Select Table");
					
					btnCheck = new Button[tables.length];
					for (int d = 0; d < btnCheck.length*10; d++) {
						
						btnCheck[0] = new Button(composite2, SWT.CHECK);
						
						btnCheck[0].addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
						     
							}
						});
						btnCheck[0].setBounds(10, 10 + (d * 20), 93, 16);
						btnCheck[0].setText(tables[0]+"."+d);
					}
					lblInfo.setText("Connection Success.");
				}
				else lblInfo.setText("Connection Failed.");
			}
		});
		btnConnect.setBounds(416, 125, 75, 25);
		btnConnect.setText("Connect");
		
		txtDatabase = new Text(parent, SWT.BORDER);
		txtDatabase.setText("jdbc:mysql://<host>:3306/<database>");
		txtDatabase.setBounds(149, 64, 252, 21);
		
		Label lblDatabaseName = new Label(parent, SWT.NONE);
		lblDatabaseName.setText("Connection String");
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
		lblInfo.setBounds(68, 500, 386, 70);
		
		comboTB = new Combo(parent, SWT.NONE);
		comboTB.setItems(new String[] {});
		comboTB.setBounds(149, 209, 252, 23);
		
		Button btnGenerate = new Button(parent, SWT.NONE);
		btnGenerate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = "";
				for (int d = 0; d < btnCheck.length; d++) {
					
					if(btnCheck[d].getSelection()){
						s+=btnCheck[d].getText();
					}
					lblInfo.setText(s);
				}
				/*lblInfo.setText("Waiting....");
				String locationPath = txtLocation.getText();
				if(FileUtil.checkLocation(locationPath)){
					if(comboTB.getText().equals("")){
						lblInfo.setText("Please select table.");
					}
					else{
						InputSteamToFileApp in = new InputSteamToFileApp();
						in.extactFileTemplate(locationPath);
						if(CreateProject.createProject(locationPath,txtDatabase.getText(), txtUser.getText(), txtPass.getText() )){
							
							mysql = new MySQL(txtDatabase.getText(), txtUser.getText(), txtPass.getText());
							if (mysql.getConnection()){
								writer = new Writer(comboTB.getText(), "model", locationPath, mysql);
								writer = new Writer(comboTB.getText(), "dao", locationPath, mysql);
								writer = new Writer(comboTB.getText(), "service", locationPath, mysql);
								writer = new Writer(comboTB.getText(), "repository", locationPath, mysql);
								writer = new Writer(comboTB.getText(), "controller", locationPath, mysql);
								writer = new Writer(comboTB.getText(), "view", locationPath, mysql);
								
								//create new hibernate.cfg.xml
								CreateProject.createHibernateConfig(txtLocation.getText());
								lblInfo.setText("Generation Success.");
							}else{
								lblInfo.setText("Connection Failed.");
							}
							
						}else{
							lblInfo.setText("Generation Failed.");
						}
						FileUtil.deleteFile(locationPath+"\\template.zip");
						FileUtil.deleteDirectory(locationPath+"\\template");
					}
				}
				else lblInfo.setText(txtLocation.getText()+"Wrong project location.");*/
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
		
		Button btnBrowse = new Button(parent, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//TODO
			}
		});
		btnBrowse.setBounds(416, 179, 75, 25);
		btnBrowse.setText("Browse...");
		
		btnBrowse.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				DirectoryDialog directoryDialog = new DirectoryDialog(shell);

				directoryDialog.setFilterPath(selectedDir);
				directoryDialog.setMessage("Please select a directory and click OK");

				String dir = directoryDialog.open();
				if (dir != null) {
					txtLocation.setText(dir);
					selectedDir = dir;
				}
			}
		});
		
		TextTable = new Text(parent, SWT.BORDER);
		TextTable.setBounds(149, 209, 252, 21);
		
		composite2 = new Composite(parent, SWT.NONE | SWT.V_SCROLL);
		composite2.setBounds(149, 236, 252, 147);
		
		
		
	

	}
}

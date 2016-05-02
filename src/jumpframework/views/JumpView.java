package jumpframework.views;

import jumpframework.createproject.CreateProject;
import jumpframework.createproject.FileUtil;
import jumpframework.createproject.InputSteamToFileApp;
import jumpframework.createproject.MySQL;
import jumpframework.createproject.Writer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;

public class JumpView extends ViewPart {

	private Label lblConfiguration,lblInfo;
	private Composite composite2;
	private Text txtDatabase;
	private Text txtUser;
	private Text txtPass;
	private MySQL mysql;
	private Text txtLocation;
	private Button[] btnCheck;
	private Button btnSelectAllTable;
	private Button btnUnSelectAll;
	
	Writer writer;
	
	public static int i = 0;
	private String selectedDir;
	
	public JumpView() {
		
	}

	public void setFocus() {
		lblConfiguration.setFocus();

	}

	public void createPartControl(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		parent.setLayout(null);
		lblConfiguration = new Label(parent, 0);
		lblConfiguration.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblConfiguration.setText("JUMP Task");
		lblConfiguration.setBounds(22, 20, 121, 24);
		lblConfiguration.setAlignment(SWT.CENTER);
		Shell shell = new Shell();
		lblConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		composite2 = new Composite(parent, SWT.NONE | SWT.V_SCROLL);
		composite2.setBounds(149, 209, 252, 147);
		composite2.setEnabled(true);
		
		Button btnConnect = new Button(parent, SWT.NONE);
		btnConnect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblInfo.setText("Waiting....");
				Control[] c = composite2.getChildren();
				System.out.println("first check children.");
				for (Control c1 : c) {
					System.out.println(c1.toString());
					c1.dispose();
				}
				mysql = new MySQL(txtDatabase.getText(), txtUser.getText(), txtPass.getText());
				if (mysql.getConnection()){
					String[] tables = mysql.getTablesName();					
					btnCheck = new Button[tables.length];
					for (int d = 0; d < btnCheck.length; d++) {
						
						btnCheck[d] = new Button(composite2, SWT.CHECK);
						btnCheck[d].setBounds(10, 10 + (d * 20), 93, 16);
						btnCheck[d].setText(tables[d]);
					}
					lblInfo.setText("Connection Success.");
				}
				else lblInfo.setText("Connection Failed.");
				System.out.println("second check children.");
				for (Control c1 : c) {
					System.out.println(c1.toString());
				}
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
		
		lblInfo = new Label(parent, SWT.WRAP);
		lblInfo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblInfo.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblInfo.setBounds(68, 400, 386, 106);
		
		Button btnGenerate = new Button(parent, SWT.NONE);
		btnGenerate.setBounds(416, 330, 75, 25);
		btnGenerate.setText("Generate");
		btnGenerate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblInfo.setText("Waiting....");
				String locationPath = txtLocation.getText();
				if(FileUtil.checkLocation(locationPath)){
					InputSteamToFileApp in = new InputSteamToFileApp();
					in.extactFileTemplate(locationPath);
					boolean isCreated = CreateProject.createProject(locationPath,txtDatabase.getText(), txtUser.getText(), txtPass.getText());
					
					if(isCreated){
						
						mysql = new MySQL(txtDatabase.getText(), txtUser.getText(), txtPass.getText());
						if (mysql.getConnection()){					
							String textInfo = "";
							for (int d = 0; d < btnCheck.length; d++) {								
								if(btnCheck[d].getSelection()){
									String tableName = btnCheck[d].getText();
									writer = new Writer(tableName, "model", locationPath, mysql);
									writer = new Writer(tableName, "dao", locationPath, mysql);
									writer = new Writer(tableName, "service", locationPath, mysql);
									writer = new Writer(tableName, "repository", locationPath, mysql);
									writer = new Writer(tableName, "controller", locationPath, mysql);
									writer = new Writer(tableName, "view", locationPath, mysql);
									
									textInfo += tableName+", ";
								}							
							}
							
							//create new hibernate.cfg.xml
							CreateProject.createHibernateConfig(txtLocation.getText());
							
							lblInfo.setText(textInfo+" has been generated.");
						}else{
							lblInfo.setText("Connection Failed.");
						}
						
					}else{
						lblInfo.setText("Generation Failed.");
					}
					FileUtil.deleteFile(locationPath+"\\template.zip");
					FileUtil.deleteDirectory(locationPath+"\\template");
					
				}
				else lblInfo.setText(txtLocation.getText()+"Wrong project location.");
			}
		});
		
		
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
		
		btnSelectAllTable = new Button(parent, SWT.NONE);
		btnSelectAllTable.setBounds(416, 207, 91, 25);
		btnSelectAllTable.setText("Select all tables");
		btnSelectAllTable.addSelectionListener(new SelectionAdapter() {
			@Override
			
			public void widgetSelected(SelectionEvent e) {
				for (int b = 0; b < btnCheck.length; b++) {
					btnCheck[b].setSelection(true);	
				}
				
			}
		});
		
		btnUnSelectAll = new Button(parent, SWT.NONE);
		btnUnSelectAll.setBounds(416, 236, 109, 25);
		btnUnSelectAll.setText("Unselect all tables");
		btnUnSelectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (int b = 0; b < btnCheck.length; b++) {
					btnCheck[b].setSelection(false);	
				}
			}
		});
		
		
		
		
		
	

	}
}

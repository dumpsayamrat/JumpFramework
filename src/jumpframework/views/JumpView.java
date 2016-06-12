package jumpframework.views;

import jumpframework.createproject.CreateProject;
import jumpframework.createproject.FileUtil;
import jumpframework.createproject.InputSteamToFileApp;
import jumpframework.createproject.MySQL;
import jumpframework.createproject.Writer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;

public class JumpView extends ViewPart {

	private Label lblConfiguration,lblInfo,lblNewLabel;
	private Composite composite2;
	//private ScrolledComposite scrolledComposite;
	private Text txtDatabase;
	private Text txtUser;
	private Text txtPass;
	private MySQL mysql;
	private Text txtLocation;
	private Button[] btnCheck;
	private Button btnCheckButton;
	private Button btnSelectAllTable;
	private Button btnUnSelectAll, btnMySql, btnPostgres;
	
	Writer writer;
	
	public static int i = 0;
	private String selectedDir;
	private Label label;
	
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
		lblConfiguration.setBounds(10, 10, 121, 24);
		Shell shell = new Shell();
		lblConfiguration.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		//scrolledComposite.setSize(252, 147);
		composite2 = new Composite(parent, SWT.NONE);
		composite2.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite2.setBounds(10, 312, 333, 147);
		//composite2.
		composite2.setEnabled(true);
		
		txtDatabase = new Text(parent, SWT.BORDER);
		txtDatabase.setBounds(10, 80, 333, 21);
		
		btnMySql = new Button(parent, SWT.RADIO);
		btnMySql.setText("MySQL");
		btnMySql.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtDatabase.setText("jdbc:mysql://<host>:3306/<database>");
			}
		});
		btnMySql.setBounds(148, 60, 59, 16);
		
		btnPostgres = new Button(parent, SWT.RADIO);
		btnPostgres.setText("PostgreSQL");
		btnPostgres.setBounds(213, 60, 90, 16);
		btnPostgres.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtDatabase.setText("jdbc:postgresql://<host>:5432/<database>");
			}
		});
		
		txtDatabase.setText("jdbc:mysql://<host>:3306/<database>");
		btnMySql.setSelection(true);
		
		
		
		
		Button btnConnect = new Button(parent, SWT.NONE);
		btnConnect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblInfo.setText("Waiting....");
				Control[] c = composite2.getChildren();
				for (Control c1 : c) {
					System.out.println(c1.toString());
					c1.dispose();
				}
				String database = "postgresql";
				
				if(btnMySql.getSelection()){
					database = "mysql";
				}
				mysql = new MySQL(txtDatabase.getText(), txtUser.getText(), txtPass.getText(), database);
				if (mysql.getIsConnection()){
					String[] tables = mysql.getTablesName();					
					btnCheck = new Button[tables.length];
					int y = 0;
					int x = 0;
					for (int d = 0; d < btnCheck.length; d++) {
						
						btnCheck[d] = new Button(composite2,  SWT.CHECK);
						
						int rs = (d+1)%3;
						if(rs==1){
							x=10;
						}else if(rs==2){
							x = 120;
						}else{
							x = 230;
						}
						
						btnCheck[d].setLocation(x, 10 + (y * 30));
						btnCheck[d].setSize(90, 20);
						btnCheck[d].setText(tables[d]);
						composite2.setLocation(10, 280);
						
						composite2.setSize(composite2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
						
						if((d+1)%3 == 0){
							y++;
						}
					}
					lblNewLabel.setImage(SWTResourceManager.getImage(JumpView.class, "/org/eclipse/jface/dialogs/images/message_info.gif"));
					lblInfo.setText("Connection Success.");
				}
				
				else{
					lblNewLabel.setImage(SWTResourceManager.getImage(JumpView.class, "/org/eclipse/jface/dialogs/images/message_error.gif"));
					lblInfo.setText("Connection Failed.");
				}
				System.out.println("second check children.");
				for (Control c1 : c) {
					System.out.println(c1.toString());
				}
			}
		});
		
		btnConnect.setBounds(349, 178, 75, 25);
		btnConnect.setText("Connect");		
		
		txtUser = new Text(parent, SWT.BORDER);
		txtUser.setBounds(10, 130, 333, 21);
		
		txtPass = new Text(parent, SWT.BORDER | SWT.PASSWORD);
		txtPass.setTouchEnabled(true);
		txtPass.setBounds(10, 180, 333, 21);
		
		
		txtLocation = new Text(parent, SWT.BORDER);
		txtLocation.setBounds(10, 230, 333, 21);
		
		Label lblDatabaseName = new Label(parent, SWT.NONE);
		lblDatabaseName.setText("Connection String:");
		lblDatabaseName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDatabaseName.setBounds(10, 60, 101, 18);
		
		Label lblDatabaseUser = new Label(parent, SWT.NONE);
		lblDatabaseUser.setText("Database User:");
		lblDatabaseUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDatabaseUser.setBounds(10, 110, 91, 15);
		
		Label lblDatabasePassword = new Label(parent, SWT.NONE);
		lblDatabasePassword.setText("Database Password:");
		lblDatabasePassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDatabasePassword.setBounds(10, 160, 121, 15);
		
		lblInfo = new Label(parent, SWT.WRAP);
		lblInfo.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblInfo.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NONE));
		lblInfo.setBounds(30, 32, 527, 24);
		
		Button btnGenerate = new Button(parent, SWT.NONE);
		btnGenerate.setBounds(349, 374, 75, 25);
		btnGenerate.setText("Generate");
		btnGenerate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblInfo.setText("Waiting....");
				String locationPath = txtLocation.getText();
				if(FileUtil.checkLocation(locationPath)){
					InputSteamToFileApp in = new InputSteamToFileApp();
					in.extactFileTemplate(locationPath);
					
					String connectionString = txtDatabase.getText();
					String username = txtUser.getText();
					String password = txtPass.getText();
					
					String database = "postgresql";
					
					if(btnMySql.getSelection()){
						database = "mysql";
					}
					
					boolean isCreated = CreateProject.createProject(locationPath, connectionString, username, password, database);
					
					if(isCreated){					
						
						
						mysql = new MySQL(txtDatabase.getText(), txtUser.getText(), txtPass.getText(), database);
						if (mysql.getIsConnection()){
							String textInfo = "";
							
							if(btnCheckButton.getSelection()){
								CreateProject.createJUser("juser", mysql, database);
								writer = new Writer("juser", "model", locationPath, mysql);
								writer = new Writer("juser", "dao", locationPath, mysql);
								writer = new Writer("juser", "service", locationPath, mysql);
								writer = new Writer("juser", "repository", locationPath, mysql);
								writer = new Writer("juser", "controller", locationPath, mysql);
								writer = new Writer("juser", "view", locationPath, mysql);
							}
							
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
							
							//lblInfo.setText(textInfo+" has been generated.");
							lblNewLabel.setImage(SWTResourceManager.getImage(JumpView.class, "/org/eclipse/jface/dialogs/images/message_info.gif"));
							lblInfo.setText("CRUD codes has been generated.");
							
							mysql.close();
						}else{
							lblNewLabel.setImage(SWTResourceManager.getImage(JumpView.class, "/org/eclipse/jface/dialogs/images/message_error.gif"));
							lblInfo.setText("Connection Failed.");
						}
						
					}else{
						lblNewLabel.setImage(SWTResourceManager.getImage(JumpView.class, "/org/eclipse/jface/dialogs/images/message_error.gif"));
						lblInfo.setText("Generation Failed.");
					}
					FileUtil.deleteFile(locationPath+"\\template.zip");
					FileUtil.deleteDirectory(locationPath+"\\template");
					
				}
				else{
					lblNewLabel.setImage(SWTResourceManager.getImage(JumpView.class, "/org/eclipse/jface/dialogs/images/message_error.gif"));
					lblInfo.setText(txtLocation.getText()+"Wrong project location.");
				}
			}
		});
		
		Label lblProjectLocation = new Label(parent, SWT.NONE);
		lblProjectLocation.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblProjectLocation.setBounds(10, 210, 100, 15);
		lblProjectLocation.setText("Project Location");
		
		Button btnBrowse = new Button(parent, SWT.NONE);
		
		btnBrowse.setBounds(349, 228, 75, 25);
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
		btnSelectAllTable.setBounds(349, 312, 91, 25);
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
		btnUnSelectAll.setBounds(349, 343, 109, 25);
		btnUnSelectAll.setText("Unselect all tables");
		
		label = new Label(parent, SWT.NONE);
		label.setText("Project Location");
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(10, 291, 100, 15);
		
		lblNewLabel = new Label(parent, SWT.NONE);
		
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 30, 20, 24);
		
		btnCheckButton = new Button(parent, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCheckButton.setBounds(10, 267, 109, 16);
		btnCheckButton.setText("Authentication");
		
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

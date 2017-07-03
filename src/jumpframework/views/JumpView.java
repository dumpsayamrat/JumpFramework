package jumpframework.views;

import jumpframework.createproject.CreateProject;
import jumpframework.createproject.FileUtil;
import jumpframework.createproject.InputSteamToFileApp;
import jumpframework.createproject.MySQL;
import jumpframework.createproject.Writer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;

public class JumpView extends ViewPart {

	private Label lblConfiguration,lblInfo,lblNewLabel;
	private ScrolledComposite composite2;
	//private ScrolledComposite scrolledComposite;
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
	private Label lblSelectTable;
	
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
		
		lblNewLabel = new Label(parent, SWT.NONE);
		
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 30, 20, 24);
		
		lblInfo = new Label(parent, SWT.WRAP);
		lblInfo.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblInfo.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NONE));
		lblInfo.setBounds(30, 32, 527, 24);
		
		Label lblDatabaseName = new Label(parent, SWT.NONE);
		lblDatabaseName.setText("Connection String:");
		lblDatabaseName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDatabaseName.setBounds(10, 60, 101, 18);
		
		txtDatabase = new Text(parent, SWT.BORDER);
		txtDatabase.setText("jdbc:mysql://localhost:3306/<database>");
		txtDatabase.setBounds(10, 80, 333, 21);
		
		Label lblDatabaseUser = new Label(parent, SWT.NONE);
		lblDatabaseUser.setText("Database User:");
		lblDatabaseUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDatabaseUser.setBounds(10, 110, 91, 15);
		
		txtUser = new Text(parent, SWT.BORDER);
		txtUser.setBounds(10, 130, 333, 21);
		txtUser.setText();
		
		Label lblDatabasePassword = new Label(parent, SWT.NONE);
		lblDatabasePassword.setText("Database Password:");
		lblDatabasePassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDatabasePassword.setBounds(10, 160, 121, 15);
		
		txtPass = new Text(parent, SWT.BORDER | SWT.PASSWORD);
		txtPass.setTouchEnabled(true);
		txtPass.setBounds(10, 180, 333, 21);
		txtPass.setText();
		
		
		
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
				mysql = new MySQL(txtDatabase.getText(), txtUser.getText(), txtPass.getText());
				if (mysql.getConnection()){
					
					String[] tables = mysql.getTablesName();		
					
					Composite child = new Composite(composite2, SWT.NONE);
				    child.setLayout(null);
				    
					btnCheck = new Button[tables.length];
					int y = 0;
					int x = 0;
					for (int d = 0; d < btnCheck.length; d++) {
						
						btnCheck[d] = new Button(child,   SWT.CHECK);
						
						x=10;
						/*int rs = (d+1)%3;
						if(rs==1){
							x=10;
						}else if(rs==2){
							x = 120;
						}else{
							x = 230;
						}*/
						
						btnCheck[d].setLocation(x, 10 + (y++ * 25));
						btnCheck[d].setSize(90, 20);
						btnCheck[d].setText(tables[d]);
						
						//composite2.setLocation(10, 280);
						
						//composite2.setSize(composite2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	
						/*if((d+1)%3 == 0){
							y++;
						}*/
					}
					
					composite2.setMinHeight(y*25+10);
					composite2.setContent(child);
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
		
		
		txtLocation = new Text(parent, SWT.BORDER);
		txtLocation.setBounds(10, 230, 333, 21);
		
		Button btnBrowse = new Button(parent, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//TODO
			}
		});
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
		
		//scrolledComposite.setSize(252, 147);
		composite2 = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		composite2.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite2.setBounds(10, 280, 333, 147);
		composite2.setMinSize(300,500);
		composite2.setExpandVertical(true);
		composite2.setExpandHorizontal(true);
		composite2.setEnabled(true);
		
		btnSelectAllTable = new Button(parent, SWT.NONE);
		btnSelectAllTable.setBounds(349, 280, 91, 25);
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
		btnUnSelectAll.setBounds(349, 310, 109, 25);
		btnUnSelectAll.setText("Unselect all tables");
		btnUnSelectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (int b = 0; b < btnCheck.length; b++) {
					btnCheck[b].setSelection(false);	
				}
			}
		});
		
		Button btnGenerate = new Button(parent, SWT.NONE);
		btnGenerate.setBounds(349, 341, 75, 25);
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
							
							//lblInfo.setText(textInfo+" has been generated.");
							lblNewLabel.setImage(SWTResourceManager.getImage(JumpView.class, "/org/eclipse/jface/dialogs/images/message_info.gif"));
							lblInfo.setText("CRUD codes has been generated.");
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
		lblProjectLocation.setText("Project Location:");
		
		lblSelectTable = new Label(parent, SWT.NONE);
		lblSelectTable.setText("Select Table:");
		lblSelectTable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSelectTable.setBounds(11, 260, 100, 15);
		
		
		
		
		
	

	}
}

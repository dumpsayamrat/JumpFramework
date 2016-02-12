package jumpframework.createproject;

import jumpframework.crud.WriteController;
import jumpframework.crud.WriteDao;
import jumpframework.crud.WriteDaoImpl;
import jumpframework.crud.WriteModel;
import jumpframework.crud.WriteService;
import jumpframework.crud.WriteServiceImpl;
import jumpframework.crud.WriteView;

public class Writer {
	
	/*
	 * table's name
	 */
	private String tableName;
	
	/*
	 * type of file being created.
	 */
	private String type;
	
	/*
	 * Project's path only.
	 */
	private String path;
	
	/*
	 * MySql
	 */
	private MySQL mysql;
	
	/**
	 * create and write file.
	 * @param tableName = table's name
	 * @param type = type of file being created.
	 * @param path = Project's path only.
	 * @param mysql 
	 */
	public Writer(String tableName, String type, String path, MySQL mysql){
		this.tableName = tableName;
		this.type = type;
		this.path = path;
		this.mysql = mysql;
		init();
	}

	/*
	 * ???
	 */
	private void init() {
		switch (type.toLowerCase()) {
		case "model":
			createModel();
			break;
		case "dao":
			createDao();
			break;
		case "service":
			createService();
			break;
		case "repository":
			createRepository();
			break;
		case "controller":
			createController();
			break;
		case "view":
			createView();
			break;
		default:
			break;
		}
	}
	
	/*
	 * create a Model using crud.WriteModel 
	 */
	private void createModel() {
		
		WriteModel wm = new WriteModel(tableName, mysql.getFields(tableName), path);
		wm.createModel();
				
	}

	/*
	 * create a Dao using crud.WriteDao
	 */
	private void createDao() {	
		WriteDao wd = new WriteDao(tableName, mysql.getFields(tableName), path);
		wd.createDao();
	}
	
	/*
	 * create a Service using crud.WriteService
	 */
	private void createService() {	
		WriteService ws = new WriteService(tableName, mysql.getFields(tableName), path);
		ws.createService();
		
	}
	
	/*
	 * create Repository using crud.WriteServiceImpl,WriteDaoImpl
	 */
	private void createRepository() {	
		WriteServiceImpl ws = new WriteServiceImpl(tableName, mysql.getFields(tableName), path);
		WriteDaoImpl wd = new WriteDaoImpl(tableName, mysql.getFields(tableName), path);
		wd.createDaoImpl();
		ws.createServiceImpl();	
		
	}

	/*
	 * create a Controller using crud.WriteController
	 */
	private void createController() {
		WriteController wc = new WriteController(tableName, mysql.getFields(tableName), path);
		wc.createController();
		
	}

	/*
	 * create View using crud.WriteView
	 */
	private void createView() {		
		WriteView wv = new WriteView(tableName, mysql.getFields(tableName), path);
		wv.createView();
		
	}

	

}

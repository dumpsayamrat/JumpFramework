package jump.view.main;

public class test {
	public static void db(){
		Connector c  = new Connector("cdcol", "root", "");
		c.connection();
		c.showTableName();
	}
public static void main(String[] args) {
		db();
  }
}

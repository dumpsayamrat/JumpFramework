package jumpframework.createproject;

import java.io.File;
import java.io.InputStream;
import com.mysql.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	A();
    	
    }

	private static void A() {
		File f = new File("F:\\sts-workspace\\juweb\\src\\main\\java\\com\\spring\\model");
		File[] fl = f.listFiles();
		for(int i=0;i<fl.length;i++){
			String[] tmp = fl[i].getName().split("\\."); 
			System.out.println(tmp[0]);
		}
	}
    
    
}

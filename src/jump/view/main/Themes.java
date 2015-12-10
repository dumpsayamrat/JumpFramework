package jump.view.main;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.PopupMenu;



import java.awt.event.InputMethodListener;

import javax.swing.JButton;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

public class Themes extends ViewPart {

	private Label label;
	private Panel panel;
	private Button button;
	

	public Themes() {
		super();
	}

	public void setFocus() {
		label.setFocus();

	}

	public void dbcon() {
		Connector conn = new Connector("cdcol", "root", "");
		conn.connection();
		conn.showTableName();
			
	}
	
	public void createPartControl(Composite parent) {
		panel = new Panel();
		label = new Label(parent, 0);
		Shell shell = new Shell();
		Color color = new Color(shell.getDisplay(), new RGB(255, 255, 0));
		
		button = new Button(parent, 1);
		label.setBackground(color);
		
	

	}

}

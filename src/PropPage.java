import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;


public class PropPage extends PropertyPage {

	/**
	 * Create the property page.
	 */
	public PropPage() {
	}

	/**
	 * Create contents of the property page.
	 * @param parent
	 */
	@Override
	public Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		return container;
	}

}

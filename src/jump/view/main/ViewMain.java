package jump.view.main;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.ViewPart;

public class ViewMain extends ViewPart {

	public static final String ID = "jump.view.main.ViewMain"; //$NON-NLS-1$

	public ViewMain() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		class Themes extends EditorPart {

			public static final String ID = "jump.view.main.Themes"; //$NON-NLS-1$

			public Themes() {
			}

			/**
			 * Create contents of the editor part.
			 * @param parent
			 */
			@Override
			public void createPartControl(Composite parent) {
				Composite container = new Composite(parent, SWT.NONE);
				
				Button btnNewButton = new Button(container, SWT.NONE);
				btnNewButton.setBounds(147, 94, 75, 25);
				btnNewButton.setText("New Button");
				
				Button btnNewButton_1 = new Button(container, SWT.NONE);
				btnNewButton_1.setBounds(127, 168, 75, 25);
				btnNewButton_1.setText("New Button");
				
				Button btnNewButton_2 = new Button(container, SWT.NONE);
				btnNewButton_2.setBounds(319, 128, 75, 25);
				btnNewButton_2.setText("New Button");
				
				Button btnNewButton_3 = new Button(container, SWT.NONE);
				btnNewButton_3.setBounds(60, 34, 75, 25);
				btnNewButton_3.setText("New Button");

			}

			@Override
			public void setFocus() {
				// Set the focus
			}

			@Override
			public void doSave(IProgressMonitor monitor) {
				// Do the Save operation
			}

			@Override
			public void doSaveAs() {
				// Do the Save As operation
			}

			@Override
			public void init(IEditorSite site, IEditorInput input) throws PartInitException {
				// Initialize the editor part
			}

			@Override
			public boolean isDirty() {
				return false;
			}

			@Override
			public boolean isSaveAsAllowed() {
				return false;
			}
		}

	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}

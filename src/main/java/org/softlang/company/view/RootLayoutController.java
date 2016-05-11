package org.softlang.company.view;

import org.softlang.company.MainApp;
import org.softlang.company.model.Company;
import org.softlang.company.model.CompanyElement;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class RootLayoutController
{
	private final class CompanyElementTreeCellImpl extends TreeCell<CompanyElement>
	{
		private TextField edit;

		@Override
		protected void updateItem(CompanyElement item, boolean empty)
		{
			// TODO Auto-generated method stub
			super.updateItem(item, empty);
			setText(item == null ? "" : item.toString());
		}

		@Override
		public void startEdit()
		{
			// TODO Auto-generated method stub
			super.startEdit();

			makeTextField();

			setText(null);
			setGraphic(edit);
			edit.selectAll();
		}

		private void updateItemName(String newName)
		{
			CompanyElement item = getItem();
			if (item instanceof Company)
			{
				((Company) item).setName(newName);
			}
			if (item instanceof Department)
			{
				((Department) item).setName(newName);
			}
			if (item instanceof Employee)
			{
				((Employee) item).setName(newName);
			}
		}

		private void makeTextField()
		{
			if (edit == null)
			{
				edit = new TextField();
				edit.setOnKeyReleased(new EventHandler<KeyEvent>()
				{

					@Override
					public void handle(KeyEvent t)
					{
						if (t.getCode() == KeyCode.ENTER)
						{
							updateItemName(edit.getText());
							cancelEdit();
						}
						else if (t.getCode() == KeyCode.ESCAPE)
						{
							cancelEdit();
						}
					}

				});
			}
		}

		@Override
		public void commitEdit(CompanyElement newValue)
		{
			super.commitEdit(newValue);
			System.out.println("commitEdit");
		}

		@Override
		public void cancelEdit()
		{
			super.cancelEdit();
			System.out.println("cancelEdit");
			setText(getItem().toString());
			setGraphic(null);
		}

		@Override
		public String toString()
		{
			// TODO Auto-generated method stub
			return getItem().toString();
		}
	}

	@FXML
	private TreeView<CompanyElement> treeView;

	protected ListChangeListener<CompanyElement> treeRebuilder = (ListChangeListener<CompanyElement>) c -> rebuildTreeView();

	private MainApp mainApp;

	/**
	 * A <code>TreeItem</code> that can contain a <code>CompanyElement</code>
	 * and takes care of registering and unregistering Listeners in the Company
	 * structure.
	 */
	public class CompanyTreeItem extends TreeItem<CompanyElement>
	{
		CompanyElement element;

		public CompanyTreeItem(CompanyElement element)
		{
			super(element);
			this.setExpanded(true);
			element.addListChangeListener(treeRebuilder);

			element.getChildren().stream().map(child -> new CompanyTreeItem(child))
					.forEach(item -> this.getChildren().add(item));
		}

		@Override
		protected void finalize() throws Throwable
		{
			element.removeListChangeListener(treeRebuilder);
			super.finalize();
		}
	}

	/**
	 * Called by javafx on initialization.
	 */
	@FXML
	public void initialize()
	{
		System.out.println("RootLayoutController activated.");

		// Hide the empty root above the different companies.
		treeView.setShowRoot(false);

		// Set a SelectionModel so the TreeView selection is linked to the
		// Detail Panel
		treeView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mainApp.showDetails(newValue.getValue()));

		// Set a CellFactory to enable editing.
		treeView.setEditable(true);
		treeView.setCellFactory(new Callback<TreeView<CompanyElement>, TreeCell<CompanyElement>>()
		{
			@Override
			public TreeCell<CompanyElement> call(TreeView<CompanyElement> param)
			{
				return new CompanyElementTreeCellImpl();
			}
		});
	}

	/**
	 * Save the <code>MainApp</code> in this LayoutController to have access to
	 * the Company structures.
	 *
	 * @param app
	 */
	public void setMainApp(MainApp app)
	{
		this.mainApp = app;
		app.getCompanyData().addListener(treeRebuilder);
	}

	/**
	 * Recreates contents of the <code>TreeView</code> from the Company data.
	 */
	private void rebuildTreeView()
	{
		TreeItem<CompanyElement> root = new TreeItem<>();
		mainApp.getCompanyData().stream().forEach(company -> root.getChildren().add(new CompanyTreeItem(company)));
		treeView.setRoot(root);
	}

}

package org.softlang.company.view;

import org.softlang.company.MainApp;
import org.softlang.company.model.CompanyElement;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class RootLayoutController
{
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

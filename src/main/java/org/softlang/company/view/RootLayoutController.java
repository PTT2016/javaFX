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

	@FXML
	public void initialize()
	{
		System.out.println("RootLayoutController activated.");
		treeView.setShowRoot(false);
		treeView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mainApp.showDetails(newValue.getValue()));
	}

	public void setMainApp(MainApp app)
	{
		this.mainApp = app;
		app.getCompanyData().addListener(treeRebuilder);
	}

	private void rebuildTreeView()
	{
		TreeItem<CompanyElement> root = new TreeItem<>();
		mainApp.getCompanyData().stream().forEach(company -> root.getChildren().add(new CompanyTreeItem(company)));
		treeView.setRoot(root);
	}

}

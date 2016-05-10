package org.softlang.company.view;

import org.softlang.company.MainApp;
import org.softlang.company.model.Company;
import org.softlang.company.model.CompanyElement;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class RootLayoutController
{
	@FXML
	private TreeView<CompanyElement> treeView;

	private MainApp mainApp;

	public static class CompanyTreeItem extends TreeItem<CompanyElement>
	{
		CompanyElement element;

		public CompanyTreeItem(CompanyElement element)
		{
			super(element);
			this.setExpanded(true);

			element.getChildren().stream()//
					.map(child -> new CompanyTreeItem(child))//
					.forEach(item -> this.getChildren().add(item));
		}
	}

	@FXML
	public void initialize()
	{
		System.out.println("RootLayoutController activated.");
		treeView.setShowRoot(false);
	}

	public void setMainApp(MainApp app)
	{
		this.mainApp = app;
		app.getCompanyData().addListener((ListChangeListener<Company>) c -> rebuildTreeView());
	}

	private void rebuildTreeView()
	{
		TreeItem<CompanyElement> root = new TreeItem<>();
		mainApp.getCompanyData().stream()//
				.forEach(company -> root.getChildren().add(new CompanyTreeItem(company)));
		treeView.setRoot(root);
	}

}

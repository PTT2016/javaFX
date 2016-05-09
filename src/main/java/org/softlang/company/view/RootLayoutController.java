package org.softlang.company.view;

import java.util.stream.Collectors;

import org.softlang.company.MainApp;
import org.softlang.company.model.Company;
import org.softlang.company.model.CompanyElement;
import org.softlang.company.model.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
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
		ObservableList<Company> companies;

		public CompanyTreeItem(ObservableList<Company> companies)
		{
			this.companies = companies;
			companies.addListener((Change<? extends Company> c) -> getChildren());
			getChildren();
		}

		@Override
		public ObservableList<TreeItem<CompanyElement>> getChildren()
		{
			ObservableList<TreeItem<CompanyElement>> list = FXCollections.observableArrayList();
			companies.stream().map(company -> new TreeItem<CompanyElement>(company)).forEach(item -> list.add(item));
			list.stream()//
					.filter(item -> !(item.getValue() instanceof Employee))//
					.forEach(item -> item.getChildren()
							.addAll(item.getValue().getChildren().stream()//
									.map(value -> new TreeItem<>(value))//
									.collect(Collectors.toList())));
			super.getChildren().clear();
			super.getChildren().addAll(list);
			return super.getChildren();
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
		treeView.setRoot(new CompanyTreeItem(app.getCompanyData()));
		// app.getCompanyData().addListener((Change<? extends Company> change)
		// -> updateTreeView());
	}

	private void updateTreeView()
	{
		System.out.println("updating TreeView...");
		treeView.getRoot().getChildren();
		// TreeItem<CompanyElement> root = new TreeItem<>();
		//
		// if (mainApp == null)
		// {
		// System.out.println("updating TreeView... no changes");
		// return;
		// }
		//
		// for (Company c : mainApp.getCompanyData())
		// {
		// System.out.println(c.getName());
		// TreeItem<CompanyElement> companyNode = new
		// TreeItem<CompanyElement>(c);
		// root.getChildren().add(companyNode);
		// for (Department d : c.getDepartments())
		// {
		// //
		// }
		// }
		//
		// if (treeView != null)
		// treeView.setRoot(root);
	}

}

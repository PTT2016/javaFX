package org.softlang.company.view;

import org.softlang.company.model.Company;

import javafx.fxml.FXML;
import javafx.scene.control.TreeView;

public class RootLayoutController
{
	@FXML
	private TreeView<Company> treeView;

	@FXML
	public void initialize()
	{
		System.out.println("RootLayoutController activated.");
	}
}

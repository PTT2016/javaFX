package org.softlang.company.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public interface CompanyElement
{
	default ObservableList<CompanyElement> getChildren()
	{
		return FXCollections.observableArrayList();
	}

	default void addListChangeListener(ListChangeListener<CompanyElement> treeRebuilder)
	{
		return;
	}

	default void removeListChangeListener(ListChangeListener<CompanyElement> treeRebuilder)
	{
		return;
	}
}

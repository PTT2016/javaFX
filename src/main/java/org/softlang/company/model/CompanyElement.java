package org.softlang.company.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface CompanyElement
{
	default ObservableList<CompanyElement> getChildren()
	{
		return FXCollections.observableArrayList();
	}
}

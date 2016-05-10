package org.softlang.company.model;

import javafx.collections.ObservableList;

public interface CompanyElement
{
	ObservableList<CompanyElement> getChildren();
}

package org.softlang.company.model;

import java.util.HashSet;
import java.util.Set;

import org.softlang.company.feature.Cut;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public abstract class CompanyElement implements Cut
{
	public ObservableList<CompanyElement> getChildren()
	{
		return FXCollections.observableArrayList();
	}

	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		if (this.name == null || !this.name.equals(name))
		{
			this.name = name;
			notifyNameChange();
		}
	}

	private Set<NameChangeListener> listeners = new HashSet<>();

	public boolean addChangeListener(NameChangeListener l)
	{
		return listeners.add(l);
	}

	public boolean removeChangeListener(NameChangeListener l)
	{
		return listeners.remove(l);
	}

	public void removeAllChangeListeners()
	{
		listeners.clear();
	}

	public void notifyNameChange()
	{
		listeners.stream().forEach(l -> l.notifyNameChanged());
	}

	public void addListChangeListener(ListChangeListener<CompanyElement> treeRebuilder)
	{
		return;
	}

	public void removeListChangeListener(ListChangeListener<CompanyElement> treeRebuilder)
	{
		return;
	}
}

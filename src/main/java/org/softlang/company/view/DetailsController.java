package org.softlang.company.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetailsController
{

	@FXML
	private Label title;
	@FXML
	private Label nameLabel;
	@FXML
	private Label nameContent;
	@FXML
	private Label addressLabel;
	@FXML
	private Label addressContent;
	@FXML
	private Label salaryLabel;
	@FXML
	private Label salaryContent;

	public void setContentLayoutX(double layoutX)
	{
		if (layoutX < 0)
			layoutX = 60;
		nameContent.setLayoutX(layoutX);
		addressContent.setLayoutX(layoutX);
		salaryContent.setLayoutX(layoutX);
	}

	public void setTitle(String title)
	{
		this.title.setText(title);
	}

	public void setNameLabel(String label)
	{
		this.nameLabel.setText(label);
	}

	public void setName(String name)
	{
		this.nameContent.setText(name);
	}

	public void setAddressLabel(String label)
	{
		this.addressLabel.setText(label);
	}

	public void setAddress(String address)
	{
		this.addressContent.setText(address);
	}

	public void setSalaryLabel(String label)
	{
		this.salaryLabel.setText(label);
	}

	public void setSalary(String salary)
	{
		this.salaryContent.setText(salary);
	}
}

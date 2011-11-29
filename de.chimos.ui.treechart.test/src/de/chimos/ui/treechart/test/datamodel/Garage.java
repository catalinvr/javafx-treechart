package de.chimos.ui.treechart.test.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import de.chimos.property.annotation.Bean;
import de.chimos.property.annotation.PropertyHint;

@Bean(humanReadableName="Garage")
public class Garage {

	@PropertyHint(humanReadableName="Name")
	public String name;

	@PropertyHint(humanReadableName="Fahrzeuge")
	public ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
	
}

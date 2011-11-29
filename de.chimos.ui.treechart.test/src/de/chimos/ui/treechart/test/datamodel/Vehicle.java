package de.chimos.ui.treechart.test.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import de.chimos.property.annotation.Bean;
import de.chimos.property.annotation.PropertyHint;

@Bean(humanReadableName="Fahrzeug")
public class Vehicle
{

	@PropertyHint(humanReadableName="Identifikation")
	public String identification;
	
	@PropertyHint(humanReadableName="Anzahl der Räder")
	public Integer wheelCount;
	
	@PropertyHint(humanReadableName="Anzahl der Unfälle")
	public Integer numberOfAccidents;
	
	@PropertyHint(humanReadableName="Besitzer")
	public Person owner;
	
	@PropertyHint(humanReadableName="Fahrer")
	public ObservableList<Person> driver = FXCollections.observableArrayList();

	@Override
	public String toString() {
		return "Vehicle [identification=" + identification + ", wheelCount="
				+ wheelCount + ", numberOfAccidents=" + numberOfAccidents
				+ ", owner=" + owner + ", driver=" + driver + "]";
	}

}

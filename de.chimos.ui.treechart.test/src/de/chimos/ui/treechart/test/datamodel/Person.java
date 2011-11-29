package de.chimos.ui.treechart.test.datamodel;

import de.chimos.property.annotation.Bean;
import de.chimos.property.annotation.PropertyHint;

@Bean(humanReadableName="Person")
public class Person {

	@PropertyHint(humanReadableName="Vorname")
	public String firstName;

	@PropertyHint(humanReadableName="Nachname")
	public String lastName;

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}
	
}

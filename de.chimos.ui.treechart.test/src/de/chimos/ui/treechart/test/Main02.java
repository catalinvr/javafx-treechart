package de.chimos.ui.treechart.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import de.chimos.ui.treechart.objectanalyzer.ObjectAnalyzerControl;
import de.chimos.ui.treechart.test.datamodel.Garage;
import de.chimos.ui.treechart.test.datamodel.Person;
import de.chimos.ui.treechart.test.datamodel.Vehicle;

public class Main02 extends Application
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
        Application.launch(args);
	}

    @Override
    public void start(Stage primaryStage)
    {
        ObjectAnalyzerControl objectAnalyzerControl = new ObjectAnalyzerControl();
        objectAnalyzerControl.yAxisSpacingProperty().set(65.0);
        
        objectAnalyzerControl.setData(createData());
        
        ScrollPane root = new ScrollPane();
        root.setContent(objectAnalyzerControl);
        
        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add(getClass().getResource("style02.css").toString());
        
        primaryStage.setTitle("Object Analyzer Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static Object createData()
    {
    	Garage garage = null;
    	Vehicle vehicle = null;
    	Person person = null;
    	
    	// ************ Garage
    	
    	garage = new Garage();
    	
    	// ******** Vehicle 1
    	
    	vehicle = new Vehicle();
    	vehicle.identification = "ABC0001";
    	vehicle.wheelCount = 4;
    	vehicle.numberOfAccidents = 0;
    	
    	person = new Person();
    	person.firstName = "Max";
    	person.lastName = "Mustermann";
    	
    	vehicle.owner = person;
    	vehicle.driver.add(person);
    	
    	person = new Person();
    	person.firstName = "Marlene";
    	person.lastName = "Meier";

    	vehicle.driver.add(person);
    	
    	garage.vehicles.add(vehicle);

    	// ******** Vehicle 2
    	
    	vehicle = new Vehicle();
    	vehicle.identification = "ABC0002";
    	vehicle.wheelCount = 2;
    	vehicle.numberOfAccidents = 1;

    	person = new Person();
    	person.firstName = "Max";
    	person.lastName = "Mustermann";
    	
    	vehicle.owner = person;
    	vehicle.driver.add(person);
    	
    	person = new Person();
    	person.firstName = "Marlene";
    	person.lastName = "Meier";

    	vehicle.driver.add(person);
    	
    	garage.vehicles.add(vehicle);
    	
    	return garage;
    }
}

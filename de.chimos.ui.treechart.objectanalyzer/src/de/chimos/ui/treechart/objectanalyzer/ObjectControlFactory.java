package de.chimos.ui.treechart.objectanalyzer;

import java.util.Arrays;
import java.util.Collection;

import de.chimos.ui.treechart.layout.NodePosition;
import de.chimos.ui.treechart.objectanalyzer.objectcontrols.ObjectBeanControl;
import de.chimos.ui.treechart.objectanalyzer.objectcontrols.ObjectCollectionControl;

import javafx.scene.Node;

class ObjectControlFactory {
	
	public static Node create(Object data, NodePosition position, ObjectAnalyzerInterface objectAnalyzer)
	{
		if
		(
			data == null
			|| data instanceof java.lang.String
			|| data instanceof java.lang.Boolean
			|| data instanceof java.lang.Float
			|| data instanceof java.lang.Double
			|| data instanceof java.lang.Integer
			|| data instanceof java.lang.Long
			|| data instanceof java.lang.Class<?>
		)
		{
			return null;
		}
		
		if(data instanceof Collection<?>)
		{
			return new ObjectCollectionControl((Collection<?>) data, position, objectAnalyzer);
		}
		else if(data.getClass().isArray() == true)
		{
			return new ObjectCollectionControl(Arrays.asList((Object[])data), position, objectAnalyzer);
		}
		else
		{
			return new ObjectBeanControl(data, position, objectAnalyzer);
		}
	}

}

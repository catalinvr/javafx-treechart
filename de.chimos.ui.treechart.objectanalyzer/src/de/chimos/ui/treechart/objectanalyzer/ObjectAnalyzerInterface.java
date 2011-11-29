package de.chimos.ui.treechart.objectanalyzer;

import de.chimos.ui.treechart.layout.NodePosition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;

public interface ObjectAnalyzerInterface
{
	public ObjectProperty<Object> dataProperty();
	public Object getData();
	public void setData(Object value);

	public DoubleProperty yAxisSpacingProperty();
	public DoubleProperty xAxisSpacingProperty();
	public DoubleProperty lineSpacingProperty();
	public BooleanProperty showLinesProperty();
	
	boolean display(NodePosition position, Object data);
	void clear(NodePosition position);
}

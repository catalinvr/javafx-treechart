package de.chimos.ui.treechart.objectanalyzer;

import de.chimos.ui.treechart.layout.NodePosition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Control;

public class ObjectAnalyzerControl extends Control implements ObjectAnalyzerInterface
{
	public ObjectAnalyzerControl()
	{
		setSkin(new ObjectAnalyzerSkin(this));
	}
	
	protected ObjectAnalyzerSkin getMySkin()
	{
		return (ObjectAnalyzerSkin)getSkin();
	}

	public DoubleProperty yAxisSpacingProperty() { return getMySkin().yAxisSpacingProperty(); }
	public DoubleProperty xAxisSpacingProperty() { return getMySkin().xAxisSpacingProperty(); }
	public DoubleProperty lineSpacingProperty() { return getMySkin().lineSpacingProperty(); }
	public BooleanProperty showLinesProperty() { return getMySkin().showLinesProperty(); }
	
	@Override
	public ObjectProperty<Object> dataProperty() {
		return getMySkin().dataProperty();
	}

	@Override
	public Object getData() {
		return getMySkin().getData();
	}

	@Override
	public void setData(Object value) {
		getMySkin().setData(value);
	}

	@Override
	public boolean display(NodePosition position, Object data) {
		return getMySkin().display(position, data);
	}

	@Override
	public void clear(NodePosition position) {
		getMySkin().clear(position);
	}
}

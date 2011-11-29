package de.chimos.ui.treechart.objectanalyzer;

import java.util.Set;

import de.chimos.property.annotation.PropertyHint;
import de.chimos.ui.treechart.layout.NodePosition;
import de.chimos.ui.treechart.layout.TreePane;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Skin;

public class ObjectAnalyzerSkin implements Skin<ObjectAnalyzerControl>, ObjectAnalyzerInterface {

	@PropertyHint public Object data;
	
	public ObjectProperty<Object> dataProperty() { return null; }
	public Object getData() { return this.data; }
	public void setData(Object data) { this.data = data; }
	
	private final ObjectAnalyzerControl _control;
	
	private final TreePane _treePane = new TreePane();
	
	public ObjectAnalyzerSkin(ObjectAnalyzerControl control)
	{
		this._control = control;
		
		dataProperty().addListener(new ChangeListener<Object>()
			{
				@Override
				public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue)
				{
					clear(NodePosition.ROOT);
					
					if(newValue != null)
					{
						display(NodePosition.ROOT, newValue);
					}
				}
			}
		);
	}

	public DoubleProperty yAxisSpacingProperty() { return _treePane.yAxisSpacingProperty(); }
	public DoubleProperty xAxisSpacingProperty() { return _treePane.xAxisSpacingProperty(); }
	public DoubleProperty lineSpacingProperty() { return _treePane.lineSpacingProperty(); }
	public BooleanProperty showLinesProperty() { return _treePane.showLinesProperty(); }
	
	@Override
	public void dispose() { }

	@Override
	public Node getNode() {
		return _treePane;
	}

	@Override
	public ObjectAnalyzerControl getSkinnable() {
		return this._control;
	}
	
	@Override
	public boolean display(NodePosition position, Object data) {
		Node node = ObjectControlFactory.create(data, position, this);
		if(node != null)
		{
			_treePane.addChild(node, position);
			return true;
		}
		return false;
	}
	
	@Override
	public void clear(NodePosition position) {
		
		Set<Node> childNodes = _treePane.getNodesOfParent(position);
		
		if(childNodes != null)
		{
			for(Node childNode : childNodes)
			{
				clear(_treePane.getPosition(childNode));
			}
		}
		
		Node node = _treePane.getNode(position);

		if(node != null)
		{
			_treePane.removeChild(node);
		}
	}

}

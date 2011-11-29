package de.chimos.ui.treechart.objectanalyzer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.chimos.property.annotation.PropertyHint;
import de.chimos.property.util.BeanUtil;
import de.chimos.property.util.PropertyWrapper;
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
					
					if(getData() != null)
					{
						display(NodePosition.ROOT, getData(), true);
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
	public boolean display(NodePosition position, Object data, boolean modeDefault) {
		Node node = ObjectControlFactory.create(data, position, this, modeDefault);
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
			for(Object childNode : childNodes.toArray())
			{
				clear(_treePane.getPosition((Node)childNode));
			}
		}
		
		Node node = _treePane.getNode(position);

		if(node != null)
		{
			_treePane.removeChild(node);
		}
	}

	
	public void displayAll(boolean modeDefault)
	{
		class OpenTask
		{
			public final Object data;
			public final NodePosition position;
			public OpenTask(Object data, NodePosition position) { this.data = data; this.position = position; }
		}
		
		clear(NodePosition.ROOT);
		
		if(getData() != null)
		{
			Set<Object> openedData = new HashSet<Object>();
			
			List<OpenTask> openTasks = new ArrayList<>();
			openTasks.add(new OpenTask(getData(), NodePosition.ROOT));
			
			while(openTasks.size() > 0)
			{
				OpenTask currentTask = openTasks.get(0);
				openTasks.remove(0);
				
				if(openedData.contains(currentTask.data) == true)
				{
					continue;
				}
				
				if(display(currentTask.position, currentTask.data, modeDefault) == false)
				{
					continue;
				}
				
				if(currentTask.data.getClass().isArray())
				{
					int childCounter = 0;
					for(Object d : (Object[])currentTask.data)
					{
						openTasks.add(new OpenTask(d, currentTask.position.getChild(childCounter++)));
					}
				}
				else if(currentTask.data instanceof Collection<?>)
				{
					int childCounter = 0;
					for(Object d : (Collection<?>)currentTask.data)
					{
						openTasks.add(new OpenTask(d, currentTask.position.getChild(childCounter++)));
					}
				}
				else
				{
					int childCounter = 0;
					for(PropertyWrapper<Object> property : BeanUtil.getProperties(currentTask.data))
					{
						openTasks.add(new OpenTask(property.getValue(), currentTask.position.getChild(childCounter++)));
					}
				}
			}
		}
	}

}

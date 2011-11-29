package de.chimos.ui.treechart.objectanalyzer.objectcontrols;

import de.chimos.property.util.BeanUtil;
import de.chimos.property.util.PropertyWrapper;
import de.chimos.ui.treechart.layout.NodePosition;
import de.chimos.ui.treechart.objectanalyzer.ObjectAnalyzerInterface;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ObjectBeanControl extends TableView<PropertyWrapper<Object>>
{
	public ObjectBeanControl(final Object data, final NodePosition position, final ObjectAnalyzerInterface objectAnalyzer)
	{
		TableColumn<PropertyWrapper<Object>,Object> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<PropertyWrapper<Object>,Object>("name"));
		nameColumn.setSortable(true);
		nameColumn.setPrefWidth(100);
		nameColumn.setEditable(false);

		TableColumn<PropertyWrapper<Object>,Object> humanReadableNameColumn = new TableColumn<>("Full Name");
		humanReadableNameColumn.setCellValueFactory(new PropertyValueFactory<PropertyWrapper<Object>,Object>("humanReadableName"));
		humanReadableNameColumn.setSortable(true);
		humanReadableNameColumn.setPrefWidth(150);
		humanReadableNameColumn.setEditable(false);

		TableColumn<PropertyWrapper<Object>,Object> valueColumn = new TableColumn<>("Value");
		valueColumn.setCellValueFactory(new PropertyValueFactory<PropertyWrapper<Object>,Object>("value"));
		valueColumn.setSortable(false);
		valueColumn.setPrefWidth(150);
		valueColumn.setEditable(false);

		TableColumn<PropertyWrapper<Object>,Class<?>> dataTypeColumn = new TableColumn<>("Data Type");
		dataTypeColumn.setCellValueFactory(new PropertyValueFactory<PropertyWrapper<Object>,Class<?>>("dataType"));
		dataTypeColumn.setSortable(true);
		dataTypeColumn.setPrefWidth(100);
		dataTypeColumn.setEditable(false);

		TableColumn<PropertyWrapper<Object>,Object> typeColumn = new TableColumn<>("Property Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<PropertyWrapper<Object>,Object>("type"));
		typeColumn.setSortable(true);
		typeColumn.setPrefWidth(80);
		typeColumn.setEditable(false);
		
		getColumns().add(nameColumn);
		getColumns().add(humanReadableNameColumn);
		getColumns().add(valueColumn);
		getColumns().add(dataTypeColumn);
		getColumns().add(typeColumn);
		
		setItems(FXCollections.observableArrayList(BeanUtil.getProperties(data)));
		
		setPrefSize(600, 200);
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount() > 1)
				{
					PropertyWrapper<Object> property = getSelectionModel().getSelectedItem();
					if(property.getValue() != null)
					{
						objectAnalyzer.display(position.getChild(childCount++), property.getValue());
					}
				}
			}
		});
	}
	
	private int childCount = 0;
}

package de.chimos.ui.treechart.objectanalyzer.objectcontrols;

import java.util.Collection;

import de.chimos.ui.treechart.layout.NodePosition;
import de.chimos.ui.treechart.objectanalyzer.ObjectAnalyzerInterface;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ObjectCollectionControl extends TableView<Object>
{
	@SuppressWarnings("unchecked")
	public ObjectCollectionControl(final Collection<?> data, final NodePosition position, final ObjectAnalyzerInterface objectAnalyzer)
	{
		TableColumn<Object,Object> itemsColumn = new TableColumn<>("Items");
		
		itemsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object,Object>, ObservableValue<Object>>() {
			@Override
			public ObservableValue<Object> call(CellDataFeatures<Object, Object> param) {
				return new ReadOnlyObjectWrapper<>(param.getValue());
			}
		});
		
		itemsColumn.setSortable(false);
		itemsColumn.setPrefWidth(580);
		itemsColumn.setEditable(false);

		getColumns().add(itemsColumn);

		if(data instanceof ObservableList<?>)
		{
			setItems((ObservableList<Object>)data);
		}
		else
		{
			setItems(FXCollections.observableArrayList(data));
		}
		
		setPrefSize(600, 200);
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount() > 1)
				{
					Object entryData = getSelectionModel().getSelectedItem();
					if(entryData != null)
					{
						objectAnalyzer.display(position.getChild(childCount++), entryData, true);
					}
				}
			}
		});
	}
	
	private int childCount = 0;
}

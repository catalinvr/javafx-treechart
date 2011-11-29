package de.chimos.ui.treechart.objectanalyzer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import de.chimos.ui.treechart.layout.NodePosition;

public class ObjectControlContainer extends BorderPane {

	private final Node _dataNode;
	private final ToolBar _toolBarNode;
	
	private final ImageView _smallImageView;
	
	private final NodePosition _position;
	private final ObjectAnalyzerInterface _objectAnalyzer;
	
	public ObjectControlContainer(Node dataNode, NodePosition position, ObjectAnalyzerInterface objectAnalyzer, boolean modeDefault)
	{
		this._dataNode = dataNode;
		this._position = position;
		this._objectAnalyzer = objectAnalyzer;
		
		_toolBarNode = new ToolBar();
		
		Button buttonSmall = new Button("Small");
		buttonSmall.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setModeSmall();
			}
		});
		
		_toolBarNode.getItems().add(buttonSmall);
		
		Button buttonClose = new Button("Close");
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(_position.getLevel() != 0)
				{
					_objectAnalyzer.clear(_position);
				}
			}
		});
		
		_toolBarNode.getItems().add(buttonClose);
		
		_smallImageView = new ImageView(new Image(getClass().getResourceAsStream("small-icon.png")));
		_smallImageView.setCursor(Cursor.HAND);
		_smallImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setModeDefault();
			}
		});
		
		if(modeDefault == true)
		{
			setModeDefault();
		}
		else
		{
			setModeSmall();
		}
	}
	
	public void setModeDefault()
	{
		setTop(_toolBarNode);
		setCenter(_dataNode);
	}
	
	public void setModeSmall()
	{
		setTop(null);
		setCenter(_smallImageView);
	}
	
}

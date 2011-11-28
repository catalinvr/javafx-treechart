/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.chimos.ui.treechart.test;

import java.util.Random;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import de.chimos.ui.treechart.layout.NodePosition;
import de.chimos.ui.treechart.layout.TreePane;


/**
 *
 * @author Niklas Hofmann, Gerrit Linnemann
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        TreePane treePane = new TreePane();
        treePane.yAxisSpacingProperty().set(10.0);
        
        treePane.addChild(new Label("Root Node"), NodePosition.ROOT);
        generateTreeItems(treePane, NodePosition.ROOT, 3, 7);
        
        ScrollPane root = new ScrollPane();
        root.setContent(treePane);
        
        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add(getClass().getResource("style.css").toString());
        
        primaryStage.setTitle("TreeChart Layout Example");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        TimelineBuilder.create()
        	.keyFrames
    		(
    			new KeyFrame
    			(
    				Duration.seconds(5),
    				new KeyValue
    				(
    					treePane.yAxisSpacingProperty(),
    					65.0,
    					Interpolator.LINEAR
    				)
    			)
    		)
    		.cycleCount(1)
        	.build()
        	.play();
    }
    
    protected static Random random = new Random(); 
    
    protected static void generateTreeItems(TreePane treePane, NodePosition parentPosition, int maxChildren, int maxLevel)
    {
    	if(parentPosition.getLevel() >= maxLevel)
    	{
    		return;
    	}
    	
    	int maxChildrenBias1 = parentPosition.getLevel();
    	int maxChildrenBias2 = maxChildren - maxChildrenBias1;
    	
    	for(int i = 0, j = random.nextInt(maxChildrenBias1+1)+maxChildrenBias2; i < j; ++i)
    	{
    		NodePosition position = parentPosition.getChild(i);
    		
    		String labelText = "Node ";
    		
    		for(int pathElement : position.getPath())
    		{
    			labelText += "." + pathElement;
    		}
    		
    		if(random.nextInt()%3==0)
    		{
    			labelText += "\nYes!";
    		}
    		
    		if(random.nextInt()%3==0)
    		{
    			labelText += "\nNo!";
    		}
    		
    		final Label label = new Label(labelText);
    		label.setTextAlignment(TextAlignment.CENTER);
    		label.setOnMouseEntered(new EventHandler<MouseEvent>()
    			{
    				private RotateTransition rotation = RotateTransitionBuilder.create()
    						.byAngle(180).autoReverse(true).cycleCount(2)
    						.duration(Duration.seconds(1))
    						.node(label)
    						.build();
    			
					@Override
					public void handle(MouseEvent event)
					{
						rotation.play();
					}
				});

    		treePane.addChild(label, position);
    		
    		generateTreeItems(treePane, position, maxChildren, maxLevel);
    	}
    }
}

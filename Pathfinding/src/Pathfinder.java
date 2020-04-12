import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.*;

import javax.xml.transform.Source;


public class Pathfinder extends Application {
	
	static final int ROWS = 50;
	static final int COLS = 50;
	
	ArrayList<SquareNode> squaresQueue = new ArrayList<SquareNode>();
	ArrayList<SquareNode> shortestPathSet = new ArrayList<SquareNode>();
	
	SquareNode[][] squaresArray = new SquareNode[ROWS][COLS];
	
	GridPane grid = new GridPane();
	
	public static void main(String[] args)
	{
		Application.launch(args);	
	}
	
	
	public void start(Stage primaryStage)
	{
		VBox vbox = new VBox();
		HBox hbox = new HBox();
		Button button1 = new Button("Dijkstra!");
		Button button2 = new Button("Set Start Node");
		Button button3 = new Button("Set End Node");
		
		primaryStage.setTitle("Pathfinder");
		
		fillSquaresArray();	
		
		button3.setOnAction((event)->
		{
			for(SquareNode n: squaresQueue)
			{
				n.setOnMouseClicked((eventHandler)->
				{
					n.setEndNode();
				});

			}
		});
		

		button2.setOnAction((event)->
		{
			for(SquareNode n: squaresQueue)
			{
				n.setOnMouseClicked((eventHandler)->
				{
					n.setFill(Color.BLACK);
					n.setStartNode();
					squaresQueue.sort((s1, s2) -> s1.Distance.compareTo(s2.Distance));
				});

			}
		});
	

		
		

		button1.setOnAction((event) -> 
		{
			dijkstra(squaresQueue.get(0));

		});
		

		
		
		hbox.getChildren().addAll(button1, button2, button3);
		vbox.getChildren().addAll(hbox, grid);

		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.centerOnScreen();
		primaryStage.show();
		
	}
	
	
	public void fillSquaresArray() // fill squares array and add to squares queue
	{

		for(int r = 0; r < ROWS; r++)
		{
			for(int c = 0; c < COLS; c++)
			{
				SquareNode s = new SquareNode();
				s.Distance = Integer.MAX_VALUE;
				squaresArray[r][c] = s;
				squaresQueue.add(s);
				grid.add(s, c, r);
				s.indR = r;
				s.indC = c;
			}
		}
		squaresQueue.sort((s1, s2) -> s1.Distance.compareTo(s2.Distance));
	}
	
	
	
	// create a function that assigns all 4 adjacent nodes on call for a certain node and set distances
	public void assignAdjacentSquares(SquareNode source)
	{
		
		if(source.indR > 0 )
		{
			source.Up = squaresArray[source.indR-1][source.indC];	  // assigns distances and directional nodes in all directions
			source.Up.Distance = source.Distance + source.Up.Weight;
			source.Up.Previous = squaresArray[source.indR][source.indC];
		}
		

		if(source.indR < ROWS - 1)
		{
			source.Down = squaresArray[source.indR+1][source.indC];
			source.Down.Distance = source.Distance + source.Down.Weight;
			source.Down.Previous = squaresArray[source.indR][source.indC];
		}
		

		if(source.indC < COLS -1)
		{
			source.Right = squaresArray[source.indR][source.indC+1];
			source.Right.Distance = source.Distance + source.Right.Weight;
			source.Right.Previous = squaresArray[source.indR][source.indC];
		}
		
		if(source.indC > 0)
		{
			source.Left = squaresArray[source.indR][source.indC-1];
			source.Left.Distance = source.Distance + source.Left.Weight;
			source.Left.Previous = squaresArray[source.indR][source.indC];
		}		

	
		source.setVisited(); // assigns color depending on what kind of node it is and if it has been visited.
	
	}
	
	public void dijkstra(SquareNode source)
	{
		if(source.isEnd == false)
		{
			assignAdjacentSquares(squaresQueue.get(0));									// assign the nodes around the first square in the queue
			shortestPathSet.add(squaresQueue.get(0));									// add it to a second set
			squaresQueue.remove(0);														// remove that same square from the first set for condition to run condition on the next square in the queue
			squaresQueue.sort((s1, s2) -> s1.Distance.compareTo(s2.Distance));			// sort the set to run around the current minimum square
			
			if(squaresQueue.size() > 0)
			{
				dijkstra(squaresQueue.get(0));
			}
		}
	}
	
	public void drawFastestPath(SquareNode source)
	{

			
	}
	

}


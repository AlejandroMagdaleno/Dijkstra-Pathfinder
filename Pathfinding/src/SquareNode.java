import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class SquareNode extends Rectangle
{
	Integer Distance = 0;
	int Weight = 1;
	
	int indR, indC;
	SquareNode Up;
	SquareNode Down;
	SquareNode Left;
	SquareNode Right;
	SquareNode Previous;
	
	boolean Visited = false;
	boolean isEnd = false;
	boolean isStart = false;
	boolean isWall = false;
	boolean runPathHere = false;
	

	SquareNode()
	{
		this.setFill(Color.WHITE);
		this.setHeight(20);
		this.setWidth(20);	
		this.setStroke(Color.BLACK);	
	}
	
	public void setStartNode()
	{
		this.Distance = 0;
		this.isStart = true;
	}
	public void setEndNode()
	{
		this.isEnd = true;
		this.setFill(Color.DODGERBLUE);
	}
		
	public void setVisited()
	{
	
		if(this.Visited == false)
		{
			this.Visited = true;
			this.setFill(Color.DARKSEAGREEN);
			
			if(this.isStart)
			{
				this.setFill(Color.BLACK);
			}
			if(this.isWall)
			{
				this.setFill(Color.BLACK);
			}
		}
	}	
	
	
	


}

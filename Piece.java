package mvc;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public  class Piece 
{
	protected boolean[][] forme;
	protected int x_center;
	protected int y_center;
	int x;
	int y;
	
	public Piece(int x, int y)
	{
		this.x = x;
		this.y = y;
		forme = new boolean[5][5];
		forme[1][0] = true;
		forme[0][1] = true;
		forme[1][2] = true;
		forme[1][1] = true;
		
		x_center = 2;
		y_center = 2;
	}
	
	public int getXCenter()
	{
		return x_center;
	}
	
	
	public void setXCenter(int x)
	{
		x_center = x;
	}
	
	public int getYCenter()
	{
		return y_center;
	}
	
	public void setYCenter(int y)
	{
		y_center = y;
	}
	
	public boolean[][] getForme()
	{
		return forme;
	}
	
	public void down()
	{
		y++;
	}
	
	public void left()
	{
		x--;
	}
	
	public void right()
	{
		x++;
	}
	
	public void up()
	{
		y--;
	}
	
	public void rotate()
	{
		final int M = forme.length;
		final int N = forme[0].length;
		
		boolean[][] formeTournee = new boolean[N][M];
		for (int r = 0; r < M; r++) 
		{
	        for (int c = 0; c < N; c++) 
	        {
	            formeTournee[c][M-1-r] = forme[r][c];
	        }
	    }
		
		forme = formeTournee;
	}
	
}



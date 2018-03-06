/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.Observable;

/**
 *
 * @author fred
 */
public class Modele extends Observable {

    double lastValue;
    boolean err = false;
    Piece p;
    private int [][] plateau;
    int xSize = 10;
    int ySize = 10;
    int t=0;
    
    public int [][] getPlateau()
    {
    	int[][] plat = new int[xSize][ySize];
    	boolean[][] forme = p.getForme();
		int width = forme.length;
		int heigth = forme[0].length;
		int xcenter = p.getXCenter();
		int ycenter = p.getYCenter();
		System.out.println(p.y);
		if(width%2 != 0)
		{
			for(int i = -(width/2);i<=(width/2);i++)
			{
				for(int j = -(heigth/2);j<=(heigth/2);j++)
				{
					if (forme[xcenter+i][ycenter+j])
						plat[p.x+i][p.y+j] = 1;
					
				}
			}
		}
		return plat;
    }
    
    public Modele(int x, int y)
    {
    	p = new Piece(2,2);
    	xSize=x;
    	ySize=y;
    	plateau = new int[x][y];
    	for(int i=0;i<x;i++)
    	{
    		for(int j = 0; j<y; j++)
    		{
    			if(j==1)
    				plateau[i][j]=1;
    			else
    				plateau[i][j]=0;
    			
    		}
    	}
    	setChanged();
    	notifyObservers();
    }
        
    public boolean getErr() {
        return err;
    }
    
    public void add(int x, int y)
    {
    	System.out.println(x);
    	plateau[x][y]++;
    	setChanged();
    	p.up();
    	notifyObservers();
    	System.out.println(plateau[x][y]);
    }
    
    public void dec(int x, int y)
    {
    	p.down();
    	setChanged();
    	notifyObservers();
    }
    
    public void down(){
    	p.down();
    	setChanged();
    	notifyObservers();
    }
    
    public void up() {
    	p.up();
    	setChanged();
    	notifyObservers();
    }
    
    public void left() {
    	p.left();
    	setChanged();
    	notifyObservers();
    }
    
    public void right() {
    	p.right();
    	setChanged();
    	notifyObservers();
    }
    
    public double getValue() {
        return lastValue;
    }

}
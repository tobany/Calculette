
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;


import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author freder
 */
public class VueControleur extends Application {
    
    // modèle : ce qui réalise le calcule de l'expression
    Modele m;
    // affiche la saisie et le résultat
    Text affichage;
    
    @Override
    public void start(Stage primaryStage) {
        
        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele(10,10);
        
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        Rectangle [][] tab = new Rectangle[10][10];
        tab[0][0]=new Rectangle();
        
        int column = 0;
        int row = 0;
        m.addObserver(new Observer() {
            
            @Override
            public void update(Observable o, Object arg) {
            	int [][] pla = m.getPlateau();
            	for(int x=0;x<m.xSize;x++)
            	{
            		for(int y = 0; y<m.ySize; y++)
            		{
	            		switch((pla)[x][y]){
	            		case 1 : 	tab[x][y].setFill(Color.BLUE);  
	            					break;
	            		case 2 : 	tab[x][y].setFill(Color.RED); 
	            					break;
	            		case 3 : 	tab[x][y].setFill(Color.GREEN); 
	            					break;
	            		default : 	tab[x][y].setFill(Color.BLACK);  
	            					break;
	            		}
            		}
            	}
            }
        });
        
        
        for(int i=0; i < m.xSize*m.ySize; i++)
        {
        	
        	Rectangle r = new Rectangle();
        	r.setWidth(50);
        	r.setHeight(50);
        	tab[column][row] = r;
        	gPane.add(r, column++, row);
        	//tab[column][row] = r;
    		if (column == m.xSize) 
    		{
    			column = 0;
    			row++;
    		}
            r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle(MouseEvent event) {
                	if (event.getButton() == MouseButton.PRIMARY)
                		m.add(((int)event.getSceneX())/50, ((int)event.getSceneY())/50);
                	else
                		m.dec(((int)event.getSceneX())/50, ((int)event.getSceneY())/50);
                }
                
            });
        }
        
        gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);
        
        Scene scene = new Scene(border, Color.LIGHTBLUE);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	
        	public void handle(KeyEvent ke) {
        		if(ke.getCode() == KeyCode.DOWN)
        			m.down();
        		if(ke.getCode() == KeyCode.UP)
        			m.up();
        		if(ke.getCode() == KeyCode.LEFT)
        			m.left();
        		if(ke.getCode() == KeyCode.RIGHT)
        			m.right();
        	}
        }
        );
        primaryStage.setTitle("Calc FX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

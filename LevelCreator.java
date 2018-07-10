import java.awt.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.Toolkit;

public class LevelCreator {

  //GLOBAL VARIABLES
  public int map[][];
  public int brickWidth;
  public int brickHeight;
  
  //LEVEL CREATOR CONSTRUCTOR
  public LevelCreator(int row, int col) {
    map = new int[row][col];
    for (int i = 0; i < map.length; i++) {
      for(int j = 0; j < map[0].length; j++) {
        map[i][j] = 1;
      }
    }
    
    //DETERMINES THE SIZE OF THE BRICKS
    brickWidth = 540/col;
    brickHeight = 150/row;
    
  }

  //DRAWS THE BRICKS ON THE SCREEN
  public void drawBricks(Graphics2D g) {
     for (int i = 0; i < map.length; i++) {
      for(int j = 0; j < map[0].length; j++) {
       //IF THERE IS A BRICK THERE FILLS IT WITH BLUE AND THEN ADDS THE GREY OUTLINE TO SEPERATE THE BRICKS
        if(map[i][j] > 0) {
          g.setColor(Color.BLUE);
          g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
          

          g.setColor(Color.lightGray);
          g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
        }
      }
    }
  }
  
  //SETS BRICKS VALUE TO IDENTIFY THEIR STATE
  public void setBrickValue(int value, int row, int col) {
    //DETERMINES STATE (BROKEN OR NOT)
    map[row][col] = value;
  }
  
}
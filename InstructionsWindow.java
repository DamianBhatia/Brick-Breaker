import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.lang.Math;

public class InstructionsWindow extends JPanel {

    //BACKGROUND IMAGE
    Image background = Toolkit.getDefaultToolkit().getImage("MenuScreen.png");
  
    //INSTRUCTIONS IMAGE
    Image instructions = Toolkit.getDefaultToolkit().getImage("instructions.png");
    
    //ARROW KEYS IMAGE
    Image arrows = Toolkit.getDefaultToolkit().getImage("arrowKeys.png");
  
    public void paint(Graphics g) {
      //DRAWS THE IMAGES AND INSTRUCTIONS ON SCREEN
    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    g.drawImage(instructions, 280, 50, 150, 70, this);
    g.drawImage(arrows, 280, 130, 150, 150, this);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
    g.setColor(Color.WHITE);
    g.drawString("The game is very simple, use the left and right arrows", 50, 300);
    g.drawString("to move your paddle side to side. The aim of the game is", 50, 320); 
    g.drawString("to break all of the bricks that you see on screen by", 50, 340);
    g.drawString("hitting them with the ball. Each brick is worth 5 points", 50, 360);
    g.drawString("for a total of 105 points. If the you miss the ball then", 50, 380); 
    g.drawString("it is game over, but don't worry because if you want to", 50, 400);
    g.drawString("play again you can press the enter key and a new game will start.", 50, 420);
    g.drawString("Enjoy! Press anywhere to begin", 215, 455);
    g.dispose();
  }
  
}
/* Damian Bhatia June 1, 2018
 * This program is a rendition of the popular retro game Brick Breaker.
 * First a loading screen is loaded up with the choice to start playing or to view the instructions.
 * If the instructions button is clicked then the user is showed the instructions and then prompted
 * to proceed and play. If the play button is pressed then the player is taken to the actual gameplay.
 * Each brick is worth points and if all the bricks are broken then the player wins the game and has
 * the option to play again. If the ball misses the paddle and goes off the screen the game is over and
 * the player has the choice to play again.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

public class Main extends JComponent {
  
  //HELPS SET BACKGROUND TO IMAGE WITH TITLE
  private Image background;
  private Image title;
  //CONSTRUCTOR
    public Main(Image background, Image title) {
        this.background = background;
        this.title = title;
    }
    //PAINTS THE IMAGES
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(title, 35, 85, this);
    }
    
  public static void main(String args[]) {
   //MAIN MENU FRAME
    JFrame frame = new JFrame();
    
    //BUTTONS
    Image instructionsButton = Toolkit.getDefaultToolkit().getImage("instructions.png");
    JButton instructions = new JButton(new ImageIcon(instructionsButton));
    
    Image playButton = Toolkit.getDefaultToolkit().getImage("play.png");
    JButton play = new JButton(new ImageIcon(playButton));
    
    //BACKGROUND IMAGE
    Image background = Toolkit.getDefaultToolkit().getImage("MenuScreen.png");
    
    //TITLE IMAGE
    Image title = Toolkit.getDefaultToolkit().getImage("Title.png");
  
    
    //CREATES JFRAME
    frame.setBounds(10, 10, 700, 600);
    frame.setTitle("Brick Breaker");
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);
    frame.setLocationRelativeTo(null);
    //SETS BACKGROUND
    frame.setContentPane(new Main(background, title));
    
    //CREATES SECOND JFRAME
    JFrame gameWindow = new JFrame();
    Gameplay game = new Gameplay();
    gameWindow.add(game);
    gameWindow.setBounds(10, 10, 700, 600);
    gameWindow.setTitle("Brick Breaker");
    gameWindow.setResizable(false);
    gameWindow.setVisible(false);
    gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameWindow.setLocationRelativeTo(null);
    
    
    //CREATES INSTRUCTIONS JFRAME
    JFrame instructionsWindow = new JFrame();
    InstructionsWindow insWindow = new InstructionsWindow();
    instructionsWindow.setBounds(10, 10, 700, 600);
    instructionsWindow.setTitle("Brick Breaker");
    instructionsWindow.setResizable(false);
    instructionsWindow.setVisible(false);
    instructionsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    instructionsWindow.setLocationRelativeTo(null);
    instructionsWindow.add(insWindow);
    
    //PLAY BUTTON
    frame.add(play);
    play.setBorderPainted(false);
    play.setVisible(true);
    play.setSize(120,50);
    play.setLocation(285,275);
    play.setContentAreaFilled(false);
    
    //ONCLICK EVENT
    play.addActionListener( new ActionListener()
                               {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      //GETS RID OF OLD FRAME AND OPENS NEW ONE
      frame.dispose();
      gameWindow.setVisible(true);
      
     
    }
    });
    
    
    
    //INSTRUCTIONS BUTTON
    frame.add(instructions);
    instructions.setBorderPainted(false);
    instructions.setVisible(true);
    instructions.setSize(170,35);
    instructions.setLocation(265, 350);
    instructions.setContentAreaFilled(false);
    //ONCLICK EVENT
    instructions.addActionListener( new ActionListener()
                               {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      //GETS RID OF OLD FRAME
       frame.dispose();
      instructionsWindow.setVisible(true);
      //ADDS INVISIBLE BUTTON
      instructionsWindow.add(play);
      play.setBorderPainted(false);
      play.setVisible(true);
      play.setSize(120,50);
      play.setLocation(285,275);
      play.setContentAreaFilled(false);
      play.addActionListener( new ActionListener()
                               {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      instructionsWindow.dispose();
    }
    });
    }
    });
  }
  
}
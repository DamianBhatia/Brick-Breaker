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

public class Gameplay extends JPanel implements KeyListener, ActionListener {
   
  //GLOBAL VARIABLES
  private boolean isRunning = false;
  
  private int playerScore = 0;
  
  private int totalBricks = 21;
  
  private Timer timer;
  private int delay = 8;
  
  private int playerX = 310;
  
  //BALL ATTRIBUTES
  private int ballX = (int)(Math.random() * 500 + 10);
  private int ballY = 350;
  private int ballDX = -1;
  private int ballDY = -2;
  
  private LevelCreator level;
  
  //IMAGES
  private Image gameover = Toolkit.getDefaultToolkit().getImage("GameOver.png");
  private Image winner = Toolkit.getDefaultToolkit().getImage("Winner.png");
  private Image paddle = Toolkit.getDefaultToolkit().getImage("Paddle.png");
  private Image gameBackground = Toolkit.getDefaultToolkit().getImage("gameBackground.png");
  
  //GAMEPLAY CONSTRUCTOR
  public Gameplay() {
    //INITIALIZES LEVEL
    level = new LevelCreator(3, 7);
    //FOR KEY BINDINGS
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    //BEGINS THE TIMER/TICK COUNT
    timer = new Timer(delay, this);
    timer.start();
  }
  
  public void paint(Graphics g) {

    //BACKGROUND
    g.setColor(Color.lightGray);
    g.fillRect(1, 1, 692, 592);
    g.drawImage(gameBackground, 0, 0, getWidth(), getHeight(), this);
    
    //LEVEL
    level.drawBricks((Graphics2D)g);
    
    //BORDERS
    g.setColor(Color.YELLOW);
    g.fillRect(0, 0, 3, 592);
    g.fillRect(0, 0, 692, 3);
    g.fillRect(691, 0, 3, 592);
    
    //SHOW SCORE
    g.setColor(Color.BLACK);
    g.setFont(new Font("serif", Font.BOLD, 25));
    g.drawString("Score: "+ playerScore, 580, 30);
    
    //THE PADDLE
    g.drawImage(paddle, playerX, 500, this);
    //g.setColor(Color.BLUE);
    //g.fillRoundRect(playerX, 550, 100, 8, 7, 7);
    
    //THE BALL
    g.setColor(Color.ORANGE);
    g.fillOval(ballX, ballY, 20, 20);   
    
    //CHECK WINNER
    if (totalBricks <= 0) {
      isRunning = false;
      ballDX = 0;
      ballDY = 0;
      g.setColor(Color.BLACK);
      //DISPLAYS YOU WIN SCREEN
      g.fillRect(0,0,700,600);
      g.drawImage(winner, 190, 240, this);
      g.setColor(Color.RED);
      g.setFont(new Font("serif", Font.BOLD | Font.ITALIC, 20));
      g.drawString("Press Enter To Play Again", 230, 350);
    }
    
    //CHECKS GAMEOVER
    if (ballY > 570) {
      isRunning = false;
      ballDX = 0;
      ballDY = 0;
      //DISPLAYS GAME OVER SCREEN
      g.setColor(Color.BLACK);
      g.fillRect(0,0,700,600);
      g.drawImage(gameover, 140, 260, this);
      g.setColor(Color.RED);
      g.setFont(new Font("serif", Font.BOLD | Font.ITALIC, 20));
      g.drawString("Press Enter To Play Again", 230, 350);
    }
   
    
    g.dispose();
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    timer.start();
    if (isRunning) {
      //COLLISION DETECTION BETWEEN THE PLAYER AND THE BALL
      if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
        ballDY = -ballDY;
      }
      
      A: for(int i = 0; i < level.map.length; i++) {
        for(int j = 0; j<level.map[0].length; j++) {
          if(level.map[i][j] > 0) {
            //INITIALIZING BRICK VARIABLES USED FOR COLLISION DETECTION
            int brickX = j * level.brickWidth + 80;
            int brickY = i * level.brickHeight + 50;
            int brickWidth = level.brickWidth;
            int brickHeight = level.brickHeight;
            
            Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
            Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);
            Rectangle brickRect = rect;
            //COLLISION DETECTION FOR BALL AND BRICK
            if (ballRect.intersects(brickRect)) {
              level.setBrickValue(0, i, j);
              //DELETES BRICK
              totalBricks--;
              //ADDS SCORE
              playerScore += 5;
              
              //AGAIN BALL AND BRICK DETECTION BUT ON THE SIDES
              if (ballX + 19 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width) {
                ballDX = - ballDX;              
              } else {
                ballDY = - ballDY;
              }
              
              break A;
            }
          }
        }
      }
      
      
      ballX += ballDX;
      ballY += ballDY;
      if (ballX < 0) {
        ballDX = -ballDX;
;      }
      if (ballY < 0) {
        ballDY = -ballDY;
;      }
      if (ballX > 670) {
        ballDX = -ballDX;
;      }
    }
    
    
    
    repaint();
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    //CHECK RIGHT ARROW
    if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
      if(playerX >= 600) {
        playerX = 600;
      } else {
        moveRight();
      }
    }
    //CHECK LEFT ARROW
    if(e.getKeyCode() == KeyEvent.VK_LEFT) {
     if(playerX < 10) {
        playerX = 10;
      } else {
        moveLeft();
      }
    }
    //CHECK ENTER KEY FOR RESTARTING THE GAME
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if(!isRunning) {
        isRunning = true;
        ballX = (int)(Math.random() * 500 + 10);
        ballY = 350;
        ballDX = -1;
        ballDY = -2;
        playerX = 310;
        playerScore = 0;
        totalBricks = 21;
        level = new LevelCreator(3, 7);
        
        repaint();
      }
    }
  }
  
  //MOVE RIGHT
  public void moveRight() {
    isRunning = true;
    playerX+=15 + 2.0f;
  }
  
  //MOVE LEFT
  public void moveLeft() {
    isRunning = true;
    playerX-=15 + 2.0f;
  }
  
  
  
  @Override
  public void keyTyped(KeyEvent e) {}
  @Override
  public void keyReleased(KeyEvent e) {}
  
  
}
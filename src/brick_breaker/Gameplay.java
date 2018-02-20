package brick_breaker;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean play=false;
    private int score=0;
    private int totalBricks=21;
    private Timer time;
    private int delay=12;
    private int playerX=310;
    private int ballposX=120;
    private int ballposY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    private MapGenrator map;



    public Gameplay()
    {
        map=new MapGenrator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time=new Timer(delay,this);
        time.start();

    }


    public void paint(Graphics g)
    {

        //backgrounds
        g.setColor(Color.BLACK);
        g.fillRect(1,1,992,700);

        //drawing map
        map.draw((Graphics2D)g);

        //Scores
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString("Score:"+score,870,30);

        //the paddle
         g.setColor(Color.GREEN);
         g.fillRect(playerX,639,150,15);

         //the ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballposX,ballposY,25,25);

        if(totalBricks <=0)
        {
            play = false;
            ballXdir = 0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,35));
            g.drawString("You Win !",350,300);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to Restart ",300,350);
        }

        if(ballposY > 700)
        {
            play = false;
            ballXdir = 0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,35));
            g.drawString("Game Over, Scroes:"+score,300,300);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to Restart ",330,350);

            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Press Esc to go Back ",360,400);

        }
        g.dispose();
        }






    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    time.start();
    if(play)
    {
        if(new Rectangle(ballposX,ballposY,25,25).intersects(new Rectangle(playerX,640,150,15)))
        {
          ballYdir = -ballYdir;

        }

     A:    for(int i=0;i<map.map.length;i++)
    {
         for(int j=0;j<map.map[0].length;j++)
            {
               if(map.map[i][j] > 0)
               {
                   int brickX = j*map.brickWidth+150;
                   int brickY = i*map.brickHeight+60;
                   int brickWidth= map.brickWidth;
                   int brickHeight = map.brickHeight;

                   Rectangle rect =new Rectangle(brickX,brickY,brickWidth,brickHeight);
                   Rectangle ballRect = new Rectangle(ballposX,ballposY,25,25);
                   Rectangle brickRect=rect;

                   if(ballRect.intersects(brickRect))
                   {
                       map.setBrickValue(0,i,j);
                       totalBricks--;
                       score+=5;

                       if(ballposX + 19 <= brickRect.x|| ballposX + 1 >= brickRect.x + brickRect.width)
                       {
                           ballXdir= -ballXdir;
                       }
                       else
                       {
                           ballYdir = -ballYdir;
                       }
                       break A;
                   }




            }
    }

        ballposX+=ballXdir;
        ballposY+=ballYdir;

         if(ballposX < 0)
         {
            ballXdir = -ballXdir;

         }
          if(ballposY < 0)
         {
            ballYdir = -ballYdir;

         }
           if(ballposX > 970)
         {
            ballXdir = -ballXdir;

         }
    }
    repaint();

    }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
         if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            this.hide();
            Frame1 f=new Frame1();
            f.setVisible(true);
        }
       if(e.getKeyCode()==KeyEvent.VK_RIGHT)
       {
            if(playerX >=830)
            {
                playerX=830;

            }
            else
            {
                moveRight();
            }
       }
       if(e.getKeyCode()==KeyEvent.VK_LEFT)
       {
          if(playerX < 30)
            {
                playerX=30;

            }
            else
            {
                moveLeft();
            }  
       }

       if(e.getKeyCode()==KeyEvent.VK_ENTER)
       {
            if(!play)
            {
                play = true;
                ballposX= 180;
                ballposY=400;
                ballXdir=-1;
                ballYdir=-2;
                playerX=310;
                score = 0;
                totalBricks = 21;
                map = new MapGenrator(3,7);
                repaint();
            }

    }

    }
     public void moveRight()
       {
           play=true;
           playerX+=40;

       }
      public void moveLeft()
       {
           play=true;
           playerX-=40;

       }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   // public Object getValue(String key) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // }

    public void putValue(String key, Object value) {
  //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }








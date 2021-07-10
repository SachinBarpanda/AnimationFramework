package great.com.sachin;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private static final int PWIDTH = 500;
    public static final int PHEIGHT = 400;//panel height and width

    private Thread animator ; //for animation
    private volatile boolean running = false; //stops the animation

    private volatile boolean gameOver = false; // for game termination

    private Graphics dbg;
    private Image dbImage = null;

    public GamePanel(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(PWIDTH,PHEIGHT));

        //create game component
    }

    public void addNotify(){
        super.addNotify();
        startGame();
    }

    public void startGame(){
        //start the thread
        if(animator == null || !running){
            animator = new Thread(this);
            animator.start();
        }
        //end of startGame();
    }

    public void stopGame(){
        //called by the user to stop execution
        running = false;
    }


    @Override
    public void run() {
    //Repeat update,render and sleep
        running = true;
        while(running){
            gameUpdate();//game State is updated
            gameRender();//render to a buffer
            repaint();//paint with a buffer

            try{
                Thread.sleep(20);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.exit(0);
        }//end of run()
    }

    private void gameUpdate(){
        if(!gameOver){
            //update game state
        }
    }
    private void gameRender(){
        if(dbImage == null){
            dbImage = createImage(PWIDTH,PHEIGHT);
            if(dbImage == null){
                System.out.println("dbImage is full");
                return;
            }else{
                dbg = dbImage.getGraphics();
            }
            //clear the background
            dbg.setColor(Color.WHITE);
            dbg.fillRect(0,0,PWIDTH,PHEIGHT);

            //draw game elements

            if(gameOver){
                gameOverMessage(dbg);
            }
        }
    }
    private void gameOverMessage(Graphics g){
        //game over message
        //calculate x and y here...
        g.drawString(msg,x,y);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(dbImage !=null){
            g.drawImage(dbImage,0,0,null);
        }
    }

}

package great.com.sachin;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private static final int PWIDTH = 500;
    public static final int PHEIGHT = 400;//panel height and width

    private Thread animator ; //for animation
    private volatile boolean running = false; //stops the animation

    private volatile boolean gameOver = false; // for game termination

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
}

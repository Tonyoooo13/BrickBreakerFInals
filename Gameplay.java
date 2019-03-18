
import java.awt.event.KeyListener;
import java.util.Timer;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;


public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 21;
	
	private Timer time;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	public Gameplay( ) {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
//		timer = new Timer(delay, this);
//		timer.start()
	}
	
	public void paint(Graphics g) {
		
//		background 
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
//		border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
//		paddle
		g.setColor(Color.red);
		g.fillRect(playerX, 550, 100, 8);
		
//		ball
		g.setColor(Color.CYAN);
		g.fillRect(ballPosX, ballPosY, 20, 20);

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT);{
			if(playerX >=600) {
				playerX = 600;
			}else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT);{
			if(playerX < 10) {
				playerX = 10;
			}else {
				moveLeft();
			}
		}
		
	}
	
	public void moveRight() {
		play = true;
		playerX+=20;
	}
	public void moveLeft() {
		play = true;
		playerX-=20;
	}
}

	

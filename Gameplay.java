
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener  {
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	public MapGenerator map;
	
	public Gameplay( ) {
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
//		background 
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592); 
		
//		map
		map.draw((Graphics2D)g);
		
//		border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
//		scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
//		paddle
		g.setColor(Color.red);
		g.fillRect(playerX, 550, 100, 8);
		
//		ball
		g.setColor(Color.CYAN);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		if(ballPosY > 570){
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 50));
			g.drawString("Game Over, Score", 190, 300);

			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString(" Press Enter to Play Again ", 230, 350);
		}

		g.dispose();
	}
	
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = -ballYdir;
			}
			
			A: for(int i = 0; i < map.map.length; i++) {
				for( int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j* map.brickWidth + 90;
						int brickY = i*map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 1;
							
							if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							}else {
								ballYdir = -ballYdir;
							}
							break A;
						}
					}
				}
			}	
			
			ballPosX += ballXdir;
			ballPosY += ballYdir;
			if(ballPosX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballPosY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballPosX > 670) {
				ballXdir = -ballXdir;
			}
		}
		
		repaint();
	}
	
	public void keyTyped(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_D){
			if(playerX >=600) {
				playerX = 600;
			}else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			if(playerX < 10) {
				playerX = 10;
			}else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!play){
				play = true;
				ballPosX = 120;
				ballPosY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3, 7);
			}
		}

		repaint(); 
	}
	
	public void moveRight() {
		play = true;
		playerX = playerX + 20;
		repaint();
	}
	public void moveLeft() {
		play = true;
		playerX = playerX - 20;
		repaint();
	}

}

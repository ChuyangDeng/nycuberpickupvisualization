package game;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Use a PlayBoard class to represent the grids */
class PlayBoard extends JLabel {
	int number;
	int values[] = {2, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
	int coordinateX[] = {63, 184, 305, 426, 63, 184, 305, 426, 63, 184, 305, 426, 63, 184, 305, 426};
	int coordinateY[] = {265, 265, 265, 265, 386, 386, 386, 386, 507, 507, 507, 507, 628, 628, 628, 628};
	int length = 110;
	
	/* Use start to merge icons */
	public void start(int stage) {
		this.setBounds(coordinateX[stage], coordinateY[stage], length, length);
		disappear();
	}
	
	/* This method is called when a direction key is pressed
	 * the original icon that still has space in the direction will disappear */
	void disappear(){
		this.setVisible(false);
	}
	
	/* This method will generate new icons after the disappear method */
	void appear() {
		this.setVisible(true);
	}
}

public class Uber2048Game extends JFrame{
	
	/* A button to start the game */
	private JButton buttonStart = new JButton();
	
	/* Padding the file path */
	private String path = this.getClass().getClassLoader().getResource(".").getPath();
	
	private JLabel board;
	
	private JLabel score = new JLabel("SCORE", JLabel.CENTER);
	private JLabel scoreBackground = new JLabel();
	private JLabel showScore = new JLabel(" ", JLabel.CENTER);
	
	private KeyboardPanel keyboardPanel = new KeyboardPanel();
	
	private JLabel welcome1 = new JLabel("For more information about Uber trips, please visit: ");
	private JLabel welcome2 = new JLabel( "LINKE HERE");
	
	/* Create a 4 * 4 play board */
	public PlayBoard blocks[] = new PlayBoard[16];
	boolean check[] = new boolean[16];
	
	/* Store all possible values and x & y coordinates in arrays */
	int currentScore = 0;
	int values[] = {2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
	int posX[] = {63, 184, 305, 426, 63, 184, 305, 426, 63, 184, 305, 426, 63, 184, 305, 426};
	int posY[] = {265, 265, 265, 265, 386, 386, 386, 386, 507, 507, 507, 507, 628, 628, 628, 628};
	int length = 110;
	
	/* Numbers will be replaced by icons later */
	public static ImageIcon number[] = new ImageIcon[17];
	
	void setBoard(int block, int content) {
		blocks[block].setIcon(number[content]);
		blocks[block].number = content;
	}
	
	/* When all grids are filled and there is no space to move, game is over */
	boolean gameOver() {
		for(int i = 0; i < 16; i ++) {
			if(check[i] == false)
				return false;
			if(((i + 1 <= i - i % 4 + 3) && (blocks[i].number == blocks[i + 1].number)) || 
					((i + 4 < 16) && (blocks[i].number == blocks[i + 4].number)))
				return false;
		}
		return true;
	}
	
	/* When a grid calls disappear(), this grid become "inactive" */
	void disappear(int k) {
		blocks[k].disappear();
		check[k] = false;
	}
	
	/* When a grid calls appear(), this grid become "active" */
	void appear(int k) {
		blocks[k].appear();
		check[k] = true;
	}
	
	/* Merge grids and increment current score */
	void evolve(int s) {
		setBoard(s, blocks[s].number + 1);
		currentScore += values[blocks[s].number];
		showScore.setText("" + currentScore);
	}
	
	/* Create a click event
	 * When clicked, game start and two random grids are becomeing active (appear() and check = true)
	 *  */
	private class clickAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {			
			for(int i = 0; i < 16; i ++)
				check[i] = false;
			int rand1, rand2;
			Random random = new Random();
			rand1 = Math.abs(random.nextInt()) % 16;
			rand2 = Math.abs(random.nextInt()) % 16;
			while(rand2 == rand1) {
				rand2 = Math.abs(random.nextInt()) % 16;
			}
			check[rand1] = true;
			check[rand2] = true;
			
			for(int i = 0; i < 16; i ++) {
				blocks[i].start(i);
				if(Math.abs(random.nextInt()) % 10 == 0)
					setBoard(i, 1);
				else
					setBoard(i, 0);
				if(check[i] == true)
					blocks[i].appear();
			}
			currentScore = 0;
			showScore.setText("0");
			repaint();
			keyboardPanel.setFocusable(true);
			keyboardPanel.requestFocus();
		}
	}
	clickAction click = new clickAction();
	
	/* Determine keyboard interactions */
	class KeyboardPanel extends JPanel {
	    private char KeyChar = 'A' ;

	    public KeyboardPanel() {
	    	addKeyListener( new KeyAdapter() {
              public void keyPressed(KeyEvent e) {
                     switch(e.getKeyCode()) {
	                     case KeyEvent.VK_DOWN: toSouth(); break;
	                     case KeyEvent.VK_UP: toNorth(); break;
	                     case KeyEvent.VK_LEFT: toWest(); break;
	                     case KeyEvent.VK_RIGHT: toEast(); break;
	                     default: KeyChar = e.getKeyChar();
                    }
                     	
                    repaint();
              }
	    	});
	    }
	    
	    /* declare boundaries */
	    boolean boundary(int position, int direction, int i) {
	    	if(direction == 0) { /* DOWN */
	    		if(i < 16)  return true;
	    		return false;
	    	}
	    	else if(direction == 1) { /* UP */
	    		if(i >= 0)  return true;
	    		return false;
	    	}
	    	else if(direction == 2) { /* RIGHT */
	    		if(i >= position - position % 4)  return true;
	    		return false;
	    	}
	    	else { /* LEFT */
	    		if(i <= position - position % 4 + 3)  return true;
	    		return false;
	    	}
	    }
	    
	    void newUber()
	    {
	    	int newPosition = Math.abs(new Random().nextInt()) % 16;
	    	while(check[newPosition])
	    		newPosition = Math.abs(new Random().nextInt()) % 16;
	    	if(Math.abs(new Random().nextInt()) % 10 == 0)
	    		setBoard(newPosition, 1);
			else
				setBoard(newPosition, 0);
	    	appear(newPosition);
	    	
	    	if(gameOver())
	    	{
	    		System.out.println("Game Over");
	    		JOptionPane.showMessageDialog(null, "Game Over", "Information", JOptionPane.INFORMATION_MESSAGE);
	    		buttonStart.requestFocus();
	    		keyboardPanel.setFocusable(false);
	    	}
	    }
	    
	    int gone(int position, int direction)
	    {
	    	int dir[] = {4, -4, -1, 1};
	    	
    		int result = position;
    		for(int i = position + dir[direction]; boundary(position, direction, i); i = i + dir[direction])
    			if(check[i] == false)
    				result = i;
    		setBoard(result, blocks[position].number);
    		disappear(position);
    		appear(result);
    		return result;
	    }
	    
	    void toNorth()
		{	
			boolean move = false;
			for(int begin = 0; begin < 4; begin = begin + 1)
			{
				for(int position = begin; position < 16; position = position + 4)
				{
					if(check[position] == true)
					{
						for(int next = position + 4; next < 16; next = next + 4)
						{
							if(check[next] == true)
							{
								if(blocks[position].number == blocks[next].number)
								{
									move = true;
									evolve(position);
									disappear(next);
								}
								
								break;
							}
						}
						if(gone(position, 1) != position)
							move = true;
					}
				}
			}
			if(move == true)
			{
				newUber();
			}
		}
	    
	    void toEast()
		{	
			boolean move = false;
			for(int begin = 3; begin < 16; begin = begin + 4)
			{
				for(int position = begin; position > begin - 4; position = position - 1)
				{
					if(check[position] == true)
					{
						for(int next = position - 1; next > begin - 4; next = next - 1)
						{
							if(check[next] == true)
							{
								if(blocks[position].number == blocks[next].number)
								{
									move = true;
									evolve(position);
									disappear(next);
								}
								
								break;
							}
						}
						if(gone(position, 3) != position)
							move = true;
					}
				}
			}
			if(move == true)
			{
				newUber();
			}
		}
	    
	    void toSouth()
		{
	    	boolean move = false;
			for(int begin = 12; begin < 16; begin = begin + 1)
			{
				for(int position = begin; position >= 0; position = position - 4)
				{
					if(check[position] == true)
					{
						for(int next = position - 4; next >= 0; next = next - 4)
						{
							if(check[next] == true)
							{
								if(blocks[position].number == blocks[next].number)
								{
									move = true;
									evolve(position);
									disappear(next);
								}
								
								break;
							}
						}
						if(gone(position, 0) != position)
							move = true;
					}
				}
			}
		
			if(move == true)
			{
				newUber();
			}
		}
		
		void toWest() {
			boolean move = false;
			for(int begin = 0; begin < 13; begin = begin + 4)
			{
				for(int position = begin; position < begin + 4; position = position + 1)
				{
					if(check[position] == true)
					{
						for(int next = position + 1; next < begin + 4; next = next + 1)
						{
							if(check[next] == true)
							{
								if(blocks[position].number == blocks[next].number)
								{
									move = true;
									evolve(position);
									disappear(next);
								}
								
								break;
							}
						}
						if(gone(position, 2) != position)
							move = true;
					}
				}
			}
			if(move == true)
			{
				newUber();
			}
		}
	}
	
	public Uber2048Game()
	{
		super();
		this.setSize(600, 800);
		this.setLocation(600, 120);
		this.getContentPane().setLayout(null);
		this.setTitle("POKEMON 2048");
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		
//		将数字用POKEMON ICON代替
		for(int i = 0; i < 9; i ++){
			number[i] = new ImageIcon(path + values[i] + ".png");
		}
		
		add( keyboardPanel);
        keyboardPanel.setFocusable(true);
	}
	
	public void initial()
	{
		
		for(int i = 0; i < 16; i ++){
			blocks[i] = new PlayBoard();
			this.add(blocks[i]);
		}
		
		ImageIcon image = new ImageIcon(path + "back.png");
		board = new javax.swing.JLabel();
		board.setIcon(image);
		board.setBounds(47, 250, 500, 500);
		this.add(board);
		
		this.add(buttonStart);
		buttonStart.setBounds(110, 80, 140, 50);
		buttonStart.setText("START");
		buttonStart.setFont(new java.awt.Font("SansSerif", 1, 18));
		buttonStart.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonStart.setForeground(new Color(0, 49, 79));
		buttonStart.setOpaque(true);
		buttonStart.setFocusPainted(false);
		buttonStart.addActionListener(click);
		
		this.add(score);
		this.add(showScore);
		this.add(scoreBackground);
		this.add(welcome1);
		this.add(welcome2);
		scoreBackground.setBounds(310, 70, 130, 70);
		score.setBounds(310, 80, 130, 30);
		showScore.setBounds(310, 110, 130, 25);
		scoreBackground.setBackground(Color.WHITE);
		score.setForeground(new Color(0, 49, 79));
		showScore.setForeground(new Color(0, 49, 79));
		scoreBackground.setOpaque(true);
		score.setFont(new java.awt.Font("SansSerif", 1, 20));   
		showScore.setFont(new java.awt.Font("SansSerif", 1, 25));
		showScore.setText("0");
		welcome1.setBounds(120, 155, 400, 30);
		welcome2.setBounds(90, 185, 400, 30);
		
	}
	
	public static void main(String[] args){
		Uber2048Game game = new Uber2048Game();

		game.initial();
		game.setVisible(true);
	}
}
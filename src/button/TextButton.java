package button;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

public class TextButton extends JPanel {
	
	final public static int LEFT_ALIGNED = 0;
	final public static int CENTER_ALIGNED = 1;
	final public static int RIGHT_ALIGNED = 2;
	
	String text;
	int x, y;
	int width, height;

	public TextButton(String text, int x, int y, int width, int height, MouseAdapter mouseAdapter){
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		setBounds(x, y, width, height);
		
		addMouseListener(mouseAdapter);
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = Helper.getSmoothedGraphics(g);
		
		FontMetrics fm = g2.getFontMetrics(new Font("Consolas",Font.BOLD,12));
		
		g2.setColor(Color.DARK_GRAY);
		g2.setFont(new Font("Consolas",Font.ITALIC,12));
		g2.drawString(text, width / 2 - fm.stringWidth(text) / 2, height / 2 + fm.getHeight() / 3);
	}
}

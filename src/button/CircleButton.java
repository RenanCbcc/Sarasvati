package button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class CircleButton extends JPanel {
	
	private int x, y, diameter;
	private Color color;
	private Image image;
	
	// este construtor é usado nos botões fechar e minimizar
	public CircleButton(int x, int y, int diameter, Color color, MouseAdapter mouseAdapter){
		super();
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.color = color;
		
		setBounds(x, y, diameter, diameter);
		setVisible(true);
		
		addMouseListener(mouseAdapter);
	}
	// este construtor eh usado nos botoes play,proximo e anterior
	public CircleButton(int x, int y, int diameter, Image image, Color color, MouseAdapter mouseAdapter){
		super();
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.image = image;
		this.color = color;
		
		setBounds(x, y, diameter, diameter);
		setVisible(true);
		
		addMouseListener(mouseAdapter);
	}
	
	public void setImage(Image image){
		this.image = image;
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = Helper.getSmoothedGraphics(g);
		
		if (image == null){
			g2.setColor(color);
			g2.fillOval(0, 0, diameter, diameter);
		} else {
			g2.drawImage(Helper.changeImageColor((BufferedImage) image, color), 0, 0, diameter, diameter, null);
		}
		
	}
}

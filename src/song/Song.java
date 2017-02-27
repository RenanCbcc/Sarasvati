package song;
import button.Helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Song extends JPanel{
	
	String titulo, artista, caminho;
	final public static int BACK = 0;
	final public static int MUSIC = 1;
	final public static int FOLDER = 2;
	int tipo;
	
	public Song (String titulo, String artista, String caminho) 
	{
		
		this.titulo = titulo;
		this.artista = artista;
		this.caminho = caminho;
		
		
		if (artista.equals("Folder")){
			this.tipo = FOLDER;
		} else {
			this.tipo = MUSIC;
		}
		
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				if (tipo == BACK)
				{
					if (caminho.equals("/"))
					{
						String[] split = Helper.musicPath.split("/");
						Helper.musicPath = "/";
						for (int i = 0; i < split.length - 1; i++)
						{
							Helper.musicPath += split[i] + "/";
						}
						Helper.songHolder.createSongItems();
						Helper.songHolder.repaint();
						
					} else if (tipo == FOLDER)
					
					{
						Helper.musicPath += titulo + "/";
						Helper.songHolder.createSongItems();
						Helper.songHolder.repaint();
					} else 
					
					{
						//Helper.play(caminho);
					}
				}
			}
		
		};
			
	addMouseListener(mouseAdapter);
	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Helper.getSmoothedGraphics(g);
		
		BufferedImage img;
		Color iconColor = Helper.loadColorfromJSON("list_icon");
		if (tipo == BACK){
			img = (BufferedImage) Helper.loadResourceImage("/back.png");
		} else if (tipo == FOLDER){
			img = (BufferedImage) Helper.loadResourceImage("/folder.png");
		} else {
			img = (BufferedImage) Helper.loadResourceImage("/music.png");
		}
		g2.drawImage(Helper.changeImageColor(img, iconColor), 20, 10, 20, 20, null);
		
		g2.setColor(Color.DARK_GRAY);
		g2.setFont(new Font("Consolas",Font.BOLD,12));
		g2.drawString(titulo, 50, 22);
		
		g2.setFont(new Font("Consolas",Font.BOLD,12));
		g2.drawString(artista, 50, 32);
	}
}
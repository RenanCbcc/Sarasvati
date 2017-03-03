package song;
import button.Helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Song extends JPanel {
	
	String title, artist, path;
	
	final public static int BACK = 0;
	final public static int MUSIC = 1;
	final public static int FOLDER = 2;
	int type;
	
	Song(String title, String artist, String path) {
		
		this.title = title;
		this.artist = artist;
		this.path = path;
		
		setBackground(Color.white);
		
		if (artist.equals("Folder")){
			this.type = FOLDER;
		} else {
			this.type = MUSIC;
		}
		
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.lightGray);// parcialmente selecionado quando mause esta em cima
			}
			
			@Override
			public void mouseExited(MouseEvent e) { // fundo das pastas
				setBackground(Color.white);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Color.lightGray); // quando o mause eh pressionado
				
				if (type == BACK){
					if (path.equals("/")){
						String[] split = Helper.musicPath.split("/");
						Helper.musicPath = "/";
						
						for (int i = 0; i < split.length - 1; i++){
							Helper.musicPath += split[i] + "/";
						}
						
						Helper.songHolder.createSongItems();
						Helper.songHolder.repaint();
					} else {
						Helper.songHolder.setInPlaylist(false);
						Helper.songHolder.createPlaylistItems();
						Helper.songHolder.repaint();
					}
				} else if (type == FOLDER){
					Helper.musicPath += title + "/";
					Helper.songHolder.createSongItems();
					Helper.songHolder.repaint();
				} else {
					Helper.play(path);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(Color.darkGray);
			}
		};
		
		addMouseListener(mouseAdapter);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Helper.getSmoothedGraphics(g);
		
		BufferedImage img;
		Color iconColor = Color.BLACK;
		
		if (type == BACK){
			img = (BufferedImage) Helper.loadResourceImage("D:/rep/back.png");
		} else if (type == FOLDER){
			img = (BufferedImage) Helper.loadResourceImage("D:/rep/folder.png");
		} else {
			img = (BufferedImage) Helper.loadResourceImage("D:/rep/music.png");
		}
		g2.drawImage(Helper.changeImageColor(img, iconColor), 20, 10, 20, 20, null);
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Consolas",Font.ITALIC,14));
		g2.drawString(title, 50, 22);
		
		g2.setFont(new Font("Consolas",Font.ITALIC,14));
		g2.drawString(artist, 50, 32);
	}
}
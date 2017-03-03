package playlist;

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

public class PlaylistItem extends JPanel {
	
	private String title, artist, path;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private int pos;
	
	final public static int CREATE = 0;
	final public static int PLAYLIST = 1;
	int type;
	
	public PlaylistItem(String title, String artist, String path, int type, int pos) {
		this(title, artist, path, type);
		
		pos = pos;
	}
	
	public PlaylistItem(String title, String artist, String path, int type) {
		
		this.title = title;
		this.artist = artist;
		this.path = path;
		this.type = type;
		
		setBackground(Color.WHITE);
		
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.red);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.white);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Color.GRAY);
				
				if (e.getX() > getWidth() - 40 && e.getX() < getWidth() - 20 && e.getY() > 10 && e.getY() < getHeight() - 10){
					delete();
					Helper.songHolder.redraw();
				} else {
					if (type == CREATE){
						CreatePlaylist createPlaylist = new CreatePlaylist(Helper.saraswat);
					} else if (type == PLAYLIST){
						Helper.currentPlaylistIndex = pos;
						Helper.currentSongList = Helper.playlists.get(pos).songs;
						Helper.songHolder.setInPlaylist(true);
						
						Helper.songHolder.redraw();
					}
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(Color.white);
			}
		};
		
		addMouseListener(mouseAdapter);
	}
	
	public void delete(){
		Helper.playlists.remove(pos);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = Helper.getSmoothedGraphics(g);
		
		BufferedImage img;
		Color iconColor = Color.MAGENTA;
		
		try {
		if (type == CREATE){
			img = ImageIO.read(new File("D:/rep/plus.png"));
		} else if (type == PLAYLIST){
			img = (BufferedImage) ImageIO.read(new File("D:/rep/playlist.png"));
		} else {
			img = (BufferedImage) ImageIO.read(new File("D:/rep/music.png"));
			g2.drawImage(Helper.changeImageColor(img, iconColor), 20, 10, 20, 20, null);
		}
		g2.drawImage(Helper.changeImageColor(img, iconColor), 20, 10, 20, 20, null);
		
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		g2.setColor(Color.white);
		g2.setFont(new Font("Consolas",Font.ITALIC,14));
		g2.drawString(title, 50, 22);
		
		g2.setFont(new Font("Consolas",Font.ITALIC,14));
		g2.drawString(artist, 50, 32);
	}
}

package song;
import button.Helper;
import java.awt.Color;
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
	
	public Song (String titulo, String artista, String caminho) {
		
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
			public void mousePressed(MouseEvent e) {
				
				if (tipo == BACK){
					if (caminho.equals("/")){
						String[] split = Helper.musicPath.split("/");
						Helper.musicPath = "/";
						
						for (int i = 0; i < split.length - 1; i++){
							Helper.musicPath += split[i] + "/";
						}
						
						Helper.songHolder.createSongItems();
						Helper.songHolder.repaint();
					} else {
						Helper.songHolder.inPlaylist = false;
						Helper.songHolder.createPlaylistItems();
						Helper.songHolder.repaint();
					}
				} else if (tipo == FOLDER){
					Helper.musicPath += titulo + "/";
					Helper.songHolder.createSongItems();
					Helper.songHolder.repaint();
				} else {
					Helper.play(caminho);
				}
			}
			
			
		};
		
		addMouseListener(mouseAdapter);
	}
	
}

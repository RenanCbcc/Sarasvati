package song;
import button.Helper;

import java.awt.Color;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JPanel;

// esta classe mostra uma pequena janela de navegação entre as pastas
public class SongHolder extends JPanel implements MouseWheelListener {
	
	private int x, y, width, height;
	JPanel holder = new JPanel();
	
	ArrayList<String> folderList; // lista com o nome das pastas de musica
	
	public boolean isInPlaylist() {
		return inPlaylist;
	}

	public void setInPlaylist(boolean inPlaylist) {
		this.inPlaylist = inPlaylist;
	}

	private boolean inPlaylist = false;
	
	public SongHolder(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		setBounds(x, y, width, height);
		setLayout(null);
		
		Helper.songHolder = this;
		
		createSongItems();
		
		addMouseWheelListener(this);
	}
	
	
	public void createSongItems(){
		holder.removeAll();
		
		Helper.getMusic();
		folderList = Helper.getFolders();
		
		Song panel = new Song("Voltar", "Mova-se para o diretorio", "/");
		panel.type = Song.BACK;
		panel.setBounds(0, 0, width, 40);
		holder.add(panel);
		
		//Desenhas as pastas
		for (int i = 0; i < folderList.size(); i++){
			String path = System.getProperty("user.home") + Helper.musicPath + folderList.get(i) + ".mp3";
					
			panel = new Song(folderList.get(i), "Folder", path);
			panel.setBounds(0, i * 40 + 40, width, 40);
			holder.add(panel);
		}
		
		//Desenha as miniaturas
		for (int i = 0; i < Helper.currentSongList.size(); i++){
			String path = Helper.currentSongList.get(i);
			
			panel = new Song(Helper.getSongTitle(path), Helper.getSongArtist(path), path);
			panel.setBounds(0, i * 40 + 40 + 40 * folderList.size(), width, 40);
			holder.add(panel);
		}
		
		holder.setBounds(0, 0, width, 40 * Helper.currentSongList.size() + 40 + 40 * folderList.size());
		holder.setLayout(null);
		
		add(holder);
		
		repaint();
	}
	
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int scroll = holder.getY() - e.getWheelRotation();
		
		int limit = height - holder.getHeight();
		
		if (scroll < limit){
			scroll = limit;
		} else if (scroll > 0){
			scroll = 0;
		}
		
		if (limit < 0){
			holder.setLocation(0, scroll);
		}
	}
}

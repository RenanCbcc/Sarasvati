package player;
import button.Helper;
import button.Menu;
import button.TextButton;
import button.CircleButton;
import button.Button;
import song.SongHolder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;

public class Sarasvat extends JFrame {
	
	public static final String VERSION = "1.0";
	public static final int WIDTH = 300;
	public static final int HEIGHT = 450;
	JFrame frame = this;
	
	public boolean playlist = false;
	
	CircleButton closeButton = new CircleButton(5, 5, 10, Color.red, new MouseAdapter() {public void mousePressed(java.awt.event.MouseEvent e) {
		System.exit(0);
	};});
	CircleButton minimizeButton = new CircleButton(20, 5, 10, Color.yellow , new MouseAdapter() {public void mousePressed(java.awt.event.MouseEvent e) {
		frame.setState(ICONIFIED);
	};});
	
	Menu menuBar = new Menu(0, 0, WIDTH, 20, Color.lightGray, this);
	
	MusicPlayer player = new MusicPlayer(0, 20, 300, 150, this);
	
	SongHolder holder = new SongHolder(0, 170, 300, 250);
	
	TextButton mymusic = new TextButton("Player", WIDTH / 2 - 50, HEIGHT - 30, 100, 30, new MouseAdapter() {
		public void mousePressed(java.awt.event.MouseEvent e) {
			if (playlist){
				mymusic.setText("Player");
				playlist = false;
				
				Helper.songHolder.createSongItems();
			} else {
				mymusic.setText("PLAYLISTS");
				playlist = true;
				
				Helper.songHolder.createPlaylistItems();
			}
			repaint();
		};
	});
	
	Button volume = new Button(0, HEIGHT - 30, 30, 30, Helper.loadResourceImage("D:/rep/volume.png"), Color.red, new MouseAdapter() {
		public void mousePressed(java.awt.event.MouseEvent e) {
			Equalizer equalizer = new Equalizer(frame, Helper.volume);
		};
	});
	
	
	
	
	public Sarasvat(){
		super();
		
		Helper.saraswat = this;
		
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		getContentPane().setBackground(Color.WHITE); // cor de fundo do player
		
		add(closeButton);
		add(minimizeButton);
		add(menuBar);
		
		add(player);
		Helper.musicPlayer = player;
		add(holder);
		
		add(mymusic);
		
		add(volume);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Helper.init();
		Sarasvat saraswat = new Sarasvat();
		
	}
}

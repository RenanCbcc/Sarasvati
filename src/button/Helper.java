package button;
import playlist.Playlist;
import player.Equalizer;
import player.MusicPlayer;
import player.Sarasvat;
import song.SongHolder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class Helper {

	//Resources
	public static Font consolas_light;
	public static Font consolas_bold;
	
	public static Image play;
	public static Image pause;
	
	//Global Variables
	
	public static Sarasvat saraswat;
	public static MusicPlayer musicPlayer;
	public static SongHolder songHolder;
	
	public static Equalizer equalizer;
	public static double volume = 1.0;
	
	public static String musicPath = "D:/Músicas";
	public static int currentSongIndex = -1;
	public static ArrayList<String> currentSongList = new ArrayList<String>();
	
	public static int currentPlaylistIndex = -1;
	public static ArrayList<String> currentPlaylist = new ArrayList<String>();
	
	public static ArrayList<Playlist> playlists = new ArrayList<Playlist>();
	
	public static Media media;
	public static MediaPlayer mediaPlayer;
	
	public static boolean playing = false;
	public static File nowPlaying;
	
	
	//Initalize static helper function
	public static void init(){
		loadFont();
		
			play = Helper.loadResourceImage("D:/rep/play.png");
			pause = Helper.loadResourceImage("D:/rep/pause.png");
		
	}
	
	//Load font into the function
	public static void loadFont(){
		try {
			consolas_light = new Font("Consolas",Font.ITALIC,12);
			consolas_bold = new Font("Consolas",Font.BOLD ,12);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Enable Anti-aliasing, Interpolation and corrected rendering
	public static Graphics2D getSmoothedGraphics(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		RenderingHints hints = new RenderingHints(null);
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHints(hints);
		return g2;
	}
	
	public static Image loadResourceImage(String path){
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			return null;
		}
	}
	
	public static void getMusic(){
		ArrayList<String> songList = new ArrayList<String>();
		
		File folder = new File(musicPath);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++){
			File f = listOfFiles[i];
			if (!listOfFiles[i].isDirectory()){
				if (f.getName().contains(".mp3")){
					songList.add(f.getAbsolutePath());
				}
			}
		}
		
		currentSongList = songList;
		//return songList;
	}
	
	public static ArrayList<String> getFolders(){
		ArrayList<String> folderList = new ArrayList<String>();
		
		File folder = new File(musicPath);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++){
			File f = listOfFiles[i];
			if (listOfFiles[i].isDirectory()){
				folderList.add(f.getName());
			}
		}
		return folderList;
	}
	
	public static void getPlaylistMusic(){
		currentSongList = playlists.get(currentPlaylistIndex).songs;
		currentSongIndex = 0;
	}
	
	public static void play(){
		if (mediaPlayer == null){
			if (Helper.currentSongList.size() > 0){
				play(Helper.currentSongList.get(0));
			}
		} else {
			mediaPlayer.play();
			
			Helper.playing = true;
			Helper.musicPlayer.getPlay().setImage(Helper.pause);
			Helper.musicPlayer.update();
		}
	}
	
	public static void play(String path){
		if (mediaPlayer != null){
			mediaPlayer.stop();
		}
		
		nowPlaying = new File(path);
		
		media = new Media(nowPlaying.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(Helper.volume);
		
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				Helper.play(Helper.getNextSong());
			}
		});
		
		mediaPlayer.play();
		
		Helper.playing = true;
		Helper.musicPlayer.getPlay().setImage(Helper.pause);
		Helper.musicPlayer.update();
		
		currentSongIndex = currentSongList.indexOf(path);
	}
	
	public static void pause(){
		if (mediaPlayer == null){
			
		} else {
			mediaPlayer.pause();
			
			Helper.playing = false;
			Helper.musicPlayer.getPlay().setImage(Helper.play);
			Helper.musicPlayer.update();
		}
	}
	
	public static String getNextSong(){
		if (currentSongIndex == currentSongList.size() - 1){
			return Helper.currentSongList.get(0);
		}
		return Helper.currentSongList.get(Helper.currentSongIndex + 1);
	}
	
	public static String getPrevSong(){
		if (currentSongIndex == 0){
			return Helper.currentSongList.get(Helper.currentSongList.size() - 1);
		}
		return Helper.currentSongList.get(Helper.currentSongIndex - 1);
	}
	
	// desenha uma miniatura de musica
	public static BufferedImage changeImageColor(BufferedImage img, Color color){
		BufferedImage colored = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < img.getHeight(); y++){
			for (int x = 0; x < img.getWidth(); x++){
				int alpha = (img.getRGB(x, y) >> 24) & 0xFF;
				int rgb = (alpha << 24) & 0xFF000000 | (color.getRed() << 16) & 0x00FF0000 | (color.getGreen() << 8) & 0x0000FF00 | color.getBlue() & 0x000000FF;
				colored.setRGB(x, y, rgb);
			}
		}
		return colored;
	}
	
	public static String getSongTitle(String path){
		try {
			Mp3File song = new Mp3File(path);
			if (song.hasId3v2Tag()){
				ID3v2 id3v2tag = song.getId3v2Tag();
				if (id3v2tag.getTitle() != null){
					return id3v2tag.getTitle();
				} else {
					return new File(path).getName();
				}
			} else {
				return new File(path).getName();
			}
		} catch (Exception e){
			return "Unknown";
		}
	}
	
	public static String getSongArtist(String path){
		try {
			Mp3File song = new Mp3File(path);
			if (song.hasId3v2Tag()){
				ID3v2 id3v2tag = song.getId3v2Tag();
				if (id3v2tag.getArtist() != null){
					return id3v2tag.getArtist();
				} else {
					return "Unknown";
				}
			} else {
				return "Unknown";
			}
		} catch (Exception e){
			return "Unknown";
		}
	}
	
	public static Image getAlbumArt(String path){
		try {
			Mp3File song = new Mp3File(path);
			if (song.hasId3v2Tag()){
				ID3v2 id3v2tag = song.getId3v2Tag();
			    byte[] imageData = id3v2tag.getAlbumImage();
			    //converting the bytes to an image
			    BufferedImage img;
			    try {
			    	img = ImageIO.read(new ByteArrayInputStream(imageData));
			    } catch (Exception e){
			    	return null;
			    }
			    return img;
			}
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setVolume(double value){
		volume = value;
		if (mediaPlayer != null){
			mediaPlayer.setVolume(value);
		}	
	}
}

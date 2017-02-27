package button;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;


public class Helper {
	
	public static Font Consolas_light;
	public static Font Consolas_normal;
	
	public static Image play;
	public static Image pause;
	
	//Global Variables
	
		public static Color color;
		
		public static Sarasvat sarasvat;
		public static MusicPlayer musicPlayer;
		public static SongHolder songHolder;
		
		public static String musicPath = "D:/Músicas";
		public static int currentSongIndex = -1;
		public static ArrayList<String> currentSongList = new ArrayList<String>();
		
		public static int currentPlaylistIndex = -1;
		public static ArrayList<String> currentPlaylist = new ArrayList<String>();
		
		
		
		public static boolean playing = false;
		public static File nowPlaying;
		
		
		public static void getMusic(){}
		public static ArrayList<String> getFolders(){
			
		}
		public static String getSongTitle(String path){}
			
		public static String getSongArtist(String path){}
		
		public static Graphics2D getSmoothedGraphics(Graphics g){}
		
		public static Image loadResourceImage(String path)
			
}

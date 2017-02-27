package player;
import song.SongProgress;
import button.CircleButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MusicPlayer {
	String title, artist;
	Image image, unknown;
	
	int x, y, width, height;
	JFrame parent;
	
	SongProgress songProgress;
	CircleButton fastBackward, play, fastForward;

}

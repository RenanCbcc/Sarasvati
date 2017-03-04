package player;

import song.SongProgress;
import button.CircleButton;
import button.Helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javafx.embed.swing.JFXPanel;

public class MusicPlayer extends JFXPanel {

	String title, artist;
	Image image, unknown;

	int x, y, width, height;
	JFrame parent;

	private SongProgress songProgress;

	public SongProgress getSongProgress() {
		return songProgress;
	}

	public void setSongProgress(SongProgress songProgress) {
		this.songProgress = songProgress;
	}

	public CircleButton getFastBackward() {
		return fastBackward;
	}

	public void setFastBackward(CircleButton fastBackward) {
		this.fastBackward = fastBackward;
	}

	public CircleButton getPlay() {
		return play;
	}

	public void setPlay(CircleButton play) {
		this.play = play;
	}

	public CircleButton getFastForward() {
		return fastForward;
	}

	public void setFastForward(CircleButton fastForward) {
		this.fastForward = fastForward;
	}

	private CircleButton fastBackward, play, fastForward;

	public MusicPlayer(int x, int y, int width, int height, JFrame parent) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.parent = parent;

		this.title = "Not selected";
		this.artist = "Not selected";

		setBounds(x, y, width, height);
		setLayout(null);

		// setBackground(Color.gray);

		songProgress = new SongProgress(height, 60, 300 - 20 - height, 20, Color.BLACK, this);
		add(songProgress);

		int w = 300 - 20 - height;
		int r = (w - 20) / 3;

		Color iconColor = Color.black; // cor dos botões
		fastBackward = new CircleButton(height, 90, r, iconColor, new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Helper.mediaPlayer != null) {
					Helper.play(Helper.getPrevSong());
				}
			}
		});
		play = new CircleButton(height + (r + 10), 90, r, Helper.play, iconColor, new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Helper.playing) {
					Helper.pause();
				} else {
					Helper.play();
				}
				Helper.musicPlayer.repaint();
			}
		});
		
		fastForward = new CircleButton(height + (r + 10) * 2, 90, r, iconColor, new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (Helper.mediaPlayer != null) {
					Helper.play(Helper.getNextSong());
				}
			}
		});
		
		//pequenas legedas que serão mostradas quando o mouse estiver em cima do bt.
		play.setToolTipText("Reproduzir");
		fastBackward.setToolTipText("Anterior");
		fastForward.setToolTipText("Proxima");
		
		add(fastBackward);
		add(play);
		add(fastForward);
	}

	public void update() {
		title = Helper.getSongTitle(Helper.nowPlaying.getAbsolutePath());
		artist = Helper.getSongArtist(Helper.nowPlaying.getAbsolutePath());
		image = Helper.getAlbumArt(Helper.nowPlaying.getAbsolutePath());

		songProgress.update();

		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = Helper.getSmoothedGraphics(g);

		if (image != null) {
			g2.drawImage(image, 20, 20, height - 40, height - 40, null);
		} else {
			g2.drawImage(unknown, 20, 20, height - 40, height - 40, null);
		}

		g2.setColor(Color.black); // cor da fonte da musica
		g2.setFont(new Font("Consolas", Font.ITALIC, 10));
		g2.drawString(title, height, 36);

		g2.setFont(new Font("Consolas", Font.ITALIC, 10));
		g2.drawString(artist, height, 50);

	}

}

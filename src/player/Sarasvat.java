package player;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import button.Button;
import button.Helper;
import song.SongHolder;

public class Sarasvat {

	public static final int WIDTH = 300;
	public static final int HEIGHT = 460;

	public static void main(String[] args) {
		Helper.init();
		JFrame frame = new JFrame();

		MusicPlayer player = new MusicPlayer(0, 20, 300, 150, frame);
		Helper.musicPlayer = player;

		SongHolder holder = new SongHolder(0, 170, 300, 250);

		Button volume = new Button(0, HEIGHT - 30, 30, 30, Helper.loadResourceImage("D:/rep/volume.png"), Color.red,
				new MouseAdapter() {
					public void mousePressed(java.awt.event.MouseEvent e) {
						Equalizer equalizer = new Equalizer(frame, Helper.volume);
					};
				});
		volume.setToolTipText("Continuo Bugado");

		frame.getContentPane().add(player);
		frame.getContentPane().add(holder);
		frame.getContentPane().add(volume);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("Sarasvat");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

	}

}

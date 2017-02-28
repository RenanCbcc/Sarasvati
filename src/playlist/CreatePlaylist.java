package playlist;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import button.Helper;
import player.Sarasvat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class CreatePlaylist extends JFrame implements ActionListener {
	
	Sarasvat mix;
	
	JTextField name, description;
	JButton add;
	
	public CreatePlaylist(Sarasvat mix){
		super();
		
		this.mix = mix;
		
		setLayout(null);
		setUndecorated(true);
		
		getContentPane().setBackground(Helper.colorFromHEX("#222222"));
		
		name = new JTextField();
		name.setBackground(Helper.colorFromHEX("#999999"));
		name.setBorder(null);
		name.setSize(160, 25);
		name.setLocation(20, 20);
		add(name);
		
		description = new JTextField();
		description.setBackground(Helper.colorFromHEX("#999999"));
		description.setBorder(null);
		description.setSize(160, 25);
		description.setLocation(20, 50);
		add(description);
		
		add = new JButton("CREATE");
		add.setBackground(Helper.colorFromHEX("#999999"));
		add.setBorder(null);
		add.setContentAreaFilled(true);
		add.setOpaque(true);
		add.setSize(160, 25);
		add.setLocation(20, 80);
		add.addActionListener(this);
		add(add);
		
		setSize(200, 130);
		setLocationRelativeTo(mix);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(add)){
			Helper.playlists.add(new Playlist(name.getText(), description.getText()));
			dispose();
			Helper.songHolder.redraw();
		}
	}
}
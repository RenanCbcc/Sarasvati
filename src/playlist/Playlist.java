package playlist;

import java.util.ArrayList;

public class Playlist {
	
	private String name, description;
	public ArrayList<String> songs = new ArrayList<String>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<String> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<String> songs) {
		this.songs = songs;
	}

	public Playlist(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	public void add(String song){
		songs.add(song);
	}
}

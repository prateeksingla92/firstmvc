package com.singla.firstmvc.firstspring;

import java.util.List;


public class SongService {

	private SongDAO s;

	public SongService(SongDAO s) {
		this.s = s;
	}

	public Song getSong(int id) throws SongNotFoundException {
		return s.getSongById(id);
	}
	
	public List<Song> getSongs()
	{
		return s.getSongs();
	}

	public int getCount()
	{
		return s.getCount();
	}
	
	public void insert(Song song)
	{
		s.insert(song);
	}
	
	public int update(Song song)
	{
		return s.update(song);
	}
	
	public int delete(Song song)
	{
		return s.delete(song);
	}

}

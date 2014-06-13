package com.singla.firstmvc.firstspring;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SongService {

	@Autowired
	private SongMapper mapper;

	public Song getSongById(Integer songId) throws SongNotFoundException {

		Song song = null;
		song = mapper.getById(songId);
		if (song == null)
			throw new SongNotFoundException("Song not found");
		return song;
	}

	public List<Song> getSongs() {
		List<Song> s = new ArrayList<Song>();
		
		s = mapper.getAll();
		return s;
	}

	public int getCount() {
		
		return mapper.getCount();
	}

	public int insert(Song s) {
		return mapper.insert(s);
		
	}

	public int update(Song s) {
		return mapper.update(s);
	}

	public int delete(Song s) {
		return mapper.delete(s);
	}
}
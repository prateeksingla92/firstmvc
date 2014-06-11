package com.singla.firstmvc.firstspring;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SongDAO {

	

	private SqlSessionFactory sqlSessionFactory = null;

	public SongDAO(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public Song getSongById(Integer songId) throws SongNotFoundException {

		Song song = null;
		SqlSession session = sqlSessionFactory.openSession();
		//try {
		song = (Song) session.selectOne("Song.getById", songId);
		if(song==null)
			throw new SongNotFoundException("Song not found");
//		}
//		catch
		
		return song;
	}
	
	public List<Song> getSongs()
	{
		List<Song> s = new ArrayList<Song>();
		SqlSession session = sqlSessionFactory.openSession();
		s = session.selectList("Song.getAll");
		
		for(Song ss:s)
		{
			System.out.println(ss);
		}
		
		return s;
	}
	
	public int getCount()
	{
		SqlSession session = sqlSessionFactory.openSession();
		return (Integer)session.selectOne("Song.getCount");
	}
	
	public void insert(Song s)
	{
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("Song.insert",s);
		//session.commit();
	}

	public int update(Song s)
	{
		SqlSession session = sqlSessionFactory.openSession();
		return session.update("Song.update",s);
		//session.commit();
	}

	public int delete(Song s)
	{
		SqlSession session = sqlSessionFactory.openSession();
		return session.delete("Song.delete",s);
		//session.commit();
	}
}
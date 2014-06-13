package com.singla.firstmvc.firstspring;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface SongMapper {
	
	 @Select("SELECT * FROM SONGS ORDER BY ID")
	 public List<Song> getAll();
	
	 @Select("SELECT * FROM SONGS WHERE ID = #{id}")
	 public Song getById(@Param("id") int id);
	
	
	 @Select("SELECT COUNT(*) FROM SONGS")
	 public Integer getCount();
	 
	 
	 @Insert("INSERT INTO SONGS (name,lyrics) VALUES (#{name},#{lyrics})")
	    public int insert(Song song);

	@Update("UPDATE Songs SET name = #{name},lyrics = #{lyrics} WHERE id = #{id}")
	public int update(Song song);
	
	@Delete("DELETE from Songs WHERE id = #{id}")
	public int delete(Song song);
	


}

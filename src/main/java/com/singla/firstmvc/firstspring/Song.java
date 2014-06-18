package com.singla.firstmvc.firstspring;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Song implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	
	@Size(min=1,max=20)
	private String name;
	
	
	private String lyrics;

	public Song(Song s) {
		super();
		this.id = s.id;
		this.name = s.name;
		this.lyrics = s.lyrics;
	}

	public Song() {
	}

	
	public int getId() {
		return id;
	}

	public Song setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Song setName(String name) {
		this.name = name;
		return this;
	}

	public String getLyrics() {
		return lyrics;
	}

	public Song setLyrics(String lyrics) {
		this.lyrics = lyrics;
		return this;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", lyrics=" + lyrics + "]";
	}

	
}
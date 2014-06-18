package com.singla.firstmvc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.singla.firstmvc.firstspring.Song;
import com.singla.firstmvc.firstspring.SongNotFoundException;
import com.singla.firstmvc.firstspring.SongService;

@Controller
public class HomeController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String printHello(@PathVariable(value = "id") int id, ModelMap model) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-config.xml");

		String msg = "";

		SongService ss = (SongService) ctx.getBean("SongService");

		File f = new File("/home/prateeksingla/Desktop/objects.ser");
		
		ObjectOutputStream objOut = null;
		try {

			objOut = new ObjectOutputStream(new FileOutputStream(f));

			Song s = ss.getSongById(id);
			objOut.writeObject(s);
			msg += s.getLyrics() + "<br>";

		} catch (SongNotFoundException e) {
			msg += "SONG NOT FOUND<br>";
		} catch (Exception e) {
			msg += "SOME OTHER ERROR<br>";
		} finally {
			try {
				objOut.close();
			} catch (Exception e) {
				objOut = null;
			}

		}
		model.addAttribute("message", msg);
		return "hello";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printAll(ModelMap model) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-config.xml");

		String msg = "";

		SongService ss = (SongService) ctx.getBean("SongService");

		File f = new File("/home/prateeksingla/Desktop/objects.ser");
		
		ObjectOutputStream objOut = null;
		try {

			objOut = new ObjectOutputStream(new FileOutputStream(f));
			msg += ss.getCount() + "<br>";
			List<Song> SongList = ss.getSongs();
			for (Song s : SongList) {
				msg += s + "<br>";
			}
		} catch (Exception e) {
			msg += "SONG NOT FOUND<br>";

		} finally {
			try {
				objOut.close();
			} catch (Exception e) {
				objOut = null;
			}

		}
		model.addAttribute("message", msg);
		return "hello";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String addSong(@RequestBody @Validated Song song, ModelMap model) {

		String msg = "";
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		SongService ss = (SongService) ctx.getBean("SongService");

		if(song.getName()!=null) {
			ss.insert(song);
			msg += song + " added.";
		} else {
			msg += "SORRY :(";
		}
		model.addAttribute("message", msg);

		return "hello";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateSong(@RequestBody @Validated Song song,@PathVariable(value = "id") int id, ModelMap model) {

		String msg = "";
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		SongService ss = (SongService) ctx.getBean("SongService");

		song.setId(id);
		
		
			
		if(ss.update(song)>0) {
			msg += song.getName() + " " + song.getLyrics() + " updated.";
		} else {
			msg += "No such record";
		}
		model.addAttribute("message", msg);

		return "hello";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteSong(@RequestBody Song song, ModelMap model) {

		String msg = "";
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		SongService ss = (SongService) ctx.getBean("SongService");

					
		if(ss.delete(song)>0) {
			msg += song + " deleted.";
		} else {
			msg += "No such record";
		}
		model.addAttribute("message", msg);

		return "hello";
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public String displayCount(ModelMap model) {

		String msg = "";
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		SongService ss = (SongService) ctx.getBean("SongService");
		
		msg+= ss.getCount();
		
		model.addAttribute ("message", msg);

		return "hello";
	}
	
	

}
package com.singla.firstmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.singla.firstmvc.firstspring.SongService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/WEB-INF/FirstMVC-servlet.xml"})
public class HomeControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	MockHttpSession session;
	@Autowired
	MockHttpServletRequest request;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		 mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGetSignupForm() throws Exception {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		SongService ss = (SongService) ctx.getBean("SongService");
		
		String str = ""+ss.getCount();		
		this.mockMvc.perform(get("/count")).andExpect(status().isOk())
		.andExpect(view().name("hello"))
		.andExpect(forwardedUrl("hello.jsp"));
	}

}
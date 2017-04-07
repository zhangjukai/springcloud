package com.fcore.cloud.server;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fcore.cloud.eureka.client.controller.ComputeController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class ComputeControllerTest {
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new ComputeController()).build();
	}

	@Test
	public void add() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/add?a=10&b=20").accept(MediaType.ALL))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("30")));
	}
}

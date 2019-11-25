package com.guilhermemartinsdeoliveira.texoit.controllers;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntervalControllerIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test(expected = Test.None.class)
	public void testGetMinAndMaxIntervalsWithSuccess() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/producers/intervals")).andDo(print()).andExpect(status().isOk())
				.andReturn();
		String stringResponse = result.getResponse().getContentAsString();

		assertTrue(stringResponse.contains("\"producer\":\"Joel Silver\""));
		assertTrue(stringResponse.contains("\"producer\":\"Matthew Vaughn\""));
	}

	@Test(expected = Test.None.class)
	public void testGetMinAndMaxIntervalsOnWrongPathWithFail() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/producers/intervals/WRONGPATH")).andDo(print())
				.andExpect(status().isNotFound()).andReturn();

		assertTrue(result.getResponse().getStatus() == 404);
	}

}

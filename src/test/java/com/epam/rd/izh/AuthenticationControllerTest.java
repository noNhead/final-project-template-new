package com.epam.rd.izh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.net.URI;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLogin() throws Exception {
		this.mockMvc.perform(get("/login"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testLoginPost() throws Exception {
		this.mockMvc.perform((RequestBuilder) post(URI.create("/login/proceed")))
				.andDo(print())
				.andExpect(status().is3xxRedirection());
	}

	@Test
	public void testRegistration() throws Exception {
		this.mockMvc.perform(get("/registration"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testRegistrationPost() throws Exception {
		this.mockMvc.perform((RequestBuilder) post(URI.create("/")))
				.andDo(print())
				.andExpect(status().is3xxRedirection());
	}

	@Test
	@WithMockUser(username = "123", password = "123")
	public void testCheckUserPass() throws Exception {
		this.mockMvc.perform(get("/checkuserpass"))
				.andDo(print())
				.andExpect(status().isOk());
	}

}

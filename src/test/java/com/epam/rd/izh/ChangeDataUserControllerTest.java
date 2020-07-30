package com.epam.rd.izh;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "123", password = "123")
public class ChangeDataUserControllerTest {
    @Autowired
    MockMvc mockMvc;

    static HttpServletRequest request;
    static HttpServletResponse response;

    @BeforeClass
    public static void init(){
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testUserDataChange() throws Exception {
        this.mockMvc.perform(get("/userdatachange"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testUserDataChangeDelete() throws Exception {
        this.mockMvc.perform(get("/userdatachange/delete/process"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUserDelete() throws Exception {
        this.mockMvc.perform(get("/userdelete/process"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}

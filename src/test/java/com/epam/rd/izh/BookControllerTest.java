package com.epam.rd.izh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "123", password = "123")
    public void testBookForm() throws Exception {
        this.mockMvc.perform(get("/bookform")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "123", password = "123")
    public void testBookEdit() throws Exception {
        this.mockMvc.perform(get("/bookedit")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "123", password = "123")
    public void testBookDataChange() throws Exception {
        this.mockMvc.perform(get("/bookdatachange")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "123", password = "123")
    public void testDeleteBook() throws Exception {
        this.mockMvc.perform(get("/bookdatachange/delete/process")).andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "123", password = "123")
    public void testBook() throws Exception {
        this.mockMvc.perform(get("/book/{author}/{title}", "1", "1")).andDo(print()).andExpect(status().isOk());
    }
}

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
@WithMockUser(username = "123", password = "123")
public class TestSearchController {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testSearchPageWithoutParams() throws Exception {
        this.mockMvc.perform(get("/search"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchPage() throws Exception {
        this.mockMvc.perform(get("/search/{tags}", "t=1&a=&g=&d=&"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

package cn.practice.spring.boot.controller.v1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-01 11:25
 * QDescription:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestControllerTest {

    private MockMvc mockMvc;

    @Before
    public void befor() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
    }

    @Test
    public void testApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
//                .andExpect(content().);

    }

}

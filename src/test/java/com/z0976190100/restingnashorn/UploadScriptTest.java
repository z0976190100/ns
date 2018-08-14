package com.z0976190100.restingnashorn;

import com.z0976190100.restingnashorn.controller.UploadController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UploadScriptTest {

    private String script = "script=var result = 'result';'result';"; //?async=true&

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public UploadController uploadController;

    @Test
    public void uploadScriptAsync() throws Exception {

        this.mockMvc.perform(post("/script/" + script))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().string(containsString("Hello World")));

    }
}

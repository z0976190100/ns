package com.z0976190100.restingnashorn;

import com.z0976190100.restingnashorn.controller.ProcessorManagerController;
import com.z0976190100.restingnashorn.controller.ProcessorStateController;
import com.z0976190100.restingnashorn.controller.ResultController;
import com.z0976190100.restingnashorn.controller.ScriptManagerController;
import com.z0976190100.restingnashorn.service.ProcessorManagerService;
import com.z0976190100.restingnashorn.service.ProcessorStateService;
import com.z0976190100.restingnashorn.service.ResultService;
import com.z0976190100.restingnashorn.service.ScriptManagerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ScriptManagerController.class)
public class ScriptManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScriptManagerController scriptManagerController;

    @MockBean
    private ProcessorManagerController processorManagerController;

    @MockBean
    private ScriptManagerService scriptManagerService;

    @MockBean
    private ProcessorManagerService processorManagerService;

    @MockBean
    private ProcessorStateController processorStateController;

    @MockBean
    private ProcessorStateService processorStateService;

    @MockBean
    private ResultController resultController;

    @MockBean
    private ResultService resultService;


    @Test
    public void testAsyncScenarioCreatedStatus() throws Exception {
        this.mockMvc
                .perform(post("/script?script=var script='script';'script';"))
                .andExpect(status().isCreated());
               // .andExpect(content().string(containsString("script")));
    }
}
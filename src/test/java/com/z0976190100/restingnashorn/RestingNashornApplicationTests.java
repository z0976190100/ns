package com.z0976190100.restingnashorn;

import com.z0976190100.restingnashorn.controller.ProcessorManagerController;
import com.z0976190100.restingnashorn.controller.ProcessorStateController;
import com.z0976190100.restingnashorn.controller.ResultController;
import com.z0976190100.restingnashorn.controller.ScriptController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RestingNashornApplicationTests {

    @Autowired
    private ScriptController scriptController;
    @Autowired
    private ProcessorManagerController processorManagerController;
    @Autowired
    private ProcessorStateController processorStateController;
    @Autowired
    private ResultController resultController;

    @Test
    public void contextLoads() {
        assertThat(scriptController).isNotNull();
        assertThat(processorManagerController).isNotNull();
        assertThat(processorStateController).isNotNull();
        assertThat(resultController).isNotNull();
    }

}

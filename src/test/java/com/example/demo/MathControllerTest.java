package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testPi() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testCalculateAdd46() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));

    }

    @Test
    public void testCalculateAdd35() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=3&y=5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3 + 5 = 8"));

    }

    @Test
    public void testCalculateEmpty12() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?x=1&y=2");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("1 + 2 = 3"));

    }

    @Test
    public void testCalculateMultiply36() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=3&y=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3 * 6 = 18"));

    }

    @Test
    public void testCalculateMultiply46() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=4&y=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));

    }

    @Test
    public void testCalculateSubtract46() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=subtract&x=4&y=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));

    }

    @Test
    public void testCalculateDivide305() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=30&y=5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));

    }

    @Test
    public void testSum456() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/math/sum?n=4&n=5&n=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));

    }

    @Test
    public void testSum789() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/math/sum?n=7&n=8&n=9");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("7 + 8 + 9 = 24"));

    }

    @Test
    public void testVolume345() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/math/volume/3/4/5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }

    @Test
    public void testVolume135() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/math/volume/1/3/5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 1x3x5 rectangle is 15"));
    }


}

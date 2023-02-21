package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("10001"));
    }
    @Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
    //Add three more test cases for the binary API service.
    @Test
    public void add3() throws Exception { /* No operands */
        this.mvc.perform(get("/add"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }
    @Test
    public void add4() throws Exception { /* Json no operands */
        this.mvc.perform(get("/add_json"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
    @Test
    public void add5() throws Exception { /* Json 1 operand */
        this.mvc.perform(get("/add_json").param("operand1", "101010"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(101010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
    //Add test cases for each newly implemented operations. The test cases should cover almost all possible cases.
    //Multiply Tests
    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1","111").param("operand2","111"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("110001"));
    }
    @Test
    public void multiply_json() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","111").param("operand2","111"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(110001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }
    //And Tests
    @Test
    public void and() throws Exception {
        this.mvc.perform(get("/and").param("operand1","1010").param("operand2","1101"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1000"));
    }
    @Test
    public void and_json() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","1010").param("operand2","1101"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }
    //Multiply Test
    @Test
    public void or() throws Exception {
        this.mvc.perform(get("/or").param("operand1","1010").param("operand2","0110"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1110"));
    }
    @Test
    public void or_json() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","1010").param("operand2","0110"))//.andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }
}
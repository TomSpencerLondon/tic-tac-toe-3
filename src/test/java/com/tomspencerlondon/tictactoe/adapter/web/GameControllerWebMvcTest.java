package com.tomspencerlondon.tictactoe.adapter.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tomspencerlondon.tictactoe.adapter.in.web.GameController;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@Tag("integration")
@WebMvcTest(GameController.class)
@Import(TestGameConfiguration.class)
public class GameControllerWebMvcTest {


  @Autowired
  MockMvc mockMvc;

  @Test
  void getGameEndpointExists() throws Exception {
    mockMvc.perform(
        get("/")
    ).andExpect(status().isOk());
  }

  @Test
  void postGameEndpointExists() throws Exception {
    mockMvc.perform(post("/game?square=0_0"))
        .andExpect(status().is3xxRedirection());
  }

  @Test
  void postRestartEndpointExists() throws Exception {
    mockMvc.perform(post("/restart"))
        .andExpect(status().is3xxRedirection());
  }
}

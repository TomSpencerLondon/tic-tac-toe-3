package com.tomspencerlondon.tictactoe.adapter.web;

import com.tomspencerlondon.tictactoe.hexagon.application.GameService;
import com.tomspencerlondon.tictactoe.hexagon.domain.Board;
import com.tomspencerlondon.tictactoe.hexagon.domain.Computer;
import java.util.Random;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestGameConfiguration {

  @Primary
  @Bean
  GameService gameService() {
    return new GameService(new Board(new Computer(new Random())));
  }
}

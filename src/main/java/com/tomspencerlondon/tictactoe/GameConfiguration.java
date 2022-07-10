package com.tomspencerlondon.tictactoe;

import com.tomspencerlondon.tictactoe.hexagon.application.GameService;
import com.tomspencerlondon.tictactoe.hexagon.domain.Board;
import com.tomspencerlondon.tictactoe.hexagon.domain.Computer;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

  @Bean
  GameService gameService() {
    return new GameService(new Board(new Computer(new Random())));
  }
}

package com.tomspencerlondon.tictactoe.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

class ComputerTest {

  @Test
  void findsEmptySquare() {
    Computer computer = new Computer(new Random());

    List<List<String>> current = List.of(
        List.of("X", "O", "X"),
        List.of("O", "X", "O"),
        List.of("O", "X", ""));

    Position nextPosition = computer.nextPosition(current);

    assertThat(nextPosition)
        .isEqualTo(new Position(2, 2));
  }
}
package com.tomspencerlondon.tictactoe.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class BoardTest {

  @Test
  void newBoardIsEmpty() {
    Board board = new Board();

    List<List<String>> expected = List.of(
        List.of("", "", ""),
        List.of("", "", ""),
        List.of("", "", ""));

    assertThat(board.current())
        .isEqualTo(expected);
  }
}

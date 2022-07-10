package com.tomspencerlondon.tictactoe.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class BoardTest {

  @Test
  void newBoardIsEmpty() {
    Computer stubComputer = new StubComputer();
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("", "", ""),
        List.of("", "", ""),
        List.of("", "", ""));

    assertThat(board.current())
        .isEqualTo(expected);
  }

  @Test
  void boardAfterPlayHasTwoSquaresFilled() {
    Computer stubComputer = new StubComputer(new Position(1, 1));
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("X", "", ""),
        List.of("", "O", ""),
        List.of("", "", ""));

    board.play(new Position(0, 0));

    assertThat(board.current())
        .isEqualTo(expected);
  }

  @Test
  void givenBoardWithTwoSquaresPlayResultsInFourFilled() {
    Computer stubComputer = new StubComputer(new Position(1, 1), new Position(2, 1));
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("X", "X", ""),
        List.of("", "O", ""),
        List.of("", "O", ""));

    board.play(new Position(0, 0));
    board.play(new Position(0, 1));

    assertThat(board.current())
        .isEqualTo(expected);
  }
}

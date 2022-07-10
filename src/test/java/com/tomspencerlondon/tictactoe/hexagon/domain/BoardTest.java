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
  void twoPlaysResultsInFourFilled() {
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

  @Test
  void threePlaysResultsInSixFilled() {
    Computer stubComputer = new StubComputer(
        new Position(1, 1),
        new Position(2, 1),
        new Position(2, 0)
        );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("X", "X", ""),
        List.of("X", "O", ""),
        List.of("O", "O", ""));

    board.play(new Position(0, 0));
    board.play(new Position(0, 1));
    board.play(new Position(1, 0));

    assertThat(board.current())
        .isEqualTo(expected);
  }

  @Test
  void fourPlaysResultsInEightFilled() {
    Computer stubComputer = new StubComputer(
        new Position(1, 1),
        new Position(2, 1),
        new Position(2, 0),
        new Position(1, 2)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("X", "X", ""),
        List.of("X", "O", "O"),
        List.of("O", "O", "X"));

    board.play(new Position(0, 0));
    board.play(new Position(0, 1));
    board.play(new Position(1, 0));
    board.play(new Position(2, 2));

    assertThat(board.current())
        .isEqualTo(expected);
  }

  @Test
  void fivePlaysResultsInFullBoard() {
    Computer stubComputer = new StubComputer(
        new Position(1, 1),
        new Position(2, 1),
        new Position(2, 0),
        new Position(1, 2)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("X", "X", "X"),
        List.of("X", "O", "O"),
        List.of("O", "O", "X"));

    board.play(new Position(0, 0));
    board.play(new Position(0, 1));
    board.play(new Position(1, 0));
    board.play(new Position(2, 2));
    board.play(new Position(0, 2));

    assertThat(board.current())
        .isEqualTo(expected);
  }
}

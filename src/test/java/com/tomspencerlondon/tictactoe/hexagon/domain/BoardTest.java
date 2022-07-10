package com.tomspencerlondon.tictactoe.hexagon.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoardTest {

  @Test
  void newBoardIsEmpty() {
    Computer stubComputer = new StubComputer();
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(List.of("", "", ""), List.of("", "", ""), List.of("", "", ""));

    assertThat(board.current()).isEqualTo(expected);
  }

  @Test
  void boardAfterPlayHasTwoSquaresFilled() {
    Computer stubComputer = new StubComputer(new Position(1, 1));
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(List.of("X", "", ""), List.of("", "O", ""), List.of("", "", ""));

    play(board, new Position(0, 0));

    assertThat(board.current()).isEqualTo(expected);
  }

  @Test
  void twoPlaysResultsInFourFilled() {
    Computer stubComputer = new StubComputer(new Position(1, 1), new Position(2, 1));
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(List.of("X", "X", ""), List.of("", "O", ""), List.of("", "O", ""));

    play(board, new Position(0, 0), new Position(0, 1));

    assertThat(board.current()).isEqualTo(expected);
  }

  @Test
  void threePlaysResultsInSixFilled() {
    Computer stubComputer = new StubComputer(new Position(1, 1), new Position(2, 1), new Position(2, 0));
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(List.of("X", "X", ""), List.of("X", "O", ""), List.of("O", "O", ""));

    play(board, new Position(0, 0), new Position(0, 1), new Position(1, 0));

    assertThat(board.current()).isEqualTo(expected);
  }

  @Test
  void fourPlaysResultsInEightFilled() {
    Computer stubComputer = new StubComputer(new Position(1, 1), new Position(2, 1), new Position(2, 0), new Position(1, 2));
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(List.of("X", "X", ""), List.of("X", "O", "O"), List.of("O", "O", "X"));

    play(board, new Position(0, 0), new Position(0, 1), new Position(1, 0), new Position(2, 2));

    assertThat(board.current()).isEqualTo(expected);
  }

  @Test
  void fullBoardWithoutWinnerResultsInDraw() {
    Computer stubComputer = new StubComputer(new Position(0, 0), new Position(0, 2), new Position(1, 0), new Position(2, 1));
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("O", "X", "O"),
        List.of("O", "X", "X"),
        List.of("X", "O", "X"));

    play(board,
        new Position(0, 1), new Position(1, 1),
        new Position(1, 2), new Position(2, 0),
        new Position(2, 2));

    assertThat(board.current()).isEqualTo(expected);
    assertThat(board.result()).isEqualTo(Result.DRAW);
  }

  @ParameterizedTest
  @MethodSource("argumentsForPlayerWins")
  void playerWins(Computer stubComputer, List<List<String>> expectedBoard, Position[] positions, Result result) {
    Board board = new Board(stubComputer);

    play(board, positions);

    assertThat(board.current()).isEqualTo(expectedBoard);
    assertThat(board.result()).isEqualTo(result);
  }

  @ParameterizedTest
  @MethodSource("argumentsForComputerWins")
  void computerWins(Computer stubComputer, List<List<String>> expectedBoard, Position[] positions, Result result) {
    Board board = new Board(stubComputer);

    play(board, positions);

    assertThat(board.current()).isEqualTo(expectedBoard);
    assertThat(board.result()).isEqualTo(result);
  }

  public static Stream<Arguments> argumentsForComputerWins() {
    return Stream.of(
        Arguments.of(
            new StubComputer(new Position(0, 0), new Position(0, 1), new Position(0, 2)),
            List.of(List.of("O", "O", "O"), List.of("", "X", ""), List.of("", "X", "X")),
            new Position[] {new Position(1, 1), new Position(2, 1), new Position(2, 2)},
            Result.COMPUTER_WINS),
        Arguments.of(
            new StubComputer(new Position(1, 0), new Position(1, 1), new Position(1, 2)),
            List.of(List.of("", "X", ""), List.of("O", "O", "O"), List.of("", "X", "X")),
            new Position[] {new Position(0, 1), new Position(2, 1), new Position(2, 2)},
            Result.COMPUTER_WINS),
        Arguments.of(
            new StubComputer(new Position(2, 0), new Position(2, 1), new Position(2, 2)),
            List.of(List.of("", "X", ""), List.of("", "X", "X"), List.of("O", "O", "O")),
            new Position[] {new Position(0, 1), new Position(1, 1), new Position(1, 2)},
            Result.COMPUTER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 0), new Position(1, 0), new Position(2, 0)),
            List.of(List.of("O", "X", ""), List.of("O", "X", "X"), List.of("O", "", "")),
            new Position[] {new Position(0, 1), new Position(1, 1), new Position(1, 2)},
            Result.COMPUTER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 1), new Position(1, 1), new Position(2, 1)),
            List.of(List.of("X", "O", ""), List.of("X", "O", "X"), List.of("", "O", "")),
            new Position[] {new Position(0, 0), new Position(1, 0), new Position(1, 2)},
            Result.COMPUTER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 2), new Position(1, 2), new Position(2, 2)),
            List.of(List.of("X", "", "O"), List.of("X", "X", "O"), List.of("", "", "O")),
            new Position[] {new Position(0, 0), new Position(1, 0), new Position(1, 1)},
            Result.COMPUTER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 0), new Position(1, 1), new Position(2, 2)),
            List.of(List.of("O", "", "X"), List.of("X", "O", "X"), List.of("", "", "O")),
            new Position[] {new Position(0, 2), new Position(1, 0), new Position(1, 2)},
            Result.COMPUTER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 2), new Position(1, 1), new Position(2, 0)),
            List.of(List.of("X", "", "O"), List.of("X", "O", "X"), List.of("O", "", "")),
            new Position[] {new Position(0, 0), new Position(1, 0), new Position(1, 2)},
            Result.COMPUTER_WINS)
    );
  }

  public static Stream<Arguments> argumentsForPlayerWins() {
    return Stream.of(
        Arguments.of(
            new StubComputer(new Position(1, 1), new Position(2, 1)),
            List.of(List.of("X", "X", "X"), List.of("", "O", ""), List.of("", "O", "")),
            new Position[] {new Position(0, 0), new Position(0, 1), new Position(0, 2)},
            Result.PLAYER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 1), new Position(2, 1)),
            List.of(List.of("", "O", ""), List.of("X", "X", "X"), List.of("", "O", "")),
            new Position[] {new Position(1, 0), new Position(1, 1), new Position(1, 2)},
            Result.PLAYER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 1), new Position(1, 1)),
            List.of(List.of("", "O", ""), List.of("", "O", ""), List.of("X", "X", "X")),
            new Position[] {new Position(2, 0), new Position(2, 1), new Position(2, 2)},
            Result.PLAYER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 1), new Position(1, 1)),
            List.of(List.of("X", "O", ""), List.of("X", "O", ""), List.of("X", "", "")),
            new Position[] {new Position(0, 0), new Position(1, 0), new Position(2, 0)},
            Result.PLAYER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 0), new Position(1, 0)),
            List.of(List.of("O", "X", ""), List.of("O", "X", ""), List.of("", "X", "")),
            new Position[] {new Position(0, 1), new Position(1, 1), new Position(2, 1)},
            Result.PLAYER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 0), new Position(1, 0)),
            List.of(List.of("O", "", "X"), List.of("O", "", "X"), List.of("", "", "X")),
            new Position[] {new Position(0, 2), new Position(1, 2), new Position(2, 2)},
            Result.PLAYER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 2), new Position(1, 0)),
            List.of(List.of("X", "", "O"), List.of("O", "X", ""), List.of("", "", "X")),
            new Position[] {new Position(0, 0), new Position(1, 1), new Position(2, 2)},
            Result.PLAYER_WINS),
        Arguments.of(
            new StubComputer(new Position(0, 0), new Position(1, 0)),
            List.of(List.of("O", "", "X"), List.of("O", "X", ""), List.of("X", "", "")),
            new Position[] {new Position(0, 2), new Position(1, 1), new Position(2, 0)},
            Result.PLAYER_WINS)
    );
  }


  private void play(Board board, Position... positions) {
    for (Position position : positions) {
      board.play(position);
    }
  }
}

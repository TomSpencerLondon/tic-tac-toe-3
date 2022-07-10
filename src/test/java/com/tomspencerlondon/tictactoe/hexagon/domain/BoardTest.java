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
        new Position(0, 2)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("X", "X", "O"),
        List.of("X", "O", "X"),
        List.of("O", "O", "X"));

    board.play(new Position(0, 0));
    board.play(new Position(0, 1));
    board.play(new Position(1, 0));
    board.play(new Position(1, 2));
    board.play(new Position(2, 2));

    assertThat(board.current())
        .isEqualTo(expected);
  }

  @Test
  void fullBoardWithoutWinnerResultsInDraw() {
    Computer stubComputer = new StubComputer(
        new Position(1, 1),
        new Position(2, 1),
        new Position(2, 0),
        new Position(0, 2)
    );
    Board board = new Board(stubComputer);

    board.play(new Position(0, 0));
    board.play(new Position(0, 1));
    board.play(new Position(1, 0));
    board.play(new Position(1, 2));
    board.play(new Position(2, 2));

    assertThat(board.result())
        .isEqualTo(Result.DRAW);
  }

  @Test
  void playerWinsThreeInTopRow() {
    Computer stubComputer = new StubComputer(
        new Position(1, 1),
        new Position(2, 1)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("X", "X", "X"),
        List.of("", "O", ""),
        List.of("", "O", ""));

    board.play(new Position(0, 0));
    board.play(new Position(0, 1));
    board.play(new Position(0, 2));

    assertThat(board.current())
        .isEqualTo(expected);

    assertThat(board.result())
        .isEqualTo(Result.PLAYER_WINS);
  }


  @Test
  void playerWinsThreeInMiddleRow() {
    Computer stubComputer = new StubComputer(
        new Position(0, 1),
        new Position(2, 1)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("", "O", ""),
        List.of("X", "X", "X"),
        List.of("", "O", ""));

    board.play(new Position(1, 0));
    board.play(new Position(1, 1));
    board.play(new Position(1, 2));

    assertThat(board.current())
        .isEqualTo(expected);

    assertThat(board.result())
        .isEqualTo(Result.PLAYER_WINS);
  }

  @Test
  void playerWinsThreeInBottomRow() {
    Computer stubComputer = new StubComputer(
        new Position(0, 1),
        new Position(1, 1)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("", "O", ""),
        List.of("", "O", ""),
        List.of("X", "X", "X"));

    board.play(new Position(2, 0));
    board.play(new Position(2, 1));
    board.play(new Position(2, 2));

    assertThat(board.current())
        .isEqualTo(expected);

    assertThat(board.result())
        .isEqualTo(Result.PLAYER_WINS);
  }

  @Test
  void playerWinsThreeInLeftColumn() {
    Computer stubComputer = new StubComputer(
        new Position(0, 1),
        new Position(1, 1)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("X", "O", ""),
        List.of("X", "O", ""),
        List.of("X", "", ""));

    board.play(new Position(0, 0));
    board.play(new Position(1, 0));
    board.play(new Position(2, 0));

    assertThat(board.current())
        .isEqualTo(expected);

    assertThat(board.result())
        .isEqualTo(Result.PLAYER_WINS);
  }

  @Test
  void playerWinsThreeInMiddleColumn() {
    Computer stubComputer = new StubComputer(
        new Position(0, 0),
        new Position(1, 0)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("O", "X", ""),
        List.of("O", "X", ""),
        List.of("", "X", ""));

    board.play(new Position(0, 1));
    board.play(new Position(1, 1));
    board.play(new Position(2, 1));

    assertThat(board.current())
        .isEqualTo(expected);

    assertThat(board.result())
        .isEqualTo(Result.PLAYER_WINS);
  }

  @Test
  void playerWinsThreeInRightColumn() {
    Computer stubComputer = new StubComputer(
        new Position(0, 0),
        new Position(1, 0)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("O", "", "X"),
        List.of("O", "", "X"),
        List.of("", "", "X"));

    board.play(new Position(0, 2));
    board.play(new Position(1, 2));
    board.play(new Position(2, 2));

    assertThat(board.current())
        .isEqualTo(expected);

    assertThat(board.result())
        .isEqualTo(Result.PLAYER_WINS);
  }

  @Test
  void playerWinsDiagonalTopLeftToBottomRight() {
    Computer stubComputer = new StubComputer(
        new Position(0, 2),
        new Position(1, 0)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("X", "", "O"),
        List.of("O", "X", ""),
        List.of("", "", "X"));

    board.play(new Position(0, 0));
    board.play(new Position(1, 1));
    board.play(new Position(2, 2));

    assertThat(board.current())
        .isEqualTo(expected);

    assertThat(board.result())
        .isEqualTo(Result.PLAYER_WINS);
  }

  @Test
  void playerWinsDiagonalTopRightToBottomLeft() {
    Computer stubComputer = new StubComputer(
        new Position(0, 0),
        new Position(1, 0)
    );
    Board board = new Board(stubComputer);

    List<List<String>> expected = List.of(
        List.of("O", "", "X"),
        List.of("O", "X", ""),
        List.of("X", "", ""));

    board.play(new Position(0, 2));
    board.play(new Position(1, 1));
    board.play(new Position(2, 0));

    assertThat(board.current())
        .isEqualTo(expected);

    assertThat(board.result())
        .isEqualTo(Result.PLAYER_WINS);
  }
}

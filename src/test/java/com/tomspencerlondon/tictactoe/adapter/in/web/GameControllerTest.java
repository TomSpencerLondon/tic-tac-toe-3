package com.tomspencerlondon.tictactoe.adapter.in.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import com.tomspencerlondon.tictactoe.hexagon.application.GameService;
import com.tomspencerlondon.tictactoe.hexagon.domain.Board;
import com.tomspencerlondon.tictactoe.hexagon.domain.Computer;
import com.tomspencerlondon.tictactoe.hexagon.domain.Position;
import com.tomspencerlondon.tictactoe.hexagon.domain.Result;
import com.tomspencerlondon.tictactoe.hexagon.domain.StubComputer;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

class GameControllerTest {

  @Test
  void newGameReturnsEmptyBoard() {
    Board board = new Board(new Computer(new Random()));
    GameService gameService = new GameService(board);

    GameController controller = new GameController(gameService);

    Model model = new ConcurrentModel();
    String page = controller.game(model);

    assertThat(page)
        .isEqualTo("game");
    GameView gameView = (GameView) model.getAttribute("game");
    assertThat(gameView.isGameOn())
        .isTrue();
    assertThat(gameView.getBoard().get(0))
        .containsExactly("", "", "");
    assertThat(gameView.getBoard().get(1))
        .containsExactly("", "", "");
    assertThat(gameView.getBoard().get(2))
        .containsExactly("", "", "");
    assertThat(gameView.getResult())
        .isEqualTo("Game On!");
  }

  @Test
  void givenPlayGameBoardHasTwoSquares() {
    Board board = new Board(new StubComputer(new Position(0, 1)));
    GameService gameService = new GameService(board);

    GameController controller = new GameController(gameService);

    Model model = new ConcurrentModel();
    controller.game(model);
    controller.playGame("0_0");

    GameView gameView = (GameView) model.getAttribute("game");
    assertThat(gameView.isGameOn())
        .isTrue();
    assertThat(gameView.getBoard().get(0))
        .containsExactly("X", "O", "");
    assertThat(gameView.getBoard().get(1))
        .containsExactly("", "", "");
    assertThat(gameView.getBoard().get(2))
        .containsExactly("", "", "");
    assertThat(gameView.getResult())
        .isEqualTo("Game On!");
  }

  @Test
  void givenPlayGameRestartReturnsEmptyBoard() {
    Board board = new Board(new StubComputer(new Position(0, 1)));
    GameService gameService = new GameService(board);

    GameController controller = new GameController(gameService);

    Model model = new ConcurrentModel();
    controller.game(model);
    controller.playGame("0_0");
    controller.restart();

    GameView gameView = (GameView) model.getAttribute("game");
    assertThat(gameView.isGameOn())
        .isTrue();
    assertThat(gameView.getBoard().get(0))
        .containsExactly("", "", "");
    assertThat(gameView.getBoard().get(1))
        .containsExactly("", "", "");
    assertThat(gameView.getBoard().get(2))
        .containsExactly("", "", "");
    assertThat(gameView.getResult())
        .isEqualTo("Game On!");
  }

  @Test
  void fullBoardWithoutWinnerResultsInDraw() {
    Computer stubComputer = new StubComputer(new Position(0, 0), new Position(0, 2), new Position(1, 0), new Position(2, 1));
    Board board = new Board(stubComputer);
    GameService gameService = new GameService(board);
    GameController controller = new GameController(gameService);

    Model model = new ConcurrentModel();
    controller.game(model);
    controller.playGame("0_1");
    controller.playGame("1_1");
    controller.playGame("1_2");
    controller.playGame("2_0");
    controller.playGame("2_2");
    controller.game(model);

    GameView gameView = (GameView) model.getAttribute("game");
    assertThat(gameView.isGameOn())
        .isFalse();
    assertThat(gameView.getBoard().get(0))
        .containsExactly("O", "X", "O");
    assertThat(gameView.getBoard().get(1))
        .containsExactly("O", "X", "X");
    assertThat(gameView.getBoard().get(2))
        .containsExactly("X", "O", "X");
    assertThat(gameView.getResult())
        .isEqualTo("Draw!");
  }

  @Test
  void givenHorizontalLinePlayerWins() {
    Computer stubComputer = new StubComputer(new Position(1, 1), new Position(2, 1));
    Board board = new Board(stubComputer);
    GameService gameService = new GameService(board);
    GameController controller = new GameController(gameService);
    Model model = new ConcurrentModel();
    controller.game(model);
    controller.playGame("0_0");
    controller.playGame("0_1");
    controller.playGame("0_2");

    controller.game(model);

    GameView gameView = (GameView) model.getAttribute("game");
    assertThat(gameView.isGameOn())
        .isFalse();
    assertThat(gameView.getBoard().get(0))
        .containsExactly("X", "X", "X");
    assertThat(gameView.getBoard().get(1))
        .containsExactly("", "O", "");
    assertThat(gameView.getBoard().get(2))
        .containsExactly("", "O", "");
    assertThat(gameView.getResult())
        .isEqualTo("Player wins!");
  }

  @Test
  void givenLeftToRightDiagonalComputerWins() {
    Computer stubComputer = new StubComputer(new Position(0, 0), new Position(1, 1), new Position(2, 2));
    Board board = new Board(stubComputer);
    GameService gameService = new GameService(board);
    GameController controller = new GameController(gameService);
    Model model = new ConcurrentModel();
    controller.game(model);
    controller.playGame("0_2");
    controller.playGame("1_0");
    controller.playGame("1_2");

    controller.game(model);

    GameView gameView = (GameView) model.getAttribute("game");
    assertThat(gameView.isGameOn())
        .isFalse();
    assertThat(gameView.getBoard().get(0))
        .containsExactly("O", "", "X");
    assertThat(gameView.getBoard().get(1))
        .containsExactly("X", "O", "X");
    assertThat(gameView.getBoard().get(2))
        .containsExactly("", "", "O");
    assertThat(gameView.getResult())
        .isEqualTo("Computer wins!");
  }
}
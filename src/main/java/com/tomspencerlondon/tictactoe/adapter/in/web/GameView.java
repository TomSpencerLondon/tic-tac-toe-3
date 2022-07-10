package com.tomspencerlondon.tictactoe.adapter.in.web;

import static com.tomspencerlondon.tictactoe.hexagon.domain.Result.GAME_ON;

import com.tomspencerlondon.tictactoe.hexagon.domain.Board;
import com.tomspencerlondon.tictactoe.hexagon.domain.Result;
import java.util.List;

public class GameView {
  private final List<List<String>> board;
  private final String result;
  private final boolean gameOn;

  private GameView(List<List<String>> board, String result, boolean gameOn) {
    this.board = board;
    this.result = result;
    this.gameOn = gameOn;
  }

  public static GameView from(Board board) {
    List<List<String>> current = board.current();
    Result result = board.result();
    return new GameView(current, resultDisplayFrom(result), result == GAME_ON);
  }

  private static String resultDisplayFrom(Result result) {
    switch (result) {
      case PLAYER_WINS -> {
        return "Player wins!";
      }
      case COMPUTER_WINS -> {
        return "Computer wins!";
      }
      case DRAW -> {
        return "Draw!";
      }
      default -> {
        return "Game On!";
      }
    }
  }

  public List<List<String>> getBoard() {
    return board;
  }

  public String getResult() {
    return result;
  }

  public boolean isGameOn() {
    return gameOn;
  }
}

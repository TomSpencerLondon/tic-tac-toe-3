package com.tomspencerlondon.tictactoe.hexagon.application;

import com.tomspencerlondon.tictactoe.hexagon.domain.Board;
import com.tomspencerlondon.tictactoe.hexagon.domain.Position;
import java.util.Arrays;
import java.util.List;

public class GameService {

  private final Board board;

  public GameService(Board board) {
    this.board = board;
  }


  public Board board() {
    return board;
  }

  public void play(String square) {
    List<Integer> values = Arrays.stream(square.split("_")).map(Integer::parseInt).toList();
    Position position = new Position(values.get(0), values.get(1));
    board.play(position);
  }

  public void restart() {
    board.restart();
  }
}

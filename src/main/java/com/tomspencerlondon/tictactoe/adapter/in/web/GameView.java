package com.tomspencerlondon.tictactoe.adapter.in.web;

import java.util.List;

public class GameView {
  private List<List<String>> board = List.of(
      List.of("X", "X", "X"),
      List.of("X", "X", "X"),
      List.of("X", "X", "X")
  );
  private String result;

  public List<List<String>> getBoard() {
    return board;
  }

  public String getResult() {
    return result;
  }
}

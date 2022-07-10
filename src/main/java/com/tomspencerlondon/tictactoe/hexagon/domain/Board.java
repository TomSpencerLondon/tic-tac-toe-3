package com.tomspencerlondon.tictactoe.hexagon.domain;

import java.util.List;

public class Board {

  private List<List<String>> current;

  public Board() {
    this.current = List.of(
        List.of("", "", ""),
        List.of("", "", ""),
        List.of("", "", "")
    );
  }

  public List<List<String>> current() {
    return current;
  }
}

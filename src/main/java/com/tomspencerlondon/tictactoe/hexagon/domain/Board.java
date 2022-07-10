package com.tomspencerlondon.tictactoe.hexagon.domain;

import static java.util.Arrays.asList;

import java.util.List;

public class Board {

  private List<List<String>> current;
  private Computer computer;

  public Board(Computer computer) {
    this.computer = computer;
    current = asList(asList("", "", ""), asList("", "", ""), asList("", "", ""));
  }

  public List<List<String>> current() {
    return current;
  }

  public void play(Position position) {
    current.get(position.x()).set(position.y(), "X");
    if (freeSquare()) {
      computerPlay();
    }
  }

  private void computerPlay() {
    Position computerPosition = computer.nextPosition();
    current.get(computerPosition.x()).set(computerPosition.y(), "O");
  }

  private boolean freeSquare() {
    for (List<String> line : current) {
      for (String square : line) {
        if (square.isEmpty()) {
          return true;
        }
      }
    }

    return false;
  }
}

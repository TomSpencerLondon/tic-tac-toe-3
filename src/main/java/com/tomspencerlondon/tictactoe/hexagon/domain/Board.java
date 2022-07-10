package com.tomspencerlondon.tictactoe.hexagon.domain;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.List;

public class Board {

  private final List<List<String>> current;
  private final Computer computer;

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
    return current.stream()
        .flatMap(Collection::stream)
        .anyMatch(String::isEmpty);
  }
}

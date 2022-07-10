package com.tomspencerlondon.tictactoe.hexagon.domain;

import static java.util.Arrays.asList;

import java.util.Iterator;

public class StubComputer extends Computer {
  private Iterator<Position> iterator;

  public StubComputer(Position... positions) {
    iterator = asList(positions).iterator();
  }

  @Override
  public Position nextPosition() {
    return iterator.next();
  }
}

package com.tomspencerlondon.tictactoe.hexagon.domain;

import static java.util.Arrays.asList;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StubComputer extends Computer {
  private Iterator<Position> iterator;

  public StubComputer(Position... positions) {
    super(new Random());
    iterator = asList(positions).iterator();
  }

  @Override
  public Position nextPosition(List<List<String>> current) {
    return iterator.next();
  }
}

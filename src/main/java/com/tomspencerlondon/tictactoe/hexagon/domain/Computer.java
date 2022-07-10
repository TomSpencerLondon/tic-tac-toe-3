package com.tomspencerlondon.tictactoe.hexagon.domain;

import java.util.List;
import java.util.Random;

public class Computer {

  private final Random random;

  public Computer(Random random) {
    this.random = random;
  }

  public Position nextPosition(List<List<String>> current) {
    int x;
    int y;
    do {
      x = random.nextInt(3);
      y = random.nextInt(3);
    } while (!current.get(x).get(y).isEmpty());

    return new Position(x, y);
  }
}

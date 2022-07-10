package com.tomspencerlondon.tictactoe.hexagon.domain;

public class StubComputer extends Computer {

  @Override
  public Position nextPosition() {
    return new Position(1, 1);
  }
}

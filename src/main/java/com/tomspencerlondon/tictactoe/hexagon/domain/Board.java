package com.tomspencerlondon.tictactoe.hexagon.domain;

import static java.util.Arrays.asList;
import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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
    if (freeSquare() && !isPlayerWinner()) {
      computerPlay();
    }
  }

  private boolean isPlayerWinner() {
    return isRowWin("X") || isColumnWin("X") || isDiagonalWin("X");
  }

  private boolean isDiagonalWin(String player) {
    List<String> playerWins = List.of(player, player, player);
    return List.of(current.get(0).get(0), current.get(1).get(1), current.get(2).get(2)).equals(playerWins)
        || List.of(current.get(0).get(2), current.get(1).get(1), current.get(2).get(0)).equals(playerWins);
  }

  private boolean isRowWin(String player) {
    return current.contains(List.of(player, player, player));
  }

  private boolean isColumnWin(String player) {
    return columns()
        .values()
        .stream()
        .anyMatch(c -> c.equals(List.of(player, player, player)));
  }

  private Map<Integer, List<String>> columns() {
    Map<Integer, List<String>> columns = Map.ofEntries(
        entry(0, new ArrayList<>()),
        entry(1, new ArrayList<>()),
        entry(2, new ArrayList<>())
    );

    current.forEach(line -> {
      IntStream.range(0, 3).forEachOrdered(i -> {
        columns.get(i).add(line.get(i));
      });
    });

    return columns;
  }

  private void computerPlay() {
    Position computerPosition = computer.nextPosition(current);
    current.get(computerPosition.x()).set(computerPosition.y(), "O");
  }

  private boolean freeSquare() {
    return current.stream().flatMap(Collection::stream).anyMatch(String::isEmpty);
  }

  public Result result() {
    if (isPlayerWinner()) {
      return Result.PLAYER_WINS;
    } else if (isComputerWinner()) {
      return Result.COMPUTER_WINS;
    } else if (!freeSquare()) {
      return Result.DRAW;
    }

    return Result.GAME_ON;
  }

  private boolean isComputerWinner() {
    return isRowWin("O") || isColumnWin("O") || isDiagonalWin("O");
  }
}

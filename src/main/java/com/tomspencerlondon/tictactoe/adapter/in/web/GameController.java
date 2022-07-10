package com.tomspencerlondon.tictactoe.adapter.in.web;

import com.tomspencerlondon.tictactoe.hexagon.application.GameService;
import com.tomspencerlondon.tictactoe.hexagon.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/")
  public String game(Model model) {
    Board board = gameService.board();
    model.addAttribute("game", GameView.from(board));
    return "game";
  }

  @PostMapping("/game")
  public String playGame(@RequestParam(name = "square") String square) {
    gameService.play(square);
    return "redirect:/";
  }

  @PostMapping("/restart")
  public String restart() {
    gameService.restart();
    return "redirect:/";
  }
}

package com.tomspencerlondon.tictactoe.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GameController {

  @GetMapping("/")
  public String game(Model model) {
    model.addAttribute("game", new GameView());
    return "game";
  }
}

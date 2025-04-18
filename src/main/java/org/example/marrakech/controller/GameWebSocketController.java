package org.example.marrakech.controller;

import org.example.marrakech.dto.GameUpdateMessage;
import org.example.marrakech.entity.Game;
import org.example.marrakech.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class GameWebSocketController {

  private final SimpMessagingTemplate messagingTemplate;
  private final GameService gameService;

  @Autowired
  public GameWebSocketController(SimpMessagingTemplate messagingTemplate, GameService gameService) {
    this.messagingTemplate = messagingTemplate;
    this.gameService = gameService;
  }

  @MessageMapping("/game/update")
  public void updateGame(GameUpdateMessage message) {
    Optional<Game> optionalGame = gameService.getGameById(message.getGameId());
    if (optionalGame.isPresent()) {
      Game game = optionalGame.get();
      String currentTurnUsername = game.getCurrentTurn() != null ? game.getCurrentTurn().getUsername() : "none";
      GameUpdateMessage update = new GameUpdateMessage(game.getId(), game.getStatus(), currentTurnUsername);
      messagingTemplate.convertAndSend("/topic/game/" + game.getId(), update);
    }
  }
}

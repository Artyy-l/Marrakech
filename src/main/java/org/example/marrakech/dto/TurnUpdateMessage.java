package org.example.marrakech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnUpdateMessage {
  private Long gameId;
  private String currentPlayerUsername;
  private int moveNumber;
}

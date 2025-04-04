package org.example.marrakech.repository;

import org.example.marrakech.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
  @Query("SELECT g FROM Game g WHERE g.status = :status AND cardinality(g.turnOrder) < :maxPlayers ORDER BY g.id ASC")
  Optional<Game> findFirstByStatusAndPlayerCountLessThan(String status, int maxPlayers);

  @Query("SELECT COUNT(gp) FROM GamePlayer gp WHERE gp.game.id = :gameId")
  int countPlayersInGame(@Param("gameId") Long gameId);
}

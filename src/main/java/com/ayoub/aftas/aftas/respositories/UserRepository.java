package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
    @Query("SELECT u FROM User u " +
            "WHERE u.id NOT IN ( " +
            "  SELECT r.user.id FROM Ranking r WHERE r.competition.id = :competitionId " +
            ")")
    List<User> findUserNotRankedInCompetition(@Param("competitionId") Long competitionId);

    @Query("SELECT u FROM User u " +
            "WHERE u.id  IN ( " +
            "  SELECT r.user.id FROM Ranking r WHERE r.competition.id = :competitionId " +
            ")")
    List<User> findUserRankedInCompetition(@Param("competitionId") Long competitionId);
}


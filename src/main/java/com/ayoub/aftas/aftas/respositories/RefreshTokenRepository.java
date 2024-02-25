package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);

    @Query ("select t from RefreshToken  t inner join User u on t.user.id = u.id where u.id = :userId")
    Optional<RefreshToken> findByUser(@Param("userId") Long userId);
}

package com.aishwarya.BookMyShow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aishwarya.BookMyShow.models.Show;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    @Override
    Optional<Show> findById(Long showId);
}

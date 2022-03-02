package com.qa.blockbuster.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.blockbuster.domain.Blockbuster;

@Repository
public interface BlockbusterRepo extends JpaRepository<Blockbuster, Long> {
}

package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}

package com.cs.cn.repository;

import com.cs.cn.model.Agent;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    ArrayList<Agent> findByStateTrue();
    Optional<Agent> findByUsername(String username);
}

package com.example.yourcar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.yourcar.entities.Agent;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
    Agent findByMail(String mail);
}

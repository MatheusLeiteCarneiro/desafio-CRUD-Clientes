package com.devmlc.clientCRUD.repositories;

import com.devmlc.clientCRUD.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{
}

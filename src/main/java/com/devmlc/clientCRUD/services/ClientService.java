package com.devmlc.clientCRUD.services;

import com.devmlc.clientCRUD.dto.ClientDTO;
import com.devmlc.clientCRUD.entities.Client;
import com.devmlc.clientCRUD.exceptions.ResourceNotFoundException;
import com.devmlc.clientCRUD.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public ClientDTO findById(Long id){
        Client client  = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Resource not Found"));
        return tranformEntityToDTO(client);
    }


    public ClientDTO tranformEntityToDTO(Client client){
        return new ClientDTO(client);
    }

}

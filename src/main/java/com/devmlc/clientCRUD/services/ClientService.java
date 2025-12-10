package com.devmlc.clientCRUD.services;

import com.devmlc.clientCRUD.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;


}

package com.devmlc.clientCRUD.services;

import com.devmlc.clientCRUD.dto.ClientDTO;
import com.devmlc.clientCRUD.entities.Client;
import com.devmlc.clientCRUD.exceptions.ResourceNotFoundException;
import com.devmlc.clientCRUD.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client  = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Resource not Found"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
       Page<Client> page = repository.findAll(pageable);
       return page.map(x -> new ClientDTO(x));
    }

    public ClientDTO insert(ClientDTO dto){
        Client client = copyDtotoEntity(dto);
        client =  repository.save(client);
        return new ClientDTO(client);
    }


    private Client copyDtotoEntity(ClientDTO dto){
        Client newClient = new Client();
        newClient.setName(dto.getName());
        newClient.setCpf(dto.getCpf());
        newClient.setIncome(dto.getIncome());
        newClient.setBirthDate(dto.getBirthDate());
        newClient.setChildren(dto.getChildren());
        return newClient;
    }

}

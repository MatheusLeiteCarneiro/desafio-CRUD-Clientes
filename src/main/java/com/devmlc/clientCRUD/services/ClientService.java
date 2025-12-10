package com.devmlc.clientCRUD.services;

import com.devmlc.clientCRUD.dto.ClientDTO;
import com.devmlc.clientCRUD.entities.Client;
import com.devmlc.clientCRUD.exceptions.ResourceNotFoundException;
import com.devmlc.clientCRUD.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client client = new Client();
        copyDtotoEntity(dto, client);
        client =  repository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client client = repository.getReferenceById(id);
            copyDtotoEntity(dto, client);
            client = repository.save(client);
            return new ClientDTO(client);

        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    private void copyDtotoEntity(ClientDTO dto, Client client){
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());
    }

}

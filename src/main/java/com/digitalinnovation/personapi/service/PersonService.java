package com.digitalinnovation.personapi.service;

import com.digitalinnovation.personapi.dto.MessageResponseDTO;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PersonService {
    private PersonRepository personrepository;
@Autowired
    public PersonService(PersonRepository personrepository) {
        this.personrepository = personrepository;
    }
    public MessageResponseDTO createPerson( Person person) {
        Person savedPerson;
        savedPerson = personrepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created Person with Id" + savedPerson.getId())
                .build();
}
}

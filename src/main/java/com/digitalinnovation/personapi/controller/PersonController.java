package com.digitalinnovation.personapi.controller;

import com.digitalinnovation.personapi.dto.MessageResponseDTO;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

private PersonRepository personrepository;

@Autowired
    public PersonController(PersonRepository personrepositary) {
        this.personrepository = personrepositary;
    }



@PostMapping
        public MessageResponseDTO createPerson(@RequestBody Person person){
    Person savedPerson = personrepository.save( person);
    return  MessageResponseDTO
            .builder()
            .message("Created Person with Id" + savedPerson.getId())
            .build();
}
    }


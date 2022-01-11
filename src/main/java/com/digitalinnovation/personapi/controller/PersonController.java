package com.digitalinnovation.personapi.controller;

import com.digitalinnovation.personapi.dto.MessageResponseDTO;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.repository.PersonRepository;
import com.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository personrepository;

    @Autowired
    public PersonController(PersonRepository personrepositary) {

        this.personrepository = personrepositary;
    }
    @GetMapping
    public String index() {
        return "API teste";
    }
    @Autowired
    private PersonService personService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person) {
return  personService.createPerson(person);
    }
}


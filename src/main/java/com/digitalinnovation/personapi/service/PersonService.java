package com.digitalinnovation.personapi.service;

import com.digitalinnovation.personapi.Exception.PersonNotFoundException;
import com.digitalinnovation.personapi.dto.request.PersonDTO;
import com.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.mapper.PersonMapper;
import com.digitalinnovation.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private final PersonRepository personrepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;


    public MessageResponseDTO createPerson( PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

    Person savedPerson = personrepository.save(personToSave);
        return createMethodResponse("Created Person with Id", savedPerson.getId());
}

    public List<PersonDTO> listAll() {
    List<Person> allPeople = personrepository.findAll();
    return allPeople.stream()
        .map(personMapper::toDTO)
            .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);

}



    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personrepository.deleteById(id);

    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
       verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
    Person updatedPerson = personrepository.save(personToUpdate);
        return createMethodResponse("Updated Person with Id", updatedPerson.getId());
    }
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personrepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
    private MessageResponseDTO createMethodResponse(String message, Long id) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}

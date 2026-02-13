package ee.murdvee.veebipood.controller;

import ee.murdvee.veebipood.dto.PersonLoginRecordDto;
import ee.murdvee.veebipood.entity.Person;
import ee.murdvee.veebipood.repository.PersonRepository;
import ee.murdvee.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    //Dependency Injection. Kui luuakse see klass (PersonController)
    // ,seotakse 2ra sama ajala temaga koik allolevad
    // muutjuad injectiga voib ka labi constructorite
    @Autowired
    private PersonService personService;

    @GetMapping("persons")
    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    @DeleteMapping("persons/{id}")
    public List<Person> deletePerson(@PathVariable Long id){
        personRepository.deleteById(id); // kustutan
        return personRepository.findAll(); // uuenenud seis
    }

    @PostMapping("signup")
    public Person signup(@RequestBody Person person){
        personService.validate(person);
        return personRepository.save(person); // siin salvestab
    }

    @PostMapping("login")
    public Person login(@RequestBody PersonLoginRecordDto personDto){
        Person dbPerson = personRepository.findByEmail(personDto.email());
        if (dbPerson == null) {
            throw new RuntimeException("Invalid email");
        }
        if (!dbPerson.getPassword().equals(personDto.password())) {
            throw new RuntimeException("Invalid password");
        }
        return dbPerson;
    }
}

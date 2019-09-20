package com.accenture.test.contollers.cache;

import com.accenture.test.domain.Person;
import com.accenture.test.services.cache.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TestCacheRestController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public Optional<Person> get(@PathVariable String id) {
        return personService.get(id);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public void post(@RequestBody Person person) {
        personService.post(person);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.DELETE)
    public void delete(@RequestBody Person person) {
        personService.delete(person);
    }

}

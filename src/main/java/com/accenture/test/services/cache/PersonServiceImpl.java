package com.accenture.test.services.cache;

import com.accenture.test.domain.Person;
import com.accenture.test.services.cache.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    private List<Person> data;

    @PostConstruct
    private void init() {
        data = new ArrayList<>();
        data.add(new Person("1", "a"));
        data.add(new Person("2", "b"));
    }

    @Override
    @Cacheable("cache1")
    public Optional<Person> get(String id) {
        log.info("Accessing to method (not in cache)...");
        return data.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Person post(Person person) {
        data.add(person);
        return person;
    }

    @Override
    @CacheEvict(cacheNames = {"cache1"})
    public Optional<Person> delete(Person person) {
        final Optional<Person> personFound = get(person.getId());
        if (personFound.isPresent()) {
            data.remove(person);
        }
        return personFound;
    }
}

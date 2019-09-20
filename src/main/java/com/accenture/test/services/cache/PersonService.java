package com.accenture.test.services.cache;

import com.accenture.test.domain.Person;

import java.util.Optional;

public interface PersonService {

    Optional<Person> get(String id);

    Person post(Person person);

    Optional<Person> delete(Person person);
}

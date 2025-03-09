package com.example.demo;


import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Event;
import com.example.demo.model.Group;
import com.example.demo.repository.GroupRepository;

@Component
class Initializer implements CommandLineRunner {

    private final GroupRepository repository;

    public Initializer (GroupRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        Stream.of("Seattle JUG", "Denver JUG", "Dublin JUG",
                "London JUG").forEach(name->
                repository.save(new Group(name))
        );

        Group group = repository.findByName("Seattle JUG");
        Event e = Event.builder().title("Micro Frontends for Java Developers")
        .description("JHipster now has microfrontend support!")
        .date(Instant.parse("2022-09-13T17:00:00.000Z")).build();

        group.setEvents(Collections.singleton(e));
        repository.save(group);

        repository.findAll().forEach(System.out::println);
    }
    



}

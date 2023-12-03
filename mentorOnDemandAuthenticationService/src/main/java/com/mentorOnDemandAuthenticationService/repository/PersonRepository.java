package com.mentorOnDemandAuthenticationService.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mentorOnDemandAuthenticationService.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Optional<Person> findByPersonName(String personName);
}

package com.baseapp.it_support_api.repository;

import com.baseapp.it_support_api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}

package ru.start.springhibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.start.springhibernate.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {

    Optional<Person> findByFullName(String fullName); // Нужен для Валидатора

    // Создаем сигнатуры методов
//    List<Person> findByName(String name);
//
//    List<Person> findByNameOrderByAge(String name);
//
//    List<Person> findByEmail(String email);
//
//    List<Person> findByNameStartingWith(String startingWith);
//
//    List<Person> findByNameOrEmail(String name, String email);
}

package ru.start.springhibernate.dao;


import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.start.springhibernate.models.Person;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlusOne(){
        Session session = entityManager.unwrap(Session.class);
///        ВЫЗЫВАЕМ ПРОБЛЕМУ N+1
//        // 1 запрос
//        List<Person> people = session.createQuery("SELECT p FROM Person p", Person.class)
//                .getResultList();
//
//        // N запросов к бд
//        for (Person person : people) {
//            System.out.println("Person " + person.getName() + " has: " + person.getItems());
///        РЕШЕНИЕ
//        SQL: A LEFT JOIN B -> Результирующая объединенная таблица
        Set<Person> people = new HashSet<Person>(session.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.items")
                .getResultList());

        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        }

        }
    }
}

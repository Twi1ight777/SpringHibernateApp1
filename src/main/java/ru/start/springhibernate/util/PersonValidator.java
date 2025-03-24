package ru.start.springmvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.start.springmvc.dao.PersonDAO;
import ru.start.springmvc.models.Person;

// Для каждой сущности свой валидатор
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    // Не позволяет использовать валидатор на любых других сущностях, кроме Person
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        // Проверяем поля на соответствие условиям (Есть ли человек с таким же email, что и у людей в бд уже созданных)
        if (personDAO.getPersonByFullName(person.getFullName()).isPresent())
            errors.rejectValue("fullName", "fullName.exist", "Человек с таким ФИО уже существует");
    }
}

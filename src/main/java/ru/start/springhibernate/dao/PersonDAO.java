package ru.start.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.start.springmvc.models.Book;
import ru.start.springmvc.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    // Добавлять, удалять и изменять данные людей в БД здесь
//    private static int PEOPLE_COUNT = 0;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    //Optional
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth) VALUES(?, ?)", person.getFullName(),
                person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_birth=? WHERE id=?", updatedPerson.getFullName(),
                updatedPerson.getYearOfBirth());
    }

    // Реализовали дополнительно каскадирование в бд ON DELETE SET NULL - принадлежащие человеку книги вернутся в список
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    // Join не нужен. С помощью отдельного метода show получили id человека. Возвращаем все книги из списка книг человека
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
    //////////////////////////Тестируем производительность пакетной вставки/////////////////////////////////////////////
//
//    public void testMultipleUpdate(){
//        List<Person> people = create1000Records();
//
//        long startTime = System.currentTimeMillis();
//        for (Person person : people) {
//            jdbcTemplate.update("INSERT INTO Person VALUES(?,?,?,?)", person.getId(), person.getName(), person.getAge(),
//                    person.getEmail());
//        }
//        long endTime = System.currentTimeMillis();
//        System.out.println("Time for 1000 records: " + (endTime - startTime) + " ms");
//    }
//    private List<Person>  create1000Records(){
//        List<Person> people = new ArrayList<>();
//        for (int i = 0; i < 1000; i++) {
//            people.add(new Person(i,"Name", 30, "email" + i + "@example.com"));
//        }
//        return people;
//    }
//    public void testBatchUpdate()
//    {
//        List<Person> people = create1000Records();
//        long startTime = System.currentTimeMillis();
//        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?,?,?,?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setInt(1, people.get(i).getId());
//                ps.setString(2, people.get(i).getName());
//                ps.setInt(3, people.get(i).getAge());
//                ps.setString(4, people.get(i).getEmail());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return people.size();
//            }
//        });
//        long endTime = System.currentTimeMillis();
//        System.out.println("Time for 1000 records (batch): " + (endTime - startTime) + " ms");
//    }

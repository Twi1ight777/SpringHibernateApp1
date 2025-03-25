package ru.start.springhibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.start.springhibernate.models.Item;
import ru.start.springhibernate.models.Person;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item,Integer> {
    List<Item> findByItemName(String itemName);
    // person.getItems();
    List<Item> findByOwner(Person owner);
}

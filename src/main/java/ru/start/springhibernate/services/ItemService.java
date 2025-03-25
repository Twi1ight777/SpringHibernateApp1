package ru.start.springhibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.start.springhibernate.models.Item;
import ru.start.springhibernate.models.Person;
import ru.start.springhibernate.repositories.ItemsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findByItemName (String itemName) {
        return itemsRepository.findByItemName(itemName);
    }

    public List<Item> findByOwner (Person owner){
        // Бизнес логика. Манипуляция с данными
        return itemsRepository.findByOwner(owner);
    }
}

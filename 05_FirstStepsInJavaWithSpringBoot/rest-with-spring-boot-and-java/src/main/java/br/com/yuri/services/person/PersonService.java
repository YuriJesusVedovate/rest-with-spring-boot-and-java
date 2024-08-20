package br.com.yuri.services.person;

import br.com.yuri.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    private final List<Person> personList = new ArrayList<>();

    public PersonService() {
        generatePersons();
    }

    public Person findById(String id) {
        logger.info("Finding person by id: " + id);

        return personList.stream()
                .filter(p -> p.getId() == Long.parseLong(id))
                .findFirst()
                .orElse(null);
    }

    public List<Person> findAll() {
        logger.info("Finding all people");
        return personList;
    }

    public Person Create(Person person) {
        logger.info("Creating person: " + person.getFirstName());
        person.setId(counter.incrementAndGet());
        personList.add(person);
        return person;
    }

    public Person Update(Person person, String id) {
        logger.info("Updating person by id: " + id);
        personList.stream()
                .filter(p -> p.getId() == Long.parseLong(id))
                .forEach(p -> {
                    p.setFirstName(person.getFirstName());
                    p.setLastName(person.getLastName());
                    p.setAddress(person.getAddress());
                    p.setGender(person.getGender());
                });

        return personList.stream()
                .filter(p -> p.getId() == Long.parseLong(id))
                .findFirst()
                .orElse(null);
    }

    public void Delete(String id){
        logger.info("Deleting person with id: " + id);

        personList.removeIf(p -> p.getId() == Long.parseLong(id));
    }

    private void generatePersons() {
        for (int i = 1; i <= 10; i++) {
            Person person = new Person();
            person.setId(counter.incrementAndGet());
            person.setFirstName("FirstName " + i);
            person.setLastName("LastName " + i);
            person.setAddress("Address " + i);
            person.setGender(i % 2 == 0 ? "Male" : "Female");
            personList.add(person);
        }
    }


}

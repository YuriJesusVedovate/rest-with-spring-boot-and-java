package br.com.yuri.services.person;

import br.com.yuri.exceptions.ResourceNotFoundException;
import br.com.yuri.models.Person;
import br.com.yuri.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    final
    IPersonRepository personRepository;

    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(Long id) {
        logger.info("Finding person by id: " + id);
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));
    }

    public List<Person> findAll() {
        logger.info("Finding all people");
        return personRepository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating person: " + person.getFirstName());
        return personRepository.save(person);
    }

    public Person update(Person person, Long id) {
        logger.info("Updating person by id: " + id);

        Person personById = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));

        personById.setFirstName(person.getFirstName());
        personById.setLastName(person.getLastName());
        personById.setAddress(person.getAddress());
        personById.setGender(person.getGender());

        return personRepository.save(personById);
    }

    public void delete(Long id){
        logger.info("Deleting person with id: " + id);
        Person personById = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));

        personRepository.deleteById(id);
    }

}

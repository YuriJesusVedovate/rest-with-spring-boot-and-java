package br.com.yuri.services.person;

import br.com.yuri.data.vo.PersonVO;
import br.com.yuri.exceptions.ResourceNotFoundException;
import br.com.yuri.mapper.Mapper;
import br.com.yuri.models.Person;
import br.com.yuri.repositories.IPersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    final
    IPersonRepository personRepository;

    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people");
        List<Person> people = personRepository.findAll();
        return Mapper.parseListObjects(people, PersonVO.class);
    }

    public PersonVO findById(Long id) {
        logger.info("Finding person by id: " + id);

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));

        return Mapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO request) {
        logger.info("Creating person: " + request.getFirstName());
        Person person = Mapper.parseObject(request, Person.class);
        person = personRepository.save(person);
        return Mapper.parseObject(person, PersonVO.class);
    }

    public PersonVO update(PersonVO request, Long id) {
        logger.info("Updating person by id: " + id);

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));

        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setAddress(request.getAddress());
        person.setGender(request.getGender());

        personRepository.save(person);

        return Mapper.parseObject(person, PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting person with id: " + id);
        personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));

        personRepository.deleteById(id);
    }

}

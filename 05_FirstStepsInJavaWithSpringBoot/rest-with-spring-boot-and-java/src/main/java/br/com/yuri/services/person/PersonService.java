package br.com.yuri.services.person;

import br.com.yuri.controllers.PersonController;
import br.com.yuri.data.vo.PersonVO;
import br.com.yuri.exceptions.RequiredObjectIsNullException;
import br.com.yuri.exceptions.ResourceNotFoundException;
import br.com.yuri.mapper.Mapper;
import br.com.yuri.models.Person;
import br.com.yuri.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    private PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people");
        List<Person> people = personRepository.findAll();
        var persons = Mapper.parseListObjects(people, PersonVO.class);
        persons.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO findById(Long id) {
        logger.info("Finding person by id: " + id);

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));

        PersonVO result = Mapper.parseObject(entity, PersonVO.class);
        result.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return result;
    }

    public PersonVO create(PersonVO request) {
        validateRequestPersonVO(request);
        logger.info("Creating person: " + request.getFirstName());
        Person person = Mapper.parseObject(request, Person.class);
        person = personRepository.save(person);
        PersonVO result = Mapper.parseObject(person, PersonVO.class);
        result.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
        return result;
    }

    public PersonVO update(PersonVO request, Long id) {
        validateRequestPersonVO(request);
        logger.info("Updating person by id: " + id);

        Person person = getPersonById(id);

        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setAddress(request.getAddress());
        person.setGender(request.getGender());

        personRepository.save(person);

        PersonVO result = Mapper.parseObject(person, PersonVO.class);
        result.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
        return result;
    }

    public void delete(Long id) {
        logger.info("Deleting person with id: " + id);
        getPersonById(id);

        personRepository.deleteById(id);
    }

    private Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));
    }

    private static void validateRequestPersonVO(PersonVO request) {
        if (request == null) {
            throw new RequiredObjectIsNullException("Person cannot be null");
        }
        if (isNullOrEmpty(request.getFirstName())) {
            throw new RequiredObjectIsNullException("FirstName cannot be null");
        }
        if (isNullOrEmpty(request.getLastName())) {
            throw new RequiredObjectIsNullException("LastName cannot be null");
        }
        if (isNullOrEmpty(request.getAddress())) {
            throw new RequiredObjectIsNullException("Address cannot be null");
        }
        if (isNullOrEmpty(request.getGender())) {
            throw new RequiredObjectIsNullException("Gender cannot be null");
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.isBlank();
    }

}

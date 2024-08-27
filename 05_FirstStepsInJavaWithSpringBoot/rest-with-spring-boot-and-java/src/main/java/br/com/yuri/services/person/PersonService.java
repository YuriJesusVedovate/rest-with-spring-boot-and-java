package br.com.yuri.services.person;

import br.com.yuri.controllers.v1.PersonController;
import br.com.yuri.data.vo.v1.PersonVO;
import br.com.yuri.data.vo.v2.PersonVOV2;
import br.com.yuri.exceptions.ResourceNotFoundException;
import br.com.yuri.mapper.Mapper;
import br.com.yuri.mapper.custom.PersonMapper;
import br.com.yuri.models.Person;
import br.com.yuri.repositories.IPersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    final
    private IPersonRepository personRepository;
    final
    private PersonMapper personMapper;


    public PersonService(IPersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
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

        PersonVO vo = Mapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO request) {
        logger.info("Creating person: " + request.getFirstName());
        Person person = Mapper.parseObject(request, Person.class);
        person = personRepository.save(person);
        PersonVO vo = Mapper.parseObject(person, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 request) {
        logger.info("Creating person V2: " + request.getFirstName());
        Person person = personMapper.ConvertVOToEntity(request);
        person = personRepository.save(person);
        return personMapper.ConvertEntityToVO(person);
    }

    public PersonVO update(PersonVO request, Long id) {
        logger.info("Updating person by id: " + id);

        Person person = getPersonById(id);

        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setAddress(request.getAddress());
        person.setGender(request.getGender());

        personRepository.save(person);

        PersonVO vo = Mapper.parseObject(person, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
        return vo;
    }

    public PersonVOV2 updateV2(PersonVOV2 request, Long id) {
        logger.info("Updating person V2 by id: " + id);

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));

        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setAddress(request.getAddress());
        person.setGender(request.getGender());

        personRepository.save(person);

        return personMapper.ConvertEntityToVO(person);
    }

    public void delete(Long id) {
        logger.info("Deleting person with id: " + id);
        personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));

        personRepository.deleteById(id);
    }

    private Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + id));
    }

}

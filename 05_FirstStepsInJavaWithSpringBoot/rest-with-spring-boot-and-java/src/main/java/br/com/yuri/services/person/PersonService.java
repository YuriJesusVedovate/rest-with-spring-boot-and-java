package br.com.yuri.services.person;

import br.com.yuri.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id) {
        logger.info("Finding person by id: " + id);

        Person person =  new Person(){
            {
                setId(counter.incrementAndGet());
                setFirstName("Yuri");
                setLastName("Vedovate");
                setAddress("São Paulo - Brazil");
                setGender("Male");
            }
        };
        return person;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}

package br.com.yuri.repositories;

import br.com.yuri.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
    
}

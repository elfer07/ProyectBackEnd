package ar.com.morenofernando.d4t.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.morenofernando.d4t.entity.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person,Long> {

}

package ar.com.morenofernando.d4t.interfaces;


import ar.com.morenofernando.d4t.entity.Person;

import java.util.List;

public interface IPersonService {

	public List<Person> getPerson();
	
	//save person
	public void savePerson(Person person);
	
	//delete person
	public void deletePerson(int id);
	
	//search person
	public Person findPerson(int id);
}

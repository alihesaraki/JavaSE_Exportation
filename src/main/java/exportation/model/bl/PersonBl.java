package exportation.model.bl;

import exportation.controller.exception.NoPersonFoundException;
import lombok.Getter;
import exportation.model.da.PersonDa;
import exportation.model.entity.Person;
import exportation.model.tools.CRUD;

import java.util.List;

public class PersonBl implements CRUD<Person> {
    @Getter
    private static PersonBl personBl = new PersonBl();

    private PersonBl() {
    }

    //save
    @Override
    public Person save(Person person) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            personDa.save(person);
            return person;
        }
    }

    //edit
    @Override
    public Person edit(Person person) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            if (personDa.findById(person.getId()) != null) {
                personDa.edit(person);
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    //remove
    @Override
    public Person remove(int id) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Person person = personDa.findById(id);
            if (person != null) {
                personDa.remove(id);
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Person> findAll() throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            List<Person> perosnList = personDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    //findById
    @Override
    public Person findById(int id) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Person person = personDa.findById(id);
            if (person != null) {
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    //findByFamily
    public List<Person> findByFamily(String family) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            List<Person> perosnList = personDa.findByFamily(family);
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }
}

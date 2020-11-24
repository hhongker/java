package service;


import dao.PersonDao;
import domain.Person;
import util.MySpring;

import java.util.List;

public class PersonService {

    PersonDao personDao = MySpring.myObj("dao.PersonDao");
    public int insertPerson(Person person){
        return personDao.insertPerson(person);
    }

    public int deletePeron(String  id){
        return personDao.deletePerson(id);
    }

    public int updatePerson(Person person){
        return personDao.updatePerson(person);
    }

    public Person selectOnePerson(String id){
        return personDao.selectOnePerson(id);
    }

    public List<Person>selectListPerson(String flag){
        return personDao.selectListPerson(flag);
    }



}

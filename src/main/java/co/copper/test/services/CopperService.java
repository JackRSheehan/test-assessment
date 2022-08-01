package co.copper.test.services;

import co.copper.test.datamodel.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CopperService {

    public List<Person> getPeople();
    public void savePeople(List<Person> users);
}

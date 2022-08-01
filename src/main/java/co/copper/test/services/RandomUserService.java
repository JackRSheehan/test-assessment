package co.copper.test.services;

import java.util.List;

import co.copper.test.datamodel.Person;
import co.copper.test.exceptions.GetUsersException;
import org.springframework.stereotype.Service;


@Service
public interface RandomUserService {
    List<Person> getUsers() throws GetUsersException;
}

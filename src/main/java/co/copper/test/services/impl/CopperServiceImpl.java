package co.copper.test.services.impl;

import co.copper.test.datamodel.Person;
import co.copper.test.datamodel.RandomUsers;
import co.copper.test.services.CopperService;
import co.copper.test.storage.PeopleJavaRepository;
import org.asynchttpclient.AsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopperServiceImpl implements CopperService {

    private static final Logger log = LoggerFactory.getLogger(co.copper.test.services.CopperService.class);
    private final PeopleJavaRepository peopleJavaRepository;

    @Autowired
    public CopperService(PeopleJavaRepository peopleJavaRepository) {
        this.peopleJavaRepository = peopleJavaRepository;
    }

    public List<Person> getPeople() {
        return peopleJavaRepository.getPeople();
    }

    public void savePeople(List<Person> users) {

    }
}
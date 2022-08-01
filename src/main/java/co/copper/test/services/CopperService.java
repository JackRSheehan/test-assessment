package co.copper.test.services;

import co.copper.test.datamodel.People;
import co.copper.test.datamodel.RandomUser;
import co.copper.test.datamodel.RandomUsers;
import co.copper.test.storage.PeopleJavaRepository;
import co.copper.test.storage.TestJavaRepository;
import org.asynchttpclient.AsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

@Service
public class CopperService {

    private static final Logger log = LoggerFactory.getLogger(CopperService.class);
    private final PeopleJavaRepository peopleJavaRepository;
    private final AsyncHttpClient httpClient;

    @Autowired
    public CopperService(PeopleJavaRepository peopleJavaRepository, AsyncHttpClient httpClient) {
        this.peopleJavaRepository = peopleJavaRepository;
        this.httpClient = httpClient;
    }

    public List<People> getPeople() {
        return peopleJavaRepository.getPeople();
    }

    public void savePeople(RandomUsers randomUsers) {
        try {
            List<People> people = randomUsers.getResults().stream().map(randomUser -> People.builder()
                    .email(randomUser.getEmail())
                    .firstName(randomUser.getName().getFirst())
                    .lastName(randomUser.getName().getLast())
                    .password(randomUser.getLogin())
                    .build()).toList();
            for (People person : people) {
                peopleJavaRepository.savePeople(person);
            }
        } catch (Exception e) {
            log.error("Error when inserting person into the database. ");
            log.error(e.getMessage());
        }
    }
}

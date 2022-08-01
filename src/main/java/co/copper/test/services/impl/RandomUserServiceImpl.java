package co.copper.test.services.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;

import co.copper.test.datamodel.Person;
import co.copper.test.datamodel.RandomUsers;
import co.copper.test.exceptions.GetUsersException;
import co.copper.test.services.RandomUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RandomUserServiceImpl implements RandomUserService {

    private static final Logger log = LoggerFactory.getLogger(RandomUserServiceImpl.class);
    private final AsyncHttpClient httpClient;

    ObjectMapper objectMapper;

    @Autowired
    public RandomUserServiceImpl(AsyncHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public List<Person> getUsers() throws GetUsersException {
        try {
            log.debug("Attempting to fetch the users from the external API.");
            String resultString = this.httpClient.prepareGet("https://randomuser.me/api/?results=20").execute().toCompletableFuture()
                    .handle((res, t) -> res.getResponseBody()).get();

            log.debug("Attempting to parse the external users into RandomUser format.");
            RandomUsers randomUsers = objectMapper.readValue(resultString, RandomUsers.class);

            log.debug("Returning the random users in list format.");
            return randomUsers.getResults().stream().map(randomUser -> Person.builder()
                    .first(randomUser.getName().getFirst())
                    .last(randomUser.getName().getLast())
                    .email(randomUser.getEmail())
                    .password(randomUser.getLogin().getPassword()).build()).toList();
        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            log.error("Error whilst fetching the users from the external API.");
            log.error(e.getMessage());
            throw new GetUsersException(e.getMessage());
        }

    }
}
package co.copper.test.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

import co.copper.test.datamodel.Person;
import co.copper.test.datamodel.RandomUser;
import co.copper.test.datamodel.RandomUsers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RandomUserService {

    private static final Logger log = LoggerFactory.getLogger(RandomUserService.class);
    private final AsyncHttpClient httpClient;

    ObjectMapper objectMapper;

    @Autowired
    public RandomUserService(AsyncHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public List<Person> getUsers() throws JsonProcessingException, ExecutionException, InterruptedException {
        String resultString = this.httpClient.prepareGet("https://randomuser.me/api/?results=20").execute().toCompletableFuture()
                .handle((res, t) -> res.getResponseBody()).get();

        RandomUsers randomUsers = objectMapper.readValue(resultString, RandomUsers.class);

        return randomUsers.getResults().stream().map(randomUser -> Person.builder()
                .email(randomUser.getEmail())
                .firstName(randomUser.getName().getFirst())
                .lastName(randomUser.getName().getLast())
                .password(randomUser.getLogin()).build()).toList();
    }
}

package co.copper.test.services;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import co.copper.test.datamodel.RandomUsers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.copper.test.storage.TestJavaRepository;


@Service
public class RandomUserService {

    private static final Logger log = LoggerFactory.getLogger(TestJavaService.class);
    private final TestJavaRepository testRepo;
    private final AsyncHttpClient httpClient;

    ObjectMapper objectMapper;

    @Autowired
    public RandomUserService(TestJavaRepository testRepo, AsyncHttpClient httpClient, ObjectMapper objectMapper) {
        this.testRepo = testRepo;
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public RandomUsers getUsers() throws JsonProcessingException {
        log.debug(testRepo.getById(1L).get(0).getVal());

        CompletableFuture s = this.httpClient.prepareGet("https://randomuser.me/api/?results=20").execute().toCompletableFuture()
                .handle((res, t) -> res.getResponseBody());

        return objectMapper.readValue(s.toString(), RandomUsers.class);
    }
}

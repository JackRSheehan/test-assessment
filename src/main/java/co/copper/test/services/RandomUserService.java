package co.copper.test.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

import co.copper.test.datamodel.Person;
import co.copper.test.datamodel.RandomUser;
import co.copper.test.datamodel.RandomUsers;
import co.copper.test.exceptions.GetUsersException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public interface RandomUserService {
    List<Person> getUsers() throws GetUsersException;
}

package co.copper.test.controller;

import co.copper.test.datamodel.Person;
import co.copper.test.datamodel.RandomUsers;
import co.copper.test.exceptions.GetUsersException;
import co.copper.test.services.CopperService;
import co.copper.test.services.RandomUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;

@RequestMapping(value = "/copper")
@Controller
public class CopperController {

    private static final Logger log = LoggerFactory.getLogger(CopperController.class);

    @Autowired
    RandomUserService randomUserService;

    @Autowired
    CopperService copperService;

    @GetMapping("/getUsers")
    @ResponseBody
    public List<Person> getPeople() {
        return copperService.getPeople();
    }

    @PostMapping("/saveUsers")
    public ResponseEntity<HttpStatus> savePeople() {
        try {
            List<Person> randomUsers = randomUserService.getUsers();
            copperService.savePeople(randomUsers);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (GetUsersException e) {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }
}

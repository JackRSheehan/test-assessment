package co.copper.test.controller;

import co.copper.test.datamodel.People;
import co.copper.test.datamodel.RandomUsers;
import co.copper.test.services.CopperService;
import co.copper.test.services.RandomUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

@RequestMapping(value = "/copper", produces = "application/json")
@Controller
public class CopperController {

    @Autowired
    RandomUserService randomUserService;

    @Autowired
    CopperService copperService;

    @GetMapping("/users")
    @ResponseBody
    public List<People> getPeople() {
        System.out.println("Being hit.");
        List<People> peopleList = new ArrayList<People>();

        return copperService.getPeople();
    }

    @PostMapping("/saveUsers")
    public HttpStatus savePeople() throws JsonProcessingException {
        RandomUsers randomUsers = randomUserService.getUsers();

        copperService.savePeople(randomUsers);

        return HttpStatus.CREATED;
    }
}

package co.copper.test.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.copper.test.datamodel.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sbuslab.utils.db.JacksonBeanRowMapper;


@Repository
public class PeopleJavaRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JacksonBeanRowMapper<Person> rowMapper;

    private String sqlInsetStatement =
            "INSERT INTO people (firstName, lastName, email, password) " +
            "VALUES (:firstName, :lastName, :email, :password)";

    @Autowired
    public PeopleJavaRepository(NamedParameterJdbcTemplate jdbcTemplate, ObjectMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new JacksonBeanRowMapper<>(Person.class, mapper);
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM people", rowMapper);
    }

    public void savePeople(List<Person> people) {
        for (Person person : people) {
            Map<String, String> dbInsertParams = new HashMap<>();
            dbInsertParams.put("firstName", person.getFirst());
            dbInsertParams.put("lastName", person.getLast());
            dbInsertParams.put("email", person.getEmail());
            dbInsertParams.put("password", person.getPassword());

            jdbcTemplate.update(sqlInsetStatement, dbInsertParams);
        }
    }

}

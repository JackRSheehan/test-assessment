package co.copper.test.storage;

import java.util.List;

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

    @Autowired
    public PeopleJavaRepository(NamedParameterJdbcTemplate jdbcTemplate, ObjectMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new JacksonBeanRowMapper<>(Person.class, mapper);
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM people", rowMapper);
    }

    public void savePeople(final Person people) {
        //jdbcTemplate.query("")
    }

}

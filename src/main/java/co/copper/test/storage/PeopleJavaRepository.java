package co.copper.test.storage;

import java.sql.PreparedStatement;
import java.util.List;

import co.copper.test.datamodel.People;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sbuslab.utils.db.JacksonBeanRowMapper;

import co.copper.test.datamodel.Test;


@Repository
public class PeopleJavaRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JacksonBeanRowMapper<People> rowMapper;

    @Autowired
    public PeopleJavaRepository(NamedParameterJdbcTemplate jdbcTemplate, ObjectMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = new JacksonBeanRowMapper<>(People.class, mapper);
    }

    public List<People> getPeople() {
        return jdbcTemplate.query("SELECT * FROM people", rowMapper);
    }

    public void savePeople(final People people) {
        jdbcTemplate.query("")
    }

}

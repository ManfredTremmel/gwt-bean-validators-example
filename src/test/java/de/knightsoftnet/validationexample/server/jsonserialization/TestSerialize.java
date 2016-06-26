package de.knightsoftnet.validationexample.server.jsonserialization;

import de.knightsoftnet.validationexample.shared.models.PersonWithLinks;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestSerialize {

  @Test
  public void test() {
    final ObjectMapper mapper = new ObjectMapper();

    // For testing
    final PersonWithLinks person = new PersonWithLinks();
    final Map<String, String> href = new HashMap<>();
    href.put("href", "http://localhost:8080/validationexample/api/rest/person/1");
    person.setFirstName("Firstname");
    person.setLastName("Lastname");
    person.setEmail("fn@test.de");
    person.setBirthday(new Date(1466546400000L));
    person.getLinks().put("self", href);
    person.getLinks().put("person", href);

    try {
      // Convert object to JSON string
      final String jsonInString =
          mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
      Assert.assertTrue(jsonInString.contains("\"firstName\" : \"Firstname\""));
      Assert.assertTrue(jsonInString.contains("\"lastName\" : \"Lastname\""));
      Assert.assertTrue(jsonInString.contains("\"email\" : \"fn@test.de\""));
      Assert.assertTrue(jsonInString
          .contains("\"href\" : \"http://localhost:8080/validationexample/api/rest/person/1\""));

    } catch (final JsonGenerationException e) {
      e.printStackTrace();
    } catch (final JsonMappingException e) {
      e.printStackTrace();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

}

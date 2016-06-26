package de.knightsoftnet.validationexample.server.jsonserialization;

import de.knightsoftnet.validationexample.shared.models.PersonWithLinks;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDeSerialize {

  private static final String JSON = //
      "{\n" //
          + "  \"firstName\" : \"Firstname\",\n" //
          + "  \"lastName\" : \"Lastname\",\n" //
          + "  \"email\" : \"fn@test.de\",\n" //
          + "  \"birthday\" : \"2016-06-21T22:00:00.000+0000\",\n" //
          + "  \"_links\" : {\n" //
          + "    \"self\" : {\n" //
          + "      \"href\" : \"http://localhost:8080/validationexample/api/rest/person/1\"\n" //
          + "    },\n" //
          + "    \"person\" : {\n" //
          + "      \"href\" : \"http://localhost:8080/validationexample/api/rest/person/1\"\n" //
          + "    }\n" //
          + "  }\n" //
          + "}";

  final ObjectMapper mapper = new ObjectMapper();

  @Test
  public void test() {
    try {
      // Convert JSON string to Object
      final PersonWithLinks user1 = this.mapper.readValue(JSON, PersonWithLinks.class);
      Assert.assertEquals("Firstname", user1.getFirstName());
      Assert.assertEquals("Lastname", user1.getLastName());
      Assert.assertEquals("fn@test.de", user1.getEmail());
      Assert.assertEquals(1466546400000L, user1.getBirthday().getTime());
      Assert.assertEquals("http://localhost:8080/validationexample/api/rest/person/1",
          user1.getLinks().get("self").get("href"));
      Assert.assertEquals("http://localhost:8080/validationexample/api/rest/person/1",
          user1.getLinks().get("person").get("href"));
    } catch (final JsonGenerationException e) {
      e.printStackTrace();
    } catch (final JsonMappingException e) {
      e.printStackTrace();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

}

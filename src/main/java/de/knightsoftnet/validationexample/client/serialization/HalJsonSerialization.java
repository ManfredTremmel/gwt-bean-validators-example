package de.knightsoftnet.validationexample.client.serialization;

import com.github.nmorel.gwtjackson.client.JsonDeserializationContext;
import com.github.nmorel.gwtjackson.client.JsonSerializationContext;
import com.github.nmorel.gwtjackson.client.ObjectMapper;
import com.github.nmorel.gwtjackson.client.exception.JsonMappingException;
import com.gwtplatform.dispatch.rest.client.serialization.JacksonMapperProvider;
import com.gwtplatform.dispatch.rest.client.serialization.Serialization;
import com.gwtplatform.dispatch.rest.client.serialization.SerializationException;
import com.gwtplatform.dispatch.rest.client.serialization.SerializedValue;
import com.gwtplatform.dispatch.rest.shared.ContentType;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

public class HalJsonSerialization implements Serialization {
  private static final String VOID = Void.class.getName();
  private static final ContentType CONTENT_TYPE_JSON =
      ContentType.valueOf(MediaType.APPLICATION_JSON + "; charset=utf-8");
  private static final ContentType CONTENT_TYPE_HAL_JSON =
      ContentType.valueOf("application/hal+json; charset=utf-8");

  private final JacksonMapperProvider jacksonMapperProvider;
  private final JsonSerializationContext.Builder serializationContext;
  private final JsonDeserializationContext.Builder deserializationContext;

  @Inject
  HalJsonSerialization(final JacksonMapperProvider jacksonMapperProvider) {
    this.jacksonMapperProvider = jacksonMapperProvider;

    this.deserializationContext =
        JsonDeserializationContext.builder().failOnUnknownProperties(false);
    this.serializationContext = JsonSerializationContext.builder();
  }

  @Override
  public boolean canSerialize(final String type, final List<ContentType> contentTypes) {
    return this.matchesContentType(contentTypes)
        && (VOID.equals(type) || this.jacksonMapperProvider.hasMapper(type));
  }

  @Override
  public boolean canDeserialize(final String type, final ContentType contentType) {
    return VOID.equals(type) || (CONTENT_TYPE_JSON.isCompatible(contentType)
        || CONTENT_TYPE_HAL_JSON.isCompatible(contentType))
        && this.jacksonMapperProvider.hasMapper(type);
  }

  @Override
  public <T> SerializedValue serialize(final String type, final List<ContentType> contentTypes,
      final T object) {
    if (VOID.equals(type) || object == null) {
      return null;
    }

    final ObjectMapper<T> mapper = this.jacksonMapperProvider.getMapper(type);
    String json;
    try {
      json = mapper.write(object, this.getSerializationContext().build());
    } catch (final JsonMappingException e) {
      throw new SerializationException(
          "Unable to serialize request body. An unexpected error occurred.", e);
    }

    return new SerializedValue(CONTENT_TYPE_HAL_JSON, json);
  }

  @Override
  public <T> T deserialize(final String type, final ContentType contentType, final String json) {
    if (VOID.equals(type) || json == null || json.isEmpty()) {
      return null;
    }

    final ObjectMapper<T> mapper = this.jacksonMapperProvider.getMapper(type);

    try {
      return mapper.read(json, this.getDeserializationContext().build());
    } catch (final JsonMappingException e) {
      throw new SerializationException(
          "Unable to deserialize response. An unexpected error occurred.", e);
    }
  }

  protected JsonSerializationContext.Builder getSerializationContext() {
    return this.serializationContext;
  }

  protected JsonDeserializationContext.Builder getDeserializationContext() {
    return this.deserializationContext;
  }

  private boolean matchesContentType(final List<ContentType> contentTypes) {
    boolean contentTypeMatch = false;
    for (final ContentType contentType : contentTypes) {
      if (CONTENT_TYPE_JSON.isCompatible(contentType)
          || CONTENT_TYPE_HAL_JSON.isCompatible(contentType)) {
        contentTypeMatch = true;
        break;
      }
    }
    return contentTypeMatch;
  }
}

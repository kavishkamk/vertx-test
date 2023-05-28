package io.github.kavishkamk.testapp;

import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestJson {

  @Test
  void jsonObjectCanBeMapped() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.put("id", 1);
    jsonObject.put("name", "kavishka");
    jsonObject.put("isActive", true);

    String encodeObject = jsonObject.encode();

    Assertions.assertEquals("{\"id\":1,\"name\":\"kavishka\",\"isActive\":true}", encodeObject);

    JsonObject decodedObject = new JsonObject(encodeObject);
    Assertions.assertEquals(jsonObject, decodedObject);
  }

  @Test
  void jsonObjectCanBeCreatedFromMap() {
    final Map<String, Object> map = new HashMap<>();
    map.put("id", 1);
    map.put("name", "kavishka");
    map.put("isActive", true);

    final JsonObject jsonObject = new JsonObject(map);

    Assertions.assertEquals(map, jsonObject.getMap());
    Assertions.assertEquals(1, jsonObject.getInteger("id"));
  }

}

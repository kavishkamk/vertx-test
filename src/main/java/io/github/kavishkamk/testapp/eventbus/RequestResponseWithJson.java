package io.github.kavishkamk.testapp.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestResponseWithJson {

  public static final Logger Log = LoggerFactory.getLogger(RequestResponseWithJson.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new RequestVerticle());
    vertx.deployVerticle(new ResponseVerticle());
  }

  static class RequestVerticle extends AbstractVerticle {

    private static final Logger Log = LoggerFactory.getLogger(RequestVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) {
      startPromise.complete();
      EventBus eventBus = vertx.eventBus();
      JsonObject message = new JsonObject();
      message.put("message", "hello world");
      Log.debug("send message: {}", message);
      eventBus.<JsonArray>request("send.address", message, response ->
        Log.debug("Response: {}", response.result().body())
      );
    }
  }

  static class ResponseVerticle extends AbstractVerticle {

    public void start(Promise<Void> startPromise) {
      startPromise.complete();
      vertx.eventBus().<JsonObject>consumer("send.address", message -> {
        Log.debug("Reserved message: {}", message.body());
        message.reply(new JsonArray().add("one").add("two"));
      });
    }

  }

}

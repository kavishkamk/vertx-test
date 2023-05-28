package io.github.kavishkamk.testapp.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestResponse {

  public static final String MY_REQUEST_ADDRESS = "my.request.address";
  private static final Logger Log = LoggerFactory.getLogger(RequestResponse.class);
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new RequestVerticle());
    vertx.deployVerticle(new ResponseVerticle());
  }

  static class RequestVerticle extends AbstractVerticle {

    private static final Logger Log = LoggerFactory.getLogger(RequestVerticle.class);

    @Override

    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      EventBus eventBus = vertx.eventBus();
      String message = "hello world";
      Log.debug("send message: {}", message);
      eventBus.<String>request(MY_REQUEST_ADDRESS, message, reply -> {
        Log.debug("Response: {}", reply.result().body());
      });
    }

  }

  static class ResponseVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(MY_REQUEST_ADDRESS, message -> {
        Log.debug("Reserved message: {}", message.body());
        message.reply("Reserved it");
      });
    }

  }

}

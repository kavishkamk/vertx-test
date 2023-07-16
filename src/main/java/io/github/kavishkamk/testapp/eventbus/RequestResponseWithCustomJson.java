package io.github.kavishkamk.testapp.eventbus;

import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestResponseWithCustomJson {

  public static final Logger Log = LoggerFactory.getLogger(RequestResponseWithCustomJson.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new PingVerticle(), getAsyncResultHandler());
    vertx.deployVerticle(new PongVerticle(), getAsyncResultHandler());
  }

  private static Handler<AsyncResult<String>> getAsyncResultHandler() {
    return response -> {
      if (response.failed()) {
        Log.error("error: " + response.cause());
      }
    };
  }

  static class PingVerticle extends AbstractVerticle {

    public static final Logger Log = LoggerFactory.getLogger(PingVerticle.class);
    @Override
    public void start(Promise<Void> startPromise) throws Exception {

      Ping ping = new Ping("this is message", true);
      Log.debug("send message: " + ping);

      vertx.eventBus().registerDefaultCodec(Ping.class, new LocalMessageCodec<>(Ping.class));
      vertx.eventBus().<Pong>request("test-address", ping , result -> {

        if(result.failed()) {
          Log.error("error: " + result.cause());
          return;
        }

        Log.debug("Reserved message: {}", result.result().body());
      });
      startPromise.complete();
    }
  }

  static class PongVerticle extends AbstractVerticle {

    public static final Logger Log = LoggerFactory.getLogger(PongVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

      vertx.eventBus().registerDefaultCodec(Pong.class, new LocalMessageCodec<>(Pong.class));
      vertx.eventBus().<Ping>consumer("test-address", message -> {
        Log.debug("reserved message: {}", message.body());
        message.reply(new Pong(1));
      }).exceptionHandler(error -> {
        Log.error("exception", error);
      });
      startPromise.complete();
    }
  }

}

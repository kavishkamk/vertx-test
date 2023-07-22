package io.github.kavishkamk.testapp;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(VertxExtension.class)
public class PromiseFutureExample {

  private static final Logger Log = LoggerFactory.getLogger(PromiseFutureExample.class);

  @Test
  void promiseSuccess(Vertx vertx, VertxTestContext context) {

    final Promise<String> promise = Promise.promise();
    Log.debug("Start");

    vertx.setTimer(500, id -> {
      promise.complete("success");
      Log.debug("Timer done");
      context.completeNow();
    });

    Log.debug("End");
  }

  @Test
  void promiseFail(Vertx vertx, VertxTestContext context) {

    final Promise<String> promise = Promise.promise();
    Log.debug("Start");

    vertx.setTimer(500, id -> {
      promise.fail(new RuntimeException("Failed"));
      Log.debug("Failed");
      context.completeNow();
    });

    Log.debug("End");

  }

  @Test
  void futureSuccess(Vertx vertx, VertxTestContext context) {

    final Promise<String> promise = Promise.promise();
    Log.debug("Start");

    vertx.setTimer(500, id -> {
      promise.complete("Success");
      Log.debug("Timer Done");
    });

    final Future<String> future = promise.future();

    future.onSuccess(result -> {
      Log.debug("result: " + result);
      context.completeNow();
    }).onFailure(context::failNow);

    Log.debug("End");

  }

  @Test
  void futureFail(Vertx vertx, VertxTestContext context) {

    final Promise<String> promise = Promise.promise();
    Log.debug("Started");

    vertx.setTimer(500, id -> {
      promise.fail(new RuntimeException("Failed"));
      Log.debug("Failed");
    });

    final Future<String> future = promise.future();

    future.onSuccess(context::failNow)
      .onFailure(error -> {
        Log.debug("error: " + error);
      });

    Log.debug("end");

  }

  @Test
  void future_map(Vertx vertx, VertxTestContext context) {

    final Promise<String> promise = Promise.promise();

    vertx.setTimer(500, id -> {
      promise.complete("Success");
      Log.debug("Timer Done");
    });

    promise.future()
      .map(result -> {
        Log.debug("reserved result: " + result);
        return new JsonObject().put("key", result);
      })
      .map(result -> new JsonArray().add(result))
      .onSuccess(result -> {
        Log.debug("result: " + result);
        context.completeNow();
      })
      .onFailure(context::failNow);
  }

  @Test
  void future_ordination(Vertx vertx, VertxTestContext context) {

    vertx.createHttpServer()
      .requestHandler(request -> Log.debug("request: " + request))
      .listen(10_000)
      .compose(httpServer -> {
        Log.debug("Another task");
        return Future.succeededFuture(httpServer);
      })
      .compose(httpServer -> {
        Log.debug("other task");
        return Future.succeededFuture(httpServer);
      })
      .onFailure(context::failNow)
      .onSuccess(server -> {
        Log.info("server start on port: " + server.actualPort());
        context.completeNow();
      });

  }

  @Test
  void future_composition(Vertx vertx, VertxTestContext context) {

    var promise1 = Promise.<Void>promise();
    var promise2 = Promise.<Void>promise();
    var promise3 = Promise.<Void>promise();

    var future1 = promise1.future();
    var future2 = promise2.future();
    var future3 = promise3.future();

    CompositeFuture.all(future1, future2, future3)
      .onFailure(context::failNow)
      .onSuccess(result -> {
        Log.debug("Success");
        context.completeNow();
      });

    vertx.setTimer(500, id -> {
      promise1.complete();
      promise2.complete();
      promise3.complete();
    });



  }

}

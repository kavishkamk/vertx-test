package io.github.kavishkamk.testapp.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerExample extends AbstractVerticle {

  private static final Logger Log = LoggerFactory.getLogger(WorkerExample.class);

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerExample());

  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(new WorkerVerticle(), new DeploymentOptions()
      .setWorker(true)
      .setWorkerPoolSize(1)
      .setWorkerPoolName("my-worker-verticle-pool"));
    startPromise.complete();
    vertx.executeBlocking(event -> {
      Log.debug("Execute blocking code block");
      try {
        Thread.sleep(5000);
//        event.fail("Force fail");
        event.complete();
      } catch (InterruptedException e) {
        Log.error("Failed" + e);
        event.fail(e);
      }
    }, result -> {
      if(result.succeeded()) {
        Log.debug("Blocking code done");
      } else {
        Log.debug("Blocking call Failed due to:" + result.cause());
      }
    });

  }
}

package io.github.kavishkamk.testapp.eventloop;

import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class EventLoopVerticle extends AbstractVerticle {

  private static final Logger Log = LoggerFactory.getLogger(EventLoopVerticle.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx(new VertxOptions()
      .setMaxEventLoopExecuteTime(500)
      .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
      .setBlockedThreadCheckInterval(1)
      .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
      .setEventLoopPoolSize(2));
    vertx.deployVerticle(EventLoopVerticle.class.getName(),
      new DeploymentOptions()
        .setInstances(4));
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Log.debug("verticle: {}", EventLoopVerticle.class);
    Thread.sleep(5000);
  }
}

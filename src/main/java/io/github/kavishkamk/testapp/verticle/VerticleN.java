package io.github.kavishkamk.testapp.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleN extends AbstractVerticle {

  private static final Logger Log = LoggerFactory.getLogger(VerticleN.class);

  @Override
  public void start(final Promise<Void> startPromise) {
    Log.debug(
      "Start verticle: {} in thread: {}, configs: {}", VerticleN.class.getName()
      , Thread.currentThread().getName(), config().toString()
    );
    startPromise.complete();
  }

  @Override
  public void stop(final Promise<Void> storPromise) {
    Log.debug("Stop Promise: {}", VerticleN.class.getName());
    storPromise.complete();
  }

}

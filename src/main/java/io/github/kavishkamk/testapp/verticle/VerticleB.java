package io.github.kavishkamk.testapp.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleB extends AbstractVerticle {

  private static final Logger Log = LoggerFactory.getLogger(VerticleB.class);

  @Override
  public void start(final Promise<Void> startPromise) {
    Log.debug("Start verticle: {}" , VerticleB.class.getName());
    startPromise.complete();
  }

  @Override
  public void stop(final Promise<Void> storPromise) {
    Log.debug("Stop Promise: {}", VerticleB.class.getName());
    storPromise.complete();
  }

}

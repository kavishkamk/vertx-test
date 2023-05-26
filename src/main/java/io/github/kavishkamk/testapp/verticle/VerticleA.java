package io.github.kavishkamk.testapp.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VerticleA extends AbstractVerticle {
  private static final Logger Log = LoggerFactory.getLogger(VerticleA.class);

  @Override
  public void start(final Promise<Void> startPromise) {
    Log.debug("Start Verticle: {}", VerticleA.class.getName());
    vertx.deployVerticle(new VerticleAA());
    vertx.deployVerticle(new VerticleAB());
    startPromise.complete();
  }

  @Override
  public void stop(final Promise<Void> storPromise) {
    Log.debug("Stop Promise: {}", VerticleA.class.getName());
    storPromise.complete();
  }

}

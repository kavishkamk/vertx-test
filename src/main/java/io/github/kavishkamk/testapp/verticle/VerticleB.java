package io.github.kavishkamk.testapp.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleB extends AbstractVerticle {

  @Override
  public void start(final Promise<Void> startPromise) {
    System.out.println("Start verticle: " + VerticleB.class.getName());
    startPromise.complete();
  }

  @Override
  public void stop(final Promise<Void> storPromise) {
    System.out.println("Stop Promise: " + VerticleB.class.getName());
    storPromise.complete();
  }

}

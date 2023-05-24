package io.github.kavishkamk.testapp.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleN extends AbstractVerticle {

  @Override
  public void start(final Promise<Void> startPromise) {
    System.out.println("Start verticle: " + VerticleN.class.getName() + " in thread: " + Thread.currentThread().getName());
    startPromise.complete();
  }

  @Override
  public void stop(final Promise<Void> storPromise) {
    System.out.println("Stop Promise: " + VerticleN.class.getName());
    storPromise.complete();
  }

}

package io.github.kavishkamk.testapp.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleAA extends AbstractVerticle {

  @Override
  public void start(final Promise<Void> startPromise) {
    System.out.println("Start Promise: " + VerticleAA.class.getName());
    startPromise.complete();
  }

}

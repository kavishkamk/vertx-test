package io.github.kavishkamk.testapp.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleAB extends AbstractVerticle {

  @Override
  public void start(final Promise<Void> startPromise) {
    System.out.println("Start Verticle: " + VerticleAB.class.getName());
    startPromise.complete();
  }

}

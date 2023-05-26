package io.github.kavishkamk.testapp.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleAB extends AbstractVerticle {
  private static final Logger Log = LoggerFactory.getLogger(VerticleAB.class);
  @Override
  public void start(final Promise<Void> startPromise) {
    Log.debug("Start Verticle: {}", VerticleAB.class.getName());
    startPromise.complete();
  }

}

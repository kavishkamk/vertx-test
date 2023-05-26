package io.github.kavishkamk.testapp.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class MainVerticle extends AbstractVerticle {

  private static final Logger Log = LoggerFactory.getLogger(MainVerticle.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    Log.debug("Start Verticle: {}", MainVerticle.class.getName());
    vertx.deployVerticle(new VerticleA(), whenDeployed -> {
      Log.debug("Deployed Verticle: {}", VerticleA.class.getName());
      vertx.undeploy(whenDeployed.result());
    });
    vertx.deployVerticle(new VerticleB(), whenDeployed -> {
      Log.debug("Deployed Verticle: {}", VerticleB.class.getName());
      // not deployed
    });

    // multiple instance for scalling
    vertx.deployVerticle(
      VerticleN.class.getName(),
      new DeploymentOptions()
        .setInstances(4)
        .setConfig(
          new JsonObject()
            .put("id", UUID.randomUUID().toString())
            .put("name", VerticleN.class.getName())
        )
    );

    startPromise.complete();
  }

}

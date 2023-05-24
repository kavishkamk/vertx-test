package io.github.kavishkamk.testapp.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class MainVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }

  @Override
  public void start(final Promise<Void> startPromise) throws Exception {
    System.out.println("Start Verticle: " + MainVerticle.class.getName());
    vertx.deployVerticle(new VerticleA(), whenDeployed -> {
      System.out.println("Deployed Verticle: " + VerticleA.class.getName());
      vertx.undeploy(whenDeployed.result());
    });
    vertx.deployVerticle(new VerticleB(), whenDeployed -> {
      System.out.println("Deployed Verticle: " + VerticleB.class.getName());
      // not deployed
    });

    // multiple instance for scalling
    vertx.deployVerticle(VerticleN.class.getName(), new DeploymentOptions().setInstances(4));

    startPromise.complete();
  }

}

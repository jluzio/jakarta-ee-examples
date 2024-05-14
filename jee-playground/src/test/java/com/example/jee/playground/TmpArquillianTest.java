package com.example.jee.playground;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ArquillianExtension.class)
class TmpArquillianTest {

  @ApplicationScoped
  static class FooService {
    @Inject
    BarService barService;

    public String bar() {
      return barService.bar();
    }
  }

  @ApplicationScoped
  static class BarService {

    public String bar() {
      return "bar";
    }
  }

  @Deployment
  public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
        .addClasses(FooService.class, BarService.class)
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Inject
  private FooService fooService;

  @Test
  void test() throws Exception {
    Assertions.assertEquals(fooService.bar(), "bar");
  }
}

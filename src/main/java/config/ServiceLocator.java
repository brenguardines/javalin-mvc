package config;

import controllers.ProductosController;
import models.repositories.RepositorioDeProductos;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
  private static Map<String, Object> instances = new HashMap<>();

  @SuppressWarnings("unchecked")
  public static <T> T instanceOf(Class<T> componentClass) {
    String componentName = componentClass.getName();

    if (!instances.containsKey(componentName)) {
      if(componentName.equals(ProductosController.class.getName())) {
        ProductosController instance = new ProductosController(instanceOf(RepositorioDeProductos.class));
        instances.put(componentName, instance);
      }
      else if (componentName.equals(RepositorioDeProductos.class.getName())) {
        RepositorioDeProductos instance = new RepositorioDeProductos();
        instances.put(componentName, instance);
      }
    }

    return (T) instances.get(componentName);
  }
}

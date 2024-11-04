package utils;

import config.ServiceLocator;
import models.entities.Producto;
import models.repositories.RepositorioDeProductos;

public class Initializer {
  public static void init() {
    RepositorioDeProductos repositorioDeProductos = ServiceLocator.instanceOf(RepositorioDeProductos.class);

    Producto producto1 = Producto
        .builder()
        .nombre("Café")
        .precio(14000F)
        .build();
    Producto producto2 = Producto
        .builder()
        .nombre("Yerba")
        .precio(5500F)
        .build();
    Producto producto3 = Producto
        .builder()
        .nombre("Té de boldo")
        .precio(3300F)
        .build();

    repositorioDeProductos.guardar(producto1);
    repositorioDeProductos.guardar(producto2);
    repositorioDeProductos.guardar(producto3);
  }
}

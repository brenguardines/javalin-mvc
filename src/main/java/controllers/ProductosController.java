package controllers;

import exceptions.AccessDeniedException;
import exceptions.UsuarioNoAutenticadoException;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.entities.Producto;
import models.entities.TipoRol;
import models.repositories.RepositorioDeProductos;
import utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductosController implements ICrudViewsHandler {

  private RepositorioDeProductos repositorioDeProductos;

  public ProductosController(RepositorioDeProductos repositorioDeProductos){
    this.repositorioDeProductos = repositorioDeProductos;
  }

  @Override
  public void index(Context context) {
    //Al tener un middleware para esto, esto ya no iria
    /*
    String idUsuario = context.sessionAttribute("idUsuario");

    if(idUsuario == null){
      throw new UsuarioNoAutenticadoException();
    }

    TipoRol rol = TipoRol.valueOf(context.sessionAttribute("rolUsuario"));

    if(!rol.equals((TipoRol.SELLER))){
      throw new AccessDeniedException();
    }
     */

    //Pretende devolver una vista que contenga todos los recursos almacenados en mi sistema (en este caso productos)
    List<Producto> productos = this.repositorioDeProductos.buscarTodos();

    Map<String, Object> model = new HashMap<>();
    model.put("productos", productos);
    model.put("titulo", "Listado de Productos");

    context.render("productos/productos.hbs", model); //Render va a llamar a handlebars (handlebars rellena las plantillas con los datos)
  }

  @Override
  public void show(Context context) {
    //Pretende devolver una vista que contenga el detalle de un producto, sin posibilidad de edicion
    Optional<Producto> posibleProductoBuscado = this.repositorioDeProductos.buscarPorId(Long.valueOf(context.pathParam("id")));

    if(posibleProductoBuscado.isEmpty()) {
      context.status(HttpStatus.NOT_FOUND);
      return;
    }

    Map<String, Object> model = new HashMap<>();
    model.put("producto", posibleProductoBuscado.get());
    model.put("visualizacion", true);

    context.render("productos/detalle_producto.hbs", model);
  }

  @Override
  public void create(Context context) {
    //Pretende devolver una vista que contenga un formulario para crear un producto nuevo
    Map<String, Object> model = new HashMap<>();
    model.put("titulo", "Alta de producto");

    context.render("productos/formulario_producto.hbs");
  }

  @Override
  public void save(Context context) {
    //Pretende dar de alta un nuevo producto en base a los datos recibidos en la request
    Producto nuevoProducto = new Producto();

    nuevoProducto.setNombre(context.formParam("nombre")); //La key tiene que ser la misma del name de los inputs
    nuevoProducto.setPrecio(Float.valueOf(context.formParam("precio"))); //El valueOf es porque tod o lo que llega es un string

    this.repositorioDeProductos.guardar(nuevoProducto);
    //O bien lanzo una pantalla de exito o bien redirecciono al user a la pantalla de listado de productos
    context.redirect("/productos");
  }

  @Override
  public void edit(Context context) {
    //Pretende devolver una vista con un formulario de edicion para un producto en particular (se recibe el ID por Path Param)
    Optional<Producto> posibleProductoBuscado = this.repositorioDeProductos.buscarPorId(Long.valueOf(context.pathParam("id")));

    if(posibleProductoBuscado.isEmpty()) {
      context.status(HttpStatus.NOT_FOUND);
      return;
    }

    Map<String, Object> model = new HashMap<>();
    model.put("producto", posibleProductoBuscado.get());
    model.put("edicion", true);

    context.render("productos/detalle_producto.hbs", model);
  }

  @Override
  public void update(Context context) {
    //Pretende modificar el producto en base a los datos recibidos en la request (se recibe el ID por Path Param)
    Optional<Producto> posibleProductoBuscado = this.repositorioDeProductos.buscarPorId(Long.valueOf(context.pathParam("id")));

    if(posibleProductoBuscado.isEmpty()) {
      context.status(HttpStatus.NOT_FOUND);
      return;
    }

    Producto producto = posibleProductoBuscado.get();

    producto.setNombre(context.formParam("nombre")); //La key tiene que ser la misma del name de los inputs
    producto.setPrecio(Float.valueOf(context.formParam("precio"))); //El valueOf es porque tod o lo que llega es un string

    this.repositorioDeProductos.guardar(producto);
    //O bien lanzo una pantalla de exito o bien redirecciono al user a la pantalla de listado de productos
    context.redirect("/productos");
  }

  @Override
  public void delete(Context context) {
    //Pretende eliminar el producto en base al ID recibido por Path Param
    Optional<Producto> posibleProductoBuscado = this.repositorioDeProductos.buscarPorId(Long.valueOf(context.pathParam("id")));

    if(posibleProductoBuscado.isEmpty()) {
      context.status(HttpStatus.NOT_FOUND);
      return;
    }

    Producto producto = posibleProductoBuscado.get();

    this.repositorioDeProductos.eliminar(producto);
    context.redirect("/productos");
  }
}

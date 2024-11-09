package server;

import config.ServiceLocator;
import controllers.ProductosController;
import io.javalin.Javalin;
import models.entities.TipoRol;
import models.repositories.RepositorioDeProductos;

public class Router {
  public static void init(Javalin app) {

    //EJEMPLOS
    app.get("/prueba", ctx -> ctx.result("Hola Mundo")); //ctx es context

    //Query Params
    app.get("/saludo", ctx -> {
      ctx.result("Hola " + ctx.queryParam("nombre") + " " + ctx.queryParam("apellido"));
    });

    //Route params | Path params
    app.get("/saludo-para/{nombre}", ctx -> ctx.result("Hola " + ctx.pathParam("nombre")));

    //Proyecto

    //ProductosController productosController = new ProductosController(new RepositorioDeProductos());
    //app.get("/productos", productosController::index); //:: es una referencia a metodos

    //Al no estar trabajando con API REST no puedo usar put/patch/delete
    //Como estoy trabajando con formularios HTML (y estos solo acepta get y post), utilizo estos dos para solucionar lo de arriba
    app.get("/productos", ServiceLocator.instanceOf(ProductosController.class)::index, TipoRol.ADMIN, TipoRol.SELLER);
    app.get("/productos/nuevo", ServiceLocator.instanceOf(ProductosController.class)::create);
    app.get("/productos/{id}", ServiceLocator.instanceOf(ProductosController.class)::show);
    app.get("/productos/{id}/edicion", ServiceLocator.instanceOf(ProductosController.class)::edit);
    app.post("/productos/{id}/edicion", ServiceLocator.instanceOf(ProductosController.class)::update);
    app.post("/productos/{id}/eliminiacion", ServiceLocator.instanceOf(ProductosController.class)::delete);
    app.post("/productos", ServiceLocator.instanceOf(ProductosController.class)::save);

  }
}

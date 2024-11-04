package server;

import io.javalin.Javalin;

public class App {
  public static void main(String[] args) {

    Server.init();

    /*
    Javalin app = Javalin.create().start(8080);

    app.get("/prueba", ctx -> ctx.result("Hola Mundo")); //ctx es context

    //QUERY PARAMS (tod o lo que este luego del ? es query params) - Se utiliza mas para filtrado
    app.get("/saludo", ctx -> ctx.result("Hola " + ctx.queryParam("nombre") + " " + ctx.queryParam("apellido")));

    //Primero va lo hardcodeado y luego lo generico
    //Colision de patrones - siempre hay que ponerlos primero ya que sino me va a tomar la otra ruta
    app.get("/saludo-para/lola", ctx -> ctx.result("Hola Lola!!"));

    //ROUTE PARAMS/PATH PARAMS - Se utiliza cuando es algo que forma parte de un recurso
    app.get("/saludo-para/{nombre}", ctx -> ctx.result("Hola " + ctx.pathParam("nombre")));
    app.get("/saludo-para/{nombre}/{apellido}", ctx -> ctx.result("Hola " + ctx.pathParam("nombre") + " " + ctx.pathParam("apellido")));
    //app.get("/saludo-para/lola", ctx -> ctx.result("Hola Lola!!"));

     */

  }
}

//Ejemplo ruta de Query Params
//http://localhost:8080/saludo?nombre=bren&apellido=guardines
//http://localhost:8080/saludo?apellido=guardines&nombre=bren

//Ejemplo ruta de Route Params/Path Params
//http://localhost:8080/saludo-para/bren
//http://localhost:8080/saludo-para/bren/guardines
//http://localhost:8080/saludo-para/lola
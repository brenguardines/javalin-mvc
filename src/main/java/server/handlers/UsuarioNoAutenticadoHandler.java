package server.handlers;

import io.javalin.Javalin;

public class UsuarioNoAutenticadoHandler implements IHandler{
  @Override
  public void setHandle(Javalin app) {
    app.exception(UnsupportedOperationException.class, (e, context) -> {
      context.redirect("/login");
    });
  }
}

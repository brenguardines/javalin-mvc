package middlewares;

import exceptions.AccessDeniedException;
import exceptions.UsuarioNoAutenticadoException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import models.entities.TipoRol;

public class AuthMiddleware {
  public static void apply(Javalin app){
    app.beforeMatched(ctx -> {
      var rol = getUsuarioRole(ctx);

      //Verifico Sesion
      if(rol == null){
        throw new UsuarioNoAutenticadoException();
      }

      //Verifico Roles
      if(!ctx.routeRoles().isEmpty() && !ctx.routeRoles().contains(rol)){
        throw new AccessDeniedException();
      }
    });
  }

  public static TipoRol getUsuarioRole(Context context){
    return context.sessionAttribute("usuarioRol") != null ?
        TipoRol.valueOf(context.sessionAttribute("usuarioRol")) : null;
  }
}

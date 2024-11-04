package models.entities;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
  private Long id;
  private String nombre;
  private Float precio;

  public Double precioFinal() {
    return this.precio * 1.21;
  }
}
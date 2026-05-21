/*
 * Copyright © 2026 Nicolás Cruz && Isaac Vazqués.
 * Todos los derechos reservados.
 *
 * Este software es de uso académico y privado.
 * Fecha de creación: 7 de mayo del 2026
 */
package controlescolarfx.modelo.pojo;

/**
 * POJO nacido de la necesidad entre las capas, comunicar la sesión con otras
 * capas.
 *
 * @author yazid
 */
public class Sesion {
    
    //private Boolean credencialesValidas;
    private Boolean credencialesEncontradas;
    private String mensaje;
    private Usuario usuarioSesion;

    public Sesion() {
    }

    public Sesion(Boolean credencialesEncontradas, String mensaje, Usuario usuarioSesion) {
        this.credencialesEncontradas = credencialesEncontradas;
        this.mensaje = mensaje;
        this.usuarioSesion = usuarioSesion;
    }

    public Boolean getCredencialesEncontradas() {
        return credencialesEncontradas;
    }

    public void setCredencialesEncontradas(Boolean credencialesEncontradas) {
        this.credencialesEncontradas = credencialesEncontradas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }
    
}

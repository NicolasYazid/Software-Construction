/*
 * Copyright © 2026 Nicolás Cruz && Isaac Vazqués.
 * Todos los derechos reservados.
 *
 * Este software es de uso académico y privado.
 * Fecha de creación: 14 de mayo del 2026
 */
package controlescolarfx.utilidades;

import javafx.scene.control.Alert;

/**
 *
 * @author yazid
 */
public class Utilidades {
    
    public static void mostrarAlertaSimple(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.setHeaderText(null);
        alerta.showAndWait();
        //alerta.show();
    }
    
}

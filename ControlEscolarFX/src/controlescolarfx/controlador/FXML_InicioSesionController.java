/*
 * Copyright © 2026 Nicolás Cruz && Isaac Vazqués.
 * Todos los derechos reservados.
 *
 * Este software es de uso académico y privado.
 * Fecha de creación: 5 de mayo del 2026
 */
package controlescolarfx.controlador;

import controlescolarfx.ControlEscolarFX;
import controlescolarfx.modelo.dao.AutenticacionDAO;
import controlescolarfx.modelo.pojo.Sesion;
import controlescolarfx.modelo.pojo.Usuario;
import controlescolarfx.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author yazid
 */
public class FXML_InicioSesionController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField tfNoPersonal;
    @FXML
    private TextField tfPassword;
    private Label lblErrorPersonal;
    private Label lblErrorPassword;
    @FXML
    private Button btnIngresar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickBtnIngresar(ActionEvent event) {
        
        String noPersonal = tfNoPersonal.getText();
        String contrasenia = tfPassword.getText();
        
        if (sonDatosPermitidos(noPersonal, contrasenia)) {
            validarCredenciales(noPersonal, contrasenia);
        }
        
    }

    private boolean sonDatosPermitidos(String noPersonal, String password) {
        //lblErrorPersonal.setText("");
        //lblErrorPassword.setText("");
        boolean bandera = true;
        if (noPersonal.isEmpty()) {
            //lblErrorPersonal.setText("No de personal obligatorio");
            bandera = false;
        }
        if (password.isEmpty()) {
            //lblErrorPassword.setText("Contraseñia obligatoria");
            bandera = false;
        }
        return bandera;
    }

    private void validarCredenciales(String noPersonal, String password) {
        try {
            Sesion sesionUsuario = AutenticacionDAO.validarSesionUsuario(noPersonal, password);
            if (sesionUsuario.getCredencialesEncontradas()) {
                Utilidades.mostrarAlertaSimple("Credenciales verificadas", "Bienvenido(a)"
                        + sesionUsuario.getUsuarioSesion().getNombre() + " al sistema de control escolar UV",
                        Alert.AlertType.INFORMATION);
                
                irPantallaPrincipal(sesionUsuario.getUsuarioSesion());
                
            } else {
                Utilidades.mostrarAlertaSimple("Error al validar credenciales", sesionUsuario.getMensaje(),
                        Alert.AlertType.WARNING);
            }
        } catch (SQLException ex) {
            Utilidades.mostrarAlertaSimple("ERROR", ex.getMessage(), Alert.AlertType.ERROR);
            System.out.println(ex.getMessage());
        } catch (NullPointerException ex) {
            Utilidades.mostrarAlertaSimple("Error general", ex.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException ex) {
            Utilidades.mostrarAlertaSimple("Error de I/O", "No se pudo cargar la siguiente pantalla", Alert.AlertType.ERROR);
            ex.printStackTrace();
        }
    }

    private void irPantallaPrincipal(Usuario usuarioSesion) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(ControlEscolarFX.class.getResource("vista/FXMLPrincipal.fxml"));
            Parent vista = loader.load();
            FXMLPrincipalController controlador = loader.getController();
            controlador.inicializarInformacion(usuarioSesion);
            Scene scene = new Scene(vista);
            
            Stage escenario = (Stage) tfNoPersonal.getScene().getWindow();
            escenario.setScene(scene);
            escenario.setTitle("Pantalla principal");
            escenario.show();            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

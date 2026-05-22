/*
 * Copyright © 2026 Nicolás Cruz && Isaac Vazqués.
 * Todos los derechos reservados.
 *
 * Este software es de uso académico y privado.
 * Fecha de creación: 14 de mayo del 2026
 */
package controlescolarfx.controlador;

import controlescolarfx.ControlEscolarFX;
import controlescolarfx.modelo.pojo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yazid
 */
public class FXMLPrincipalController implements Initializable {
    
    private Usuario usuarioSesion;
    
    @FXML
    private Button clicBtnIrAlumnos;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void inicializarInformacion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }
    
    @FXML
    private void clicBtnIrAdminAlumnos(ActionEvent event) {
        try {
            Parent vista = FXMLLoader.load(ControlEscolarFX.class.getResource("vista/FXML_AdminAlumnos.fxml"));
            Scene scene = new Scene(vista);
            
            Stage stageAdmin = new Stage();
            stageAdmin.setScene(scene);
            stageAdmin.setTitle("Administrador de alumnos");
            stageAdmin.initModality(Modality.APPLICATION_MODAL);
            stageAdmin.showAndWait();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}

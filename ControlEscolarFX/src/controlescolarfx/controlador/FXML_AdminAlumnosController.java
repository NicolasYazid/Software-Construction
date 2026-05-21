/*
 * Copyright © 2026 Nicolás Cruz && Isaac Vazqués.
 * Todos los derechos reservados.
 *
 * Este software es de uso académico y privado.
 * Fecha de creación: 14 de mayo del 2026
 */
package controlescolarfx.controlador;

import controlescolarfx.modelo.dao.AlumnoDAO;
import controlescolarfx.modelo.pojo.Alumno;
import controlescolarfx.utilidades.Utilidades;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author yazid
 */
public class FXML_AdminAlumnosController implements Initializable {

    @FXML
    private TextField tfBusquedaAlumno;
    @FXML
    private TableView<Alumno> tvAlumnos;
    @FXML
    private TableColumn callMatricula;
    @FXML
    private TableColumn callNombre;
    @FXML
    private TableColumn callApPaterno;
    @FXML
    private TableColumn callApMaterno;
    @FXML
    private TableColumn callFacultad;
    @FXML
    private TableColumn callCarrera;
    @FXML
    private TableColumn callEmail;
    
    private ObservableList<Alumno> alumnos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacionAlumnos();
    }
    
    private void configurarTabla() { // ósea, configurar lo que la tabla va a mostrar
        callMatricula.setCellValueFactory(new PropertyValueFactory("matricula"));
        callNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        callApPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        callApMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        callEmail.setCellValueFactory(new PropertyValueFactory("email"));
        callFacultad.setCellValueFactory(new PropertyValueFactory("facultad"));
        callCarrera.setCellValueFactory(new PropertyValueFactory("carrera"));
    }
    
    private void cargarInformacionAlumnos() {
        try {
            alumnos = FXCollections.observableArrayList();
            List<Alumno> alumnosBD = AlumnoDAO.obtenerAlumnos();
            alumnos.addAll(alumnos);
            tvAlumnos.setItems(alumnos);
        } catch (SQLException ex) {
            Utilidades.mostrarAlertaSimple("Error de conexión", ex.getMessage(), Alert.AlertType.NONE);
        } catch (NullPointerException n) {
            Utilidades.mostrarAlertaSimple("Error al cargar",
                    "Lo sentimos la información de los alumnos no puede ser cargada en este momento,"
                            + "por favor inténtelo más tarde",
                    Alert.AlertType.WARNING);
        }
    }
    
    @FXML
    private void clicBtnRegistrar(ActionEvent event) {
    }

    @FXML
    private void clicBtnModificar(ActionEvent event) {
    }

    @FXML
    private void clicBtnEliminar(ActionEvent event) {
    }
    
}

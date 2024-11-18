package org.example.gameofthronesbd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.gameofthronesbd.model.Characters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.gameofthronesbd.model.CharactersItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import java.io.File;
import java.io.IOException;

public class GoTController {
    @FXML
    private ImageView imagenCharacter;

    @FXML
    private VBox vboxOk;

    @FXML
    private Button boton_ok;

    @FXML
    private Label doc_vacio;

    @FXML
    private Label datos_incorrectos;

    @FXML
    private TextField nombre_doc;

    @FXML
    private VBox exportar_documento;

    @FXML
    private Button Volver;

    @FXML
    private TextField buscarid;

    @FXML
    private TextField buscarnombre;

    @FXML
    private TextField buscarapellido;

    @FXML
    private TableView<CharactersItem> tablabusqueda;

    @FXML
    private TableColumn<CharactersItem, String> colnombrecompleto;

    @FXML
    private TableColumn<CharactersItem, String> colcasa;

    @FXML
    private TableColumn<CharactersItem, String> coltitulo;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtpassword;

    @FXML
    private Button botonbuscar;

    @FXML
    private Button botonexportartabla;

    @FXML
    private Button botonlogin;


    //Modificar para que realice una consulta contra la base de datos
    @FXML
    public void busqueda(MouseEvent event) {
        String idText = buscarid.getText().trim();
        String nombre = buscarnombre.getText().trim();
        String apellido = buscarapellido.getText().trim();

        // Filtrar los personajes según los criterios de búsqueda
    }

    @FXML
    protected void clickVolver(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthrones/proyecto/consultas_tabla.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void salir(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthrones/proyecto/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickOk(ActionEvent actionEvent) {
        vboxOk.setVisible(false);
    }

    @FXML
    public void abrirExportarDocumento(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthrones/proyecto/exportar_documento.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Modificar el metodo para que el onclick en la tabla cargue los datos del personaje en los campos, no la imagen
    public void onTableClicked(MouseEvent event) {
        // Verifica si se ha realizado un doble clic en la tabla
        if (event.getClickCount() == 2) {
            // Obtiene el elemento seleccionado de la tabla
            CharactersItem item = tablabusqueda.getSelectionModel().getSelectedItem();
            //Crea una imagen a partir de la url a la imagen del personaje

        }
    }
}
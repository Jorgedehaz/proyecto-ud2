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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import org.example.gameofthronesbd.model.Conectar;
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

    // Método para cifrar la contraseña en SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString(); // Devuelve el hash como un String hexadecimal
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    @FXML
    private boolean verificacion(String nombre, String password) {
        // Cifrar la contraseña ingresada usando SHA-256
        String hashedPassword = hashPassword(password);

        String query = "SELECT password FROM usuarios WHERE nombre = ?";

        try (Connection connection = Conectar.conectarusuarios();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nombre);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Verificar si se encontró el usuario y si la contraseña coincide
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    // Comparar el hash de la contraseña almacenada con el de la ingresada
                    return storedPassword.equals(hashedPassword);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        String nombre = txtname.getText().trim();
        String password = txtpassword.getText().trim();

        // Verificar si los campos están vacíos
        if (nombre.isEmpty() || password.isEmpty()) {
            datos_incorrectos.setVisible(true);  // Mostrar el mensaje de error si faltan datos
            return;
        }

        // Verificar si las credenciales son correctas
        if (verificacion(nombre, password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthronesbd/consultas_tabla.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            datos_incorrectos.setVisible(true);  // Mostrar mensaje de error si la verificación falla
            txtname.clear();
            txtpassword.clear();
        }
    }


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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthronesbd/consultas_tabla.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthronesbd/login.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameofthronesbd/exportar_documento.fxml"));
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


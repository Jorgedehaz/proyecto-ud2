package org.example.gameofthronesbd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import org.example.gameofthronesbd.model.Conectar;
import java.io.File;
import java.io.IOException;

public class GoTController {

    @FXML
    private ImageView imagenCharacter;

    @FXML
    private VBox consultas;

    @FXML
    private Label txtErrores;

    @FXML
    private VBox vboxCerrar;

    @FXML
    private Button boton_cerrar;

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
    private TextField txtid;
    @FXML
    private TextField txtnombre;
    @FXML
    private TextField txtapellido;
    @FXML
    private TextField txtnombrecompleto;
    @FXML
    private TextField txttitulo;
    @FXML
    private TextField txtfamilia;
    @FXML
    private TextField txtnuevonombre;
    @FXML
    private TextField txtnuevoapellido;
    @FXML
    private TextField txtnuevotitulo;
    @FXML
    private TextField txtnuevonombrecompleto;
    @FXML
    private TextField txtnuevofamilia;

    @FXML
    private Button botonbuscar;

    @FXML
    private Button botonexportartabla;

    @FXML
    private Button botonlogin;

    @FXML
    private Button JSON;

    @FXML
    private Button botoninsert;

    @FXML
    public void initialize() {

        if (colnombrecompleto != null || colcasa != null || coltitulo != null) {
            colnombrecompleto.setCellValueFactory(new PropertyValueFactory<>("fullName"));
            colcasa.setCellValueFactory(new PropertyValueFactory<>("family"));
            coltitulo.setCellValueFactory(new PropertyValueFactory<>("title"));
        }else {
            ;
        }
        // Añadir más columnas si vemos que hace falta, por ej región
    }

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

                //Cambio de escena
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


    @FXML
    public void busqueda() {
        // Obtener valores de los campos de texto
        String id = txtid.getText().trim();
        String firstName = txtnombre.getText().trim();
        String lastName = txtapellido.getText().trim();
        String fullName = txtnombrecompleto.getText().trim();
        String title = txttitulo.getText().trim();
        String family = txtfamilia.getText().trim();
        List<CharactersItem> results = new ArrayList<>();
        if (!id.equals("") || !firstName.equals("") || !lastName.equals("") || !fullName.equals("") || !title.equals("") || !family.equals("")) {
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM characters WHERE 1=1");
            List<String> parameters = new ArrayList<>();

            // Construir la consulta dinámica según los campos no vacíos
            if (!txtid.getText().trim().isEmpty()) {
                queryBuilder.append(" AND id = ?");
                parameters.add(id);
            }
            if (!txtnombre.getText().trim().isEmpty()) {
                queryBuilder.append(" AND firstName LIKE ?");
                parameters.add("%" + firstName + "%");
            }
            if (!txtapellido.getText().trim().isEmpty()) {
                queryBuilder.append(" AND lastName LIKE ?");
                parameters.add("%" + lastName + "%");
            }
            if (!txtnombrecompleto.getText().trim().isEmpty()) {
                queryBuilder.append(" AND fullName LIKE ?");
                parameters.add("%" + fullName + "%");
            }
            if (!txttitulo.getText().trim().isEmpty()) {
                queryBuilder.append(" AND title LIKE ?");
                parameters.add("%" + title + "%");
            }
            if (!txtfamilia.getText().trim().isEmpty()) {
                queryBuilder.append(" AND family LIKE ?");
                parameters.add("%" + family + "%");
            }

            // Ejecutar la consulta
            results = new ArrayList<>();
            try (Connection connection = Conectar.conectarGoT();
                 PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {

                // Establecer los valores de los parámetros
                for (int i = 0; i < parameters.size(); i++) {
                    preparedStatement.setString(i + 1, parameters.get(i));
                }

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Crear objetos CharactersItem con los resultados
                        CharactersItem character = new CharactersItem();
                        character.setId(resultSet.getInt("id"));
                        character.setFirstName(resultSet.getString("firstName"));
                        character.setLastName(resultSet.getString("lastName"));
                        character.setFullName(resultSet.getString("fullName"));
                        character.setFamily(resultSet.getString("family"));
                        character.setTitle(resultSet.getString("title"));
                        results.add(character);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Cargar los resultados en la tabla
        ObservableList<CharactersItem> observableResults = FXCollections.observableArrayList(results);
        tablabusqueda.setItems(observableResults);
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
    public void clickCerrar(ActionEvent actionEvent) {
        vboxCerrar.setVisible(false);
        txtErrores.setText("");
        consultas.setDisable(false);;
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

    @FXML
    public void exportarJSON(ActionEvent actionEvent) {
        if (!this.nombre_doc.getText().isEmpty()) {
            this.doc_vacio.setVisible(false);
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/docs/" + this.nombre_doc.getText() + ".json"), SearchHolder.getInstance().getCharacterItems());
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.vboxCerrar.setVisible(true);
        } else {
            this.doc_vacio.setVisible(true);
        }

    }


    @FXML
    public void onTableClicked(MouseEvent event) {
        // Verifica si se ha realizado un doble clic en la tabla
        if (event.getClickCount() == 1) {
            // Obtiene el elemento seleccionado de la tabla
            CharactersItem item = tablabusqueda.getSelectionModel().getSelectedItem();
            if (item != null) {
                //Crea una imagen a partir de la url a la imagen del personaje
                txtid.setText(String.valueOf(item.getId()));
                txtnombre.setText(item.getFirstName());
                txtapellido.setText(item.getLastName());
                txtnombrecompleto.setText(item.getFullName());
                txttitulo.setText(item.getTitle());
                txtfamilia.setText(item.getFamily());
            }
        }
    }

    @FXML
    public void abrirNuevoPersonaje(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/gameofthronesbd/nuevo_personaje.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void insertCharacter(ActionEvent event) {
        String firstName = txtnuevonombre.getText().trim();
        String lastName = txtnuevoapellido.getText().trim();
        String fullName = txtnuevonombrecompleto.getText().trim();
        String title = txtnuevotitulo.getText().trim();
        String family = txtnuevofamilia.getText().trim();

        try (Connection connection = Conectar.conectarGoT()) {
            String sql = "INSERT INTO characters (firstName, lastName, fullName, title, family) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, fullName);
                preparedStatement.setString(4, title);
                preparedStatement.setString(5, family);

                // Ejecutar la consulta
                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("¡Personaje creado exitosamente!");
                } else {
                    System.out.println("No se insertó ningún personaje.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clearCampos() {
        txtid.clear();
        txtnombre.clear();
        txtfamilia.clear();
        txtapellido.clear();
        txttitulo.clear();
        txtnombrecompleto.clear();
    }

    @FXML
    public void deleteCharacter() {
        CharactersItem item = tablabusqueda.getSelectionModel().getSelectedItem();
        if (item != null) {
            String str = "DELETE FROM characters WHERE id = ?";
            try (Connection connection = Conectar.conectarGoT();
                 PreparedStatement statement = connection.prepareStatement(str);) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
                clearCampos();
                busqueda();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            txtErrores.setText("No se ha seleccionado ningún personaje a borrar");
            vboxCerrar.setVisible(true);
        }
    }
}


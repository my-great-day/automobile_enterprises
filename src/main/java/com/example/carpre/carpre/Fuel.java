package com.example.carpre.carpre;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;

public class Fuel {

    @FXML
    private Button addCancelDriveButton;

    @FXML
    private Button addDriveButtonData;

    @FXML
    private AnchorPane addPaneDataDrive;

    @FXML
    private Button addbtn;

    @FXML
    private TextField addfuel;

    @FXML
    private TextField addnumber;

    @FXML
    private TextField addticket;

    @FXML
    private Button deletebtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TableColumn<FuelSql, Integer> id;

    @FXML
    private TableColumn<FuelSql, String> nticket;

    @FXML
    private TableView<FuelSql> tableViewData;

    @FXML
    private TableColumn<FuelSql, String> tfuel;

    @FXML
    private TableColumn<FuelSql, String> tnumber;

    @FXML
    private Button updatebtn;

    @FXML
    private AnchorPane workPaneFuel;

    private Connection connection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:src/main/resources/data/fuel.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);


            System.out.println("Connected");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Good Job");
        }
        return conn;
    }
    ObservableList<FuelSql> FuelSqlList = FXCollections.observableArrayList();

    private void refTable(){
        FuelSqlList.clear();

        String sql = "SELECT * FROM fuel";
        try (Connection connection = this.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                FuelSqlList.add(new FuelSql(
                        resultSet.getInt("id"),
                        resultSet.getString("nomer_cheka"),
                        resultSet.getString("toplivo"),
                        resultSet.getString("chek")));

                tableViewData.setItems(FuelSqlList);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        loadDate();
        delete_only();
    }

    @FXML
    void add(ActionEvent event) {
        addPaneDataDrive.setVisible(true);
    }

    @FXML
    void add_fuel_to_sql(ActionEvent event) {
        String nomer_cheka = addticket.getText();
        String toplivo = addfuel.getText();
        String chek = addnumber.getText();

        String ask_question = "INSERT INTO fuel(nomer_cheka, toplivo, chek) values(?,?,?);";

        try (Connection connection = this.connection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(ask_question)){
            preparedStatement1.setString(1, nomer_cheka);
            preparedStatement1.setString(2, toplivo);
            preparedStatement1.setString(3, chek);

            preparedStatement1.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Информация");
            alert.setHeaderText(null);
            alert.setContentText("Добавлено!");
            alert.showAndWait();

            addPaneDataDrive.setVisible(false);
            refTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cancel_fuel_to_sql(ActionEvent event) {
        addPaneDataDrive.setVisible(false);
    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void update(ActionEvent event) {
        refTable();
    }
    private void loadDate() {
        refTable();
        id.setCellValueFactory(new PropertyValueFactory<FuelSql, Integer>("id"));
        nticket.setCellValueFactory(new PropertyValueFactory<FuelSql, String>("nomer_cheka"));
        tfuel.setCellValueFactory(new PropertyValueFactory<FuelSql, String>("toplivo"));
        tnumber.setCellValueFactory(new PropertyValueFactory<FuelSql, String>("chek"));
    }
    private void delete_only(){
        TableView.TableViewSelectionModel<FuelSql> selectionModel = tableViewData.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<FuelSql>(){
            @Override
            public void changed(ObservableValue<? extends FuelSql> observableValue, FuelSql driveSql, FuelSql t1) {
                deletebtn.setOnAction(ActionEvent -> deleteBtn(t1.getId()));
            }

        });
    }
    private void deleteBtn(int id){
        String sql = "DELETE FROM fuel WHERE id = ?";
        try (Connection connection = this.connection();
             PreparedStatement preparedS = connection.prepareStatement(sql)){
            preparedS.setInt(1, id);
            preparedS.executeUpdate();
            refTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

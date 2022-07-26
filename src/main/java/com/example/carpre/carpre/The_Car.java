package com.example.carpre.carpre;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

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

public class The_Car {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_data;

    @FXML
    private DatePicker add_date_vip;

    @FXML
    private TextField add_garantiya;

    @FXML
    private TextField add_gos_nomer;

    @FXML
    private TextField add_marka;

    @FXML
    private ComboBox<String> add_marka_topliva;

    @FXML
    private TextField add_nomer_dvigatel;

    @FXML
    private TextField add_nomer_kuzov;

    @FXML
    private TextField add_stoimost;

    @FXML
    private TextField add_tip_auto;

    @FXML
    private TextField add_vin_nomer;

    @FXML
    private Button adddata;

    @FXML
    private Button cancel_btn;

    @FXML
    private Button deletebtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TableColumn<The_Car_Sql, String> garantiy;

    @FXML
    private TableColumn<The_Car_Sql, String> god_vipusk;

    @FXML
    private TableColumn<The_Car_Sql, String> gos_nomer;

    @FXML
    private TableColumn<The_Car_Sql, Integer> id;

    @FXML
    private TableColumn<The_Car_Sql, String> marka;

    @FXML
    private TableColumn<The_Car_Sql, String> marka_topliva;

    @FXML
    private TableColumn<The_Car_Sql, String> nomer_dvigatel;

    @FXML
    private TableColumn<The_Car_Sql, String> nomer_kuzov;

    @FXML
    private AnchorPane paneAddData;

    @FXML
    private TableColumn<The_Car_Sql, String> stoimost;

    @FXML
    private TableView<The_Car_Sql> tableViewData;

    @FXML
    private TableColumn<The_Car_Sql, String> tip_auto;

    @FXML
    private Button update;

    @FXML
    private TableColumn<The_Car_Sql, Integer> vin;

    @FXML
    private AnchorPane workPaneCar;

    @FXML
    void add_data_func(ActionEvent event) {
        String tip_auto = add_tip_auto.getText();
        String marka = add_marka.getText();
        String god_vipusk = String.valueOf(add_date_vip.getValue());
        String vin = add_vin_nomer.getText();
        String gos_nomer = add_gos_nomer.getText();
        String nomer_dvigatel = add_nomer_dvigatel.getText();
        String nomer_kuzov = add_nomer_kuzov.getText();
        String garantiy = add_garantiya.getText();
        String stoimost = add_stoimost.getText();
        String marka_topliva = add_marka_topliva.getSelectionModel().getSelectedItem().toString();

        String ask_question = "INSERT INTO auto(tip_auto, marka, god_vipusk, vin, gos_nomer, nomer_dvigatel, nomer_kuzov, garantiy, stoimost, marka_topliva) values(?,?,?,?,?,?,?,?,?,?);";

        try (Connection connection = this.connection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(ask_question)){
            preparedStatement1.setString(1, tip_auto);
            preparedStatement1.setString(2, marka);
            preparedStatement1.setString(3, god_vipusk);
            preparedStatement1.setString(4, vin);
            preparedStatement1.setString(5, gos_nomer);
            preparedStatement1.setString(6, nomer_dvigatel);
            preparedStatement1.setString(7, nomer_kuzov);
            preparedStatement1.setString(8, garantiy);
            preparedStatement1.setString(9, stoimost);
            preparedStatement1.setString(10, marka_topliva);

            preparedStatement1.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Информация");
            alert.setHeaderText(null);
            alert.setContentText("Добавлено!");
            alert.showAndWait();

            paneAddData.setVisible(false);
            refTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void update(ActionEvent event) {
        refTable();
    }


    @FXML
    void cancel_func(ActionEvent event) {
        paneAddData.setVisible(false);
    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void add_data(ActionEvent event) {
        paneAddData.setVisible(true);
    }

    @FXML
    void initialize() {
        ObservableList<String> stringObservableList = FXCollections.observableArrayList("АИ-92","АИ-95", "АИ-98", "Солярка");
        add_marka_topliva.setItems(stringObservableList);
        loadDate();
        delete_only();
    }
    private Connection connection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:src/main/resources/data/auto.db";
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
    ObservableList<The_Car_Sql> TheCarSqlList = FXCollections.observableArrayList();

    private void refTable(){
        TheCarSqlList.clear();

        String sql = "SELECT * FROM auto";
        try (Connection connection = this.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                TheCarSqlList.add(new The_Car_Sql(
                        resultSet.getInt("id"),
                        resultSet.getString("tip_auto"),
                        resultSet.getString("marka"),
                        resultSet.getString("god_vipusk"),
                        resultSet.getInt("vin"),
                        resultSet.getString("gos_nomer"),
                        resultSet.getString("nomer_dvigatel"),
                        resultSet.getString("nomer_kuzov"),
                        resultSet.getString("garantiy"),
                        resultSet.getString("stoimost"),
                        resultSet.getString("marka_topliva")));

                tableViewData.setItems(TheCarSqlList);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadDate() {
        refTable();
        id.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, Integer>("id"));
        tip_auto.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, String>("tip_auto"));
        marka.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, String>("marka"));
        god_vipusk.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, String>("god_vipusk"));
        vin.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, Integer>("vin"));
        gos_nomer.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, String>("gos_nomer"));
        nomer_dvigatel.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, String>("nomer_dvigatel"));
        nomer_kuzov.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, String>("nomer_kuzov"));
        garantiy.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, String>("garantiy"));
        stoimost.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, String>("stoimost"));
        marka_topliva.setCellValueFactory(new PropertyValueFactory<The_Car_Sql, String>("marka_topliva"));
    }
    private void delete_only(){
        TableView.TableViewSelectionModel<The_Car_Sql> selectionModel = tableViewData.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<The_Car_Sql>(){
            @Override
            public void changed(ObservableValue<? extends The_Car_Sql> observableValue, The_Car_Sql driveSql, The_Car_Sql t1) {
                deletebtn.setOnAction(ActionEvent -> deleteBtn(t1.getId()));
            }

        });
    }
    private void deleteBtn(int id){
        String sql = "DELETE FROM auto WHERE id = ?";
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

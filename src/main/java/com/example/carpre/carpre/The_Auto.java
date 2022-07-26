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

public class The_Auto {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_data_btn;

    @FXML
    private DatePicker add_date_viezd;

    @FXML
    private DatePicker add_date_vozvrata;

    @FXML
    private TextField add_massa_gruza;

    @FXML
    private TextField add_mesto;

    @FXML
    private TextField add_rasstoyanie;

    @FXML
    private TextField add_spid_viezd;

    @FXML
    private TextField add_spid_vozvrata;

    @FXML
    private TextField add_topliva_viezda;

    @FXML
    private TextField add_topliva_vozvrata;

    @FXML
    private Button addbtn;

    @FXML
    private TextField adddrive;

    @FXML
    private Button cancel_btn;

    @FXML
    private TableColumn<The_Auto_Sql, String> date_viezd;

    @FXML
    private TableColumn<The_Auto_Sql, String> date_vozv;

    @FXML
    private Button deletebtn;

    @FXML
    private TableColumn<The_Auto_Sql, String> drive;

    @FXML
    private Button exiit;

    @FXML
    private TableColumn<The_Auto_Sql, Integer> id;

    @FXML
    private TableColumn<The_Auto_Sql, String> massa_gruza;

    @FXML
    private TableColumn<The_Auto_Sql, String> mesto;

    @FXML
    private AnchorPane pane_add_pt;

    @FXML
    private TableColumn<The_Auto_Sql, String> rastoyanie;

    @FXML
    private TableColumn<The_Auto_Sql, String> spid_viezd;

    @FXML
    private TableColumn<The_Auto_Sql, String> spid_vozv;

    @FXML
    private TableView<The_Auto_Sql> tableViewData1;

    @FXML
    private TableColumn<The_Auto_Sql, String> topl_viezd;

    @FXML
    private TableColumn<The_Auto_Sql, String> topl_vozv;

    @FXML
    private Button update;

    @FXML
    private AnchorPane workPaneCar;

    @FXML
    void add_funk(ActionEvent event) {
        pane_add_pt.setVisible(true);
    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) exiit.getScene().getWindow();
        stage.close();
    }

    @FXML
    void update_funk(ActionEvent event) {
        refTable();
    }

    @FXML
    void cancel_btn_func(ActionEvent event) {
        pane_add_pt.setVisible(false);
    }

    @FXML
    void add_data_btn_func(ActionEvent event) {
        String drive = adddrive.getText();
        String date_viezd = String.valueOf(add_date_viezd.getValue());
        String date_vozv = String.valueOf(add_date_vozvrata.getValue());
        String spid_viezd = add_spid_viezd.getText();
        String spid_vozv = add_spid_vozvrata.getText();
        String topl_viezd = add_topliva_viezda.getText();
        String topl_vozv = add_topliva_vozvrata.getText();
        String mesto = add_mesto.getText();
        String rastoyanie = add_rasstoyanie.getText();
        String massa_gruza = add_massa_gruza.getText();

        String ask_question = "INSERT INTO auto_tp(drive, date_viezd, date_vozv, spid_viezd, spid_vozv, topl_viezd, topl_vozv, mesto, rastoyanie, massa_gruza) values(?,?,?,?,?,?,?,?,?,?);";

        try (Connection connection = this.connection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(ask_question)){
            preparedStatement1.setString(1, drive);
            preparedStatement1.setString(2, date_viezd);
            preparedStatement1.setString(3, date_vozv);
            preparedStatement1.setString(4, spid_viezd);
            preparedStatement1.setString(5, spid_vozv);
            preparedStatement1.setString(6, topl_viezd);
            preparedStatement1.setString(7, topl_vozv);
            preparedStatement1.setString(8, mesto);
            preparedStatement1.setString(9, rastoyanie);
            preparedStatement1.setString(10, massa_gruza);

            preparedStatement1.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Информация");
            alert.setHeaderText(null);
            alert.setContentText("Добавлено!");
            alert.showAndWait();

            pane_add_pt.setVisible(false);
            refTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        loadDate();
        delete_only();

    }
    private Connection connection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:src/main/resources/data/auto_tp.db";
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
    ObservableList<The_Auto_Sql> TheAutoSqlList = FXCollections.observableArrayList();

    private void refTable(){
        TheAutoSqlList.clear();

        String sql = "SELECT * FROM auto_tp";
        try (Connection connection = this.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                TheAutoSqlList.add(new The_Auto_Sql(
                        resultSet.getInt("id"),
                        resultSet.getString("drive"),
                        resultSet.getString("date_viezd"),
                        resultSet.getString("date_vozv"),
                        resultSet.getString("spid_viezd"),
                        resultSet.getString("spid_vozv"),
                        resultSet.getString("topl_viezd"),
                        resultSet.getString("topl_vozv"),
                        resultSet.getString("mesto"),
                        resultSet.getString("rastoyanie"),
                        resultSet.getString("massa_gruza")));

                tableViewData1.setItems(TheAutoSqlList);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadDate() {
        refTable();
        id.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, Integer>("id"));
        drive.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, String>("drive"));
        date_viezd.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, String>("date_viezd"));
        date_vozv.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, String>("date_vozv"));
        spid_viezd.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, String>("spid_viezd"));
        spid_vozv.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, String>("spid_vozv"));
        topl_viezd.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, String>("topl_viezd"));
        topl_vozv.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, String>("topl_vozv"));
        mesto.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, String>("mesto"));
        rastoyanie.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, String>("rastoyanie"));
        massa_gruza.setCellValueFactory(new PropertyValueFactory<The_Auto_Sql, String>("massa_gruza"));
    }

    private void delete_only(){
        TableView.TableViewSelectionModel<The_Auto_Sql> selectionModel = tableViewData1.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<The_Auto_Sql>(){
            @Override
            public void changed(ObservableValue<? extends The_Auto_Sql> observableValue, The_Auto_Sql driveSql, The_Auto_Sql t1) {
                deletebtn.setOnAction(ActionEvent -> deleteBtn(t1.getId()));
            }

        });
    }
    private void deleteBtn(int id){
        String sql = "DELETE FROM auto_tp WHERE id = ?";
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

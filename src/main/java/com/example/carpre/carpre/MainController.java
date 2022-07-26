package com.example.carpre.carpre;

import java.io.IOException;
import java.sql.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private Button weybills;

    @FXML
    private Button refueling;
    @FXML
    private Button addCancelDriveButton;

    @FXML
    private Button addDriveButton;

    @FXML
    private Button addDriveButtonData;

    @FXML
    private AnchorPane addPaneDataDrive;

    @FXML
    private Button deleteDataDriverButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button theCarDriver;

    @FXML
    private Button refButtonDrive;

    @FXML
    private Button theCars;

    @FXML
    private AnchorPane workPaneTest;

    // TableView
    @FXML
    private TableView<DriveSql> tableViewData;
    @FXML
    private TableColumn<DriveSql, String> birthdayDrive;

    @FXML
    private TableColumn<DriveSql, String> categoryDrive;

    @FXML
    private TableColumn<DriveSql, String> firstNameDrive;

    @FXML
    private TableColumn<DriveSql, Integer> idDrive;

    @FXML
    private TableColumn<DriveSql, String> lastNameDrive;

    @FXML
    private TableColumn<DriveSql, String> patronycDrive;

    @FXML
    private TableColumn<DriveSql, Integer> workexDrive;

    @FXML
    private TextField addcat;

    @FXML
    private DatePicker adddat;

    @FXML
    private TextField addfam;

    @FXML
    private TextField addim;

    @FXML
    private TextField addot;

    @FXML
    private TextField addsta;
    private Connection connection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:src/main/resources/data/drives.db";
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

    ObservableList<DriveSql>  DriveSqlList = FXCollections.observableArrayList();

    public MainController() throws SQLException {
    }

    @FXML
    private void refTable(){
        DriveSqlList.clear();

        String sql = "SELECT * FROM drives";
        try (Connection connection = this.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                DriveSqlList.add(new DriveSql(
                        resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("patronic"),
                        resultSet.getInt("work_ex"),
                        resultSet.getString("birthdate"),
                        resultSet.getString("category")));
                tableViewData.setItems(DriveSqlList);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        refButtonDrive.setOnAction(ActionEvent -> refTable());
        theCarDriver.setOnAction(ActionEvent -> workPaneTest.setVisible(true));

        addDriveButton.setOnAction(ActionEvent -> {
            workPaneTest.setVisible(false);
            addPaneDataDrive.setVisible(true);});

        addCancelDriveButton.setOnAction(ActionEvent -> {
            addPaneDataDrive.setVisible(false);
            workPaneTest.setVisible(true);});

        exitButton.setOnAction(ActionEvent -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();});

        addDriveButtonData.setOnAction(ActionEvent -> save());
        theCars.setOnAction(ActionEvent -> openNewWindowTheCar());
        refueling.setOnAction(ActionEvent -> openNewWindowRefuling());
        weybills.setOnAction(ActionEvent -> openNewWindowWeybills());

        loadDate();
        deleteData();
    }

    private void loadDate() {
        refTable();
        idDrive.setCellValueFactory(new PropertyValueFactory<DriveSql, Integer>("idDrive"));
        firstNameDrive.setCellValueFactory(new PropertyValueFactory<DriveSql, String>("firstNameDrive"));
        lastNameDrive.setCellValueFactory(new PropertyValueFactory<DriveSql, String>("lastNameDrive"));
        patronycDrive.setCellValueFactory(new PropertyValueFactory<DriveSql, String>("patronycDrive"));
        workexDrive.setCellValueFactory(new PropertyValueFactory<DriveSql, Integer>("workexDrive"));
        birthdayDrive.setCellValueFactory(new PropertyValueFactory<DriveSql, String>("birthdayDrive"));
        categoryDrive.setCellValueFactory(new PropertyValueFactory<DriveSql, String>("categoryDrive"));


    }
    private void save(){
        String firstNameDrive = addim.getText();
        String lastNameDrive = addfam.getText();
        String patronycDrive = addot.getText();
        int workexDrive = Integer.parseInt(addsta.getText());
        String birthdayDrive = String.valueOf(adddat.getValue());
        String categoryDrive = addcat.getText();

        String ask_question = "INSERT INTO drives(firstname, lastname, patronic, work_ex, birthdate, category) values(?,?,?,?,?,?);";

        try (Connection connection = this.connection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(ask_question)){
                 preparedStatement1.setString(1, firstNameDrive);
                 preparedStatement1.setString(2, lastNameDrive);
                 preparedStatement1.setString(3, patronycDrive);
                 preparedStatement1.setInt(4, workexDrive);
                 preparedStatement1.setString(5, birthdayDrive);
                 preparedStatement1.setString(6, categoryDrive);
                 preparedStatement1.executeUpdate();

                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Информация");
                 alert.setHeaderText(null);
                 alert.setContentText("Добавлено!");
                 alert.showAndWait();

                workPaneTest.setVisible(true);
                addPaneDataDrive.setVisible(false);
                refTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteData(){
        TableView.TableViewSelectionModel<DriveSql> selectionModel = tableViewData.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<DriveSql>(){
            @Override
            public void changed(ObservableValue<? extends DriveSql> observableValue, DriveSql driveSql, DriveSql t1) {
                deleteDataDriverButton.setOnAction(ActionEvent -> delete(t1.getIdDrive()));
            }

        });
    }
    private void delete(int id){
        String sql = "DELETE FROM drives WHERE id = ?";
        try (Connection connection = this.connection();
            PreparedStatement preparedS = connection.prepareStatement(sql)){
            preparedS.setInt(1, id);
            preparedS.executeUpdate();
            refTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void openNewWindowTheCar(){
        FXMLLoader winloader = new FXMLLoader();
        winloader.setLocation(getClass().getResource("the_car.fxml"));

        try {
            winloader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Parent parent = winloader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
    void openNewWindowRefuling(){
        FXMLLoader winloader = new FXMLLoader();
        winloader.setLocation(getClass().getResource("the_fuel.fxml"));

        try {
            winloader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Parent parent = winloader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
    void openNewWindowWeybills(){
        FXMLLoader winloader = new FXMLLoader();
        winloader.setLocation(getClass().getResource("the_auto.fxml"));

        try {
            winloader.load();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Parent parent = winloader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
}

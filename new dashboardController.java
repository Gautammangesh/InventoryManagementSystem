/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaminiproject;

import com.mysql.cj.xdevapi.Statement;
import com.sun.jdi.connect.spi.Connection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author DELL
 */
public class dashboardController implements Initilizable {

    @FXML
    private Button addProducts_addBtn;

    @FXML
    private TextField addProducts_brand;

    @FXML
    private Button addProducts_btn;

    @FXML
    private TableColumn<productData, String> addProducts_col_brand;

    @FXML
    private TableColumn<productData, String> addProducts_col_price;

    @FXML
    private TableColumn<productData, String> addProducts_col_productName;

    @FXML
    private TableColumn<productData, String> addProducts_col_productid;

    @FXML
    private TableColumn<productData, String> addProducts_col_status;

    @FXML
    private TableColumn<productData, String> addProducts_col_type;

    @FXML
    private Button addProducts_deleteBtn;

    @FXML
    private AnchorPane addProducts_form;

    @FXML
    private ImageView addProducts_imageView;

    @FXML
    private Button addProducts_importBtn;

    @FXML
    private TextField addProducts_productName;

    @FXML
    private ComboBox<?> addProducts_productType;

    @FXML
    private TextField addProducts_productid;

    @FXML
    private Button addProducts_resetBtn;

    @FXML
    private TextField addProducts_search;

    @FXML
    private ComboBox<?> addProducts_status;

    @FXML
    private TableView<productData> addProducts_tableView;

    @FXML
    private Button addProducts_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Label home_availableProducts;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AreaChart<?, ?> home_incomeChart;

    @FXML
    private Label home_numberOrder;

    @FXML
    private BarChart<?, ?> home_orderChart;

    @FXML
    private Label home_totalIncome;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimze;

    @FXML
    private TextField orders_amount;

    @FXML
    private Label orders_balance;

    @FXML
    private ComboBox<?> orders_brand;

    @FXML
    private Button orders_btn;

    @FXML
    private TableColumn<?, ?> orders_col_brand;

    @FXML
    private TableColumn<?, ?> orders_col_price;

    @FXML
    private TableColumn<?, ?> orders_col_productName;

    @FXML
    private TableColumn<?, ?> orders_col_quantity;

    @FXML
    private TableColumn<?, ?> orders_col_type;

    @FXML
    private AnchorPane orders_form;

    @FXML
    private Button orders_payBtn;

    @FXML
    private ComboBox<?> orders_productName;

    @FXML
    private ComboBox<?> orders_productType;

    @FXML
    private Spinner<?> orders_quantity;

    @FXML
    private Button orders_receiptBtn;

    @FXML
    private Button orders_resetBtn;

    @FXML
    private Button orders_addBtn;

    @FXML
    private TableView<?> orders_tableView;

    @FXML
    private Label orders_total;

    @FXML
    private Label username;

    @FXML
    private TextField addProducts_price;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    public void addProductsAdd() {

        String sql = "INSERT INTO product (product_id, type, brand, productName, price, status, image, date)"
                + "Values(?,?,?,?,?,?,?,?)";

        connect = (Connection) database.connectDb();

        try {

            Alert alert;

            if (addProducts_productid.getText().isEmpty()
                    || addProducts_productType.getSelectionModel().getSelectedItem() == null
                    || addProducts_brand.getText().isEmpty()
                    || addProducts_productName.getText().isEmpty()
                    || addProducts_price.getText().isEmpty()
                    || (String) addProducts_status.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                //CHECK IF THE PRODUCT ID IS ALREADY EXIST
                String checkData = "SELECT product_id FROM product WHERE product_id = '"
                        + addProducts_productid.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product ID: " + addProducts_productid.getText() + " Was alredy exist!");
                    alert.showAndWait();

                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addProducts_productid.getText());
                    prepare.setString(2, (String) addProducts_productType.getSelectionModel().getSelectedItem());
                    prepare.setString(3, addProducts_brand.getText());
                    prepare.setString(4, addProducts_productName.getText());
                    prepare.setString(5, addProducts_price.getText());
                    prepare.setString(6, (String) addProducts_status.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(7, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    //TO BECOME UPDATE YOUR TABLEVIEW
                    addPruductsShowListData();

                    //CLEAR THE FIELDS
                    addProductsReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addProductsUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE product SET type = '"
                + addProducts_productType.getSelectionModel().getSelectedItem()
                + "', brand = '" + addProducts_brand.getText()
                + "', productName = '" + addProducts_productName.getText()
                + "', price = '" + addProducts_price.getText()
                + "', status = '" + addProducts_status.getSelectionModel().getSelectedItem()
                + "', image = '" + uri + "', date = '" + sqlDate + "'WHERE product_id = '"
                + addProducts_productid.getText() + "'";
        connect = (Connection) database.connectDb();

        try {

            Alert alert;

            if (addProducts_productid.getText().isEmpty()
                    || addProducts_productType.getSelectionModel().getSelectedItem() == null
                    || addProducts_brand.getText().isEmpty()
                    || addProducts_productName.getText().isEmpty()
                    || addProducts_price.getText().isEmpty()
                    || (String) addProducts_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to UPDATE Product_ID: " + addProducts_productid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executedUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated");
                    alert.showAndWait();

                    addProductsShowListData();
                    addProductsReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addProductsDelete() {

        String sql = "DELETE FROM product WHERE product_id = '"
                + addProducts_productid.getText() + "'";

        connect = (Connection) database.connectDb();

        try {

            Alert alert;

            if (addProducts_productid.getText().isEmpty()
                    || addProducts_productType.getSelectionModel().getSelectedItem() == null
                    || addProducts_brand.getText().isEmpty()
                    || addProducts_productName.getText().isEmpty()
                    || addProducts_price.getText().isEmpty()
                    || (String) addProducts_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to DELETE Product_ID: " + addProducts_productid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executedUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();

                    addProductsShowListData();
                    addProductsReset();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addProductsReset() {
        addProducts_productid.setText("");
        addProducts_productType.getSelectionModel().clearSelection();
        addProducts_brand.setText("");
        addProducts_productName.setText("");
        addProducts_price.setText("");
        addProducts_status.getSelectionModel().clearSelection();
        addProducts_imageView.setImage(null);
        getData.path = "";
    }

    public void addProductsImportImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image file", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 115, 128, false, true);
            addProducts_imageView.setImage(image);
        }
    }

    private String[] listType = {"Snacks", "Drinks", "Dessert", "Gadgets", "Personal Product", "Others"};

    public void addProductsListType() {
        List<String> list = new ArrayList<>();

        for (String data : listType) {
            listT.add(data);

        }

        ObservableList listData = FXCollections.observableArrayList(listT);
        addProducts_productType.setItems(listData);

    }

    private String[] listStatus = {"Available", "Not Available"};

    public void addProductsListStatus() {
        List<String> listS = new ArrayList<>();
        for (String data : listStatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        addProducts_status.setItems(listData);

    }

    
    
    public void addProductsSearch(){
        
    FilteredList<productData> filter = new FilteredList<>(addProductsList, e-> true);
    
    addProducts_search.textProperty().addListener((Observable, oldValue, newValue) ->{
        
        filter.setPredicate(predicateProductData ->{
            
            if(newValue == null || newValue.isEmpty()){
                return true;
            }
            
            String searchKey = newValue.toLowerCase();
//                NOTHING? THEN WE NEED TO DO THIS FIRST
            if(predicateProductData.getProductId().toString().contains(searchKey)){
//                    NOTE, IF INTEGER OR IF THE DATA TYPE IS NOT STRING, YOU MUST BE DO toString()
                return true;
            }else if(predicateProductData.getType().toLowerCase().contains(searchKey)){
                return true;
            }else if(predicateProductData.getBrand().toLowerCase().contains(searchKey)){
                return true;
            }else if(predicateProductData.getStatus().toLowerCase().contains(searchKey)){
                return true;
            }else if(predicateProductData.getProductName().toLowerCase().contains(searchKey)){
                return true;
            }else if(predicateProductData.getPrice().toString().contains(searchKey)){
                return true;
            }else 
                return false;
            
        });
    });
    
    SortedList<productData> sortList = new SortedList<>(filter);
    
    sortList.comparatorProperty().bind(addProducts_tableView.comparatorProperty());
    addProducts_tableView.setItems(sortList);
}
    
    
    
    
    
    
    
    public ObservableList<productData> addProductsListData() {

        ObservableList<productData> productList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product";
        connect = (Connection) database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            productData prodD;
            while (result.next()) {
                prodD = new productData(result.getInt("product_id"),
                        result.getString("type"),
                        result.getString("brand"),
                        result.getString("productName"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));
                productList.add(prodD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    private ObservableList<productData> addProductsList;

    public void addProductsShowListData() {
        addProductsList = addProductsListData();

        addProducts_col_productid.setCellValueFactory(new PropertyValueFactory<>("productid"));
        addProducts_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        addProducts_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        addProducts_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addProducts_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProducts_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addProducts_tableView.setItems(addProductsList);

    }

    public void addProductsSelect() {
        productData prodD = addProducts_tableView.getSelectionModel().getSelectedItem();
        int num = addProducts_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addProducts_productid.setText(String.valueOf(prodD.getProductId()));
        addProducts_brand.setText(prodD.getBrand());
        addProducts_productName.setText(prodD.getProductName());
        addProducts_price.setText(String.valueOf(prodD.getPrice()));

        String uri = "file :" + prodD.getImage();

    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addProducts_form.setVisible(false);
            orders_form.setVisible(false);

            home_btn.setStyle("-fx -background-color:linear-gradient(to bottom right, #25a473,#89892b");
            addProducts_btn.setStyle("-fx-background-color:transparent");
            orders_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == addProducts_btn) {
            home_form.setVisible(false);
            addProducts_form.setVisible(true);
            orders_form.setVisible(false);

            addProducts_btn.setStyle("-fx -background-color:linear-gradient(to bottom right, #25a473,#89892b");
            home_btn.setStyle("-fx-background-color:transparent");
            orders_btn.setStyle("-fx-background-color:transparent");

            addProductsShowListData();
            addProductsListStatus();
            addProductsListType();
            addProductsSearch();

        } else if (event.getSource() == orders_btn) {
            home_form.setVisible(false);
            addProducts_form.setVisible(false);
            orders_form.setVisible(true);

            orders_btn.setStyle("-fx -background-color:linear-gradient(to bottom right, #25a473,#89892b");
            addProducts_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
        }

    }

    private double x = 0;
    private double y = 0;

    public void logout() throws IOException {
        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
                //Linking Our Login Form
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(x - event.getScreenX());
                    stage.setY(y - event.getScreenY());

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    public void initialize(URL location, ResourceBundle resources) {
        addProductsShowListData();
        addProductsListStatus();
        addProductsListType();

    }

    private void addPruductsShowListData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

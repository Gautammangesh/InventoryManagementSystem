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
            alert.setTitle("ConfirmaAtion Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();


            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
                //Linking Our Login Form
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });
                
                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(x - event.getScreenX());
                    stage.setY(y - event.getScreenY());
                    
                    stage.setOpacity(.8);
                });
                
                root.setOnMouseReleased((MouseEvent event) ->{
                    stage.setOpacity(1);
                });
                
                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else return;

        }catch (Exception e) {
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }

}


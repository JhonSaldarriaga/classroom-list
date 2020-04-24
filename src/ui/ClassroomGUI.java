package ui;

import model.Classroom;
import model.UserAccount;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ClassroomGUI {

	//START ATTRIBUTES
	@FXML
    private BorderPane borderPaneMain;
	
	//LOGIN ATTRIBUTES
    @FXML
    private TextField usernameTextLogin;

    @FXML
    private PasswordField passwordTextLogin;
 
    //REGISTER ATTRIBUTES
    @FXML
    private TextField usernameTextRegister;

    @FXML
    private TextField passwordTextRegister;

    @FXML
    private RadioButton radioButtonM;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton radioButtonF;

    @FXML
    private CheckBox checkBoxSE;

    @FXML
    private CheckBox checkBoxTE;

    @FXML
    private CheckBox checkBoxIE;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Label txtImageRout;
    
    @FXML
    private ImageView imageViewRegister;
    
    //SHOWLIST ATTRIBUTES
    @FXML
    private TableView<UserAccount> tableView;

    @FXML
    private TableColumn<UserAccount, String> usernameColumn;

    @FXML
    private TableColumn<UserAccount, String> genderColumn;

    @FXML
    private TableColumn<UserAccount, String> careerColumn;

    @FXML
    private TableColumn<UserAccount, String> birthdayColumn;

    @FXML
    private TableColumn<UserAccount, String> browserColumn;

    @FXML
    private ImageView imageUserView;

    @FXML
    private Label labelUsernameView;
    
    ///////////////////////////////////////////////	
	private Classroom classroom;
	private UserAccount loginAccount;
	
	public ClassroomGUI(Classroom cr) {
		classroom = cr;
	}
	
	public void initialize() {
    	//the method (initialize) is called several times by diferents fxml file loads 
	}
	
	private void initializeTableView() {
		ObservableList<UserAccount> observableList;
    	observableList = FXCollections.observableArrayList(classroom.getAccounts());
    	
		tableView.setItems(observableList);
		usernameColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("userName"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender"));
		careerColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("carrer"));
		birthdayColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday"));
		browserColumn.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("favoriteBrowser"));
	}
	
	private void initializeChoiceBox() {
		ObservableList<String> observableList;
    	observableList = FXCollections.observableArrayList(UserAccount.BROWSER_1, UserAccount.BROWSER_2, UserAccount.BROWSER_3);
		choiceBox.setItems(observableList);
	}
	
	private void loadLogin() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginGUI.fxml"));
		fxmlLoader.setController(this);    	
		Parent loginPane = fxmlLoader.load();
		
		borderPaneMain.getChildren().clear();
		borderPaneMain.setCenter(loginPane);
    }
	
	private void loadList() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showListGUI.fxml"));
		fxmlLoader.setController(this);    	
		Parent addContactPane = fxmlLoader.load();
		
		borderPaneMain.getChildren().clear();
		borderPaneMain.getChildren().add(addContactPane);
		loginAccount = classroom.search(usernameTextLogin.getText(), passwordTextLogin.getText());
		labelUsernameView.setText(loginAccount.getUserName());
		if(loginAccount.getRoutProfileImage()!=null) {
			Image image = new Image(loginAccount.getRoutProfileImage());
            imageUserView.setImage(image);
		}
	
		initializeTableView();
	}
	//START METHODS
	@FXML
	void start(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginGUI.fxml"));
		fxmlLoader.setController(this);    	
		Parent loginPane = fxmlLoader.load();
		
		borderPaneMain.getChildren().clear();
		borderPaneMain.setCenter(loginPane);
	}
	//LOGIN METHODS
	@FXML
    void showListGUI(ActionEvent event) throws IOException {

		if(usernameTextLogin.getText().equals("") || passwordTextLogin.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("You must type on both fields");
			alert.setHeaderText("Notice");
			alert.setTitle("NOTICE");
			alert.show();
		}else {
			if(classroom.exist(usernameTextLogin.getText(), passwordTextLogin.getText())) {
				loadList();
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("User or password are incorrect");
				alert.setHeaderText("Notice");
				alert.setTitle("NOTICE");
				alert.show();
			}
		}
    }

    @FXML
    void showRegisterGUI(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerGUI.fxml"));
		fxmlLoader.setController(this);    	
		Parent addContactPane = fxmlLoader.load();
		
		borderPaneMain.getChildren().clear();
		borderPaneMain.setCenter(addContactPane);
		initializeChoiceBox();
    }
    
	//REGISTER METHODS
    @FXML
    void createAccount(ActionEvent event) throws IOException {
    	if(!usernameTextRegister.getText().equals("") && 
    		!passwordTextRegister.getText().equals("") &&
    		choiceBox.getValue()!=null && gender.getSelectedToggle()!=null && 
    		(checkBoxSE.isSelected() || checkBoxIE.isSelected()|| checkBoxTE.isSelected()) &&
    		datePicker.getValue()!=null) {
    		
    		String n = usernameTextRegister.getText();
        	String p = passwordTextRegister.getText();
        	String fb = choiceBox.getValue();
        	String g = ((RadioButton)gender.getSelectedToggle()).getText();
        	String pc;
        	
        	if(checkBoxSE.isSelected()) {
        		pc = checkBoxSE.getText();
        	}else {
        		if(checkBoxIE.isSelected()) {
        			pc = checkBoxIE.getText();
        		}else {
        			pc = checkBoxTE.getText();
        		}
        	}
        	
        	LocalDate ld = datePicker.getValue();
        	
        	String rpi;
        	if(txtImageRout.getText().equals("NO")) {
        		rpi = null;
        	}else {
        		rpi = txtImageRout.getText();
        	}
      
        	classroom.add(n, p, g, pc, ld, fb, rpi);
        	
        	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("The account has been added correctly");
			alert.setHeaderText("Successful!");
			alert.setTitle("COMPLETE");
			alert.show();
			
			EventHandler<DialogEvent> e = new EventHandler<DialogEvent>() { 
	            public void handle(DialogEvent e) 
	            { 
	            	Alert confirm = new Alert(AlertType.CONFIRMATION);
	   				confirm.setHeaderText("do you want to return to the lobby?");
	   				confirm.setTitle("CONFIRMATION");

	   				Optional<ButtonType> result = confirm.showAndWait();
	   				if(result.get() == ButtonType.OK) {
						try {
							loadLogin();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
	   				}
	            } 
	        };
			alert.setOnCloseRequest(e);
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("You must type on all fields and choose on all options fields");
			alert.setHeaderText("Notice");
			alert.setTitle("NOTICE");
			
			alert.show();
    	}
    }
    
    @FXML
    void searchRout(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser(); 
    	
          fileChooser.setTitle("Buscar Imagen");
          // Agregar filtros para facilitar la busqueda
          fileChooser.getExtensionFilters().addAll(
                  new FileChooser.ExtensionFilter("All Images", "*.*"),
                  new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                  new FileChooser.ExtensionFilter("PNG", "*.png")
          );
          // Obtener la imagen seleccionada
          File imgFile = fileChooser.showOpenDialog(null);

          //Mostar la imagen
          if (imgFile != null) {
             Image image = new Image("file:" + imgFile.getAbsolutePath());
             imageViewRegister.setImage(image);
             txtImageRout.setVisible(false);
             txtImageRout.setText("file:" + imgFile.getAbsolutePath());
          }else {
        	  txtImageRout.setVisible(false);
        	  txtImageRout.setText("NO");
          }
    }
    
    @FXML
    void selectIE(ActionEvent event) {
    	if(checkBoxSE.isSelected()) {
    		checkBoxSE.setSelected(false);
    	}
    	if(checkBoxTE.isSelected()) {
    		checkBoxTE.setSelected(false);
    	}
    }

    @FXML
    void selectSE(ActionEvent event) {
    	if(checkBoxIE.isSelected()) {
    		checkBoxIE.setSelected(false);
    	}
    	if(checkBoxTE.isSelected()) {
    		checkBoxTE.setSelected(false);
    	}
    }

    @FXML
    void selectTE(ActionEvent event) {
    	if(checkBoxSE.isSelected()) {
    		checkBoxSE.setSelected(false);
    	}
    	if(checkBoxIE.isSelected()) {
    		checkBoxIE.setSelected(false);
    	}
    }
    
    //SHOWLIST METHODS
    @FXML
    void logOut(ActionEvent event) throws IOException {
    	loadLogin();
    }
}

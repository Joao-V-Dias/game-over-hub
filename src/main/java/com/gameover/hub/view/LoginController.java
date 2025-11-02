package com.gameover.hub.view;

import com.gameover.hub.model.LoginResult;
import com.gameover.hub.model.Usuario;
import com.gameover.hub.service.UsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginController {

	private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    void fazerLogin(ActionEvent event) {
		String email = txtEmail.getText();
		String senha = txtSenha.getText();

		if(!email.isEmpty() || !senha.isEmpty()){
			Usuario usuario = new Usuario(email, senha);
			LoginResult lr = usuarioService.validarLogin(usuario);

			if(lr.isSucesso()){
				try{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gameover/hub/fxml/TelaHome.fxml"));
					Parent root = loader.load();
					Stage stage = new Stage();
					stage.setScene(new javafx.scene.Scene(root));
					stage.show();

					closeWindow(event);
				}catch(IOException e){
					log.error("Falha ao abrir tela principal: ", e);
				}
			} else {
				log.error(lr.getMensagem());
			}
		}
    }

	@FXML
    void closeWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

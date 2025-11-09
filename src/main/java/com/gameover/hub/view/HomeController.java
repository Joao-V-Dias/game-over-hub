package com.gameover.hub.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class HomeController {

	@FXML
    public void initialize() {
        abrirTelaAtendimento(null);
    }

    @FXML
    private Button btnAtendimento;

    @FXML
    private Button btnEstoque;

    @FXML
	private Button btnCliente;

    @FXML
    private Button btnTecnico;

	@FXML
    private AnchorPane telaContainer;

    @FXML
    void abrirTelaAtendimento(ActionEvent event) {
		carregarTela("/com/gameover/hub/fxml/TelaAtendimento.fxml");
    }

    @FXML
    void abrirTelaEstoque(ActionEvent event) {
		carregarTela("/com/gameover/hub/fxml/TelaEstoque.fxml");
    }

    @FXML
    void abrirTelaCliente(ActionEvent event) {
		carregarTela("/com/gameover/hub/fxml/TelaCliente.fxml");
    }

    @FXML
    void abrirTelaTecnico(ActionEvent event) {
		carregarTela("/com/gameover/hub/fxml/TelaTecnico.fxml");
    }

	private void carregarTela(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent novaTela = loader.load();

            telaContainer.getChildren().clear();
            telaContainer.getChildren().add(novaTela);

            AnchorPane.setTopAnchor(novaTela, 0.0);
            AnchorPane.setBottomAnchor(novaTela, 0.0);
            AnchorPane.setLeftAnchor(novaTela, 0.0);
            AnchorPane.setRightAnchor(novaTela, 0.0);

        } catch (IOException e) {
			log.error("Erro ao carregar a tela: " + fxmlPath);
        }
    }
}

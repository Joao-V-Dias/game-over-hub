package com.gameover.hub.view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AtendimentoController {

    @FXML
    private AnchorPane modalCompra;

    @FXML
    private AnchorPane modalServico;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TableView<?> tabelaProdutos; // Substitua '?' pelo seu modelo de Produto

    @FXML
    private TableView<?> tabelaTrocas; // Substitua '?' pelo seu modelo de Equipamento

    @FXML
    private ComboBox<String> comboGarantia;

    @FXML
    private Label lblTotal;

    @FXML
    private TextField txtCpfCliente;

    @FXML
    private TextArea txtRelatoCliente;

    @FXML
    private TextArea txtEquipamentosEntregues;


    @FXML
    public void initialize() {
        comboGarantia.setItems(FXCollections.observableArrayList("Nenhuma", "30 dias", "90 dias", "1 ano"));
        comboGarantia.setValue("Nenhuma");
    }

    @FXML
    void handleComprar(ActionEvent event) {
        modalServico.setVisible(false);
        modalCompra.setVisible(true);
    }

    @FXML
    void handleServico(ActionEvent event) {
        modalCompra.setVisible(false);
        modalServico.setVisible(true);
    }

    // --- Adicione aqui os métodos para os novos botões ---
    // Ex: @FXML void handleCadastrarCliente(ActionEvent event) { ... }
    // Ex: @FXML void handleFinalizarOS(ActionEvent event) { ... }
}

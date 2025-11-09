package com.gameover.hub.view;

import com.gameover.hub.model.Cliente;
import com.gameover.hub.service.ClienteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ClienteController{

	private Cliente cliente;
	private ClienteService clienteService;

	@FXML
    public void initialize() {
		cliente = new Cliente();
		clienteService = new ClienteService();
		tabela.getItems().setAll(clienteService.getClientes());
		btnSalvar.setText("Salvar novo Cliente");
		btnVoltarCriar.setDisable(true);
    }

	@FXML
    private Button btnVoltarCriar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

	@FXML
    private TableView<Cliente> tabela;

    @FXML
    void buscarNome(KeyEvent event) {
		if(txtBuscar.getText().isBlank()){
			tabela.getItems().setAll(clienteService.getClientes());
		}else{
			tabela.getItems().setAll(clienteService.getClientePorNome(txtBuscar.getText()));
		}
    }

    @FXML
    void voltar(ActionEvent event) {
		txtNome.setText("");
		txtDocumento.setText("");
		txtTelefone.setText("");
		txtEndereco.setText("");
		txtEmail.setText("");
		cliente.setId(0);
		btnSalvar.setText("Salvar novo Cliente");
		btnVoltarCriar.setDisable(true);
    }

    @FXML
    void salvar(ActionEvent event) {
		getField();
		if(cliente.getId() == 0){
			clienteService.salvar(cliente);
		}else{
			clienteService.editar(cliente);
		}
		voltar(null);
		tabela.getItems().setAll(clienteService.getClientes());
    }

    @FXML
    void pegarLinha(MouseEvent event) {
		if (event.getClickCount() == 2) {
            Cliente clienteSelecionado = tabela.getSelectionModel().getSelectedItem();
            if (clienteSelecionado != null){
				cliente.setId(clienteSelecionado.getId());
				txtNome.setText(clienteSelecionado.getNome());
				txtDocumento.setText(clienteSelecionado.getCpf());
				txtTelefone.setText(clienteSelecionado.getTelefone());
				txtEndereco.setText(clienteSelecionado.getEndereco());
				txtEmail.setText(clienteSelecionado.getEmail());
				btnSalvar.setText("Editar Cliente");
				btnVoltarCriar.setDisable(false);
            }
        }
    }

	private void getField(){
		cliente.setNome(txtNome.getText());
		cliente.setCpf(txtDocumento.getText());
		cliente.setTelefone(txtTelefone.getText());
		cliente.setEndereco(txtEndereco.getText());
		cliente.setEmail(txtEmail.getText());
	}
}
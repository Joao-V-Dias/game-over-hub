package com.gameover.hub.view;

import com.gameover.hub.model.Produto;
import com.gameover.hub.service.ProdutoService;
import com.gameover.hub.util.ConvertValues;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class CriarProdutoController {

	ProdutoService produtoService;

	@FXML
    public void initialize() {
		produtoService = new ProdutoService();
    }

	@FXML
    private Label title;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtEstoqueMinimo;

    @FXML
    private TextField txtGTIN;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtQuantidade;

	@FXML
    void closeWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void salvarProduto(ActionEvent event) {
		try{
			Produto produto = new Produto();
			montarProduto(produto);
			produto = produtoService.salvar(produto);

			log.info("Produto salvo com sucesso! {}", produto);
			closeWindow(event);
		}catch(RuntimeException e){
			log.error("Erro ao salvar produto: ", e);
		}
    }

	private void montarProduto(Produto produto){
		produto.setNome(txtNome.getText());
		produto.setPreco(ConvertValues.convertBigDecimal(txtPreco.getText()));
		produto.setCategoria(txtCategoria.getText());
		produto.setQuantidade(ConvertValues.convertInt(txtQuantidade.getText()));
		produto.setEstoqueMinimo(ConvertValues.convertInt(txtEstoqueMinimo.getText()));
		produto.setGtin(txtGTIN.getText());
	}

	public void setProdutoParaEdicao(Produto produtoSelecionado){
		title.setText("Editar Produto");
		preencherFormulario(produtoSelecionado);

		btnSalvar.setOnAction(event -> {
			try {
				montarProduto(produtoSelecionado);
				produtoService.editar(produtoSelecionado);
				log.info("Produto editado com sucesso!  {}", produtoSelecionado);
				closeWindow(event);
			} catch (RuntimeException e) {
				log.error("Erro ao editar produto: ", e);
			}
		});
	}

	private void preencherFormulario(Produto produtoSelecionado){
		txtNome.setText(produtoSelecionado.getNome());
		txtPreco.setText(String.valueOf(produtoSelecionado.getPreco()));
		txtCategoria.setText(produtoSelecionado.getCategoria());
		txtQuantidade.setText(String.valueOf(produtoSelecionado.getQuantidade()));
		txtEstoqueMinimo.setText(String.valueOf(produtoSelecionado.getEstoqueMinimo()));
		txtGTIN.setText(produtoSelecionado.getGtin());
	}
}

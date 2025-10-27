package com.gameover.hub.view;

import com.gameover.hub.model.Produto;
import com.gameover.hub.service.ProdutoService;
import com.gameover.hub.util.ConvertValues;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
			Produto produto = montarProduto();
			produto = produtoService.salvar(produto);

			log.info("Produto salvo com sucesso!", produto);
			closeWindow(event);
		}catch(RuntimeException e){
			log.error("Erro ao salvar produto: ", e);
		}
    }

	private Produto montarProduto(){
		String nome = txtNome.getText();
		BigDecimal preco = ConvertValues.convertBigDecimal(txtPreco.getText());
		String categoria = txtCategoria.getText();
		int quantidade = ConvertValues.convertInteger(txtQuantidade.getText());
		int estoqueMinimo = ConvertValues.convertInteger(txtEstoqueMinimo.getText());
		String gtin = txtGTIN.getText();

		return new Produto(estoqueMinimo, gtin, quantidade, categoria, preco, nome);
	}

	public void setProdutoParaEdicao(Produto produtoSelecionado){
		preencherFormulario(produtoSelecionado);

		btnSalvar.setOnAction(event -> {
			try {
				produtoSelecionado.setNome(txtNome.getText());
				produtoSelecionado.setPreco(ConvertValues.convertBigDecimal(txtPreco.getText()));
				produtoSelecionado.setCategoria(txtCategoria.getText());
				produtoSelecionado.setQuantidade(ConvertValues.convertInteger(txtQuantidade.getText()));
				produtoSelecionado.setEstoqueMinimo(ConvertValues.convertInteger(txtEstoqueMinimo.getText()));
				produtoSelecionado.setGtin(txtGTIN.getText());

				produtoService.editar(produtoSelecionado);
				log.info("Produto editado com sucesso!", produtoSelecionado);
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

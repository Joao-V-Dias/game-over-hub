package com.gameover.hub.view;

import com.gameover.hub.model.Produto;
import com.gameover.hub.service.ProdutoService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class EstoqueController {

	List<Produto> lstProdutos;
	ProdutoService produtoService;

	@FXML
    public void initialize() {
		produtoService = new ProdutoService();
		atualizarTabela();
    }

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnProduto;

    @FXML
    private TableView<Produto> tabela;

    @FXML
    private TextField txtProduto;

    @FXML
    void atualizarTabela() {
		if(txtProduto.getText().isBlank()){
			lstProdutos = produtoService.getProduto();
		}else{
			lstProdutos = produtoService.getProduto(txtProduto.getText());
		}
		tabela.getItems().setAll(lstProdutos);
    }

    @FXML
    void buscarProduto(KeyEvent event) {
		atualizarTabela();
    }

    @FXML
    void criarProduto() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gameover/hub/fxml/TelaCriarProduto.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.setScene(new javafx.scene.Scene(root));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		}catch(IOException e){
			log.error("Falha ao abrir tela de criar produto: ", e);
		}
    }

	@FXML
    void pegarLinha(MouseEvent event) {
		if (event.getClickCount() == 2) {
            Produto produtoSelecionado = tabela.getSelectionModel().getSelectedItem();
            if (produtoSelecionado != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/gameover/hub/fxml/TelaCriarProduto.fxml"));
                    Parent root = loader.load();

                    CriarProdutoController controller = loader.getController();
                    controller.setProdutoParaEdicao(produtoSelecionado);

                    Stage stage = new Stage();
                    stage.setScene(new javafx.scene.Scene(root));
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();

                    stage.setOnHidden(e -> atualizarTabela());
                } catch (IOException e) {
                    log.error("Falha ao abrir tela de edição de produto: ", e);
                }
            }
        }

    }
}

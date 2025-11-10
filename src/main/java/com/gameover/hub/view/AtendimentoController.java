package com.gameover.hub.view;

import com.gameover.hub.model.Cliente;
import com.gameover.hub.model.ItensPedido;
import com.gameover.hub.model.Pedido;
import com.gameover.hub.model.Produto;
import com.gameover.hub.service.ClienteService;
import com.gameover.hub.service.ItensPedidoService;
import com.gameover.hub.service.PedidoService;
import com.gameover.hub.service.ProdutoService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoController {

	private ClienteService clienteService;
	private ProdutoService produtoService;
	private PedidoService pedidoService;
	private ProdutoService itensPedidoService;
	private Pedido pedido;

	private BigDecimal total;

	private List<ItensPedido> lstItensPedido;


	@FXML
	public void initialize(){
		clienteService = new ClienteService();
		produtoService = new ProdutoService();
		pedidoService = new PedidoService();
		pedido = new Pedido();
		lstItensPedido = new ArrayList<>();
		total = BigDecimal.ZERO;
		cmvGarantia.setItems(FXCollections.observableArrayList("Nenhuma", "30 dias", "90 dias", "1 ano"));
        cmvGarantia.setValue("Nenhuma");
	}

	@FXML
    private Button btnAdicionar;

    @FXML
    private Button btnComprar;

    @FXML
    private Button btnFinalizar;

    @FXML
    private Button btnServico;

    @FXML
    private ComboBox<Cliente> cmbCliente;

    @FXML
    private ComboBox<String> cmbProduto;

    @FXML
    private ComboBox<String> cmvGarantia;

    @FXML
    private Label lblTotal;

    @FXML
    private AnchorPane modalCompra;

    @FXML
    private AnchorPane modalServico;

    @FXML
    private TableView<ItensPedido> tabelaProdutos;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtProduto;

    @FXML
    private TextField txtQtd;

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

	@FXML
    void buscarCliente(KeyEvent event) {
		String nome = txtNomeCliente.getText();

		if(!nome.isBlank()){
			List<Cliente> clientes = clienteService.getClientePorNome(nome);
			for(Cliente c : clientes){
				cmbCliente.getItems().add(c);
				cmbCliente.setValue(c);
			}
		}else{
			cmbCliente.getItems().clear();
		}
    }

    @FXML
    void buscarProduto(KeyEvent event) {
		String produto = txtProduto.getText();

		if(!produto.isBlank()){
			List<Produto> produtos = produtoService.getProduto(produto);
			for(Produto p : produtos){
				cmbProduto.getItems().add(p.getNome());
				cmbProduto.setValue(p.getNome());
			}
		}else{
			cmbProduto.getItems().clear();
		}
    }

    @FXML
    void adicionarProduto(ActionEvent event) {
		if(cmbProduto.getValue() != null && !txtQtd.getText().isBlank()){
			Produto produto = produtoService.getProduto(cmbProduto.getValue()).getFirst();
			ItensPedido itensPedido = new ItensPedido();
			itensPedido.setProduto(produto);
			itensPedido.setQuantidade(Integer.parseInt(txtQtd.getText()));
			itensPedido.setPrecoUnitario(produto.getPreco());
			lstItensPedido.add(itensPedido);
			tabelaProdutos.getItems().setAll(lstItensPedido);
			txtQtd.setText("");
			cmbProduto.setValue(null);
			total = total.add(BigDecimal.valueOf(itensPedido.getQuantidade()).multiply(itensPedido.getPrecoUnitario()));
			lblTotal.setText("Total: R$ " + total);
		}
    }

    @FXML
    void finalizarCompra(ActionEvent event) {
		if(cmbCliente.getValue() != null && !lstItensPedido.isEmpty()){
			Cliente cliente = clienteService.getCliente(cmbCliente.getValue().getId());
			pedido.setCliente(cliente);
			pedido.setItens(lstItensPedido);
			pedido.setGaratia(cmvGarantia.getValue());
			pedido.setData(new java.sql.Date(System.currentTimeMillis()));
			pedidoService.salvar(pedido);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Venda Registrada");
			alert.setHeaderText(null);
			alert.setContentText("A venda foi registrada com sucesso!");
			alert.showAndWait();

			txtNomeCliente.setText("");
			cmbCliente.getItems().clear();
			txtProduto.setText("");
			cmbProduto.getItems().clear();
			txtQtd.setText("");
			cmvGarantia.setValue("Nenhuma");
			lstItensPedido.clear();
			tabelaProdutos.getItems().clear();
			total = BigDecimal.ZERO;
			lblTotal.setText("Total: R$ 0,00");
		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Erro ao Finalizar Venda");
			alert.setHeaderText(null);
			alert.setContentText("Selecione um cliente e adicione pelo menos um produto para finalizar a venda.");
			alert.showAndWait();
		}
    }
}

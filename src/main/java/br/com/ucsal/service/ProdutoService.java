package br.com.ucsal.service;

import java.util.List;

import br.com.ucsal.anotacoes.Inject;
import br.com.ucsal.model.Produto;
import br.com.ucsal.persistencia.ProdutoRepository;

public class ProdutoService {

    @Inject // Anotação para indicar que a dependência será injetada
    private ProdutoRepository<Produto, Integer> produtoRepository;

    // Adiciona um novo produto ao repositório
    public void adicionarProduto(String nome, double preco) {
        Produto produto = new Produto(null, nome, preco);  // Produto sem ID, pois é gerado automaticamente
        produtoRepository.adicionar(produto);
    }

    // Remove um produto pelo ID
    public void removerProduto(Integer id) {
        produtoRepository.remover(id);
    }

    // Obtém um produto pelo ID
    public Produto obterProdutoPorId(Integer id) {
        return produtoRepository.obterPorID(id);
    }

    // Atualiza um produto
    public void atualizarProduto(Produto p) {
        produtoRepository.atualizar(p);
    }

    // Lista todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.listar();
    }
}

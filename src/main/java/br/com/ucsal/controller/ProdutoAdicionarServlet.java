package br.com.ucsal.controller;

import br.com.ucsal.anotacoes.Rota;
import br.com.ucsal.service.ProdutoService;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/adicionarProduto")
public class ProdutoAdicionarServlet implements Command {
    private static final long serialVersionUID = 1L;

    // Usando @Inject para a injeção automática
    @br.com.ucsal.anotacoes.Inject
    private ProdutoService produtoService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        
        if ("GET".equalsIgnoreCase(method)) {
            doGet(request, response);
        } else if ("POST".equalsIgnoreCase(method)) {
            doPost(request, response);
        }
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Encaminha para o formulário de adicionar produto
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/produtoformulario.jsp");
        dispatcher.forward(request, response);
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        produtoService.adicionarProduto(nome, preco);
        response.sendRedirect("listarProdutos");
    }
}

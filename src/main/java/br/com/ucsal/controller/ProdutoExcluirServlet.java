package br.com.ucsal.controller;

import java.io.IOException;

import br.com.ucsal.anotacoes.Inject;
import br.com.ucsal.service.ProdutoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/excluirProduto")
public class ProdutoExcluirServlet implements Command {
    private static final long serialVersionUID = 1L;

    // Injeção automática do ProdutoService
    @Inject
    private ProdutoService produtoService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        produtoService.removerProduto(id);
        response.sendRedirect("listarProdutos");
    }
}

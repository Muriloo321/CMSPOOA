package br.com.ucsal.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.ucsal.service.ProdutoService;
import br.com.ucsal.anotacoes.Inject;
import br.com.ucsal.anotacoes.Injector;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/view/", "/"}) // Mapeia todas as requisições com "/view/"
public class ProdutoController extends HttpServlet {

    @Inject
    private ProdutoService produtoService; // ProdutoService injetado

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Injetar dependências após a criação do Servlet
            Injector.injectDependencies(this); // Injeta as dependências do controller
        } catch (Exception e) {
            throw new ServletException("Erro ao injetar dependências", e);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);
        Command command = commands.get(path);

        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Página não encontrada");
        }
    }

}

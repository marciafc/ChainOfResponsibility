package br.com.marcia.pattern.chainofresponsibility.exemplo1;

import br.com.marcia.pattern.chainofresponsibility.exemplo1.handler.Middleware;
import br.com.marcia.pattern.chainofresponsibility.exemplo1.handler.RoleCheckMiddleware;
import br.com.marcia.pattern.chainofresponsibility.exemplo1.handler.ThrottlingMiddleware;
import br.com.marcia.pattern.chainofresponsibility.exemplo1.handler.UserExistsMiddleware;

import java.io.IOException;

/**
 * Filtrando o acesso
 *
 * "Este exemplo mostra como uma solicitação que contém dados do usuário passa por uma cadeia sequencial de manipuladores que executam várias tarefas,
 * como autenticação, autorização, e validação.
 *
 * Este exemplo é um pouco diferente da versão canônica do padrão fornecida por vários autores.
 * A maioria dos exemplos do padrão é construída com a noção de procurar o handler correto, iniciá-lo, e sair da cadeia depois disso.
 *
 * Mas aqui executamos todos os handlers até que um que não possa lidar com uma solicitação seja encontrado.
 * Esteja ciente de que esse ainda é o padrão Chain of Responsibility, mesmo que o fluxo seja um pouco diferente."
 *
 * Fonte: https://refactoring.guru/pt-br/design-patterns/chain-of-responsibility/java/example
 *
 */
public class Client {

    private static Server server;

    private static void init() {

        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        Middleware middleware = new ThrottlingMiddleware(3);
        middleware.linkWith(new UserExistsMiddleware(server)).linkWith(new RoleCheckMiddleware());

        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        System.out.println("");

        String email = "admin@example.com";
        String password = "admin_pass";
        server.logIn(email, password);

        System.out.println("\n");

        email = "user@example.com";
        password = "user_pass";
        server.logIn(email, password);

        System.out.println("\n");

        email = "qlq@example.com";
        password = "qlq";
        server.logIn(email, password);

        System.out.println("");

        /**

         CONSOLE:

         1 - ThrottlingMiddleware
         2 - UserExistsMiddleware
         3 - RoleCheckMiddleware
         Hello, admin!
         Authorization have been successful!


         1 - ThrottlingMiddleware
         2 - UserExistsMiddleware
         3 - RoleCheckMiddleware
         Hello, user!
         Authorization have been successful!


         1 - ThrottlingMiddleware
         2 - UserExistsMiddleware
         This email is not registered!

         */
    }
}

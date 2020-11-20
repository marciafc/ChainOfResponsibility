package br.com.marcia.pattern.chainofresponsibility.exemplo1.handler;

import br.com.marcia.pattern.chainofresponsibility.exemplo1.Server;

/**
 * Verifica se existe um usuário com as credenciais fornecidas
 */
public class UserExistsMiddleware extends Middleware {

    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String email, String password) {

        System.out.println("2 - UserExistsMiddleware");

        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!");
            return false;
        }

        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!");
            return false;
        }

        return checkNext(email, password);
    }

}
package br.com.marcia.pattern.chainofresponsibility.exemplo1.handler;

/**
 * Verifica a role de um usuário
 */
public class RoleCheckMiddleware extends Middleware {

    public boolean check(String email, String password) {

        System.out.println("3 - RoleCheckMiddleware");

        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!");
            return true;
        }

        System.out.println("Hello, user!");

        return checkNext(email, password);

    }
}

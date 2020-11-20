package br.com.marcia.pattern.chainofresponsibility.exemplo2.handler;

import br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra;

public abstract class Desconto {

    protected Desconto proximo;

    public void setProximo(Desconto proximo) {
        this.proximo = proximo;
    }

    public abstract void desconto(Compra.Promocao promocao, Compra compra);

}
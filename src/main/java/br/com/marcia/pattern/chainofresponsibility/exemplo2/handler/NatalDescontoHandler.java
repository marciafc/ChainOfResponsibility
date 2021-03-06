package br.com.marcia.pattern.chainofresponsibility.exemplo2.handler;

import br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NatalDescontoHandler extends Desconto {

    @Override
    public void desconto(Compra.Promocao promocao, Compra compra) {

        log.info(getClass().getSimpleName());

        if(promocao == Compra.Promocao.NATAL) {
            compra.setDesconto(10);

        } else if(proximo != null) {
            proximo.desconto(promocao, compra);
        }
    }
}
package br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
public class Compra {

    public enum Promocao {
        SEM_DESCONTO, DIA_DOS_NAMORADOS, NATAL, QUEIMA_ESTOQUE, DESCONTO_PROGRESSIVO, DIA_DAS_MAES
    }

    @Setter(AccessLevel.NONE)
    private Map<Produto, Integer> produtos;

    private int desconto = 0;

    public Compra() {
        this.produtos = new HashMap<>();
    }

    public void comprar(Produto produto, int quantidade) {

        int quantidadetotal = quantidade;

        if(produtos.get(produto) != null) {
            int quantidadeJaComprada = produtos.get(produto);
            quantidadetotal = quantidadeJaComprada + quantidade;
        }

        produtos.put(produto, quantidadetotal);
    }


}
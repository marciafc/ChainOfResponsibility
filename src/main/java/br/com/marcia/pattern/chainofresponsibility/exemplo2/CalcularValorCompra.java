package br.com.marcia.pattern.chainofresponsibility.exemplo2;

import br.com.marcia.pattern.chainofresponsibility.exemplo2.handler.*;
import br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra;
import br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Produto;

import java.util.Map;

public class CalcularValorCompra {

    private Desconto desconto;

    public CalcularValorCompra() {

        Desconto diaDosNamoradosDescontoHandler = new DiaDosNamoradosDescontoHandler();
        Desconto natalDescontoHandler = new NatalDescontoHandler();
        Desconto queimaDeEstoqueDescontoHandler = new QueimaDeEstoqueDescontoHandler();
        Desconto descontoProgressivoDescontoHandler = new DescontoProgressivoDescontoHandler();
        Desconto diaDasMaesDescontoHandler = new DiaDasMaesDescontoHandler();

        this.desconto = diaDosNamoradosDescontoHandler;
        diaDosNamoradosDescontoHandler.setProximo(natalDescontoHandler);
        natalDescontoHandler.setProximo(queimaDeEstoqueDescontoHandler);
        queimaDeEstoqueDescontoHandler.setProximo(descontoProgressivoDescontoHandler);
        descontoProgressivoDescontoHandler.setProximo(diaDasMaesDescontoHandler);
    }

    public Double valorCompra(Compra compra) {
        return valorCompra(Compra.Promocao.SEM_DESCONTO, compra);
    }

    public Double valorCompra(Compra.Promocao promocao, Compra compra) {

        desconto.desconto(promocao, compra);

        Double valorTotal = 0d;
        for (Map.Entry<Produto, Integer> produtoEntry : compra.getProdutos().entrySet()) {
            valorTotal += produtoEntry.getKey().getValor() * produtoEntry.getValue();
        }

        return valorTotal-(valorTotal*(compra.getDesconto()/100d));
    }

}

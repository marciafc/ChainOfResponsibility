package br.com.marcia.pattern.chainofresponsibility.exemplo2;

import br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra;
import br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Produto;

import static br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra.Promocao.*;
import static java.lang.String.format;
import static java.lang.System.out;

public class MainChainOfResponsibility {

    public static void main(String[] args) {

        Produto celularMotoGPlus = new Produto("Smartphone Moto G 4 Plus", 1499.00);
        CalcularValorCompra calcularValorCompra = new CalcularValorCompra();

        Compra comprarNoLancamento = new Compra();
        comprarNoLancamento.comprar(celularMotoGPlus, 1);
        out.println(format("Valor do %s: R$ %.2f", celularMotoGPlus.getNome(), calcularValorCompra.valorCompra(SEM_DESCONTO, comprarNoLancamento)));

        Compra comprarNoDiaDosNamorados = new Compra();
        comprarNoDiaDosNamorados.comprar(celularMotoGPlus, 1);
        out.println(format("Valor do %s: no Dia dos Namorados R$ %.2f", celularMotoGPlus.getNome(), calcularValorCompra.valorCompra(DIA_DOS_NAMORADOS, comprarNoDiaDosNamorados)));

        Compra comprarNoNatal = new Compra();
        comprarNoNatal.comprar(celularMotoGPlus, 1);
        out.println(format("Valor do %s: no Natal R$ %.2f", celularMotoGPlus.getNome(), calcularValorCompra.valorCompra(NATAL, comprarNoNatal)));

        Compra comprarQuandoForSairProximoModelo = new Compra();
        comprarQuandoForSairProximoModelo.comprar(celularMotoGPlus, 1);
        out.println(format("Valor do %s: na queima de estoque R$ %.2f", celularMotoGPlus.getNome(), calcularValorCompra.valorCompra(QUEIMA_ESTOQUE, comprarQuandoForSairProximoModelo)));

        Compra comprarMaisDeDoisDeUmaVez = new Compra();
        comprarMaisDeDoisDeUmaVez.comprar(celularMotoGPlus, 3);
        out.println(format("Valor do %s: comprando mais de uma unidade R$ %.2f", celularMotoGPlus.getNome(), calcularValorCompra.valorCompra(DESCONTO_PROGRESSIVO, comprarMaisDeDoisDeUmaVez)));

        Compra comprarNoDiaDasMaes = new Compra();
        comprarNoDiaDasMaes.comprar(celularMotoGPlus, 1);
        out.println(format("Valor do %s: no dia das Mães R$ %.2f", celularMotoGPlus.getNome(), calcularValorCompra.valorCompra(DIA_DAS_MAES, comprarNoDiaDasMaes)));

        /**

         CONSOLE:

         Valor do Smartphone Moto G 4 Plus: R$ 1499.00
         Valor do Smartphone Moto G 4 Plus: no Dia dos Namorados R$ 1274.15
         Valor do Smartphone Moto G 4 Plus: no Natal R$ 1349.10
         Valor do Smartphone Moto G 4 Plus: na queima de estoque R$ 1124.25
         Valor do Smartphone Moto G 4 Plus: comprando mais de uma unidade R$ 3147.90
         Valor do Smartphone Moto G 4 Plus: no dia das Mães R$ 749.50

         */
    }
}

package br.com.marcia.pattern.chainofresponsibility.exemplo2

import br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra
import br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Produto
import spock.lang.Specification
import spock.lang.Unroll

import static br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra.Promocao.DESCONTO_PROGRESSIVO
import static br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra.Promocao.DIA_DAS_MAES
import static br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra.Promocao.DIA_DOS_NAMORADOS
import static br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra.Promocao.NATAL
import static br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra.Promocao.QUEIMA_ESTOQUE
import static br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo.Compra.Promocao.SEM_DESCONTO

class ChainOfResponsibilityPatternUnitSpec extends Specification {

    def "Teste calcular o valor de um produto não informando nenhuma promoção NÃO deve ter desconto"() {

        given:
        Produto celularMotoGPlus = new Produto("Smartphone Moto G 4 Plus", 1499.00)
        CalcularValorCompra calcularValorCompra = new CalcularValorCompra()

        Compra comprarNoLancamento = new Compra()
        comprarNoLancamento.comprar(celularMotoGPlus, 1)

        when:
        Double valorAPagar = calcularValorCompra.valorCompra(comprarNoLancamento)

        then:
        assert valorAPagar == 1499.00d
    }

    def "Teste calcular o valor de um produto informando que não tem nenhuma promoção"() {

        given:
        Produto celularMotoGPlus = new Produto("Smartphone Moto G 4 Plus", 1499.00)
        CalcularValorCompra calcularValorCompra = new CalcularValorCompra()

        Compra comprarNoLancamento = new Compra()
        comprarNoLancamento.comprar(celularMotoGPlus, 1)

        when:
        Double valorAPagar = calcularValorCompra.valorCompra(SEM_DESCONTO, comprarNoLancamento)

        then:
        assert valorAPagar == 1499.00d
    }

    def "Teste calcular o valor de um produto informando que é promoção do dia dos namorados"() {

        given:
        Produto celularMotoGPlus = new Produto("Smartphone Moto G 4 Plus", 1499.00)
        CalcularValorCompra calcularValorCompra = new CalcularValorCompra()

        Compra comprarNoDiaDosNamorados = new Compra()
        comprarNoDiaDosNamorados.comprar(celularMotoGPlus, 1)

        when:
        Double valorAPagar = calcularValorCompra.valorCompra(DIA_DOS_NAMORADOS, comprarNoDiaDosNamorados)

        then:
        assert valorAPagar == 1274.15d
    }

    def "Teste calcular o valor de um produto informando que é promoção de Natal"() {

        given:
        Produto celularMotoGPlus = new Produto("Smartphone Moto G 4 Plus", 1499.00)
        CalcularValorCompra calcularValorCompra = new CalcularValorCompra()

        Compra comprarNoNatal = new Compra()
        comprarNoNatal.comprar(celularMotoGPlus, 1)

        when:
        Double valorAPagar = calcularValorCompra.valorCompra(NATAL, comprarNoNatal)

        then:
        assert valorAPagar == 1349.10d
    }

    def "Teste calcular o valor de um produto informando que é queima de estoque"() {

        given:
        Produto celularMotoGPlus = new Produto("Smartphone Moto G 4 Plus", 1499.00)
        CalcularValorCompra calcularValorCompra = new CalcularValorCompra()

        Compra comprarNaQueimaDeEstoque = new Compra()
        comprarNaQueimaDeEstoque.comprar(celularMotoGPlus, 1)

        when:
        Double valorAPagar = calcularValorCompra.valorCompra(QUEIMA_ESTOQUE, comprarNaQueimaDeEstoque)

        then:
        assert valorAPagar == 1124.25d
    }

    @Unroll
    def "Teste calcular o valor de um produto informando que é promoção progressiva e comprando #quantidadeComprada produto(s) vamos pagar o valor de #valorDaCompra"() {

        given:
        Produto celularMotoGPlus = new Produto("Smartphone Moto G 4 Plus", 1499.00)
        CalcularValorCompra calcularValorCompra = new CalcularValorCompra()

        Compra comprarMaisDeDoisDeUmaVez = new Compra()
        comprarMaisDeDoisDeUmaVez.comprar(celularMotoGPlus, quantidadeComprada)

        when:
        Double valorAPagar = calcularValorCompra.valorCompra(DESCONTO_PROGRESSIVO, comprarMaisDeDoisDeUmaVez)

        then:
        assert valorAPagar == valorDaCompra
        where:
        quantidadeComprada  | valorDaCompra
        0                   | 0
        1                   | 1349.1d
        2                   | 2398.4d
        3                   | 3147.9d
        4                   | 3597.6d
        5                   | 4497.0d
    }

    def "Teste calcular o valor de um produto informando que é promoção do dia das mães"() {

        given:
        Produto celularMotoGPlus = new Produto("Smartphone Moto G 4 Plus", 1499.00)
        CalcularValorCompra calcularValorCompra = new CalcularValorCompra()

        Compra comprarNoDiaDasMaes = new Compra()
        comprarNoDiaDasMaes.comprar(celularMotoGPlus, 1)

        when:
        Double valorAPagar = calcularValorCompra.valorCompra(DIA_DAS_MAES, comprarNoDiaDasMaes)

        then:
        assert valorAPagar == 749.5d
    }
}
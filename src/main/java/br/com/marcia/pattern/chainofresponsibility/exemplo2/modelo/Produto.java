package br.com.marcia.pattern.chainofresponsibility.exemplo2.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Produto {

    private String nome;
    private Double valor;

}
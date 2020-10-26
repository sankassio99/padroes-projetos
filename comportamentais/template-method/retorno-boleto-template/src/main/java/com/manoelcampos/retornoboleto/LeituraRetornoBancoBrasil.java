package com.manoelcampos.retornoboleto;

import java.time.LocalDate;

/**
 * Classe que compõe a implementação do padrão Template Method,
 * contendo método que representa um passo do template
 * na superclasse.
 *
 * @author Manoel Campos da Silva Filho
 */
public class LeituraRetornoBancoBrasil extends ProcessadorBoletos {
    @Override
    protected Boleto processarLinhaArquivo(String[] vetor) {
        Boleto boleto = new Boleto();
        boleto.setId(Integer.parseInt(vetor[0]));
        boleto.setCodBanco(vetor[1]);

        boleto.setDataVencimento(LocalDate.parse(vetor[2], ProcessadorBoletos.FORMATO_DATA));
        boleto.setDataPagamento(LocalDate.parse(vetor[3], ProcessadorBoletos.FORMATO_DATA).atTime(0, 0, 0));

        boleto.setCpfCliente(vetor[4]);
        boleto.setValor(Double.parseDouble(vetor[5]));
        boleto.setMulta(Double.parseDouble(vetor[6]));
        boleto.setJuros(Double.parseDouble(vetor[7]));
        return boleto;
    }
}

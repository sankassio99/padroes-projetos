package com.manoelcampos.retornoboleto;

import java.util.List;

/**
 * Processa arquivos de retorno de boletos bancários utilizando a implementação de
 * alguma estratégia ({@link LeituraRetorno}).
 * Esta é uma classe que chamamos de Estrategista,
 * por poder utilizar diferentes estratégias de acordo com as necessidades,
 * podendo mudar a estratégia a ser utilizada até em tempo de execução.
 *
 * @author Manoel Campos da Silva Filho
 */
public class ProcessarBoletos {
    private LeituraRetorno leituraRetorno;

    /**
     * Instancia a classe estrategista, já indicando
     * @param leituraRetorno
     */
    public ProcessarBoletos(LeituraRetorno leituraRetorno){
        this.leituraRetorno = leituraRetorno;
    }

    public void processar(String nomeArquivo){
        System.out.println("Boletos");
        System.out.println("----------------------------------------------------------------------------------");
        List<Boleto> boletos = leituraRetorno.lerArquivo(nomeArquivo);
        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }
    }

    public void setLeituraRetorno(LeituraRetorno leituraRetorno) {
        this.leituraRetorno = leituraRetorno;
    }
}

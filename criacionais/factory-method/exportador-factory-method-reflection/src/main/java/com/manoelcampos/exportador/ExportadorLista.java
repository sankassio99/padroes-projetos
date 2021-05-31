package com.manoelcampos.exportador;

import java.util.List;
import java.util.function.Function;

/**
 * Define uma interface para criação de classes que implementam
 * a exportação de uma lista de objetos para
 * formatos específicos como HTML, CSV, XML, AsciiDoc, Markdown, etc.
 *
 * <p>Esta interface e suas implementações usam adequadamente
 * o recurso de Generics do Java, permitindo saber exatamente qual
 * o tipo dos objetos da lista, não apenas usando um tipo
 * coringa como ? na declaração da lista.
 * </p>
 *
 * @param <T> tipo de objetos da lista a ser exportada
 * @author Manoel Campos da Silva Filho
 */
public interface ExportadorLista<T> {
    /**
     * Cria uma instância de uma classe
     * que realiza a exportação de dados para um formato padrão.
     * Neste caso, tal formato padrão é HTML.
     *
     * @param lista Lista genérica de objetos a ser exportada.
     * @return
     */
    static <T> ExportadorLista<T> newInstance(final List<T> lista){
        return newInstance(lista, "html");
    }

    /**
     * Cria uma instância de uma classe
     * que realiza a exportação de dados para um formato definido.
     * @param lista Lista genérica de objetos a ser exportada.
     * @param extensaoArquivoExportacao extensão de arquivo que indica o formato para converter os dados,
     *                                  como html, csv, md (markdown), etc.
     * @return
     */
    static <T> ExportadorLista<T> newInstance(final List<T> lista, String extensaoArquivoExportacao){
        switch (extensaoArquivoExportacao){
            case "html": return new ExportadorListaHtml<>(lista);
            case "md": return new ExportadorListaMarkdown<>(lista);
            default: throw new UnsupportedOperationException("Formato de arquivo não suportado: " + extensaoArquivoExportacao);
        }
    }

    /**
     * Retorna o código HTML para abertura de uma tabela.
     * @return
     */
    String abrirTabela();

    /**
     * Retorna o código para fechamento de uma tabela em um formato de dados específico.
     * @return
     */
    String fecharTabela();

    /**
     * Retorna o código para abertura de uma linha da tabela em um formato de dados específico.
     * @return
     */
    String abrirLinha();

    /**
     * Retorna o código para fechamento de uma linha da tabela em um formato de dados específico.
     * @return
     */
    String fecharLinha();

    /**
     * Retorna o código para fechamento da linha de títulos de uma tabela em um formato de dados específico.
     * @return
     */
    String fecharLinhaTitulos();

    /**
     * Inicia a exportação da lista de objetos para um formato específico.
     *
     * @return String contendo o conteúdo da lista de objetos em um formato específico
     */
    String exportar();

    /**
     * Cria uma coluna para ser inserida na tabela, cujo valor a ser exibido será obtido
     * a partir de uma função que recebe um objeto da lista a ser exportada e retorna
     * uma String com dados obtidos de qualquer atributo deste objeto.
     * <b>Este método representa a aplicação do padrão Factory Method,
     * uma vez que as subclasses é que vão decidir qual objeto criar.</b>
     *
     * @param funcaoValorColuna uma função ({@link Function}) que recebe um objeto
     *                          da lista a ser exportada e retorna uma String
     *                          que representa o conteúdo a ser exibido para a coluna
     * @param titulo título a ser exibido na coluna
     * @return
     */
    Coluna<T> newColuna(Function<T, String> funcaoValorColuna, String titulo);

    /**
     * Adiciona uma coluna à tabela.
     * @param coluna coluna a ser adicionada
     * @see #newColuna(Function, String)
     */
    void addColuna(Coluna<T> coluna);
}

package com.manoelcampos.exportador;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * Gera dados de um atributo de um objeto como uma coluna em uma tabela HTML.
 *
 * <p>Observe que, como estamos usando o padrão Factory Method para instanciar
 * objetos {@link Coluna}, as classes concretas como esta são definidas com visibilidade "package",
 * não podendo ser acessadas fora do pacote.
 * Assim, não teremos como instanciar diretamente tais classes.
 * O Factory Method faz isso pra nós.</p>
 *
 * @param <T> tipo de objetos da lista a ser exportada
 * @author Manoel Campos da Silva Filho
 */
class ColunaHtml<T> extends AbstractColuna<T> {
    /**
     * Instancia uma coluna para uma tabela HTML, cujo valor a ser exibido será obtido
     * a partir de um campo (atributo) específico de um objeto.
     * <p>Este construtor é protegido pois é usado apenas internamente.</p>
     *
     * @param campo  campo (atributo) do objeto a ser obtido o valor
     * @return o valor do campo como String ou vazio se o campo for null
     */
    protected ColunaHtml(Field campo) {
        super(campo);
    }

    /**
     * Instancia uma coluna para uma tabela HTML, cujo valor a ser exibido será obtido
     * a partir de uma função que recebe um objeto da lista a ser exportada e retorna
     * uma String com dados obtidos de qualquer atributo deste objeto.
     *
     * <p>Este construtor é definido como protegido para evitar que ele seja
     * chamado diretamente, uma vez que a classe {@link ExportadorListaHtml}
     * implementa o Factory Method {@link ExportadorListaHtml#newColuna(Function, String)},
     * usado para criar instâncias específicas desta classe quando estiver sendo
     * gerada uma tabela HTML.</p>
     *
     * @param funcaoValorColuna uma função ({@link Function}) que recebe um objeto
     *                          da lista a ser exportada e retorna uma String
     *                          que representa o conteúdo a ser exibido para a coluna
     * @param titulo título a ser exibido na coluna
     */
    protected ColunaHtml(Function<T, String> funcaoValorColuna, String titulo) {
        super(funcaoValorColuna, titulo);
    }

    @Override
    public String abrir()  {
        return "<td>";
    }

    @Override
    public String fechar()  {
        return "</td>";
    }
}

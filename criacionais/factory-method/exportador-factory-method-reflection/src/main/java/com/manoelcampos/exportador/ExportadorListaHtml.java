package com.manoelcampos.exportador;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;

/**
 * Exporta dados de uma lista de objetos para o formato HTML.
 *
 * <p>Observe que, como estamos usando o padrão Simple Factory para instanciar
 * objetos {@link ExportadorLista}, as classes concretas como esta são definidas com visibilidade "package",
 * não podendo ser acessadas fora do pacote.
 * Assim, não teremos como instanciar diretamente tais classes.
 * A Simple Factory faz isso pra nós.</p>
 *
 * @author Manoel Campos da Silva Filho
 */
class ExportadorListaHtml<T> extends AbstractExportadorLista<T> {

    /**
     * Instancia um exportador de uma determinada lista de objetos para o formato HTML.
     *
     * @param lista Lista genérica de objetos a ser exportada.
     */
    public ExportadorListaHtml(List<T> lista) {
        super(lista);
    }

    @Override
    public String abrirTabela() {
        return "<table>\n";
    }

    @Override
    public String fecharTabela() {
        return "</table>\n";
    }

    @Override
    public String abrirLinha() {
        return "  <tr>";
    }

    @Override
    public String fecharLinha() {
        return "</tr>\n";
    }

    @Override
    public String fecharLinhaTitulos() {
        return "";
    }

    @Override
    protected Coluna newColuna(Field campo) {
        return new ColunaHtml<>(campo);
    }

    @Override
    public Coluna<T> newColuna(Function<T, String> funcaoValorColuna, String titulo) {
        return new ColunaHtml<>(funcaoValorColuna, titulo);
    }
}

package com.manoelcampos.exportador;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;

/**
 * Exporta dados de uma lista de objetos para o formato HTML.
 * @author Manoel Campos da Silva Filho
 */
public class ExportadorListaHtml<T> extends AbstractExportadorLista<T> {

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
    public ColunaTabela newColuna(T objeto, Field campo) {
        return new ColunaHtml<>(objeto, campo);
    }

    @Override
    public ColunaTabela newColuna(T objeto, Function<T, String> funcaoValorColuna, String titulo) {
        return new ColunaHtml<>(objeto, funcaoValorColuna, titulo);
    }
}

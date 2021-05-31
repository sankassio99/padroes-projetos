package com.manoelcampos.cepservice;

import com.manoelcampos.model.Endereco;

import javax.enterprise.inject.Alternative;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

/**
 * Acesso à API REST to serviço <a href="https://viacep.com.br">ViaCEP</a>.
 * Exemplo de requisição: <a href="https://viacep.com.br/ws/01001000/json/">https://viacep.com.br/ws/01001000/json/</a>
 *
 * @author Manoel Campos da Silva Filho
 */
@Alternative
public final class ViaCepService extends AbstractCepService {
    private static final String DOMINIO = "https://viacep.com.br/";

    /**
     * Define um construtor padrão que não aceita parâmetros.
     * Ele apenas chama o construtor protegido na superclasse,
     * passando o domínio do serviço a ser acessado.
     * O construtor da superclasse não é incluído aqui pois
     * não queremos que quem for utilizar tal classe,
     * possa chamar aquele construtor.
     * Se tal construtor fosse adicionado,
     * o usuário da classe poderia instanciá-la, indicando um domínio diferente
     * para o serviço. No entanto, o domínio e fixo, logo, não deve ser alterado.
     */
    public ViaCepService(){
        super(DOMINIO);
    }

    @Override
    protected Endereco jsonToEndereco(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject object = reader.readObject();
        Endereco endereco = new Endereco();
        endereco.setLogradouro(object.getString("logradouro"));
        endereco.setBairro(object.getString("bairro"));
        endereco.setLocalidade(object.getString("localidade"));
        endereco.setUf(object.getString("uf"));
        endereco.setCep(object.getString("cep"));
        return endereco;
    }

    @Override
    protected String buildPath(final String cep) {
        return "ws/" + cep + "/json";
    }
}

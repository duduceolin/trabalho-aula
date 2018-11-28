package trabalho.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UsuarioDTO {

    private String id;
    private String nome;
    private String senha;
    private String usuario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("nome", nome)
                .append("senha", senha)
                .append("usuario", usuario)
                .toString();
    }
}

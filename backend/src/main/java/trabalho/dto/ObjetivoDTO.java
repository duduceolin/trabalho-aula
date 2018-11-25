package trabalho.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ObjetivoDTO {

    private Integer id;

    @NotEmpty(message = "O campo descrição é obrigatório")
    private String descricao;

    @NotNull(message = "O campo valor estipulado é obrigatório")
    private BigDecimal valorEstipulado;

    private BigDecimal valorInvestido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorEstipulado() {
        return valorEstipulado;
    }

    public void setValorEstipulado(BigDecimal valorEstipulado) {
        this.valorEstipulado = valorEstipulado;
    }

    public BigDecimal getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(BigDecimal valorInvestido) {
        this.valorInvestido = valorInvestido;
    }
}

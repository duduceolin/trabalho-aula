package trabalho.dto;

import java.math.BigDecimal;

public class DinheiroDTO {

    private Integer objetivo;

    private BigDecimal valor;

    public Integer getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Integer objetivo) {
        this.objetivo = objetivo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}

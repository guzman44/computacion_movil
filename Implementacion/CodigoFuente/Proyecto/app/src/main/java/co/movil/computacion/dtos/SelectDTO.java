package co.movil.computacion.dtos;

import java.io.Serializable;

public class SelectDTO implements Serializable {

    private Integer id;
    private String valor;

    public SelectDTO() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}

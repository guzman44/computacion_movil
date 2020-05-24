package co.movil.computacion.dtos;

import java.io.Serializable;

public class ResponseDTO implements Serializable {

    private String response;
    private String type;

    public ResponseDTO() {
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

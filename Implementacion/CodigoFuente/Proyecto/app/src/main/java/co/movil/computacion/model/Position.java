package co.movil.computacion.model;


import java.util.Date;

public class Position {

    private double latitude;
    private double logitude;

    public Position() {
    }

    public Position(double latitude, double logitude, Date fecha) {
        this.latitude = latitude;
        this.logitude = logitude;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    private Date fecha;

    public Position(double latitude, double logitude) {
        this.latitude = latitude;
        this.logitude = logitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLogitude() {
        return logitude;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }
}
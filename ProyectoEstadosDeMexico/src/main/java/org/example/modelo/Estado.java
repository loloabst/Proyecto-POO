package org.example.modelo;


import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Estado {
    private int id;
    private String nombreEdo;
    private String capital;
    private String municipio;
    private String poblacion;
    private  String URL;

    public Estado() {
    }

    public Estado(int id, String nombreEdo, String capital, String municipio, String poblacion, String  URL ) {
        this.id = id;
        this.nombreEdo = nombreEdo;
        this.capital = capital;
        this.municipio = municipio;
        this.poblacion = poblacion;
        this.URL = URL;
    }
    // metodos de acceso
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEdo() {
        return nombreEdo;
    }

    public void setNombreEdo(String nombreEdo) {
        this.nombreEdo = nombreEdo;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nombreEdo='" + nombreEdo + '\'' +
                ", capital='" + capital + '\'' +
                ", municipio='" + municipio + '\'' +
                ", poblacion=" + poblacion +
                ", URL='" + URL + '\'' +
                '}';
    }
    public ImageIcon getImagen() throws MalformedURLException {
        URL urlImage = new URL(this.URL);
        return new ImageIcon(urlImage);
    }
}

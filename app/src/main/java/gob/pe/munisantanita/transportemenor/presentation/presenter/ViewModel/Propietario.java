package gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel;

import java.io.Serializable;
@SuppressWarnings("serial")
public class Propietario implements Serializable {
    private String id;
    private String nombreCompleto;
    private String direccion;
    private String numeroDni;
    private String telefonos;
    private String placaTemporal;
    private String tipoDocumento;
    private String unidades;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroDni() {
        return numeroDni;
    }

    public void setNumeroDni(String numeroDni) {
        this.numeroDni = numeroDni;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getPlacaTemporal() {
        return placaTemporal;
    }

    public void setPlacaTemporal(String placaTemporal) {
        this.placaTemporal = placaTemporal;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }
}

package gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel;

import java.io.Serializable;
@SuppressWarnings("serial")
public class Empresa implements Serializable {
    private String id;
    private Tipo tipo;
    private String siglaComercial;
    private String razonSocial;
    private String direccion;
    private String numeroRuc;
    private String telefono;
    private String estadoEmpadronado;
    private String autorizacion;
    private String fechaInscripcion;
    private String codigoAnterior;
    private String maximoUnidades;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getSiglaComercial() {
        return siglaComercial;
    }

    public void setSiglaComercial(String siglaComercial) {
        this.siglaComercial = siglaComercial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroRuc() {
        return numeroRuc;
    }

    public void setNumeroRuc(String numeroRuc) {
        this.numeroRuc = numeroRuc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstadoEmpadronado() {
        return estadoEmpadronado;
    }

    public void setEstadoEmpadronado(String estadoEmpadronado) {
        this.estadoEmpadronado = estadoEmpadronado;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getCodigoAnterior() {
        return codigoAnterior;
    }

    public void setCodigoAnterior(String codigoAnterior) {
        this.codigoAnterior = codigoAnterior;
    }

    public String getMaximoUnidades() {
        return maximoUnidades;
    }

    public void setMaximoUnidades(String maximoUnidades) {
        this.maximoUnidades = maximoUnidades;
    }
}

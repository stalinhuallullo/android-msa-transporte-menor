package gob.pe.munisantanita.transportemenor.presentation.presenter.ViewModel;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class Unidad implements Serializable {
    private String id;
    private Empresa empresa;
    private String numeroTituloPropierdad;
    private String numeroPlaca;
    private String clase;
    private String marca;
    private String anio;
    private String modelo;
    private String color;
    private String numeroMotor;
    private String numeroSerie;
    private String condicion;
    private String cia;
    private String fechaVencimiento;
    private String fechaRegistro;
    private String codigoEstado;
    private String permisoOperacion;
    private String fechaCaducidad;
    private String certificadoOperacion;
    private String fechaCaduca;
    private String entregado;
    private String observaciones;
    private String altaBaja;
    private String numeroFlota;
    private Seguro seguro;
    private String numeroPoliza;
    private List<Propietario> propietarios;
    private List<Conductor> conductores;
    private List<Foto> fotos;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNumeroTituloPropierdad() {
        return numeroTituloPropierdad;
    }

    public void setNumeroTituloPropierdad(String numeroTituloPropierdad) {
        this.numeroTituloPropierdad = numeroTituloPropierdad;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getCia() {
        return cia;
    }

    public void setCia(String cia) {
        this.cia = cia;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getPermisoOperacion() {
        return permisoOperacion;
    }

    public void setPermisoOperacion(String permisoOperacion) {
        this.permisoOperacion = permisoOperacion;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCertificadoOperacion() {
        return certificadoOperacion;
    }

    public void setCertificadoOperacion(String certificadoOperacion) {
        this.certificadoOperacion = certificadoOperacion;
    }

    public String getFechaCaduca() {
        return fechaCaduca;
    }

    public void setFechaCaduca(String fechaCaduca) {
        this.fechaCaduca = fechaCaduca;
    }

    public String getEntregado() {
        return entregado;
    }

    public void setEntregado(String entregado) {
        this.entregado = entregado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getAltaBaja() {
        return altaBaja;
    }

    public void setAltaBaja(String altaBaja) {
        this.altaBaja = altaBaja;
    }

    public String getNumeroFlota() {
        return numeroFlota;
    }

    public void setNumeroFlota(String numeroFlota) {
        this.numeroFlota = numeroFlota;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public List<Propietario> getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(List<Propietario> propietarios) {
        this.propietarios = propietarios;
    }

    public List<Conductor> getConductores() {
        return conductores;
    }

    public void setConductores(List<Conductor> conductores) {
        this.conductores = conductores;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
}

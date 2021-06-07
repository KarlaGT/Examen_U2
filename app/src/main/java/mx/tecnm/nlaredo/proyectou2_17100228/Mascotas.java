package mx.tecnm.nlaredo.proyectou2_17100228;

import java.util.Date;

public class Mascotas {


    private String nombre;
    private String raza;
    private String tamaño;
    private Double peso;
    private Integer sexo;
    private Integer vacunado;
    private Integer Retrato;
    private Integer edad;

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    private Date fecha_nacimiento;


    public Mascotas() {
    }
    public Mascotas(String Nombre, String Raza, String Tamaño, Double Peso, Integer Sexo, Integer Vacunado, Integer Retrato, Integer Edad, Date fecha_nac)
    {
        this.nombre = Nombre;
        this.raza = Raza;
        this.tamaño = Tamaño;
        this.peso = Peso;
        this.sexo = Sexo;
        this.vacunado = Vacunado;
        this.Retrato = Retrato;
        this.edad = Edad;
        this.fecha_nacimiento =  fecha_nac;
    }

    public Integer getRetrato() {
        return Retrato;
    }

    public void setRetrato(Integer retrato) {
        Retrato = retrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public Integer getVacunado() {
        return vacunado;
    }

    public void setVacunado(Integer vacunado) {
        this.vacunado = vacunado;
    }


}

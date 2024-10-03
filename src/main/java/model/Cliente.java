package model;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String contrasena;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String contrasena) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

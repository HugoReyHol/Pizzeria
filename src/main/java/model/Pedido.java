package model;

public class Pedido {
    private int idPedido;
    private Cliente cliente;
    private String pizza;
    private int cantidad;
    private String bebida;

    public Pedido() {
    }

    public Pedido(int idPedido, Cliente cliente, String pizza, int cantidad, String bebida) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.pizza = pizza;
        this.cantidad = cantidad;
        this.bebida = bebida;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", cliente=" + cliente +
                ", pizza='" + pizza + '\'' +
                ", cantidad=" + cantidad +
                ", bebida='" + bebida + '\'' +
                '}';
    }
}

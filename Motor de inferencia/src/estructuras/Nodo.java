package estructuras;

import java.util.Objects;

public class Nodo {

    String valor;
    String atributo;

    public Nodo(String valor, String atributo) {
        this.valor = valor;
        this.atributo = atributo;
    }

    @Override
    public String toString() {
        return "[atributo: " + atributo + ", valor: " + valor + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Nodo nodo = (Nodo) obj;
        return atributo.equals(nodo.atributo) && valor.equals(nodo.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atributo, valor);
    }

}

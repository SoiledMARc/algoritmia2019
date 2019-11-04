package algoritmia.Arboles;

/**
 *
 * @author noe_7
 */
public class Nodo {
    
    public int dato;
    public Nodo izq;
    public Nodo der;
    public int altura;
    
    
    public Nodo() {
    }

    public Nodo(int dato) {
        this.dato = dato;
        this.altura = 0;
        this.izq = null;
        this.der = null;
    }      

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "{" + "dato=" + dato + " FE=" + altura + '}';
    }

    

           
    
}

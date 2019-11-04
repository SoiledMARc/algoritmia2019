package algoritmia.Arboles;

/**
 *
 * @author noe_7
 */
public class Arbol {

    private Nodo raiz;

    public Arbol() {
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    /*
    public void agregarDato(int dato) {
        if (raiz != null) {
            agregarDato(dato, raiz);
        } else {
            Nodo d = new Nodo(dato);
            raiz = d;
        }
    }

    private void agregarDato(int dato, Nodo padre) {
        Nodo nodo = new Nodo();
        nodo.setDato(dato);

        if (nodo.getDato() < padre.getDato()) {
            if (padre.getIzq() != null) {
                agregarDato(nodo.getDato(), padre.getIzq());
            } else {
                padre.setIzq(nodo);
            }
        } else {
            if (padre.getDer() != null) {
                agregarDato(nodo.getDato(), padre.getDer());
            } else {
                padre.setDer(nodo);
            }
        }
    }
     */

    //Metodo privado para agregar dato con validaciones para que no se desbalance
    private Nodo insertarDato(Nodo nuevoNodo, Nodo nodo) {
        //auxiliar
        Nodo nuevoPadre = nodo;

        // validacion para dirigir el nodo
        if (nuevoNodo.dato < nodo.dato) {
            if (nodo.izq == null) {
                nodo.izq = nuevoNodo;
            } else {
                nodo.izq = insertarDato(nuevoNodo, nodo.izq);
                //balanceo del nodo
                if ((obtenerAltura(nodo.izq) - obtenerAltura(nodo.der) == 2)) {
                    if (nuevoNodo.dato < nodo.izq.dato) {
                        nuevoPadre = rotacionIzq(nodo);
                    } else {
                        nuevoPadre = rotacionDobleIzq(nodo);
                    }
                }
            }
        } else if (nuevoNodo.dato > nodo.dato) {
            if (nodo.der == null) {
                nodo.der = nuevoNodo;
            } else {
                nodo.der = insertarDato(nuevoNodo, nodo.der);
                if ((obtenerAltura(nodo.der) - obtenerAltura(nodo.izq) == 2)) {
                    if (nuevoNodo.dato > nodo.der.dato) {
                        nuevoPadre = rotacionDer(nodo);
                    } else {
                        nuevoPadre = rotacionDobleDer(nodo);
                    }
                }
            }
        } else {
            System.out.println("Duplicado");
        }
        if ((nodo.izq == null) && (nodo.der != null)) {
            nodo.altura = nodo.der.altura + 1;
        } else if ((nodo.der == null) && (nodo.izq != null)) {
            nodo.altura = nodo.izq.altura + 1;
        } else {
            nodo.altura = Math.max(obtenerAltura(nodo.izq), obtenerAltura(nodo.der)) + 1;
        }
        return nuevoPadre;
    }

    //metodo para insertar dato en arbol usando el metodo de validaciones balanceado AVL
    public void insertar(int dato) {
        Nodo nodo = new Nodo(dato);
        // si la raiz es null inserta en el primer dato
        if (raiz == null) {
            raiz = nodo;
            // en cualquier otro caso inserta dato usando el metodo de para balancear el arbol
        } else {
            raiz = insertarDato(nodo, raiz);
        }
    }

    private void imprimirPre(Nodo reco) {
        if (reco != null) {
            System.out.print(reco + " ");
            imprimirPre(reco.izq);
            imprimirPre(reco.der);
        }
    }

    private void imprimirIn(Nodo reco) {
        if (reco != null) {
            imprimirPre(reco.izq);
            System.out.print(reco + " ");
            imprimirPre(reco.der);
        }
    }

    public void imprimirPre() {
        imprimirPre(raiz);
        System.out.println();
    }

    public void imprimirIn() {
        imprimirIn(raiz);
        System.out.println();
    }

    //metodo publico para obtener la altura del arbol
    public int obtenerAltura() {
        //llama al metodo privado para obtener la altura de la raiz
        return obtenerAltura(raiz);
    }

    //metodo privado para obtener la altura de un arbol o de una rama
    private int obtenerAltura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.altura;
        }
    }

    //rotacion izq
    public Nodo rotacionIzq(Nodo nodo) {
        //uso de auxiliar para no perder valor
        Nodo aux = nodo.izq;
        //movimientos para remplazar los valores del nodo
        nodo.izq = aux.der;
        aux.der = nodo;
        nodo.altura = Math.max(obtenerAltura(nodo.izq), obtenerAltura(nodo.der)) + 1;
        aux.altura = Math.max(obtenerAltura(aux.izq), obtenerAltura(aux.der)) + 1;
        return aux;
    }

    //rotacion der
    public Nodo rotacionDer(Nodo nodo) {
        //uso de auxiliar para no perder valor
        Nodo aux = nodo.der;
        //movimientos para remplazar los valores del nodo
        nodo.der = aux.izq;
        aux.izq = nodo;
        nodo.altura = Math.max(obtenerAltura(nodo.izq), obtenerAltura(nodo.der)) + 1;
        aux.altura = Math.max(obtenerAltura(aux.izq), obtenerAltura(aux.der)) + 1;
        return aux;
    }

    //en caso de que tenga que usar dos rotaciones
    public Nodo rotacionDobleIzq(Nodo nodo) {
        //auxiliar
        Nodo aux;
        nodo.izq = rotacionDer(nodo.izq);
        aux = rotacionIzq(nodo);
        return aux;
    }

    public Nodo rotacionDobleDer(Nodo nodo) {
        Nodo aux;
        nodo.der = rotacionIzq(nodo.der);
        aux = rotacionDer(nodo);
        return aux;
    }

    //metodo para eliminar raiz
    private boolean soloRaiz(Nodo nodo) {
        if (nodo.getDer() == null && nodo.getIzq() == null) {
            nodo = null;
            return true;
        }
        return false;
    }

    //metodo publico para eliminar nodo
    public Nodo eliminar(int dato) {
        return EliminarNodo(raiz, dato);
    }

    //metodo para encontrar nodo a eliminar
    private Nodo EliminarNodo(Nodo nodo, int dato) {
        //eliminar la raiz
        if (soloRaiz(nodo)) {
            return null;
        }

        //si nodo es null no hace nada
        if (nodo == null) {

            //validacion para ver si es del lado izquierdo o derecho
        } else if (dato < nodo.getDato()) {
            Nodo izq;
            //obtiene los hijos del lado izquierdos
            izq = EliminarNodo(nodo.getIzq(), dato);
            //conecta del lado izquierdo
            nodo.setIzq(izq);
        } else if (dato > nodo.getDato()) {
            Nodo der;
            //obtiene los hijos del lado derecho
            der = EliminarNodo(nodo.getDer(), dato);
            //conecta del lado derecho
            nodo.setDer(der);
        } else {
            //auxiliar del nodo a eliminar
            Nodo eliminar;
            eliminar = nodo;

            //conecta el nodo a eliminar con el nodo principal
            if (eliminar.getIzq() == null) {
                nodo = eliminar.getDer();
            } else if (eliminar.getDer() == null) {
                nodo = eliminar.getIzq();
            } else {
                //remplaza el nodo
                eliminar = reemplazar(eliminar);
            }
            //se elimina el nodo
            //eliminar = null;
        }
        return nodo;
    }

    //metodo para remplazar nodo
    private Nodo reemplazar(Nodo nodo) {
        Nodo N1, N2;
        N2 = nodo;
        N1 = nodo.getIzq();

        while (N1.getDer() != null) {
            N2 = N1;
            N1 = N1.getDer();
        }

        nodo.setDato(N1.getDato());

        if (N2 == nodo) {
            N2.setIzq(N1.getIzq());
        } else {
            N2.setDer(N1.getIzq());
        }

        return N1;
    }

    @Override
    public String toString() {
        return raiz + " ";
    }
}

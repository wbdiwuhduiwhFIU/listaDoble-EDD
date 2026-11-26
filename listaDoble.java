package com.mycompany.listas;

/**
 * Implementación de una Lista Doblemente Ligada con apuntadores a cabeza y cola.
 */
public class listaDoble {
    private nodoDoble cabeza; // Apuntador al primer nodo de la lista
    private nodoDoble cola;   // Apuntador al último nodo de la lista
    private int tamanio;      // Contador de nodos actuales en la lista

    /**
     * Constructor que inicializa una lista doblemente ligada vacía.
     */
    public listaDoble() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    /**
     * Verifica si la lista no contiene elementos.
     * @return true si está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Devuelve la cantidad total de elementos en la lista.
     * @return número entero con el tamaño actual.
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Inserta un nuevo nodo al inicio de la lista (actualiza cabeza).
     * @param dato valor entero a insertar.
     */
    public void insertarAlInicio(int dato) {
        nodoDoble nuevo = new nodoDoble(dato, cabeza, null);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamanio++;
    }

    /**
     * Inserta un nuevo nodo al final de la lista (actualiza cola).
     * @param dato valor entero a insertar.
     */
    public void insertarAlFinal(int dato) {
        nodoDoble nuevo = new nodoDoble(dato, null, cola);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
        tamanio++;
    }

    /**
     * Inserta un dato en una posición específica (0 <= pos <= tamanio).
     * Reenlaza los apuntadores anterior y siguiente de los nodos vecinos.
     * @param pos índice donde se insertará el nuevo nodo.
     * @param dato valor entero a almacenar.
     */
    public void insertarEnPosicion(int pos, int dato) {
        if (pos < 0 || pos > tamanio) {
            throw new IndexOutOfBoundsException("Posición fuera de rango: " + pos);
        }
        if (pos == 0) {
            insertarAlInicio(dato);
            return;
        }
        if (pos == tamanio) {
            insertarAlFinal(dato);
            return;
        }

        // Localizar el nodo actual en la posición y reenlazar ambos sentidos
        nodoDoble actual = irAPosicion(pos);
        nodoDoble previo = actual.getAnterior();
        nodoDoble nuevo = new nodoDoble(dato, actual, previo);

        previo.setSiguiente(nuevo);
        actual.setAnterior(nuevo);
        tamanio++;
    }

    /**
     * Inserta un dato manteniendo la lista ordenada de forma ascendente.
     * Actualiza cabeza, cola y enlaces dobles según corresponda.
     * @param dato valor entero a insertar ordenadamente.
     */
    public void insertarOrdenado(int dato) {
        // Caso 1: Lista vacía o menor/igual que el primer elemento
        if (estaVacia() || dato <= cabeza.getDato()) {
            insertarAlInicio(dato);
            return;
        }
        // Caso 2: Mayor o igual que el último elemento
        if (dato >= cola.getDato()) {
            insertarAlFinal(dato);
            return;
        }

        // Caso 3: Buscar posición intermedia adecuada
        nodoDoble actual = cabeza.getSiguiente();
        while (actual != null && actual.getDato() < dato) {
            actual = actual.getSiguiente();
        }

        nodoDoble previo = actual.getAnterior();
        nodoDoble nuevo = new nodoDoble(dato, actual, previo);
        previo.setSiguiente(nuevo);
        actual.setAnterior(nuevo);
        tamanio++;
    }

    /**
     * Elimina el primer nodo de la lista y retorna su valor.
     * @return dato almacenado en la antigua cabeza.
     */
    public int eliminarInicio() {
        if (estaVacia()) {
            throw new IllegalStateException("La lista está vacía.");
        }
        int valor = cabeza.getDato();
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            nodoDoble temp = cabeza;
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);
            temp.setSiguiente(null);
        }
        tamanio--;
        return valor;
    }

    /**
     * Elimina el último nodo de la lista retrocediendo el apuntador cola.
     * @return true si se eliminó correctamente, false si la lista estaba vacía.
     */
    public boolean eliminarUltimo() {
        if (estaVacia()) {
            return false;
        }
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            nodoDoble temp = cola;
            cola = cola.getAnterior();
            cola.setSiguiente(null);
            temp.setAnterior(null);
        }
        tamanio--;
        return true;
    }

    /**
     * Elimina el nodo ubicado en la posición indicada (0 <= pos < tamanio).
     * @param pos índice del elemento a eliminar.
     * @return el dato entero del nodo eliminado.
     */
    public int eliminarEnPosicion(int pos) {
        if (estaVacia() || pos < 0 || pos >= tamanio) {
            throw new IndexOutOfBoundsException("Posición inválida o lista vacía: " + pos);
        }
        if (pos == 0) {
            return eliminarInicio();
        }
        if (pos == tamanio - 1) {
            int valor = cola.getDato();
            eliminarUltimo();
            return valor;
        }

        // Reconectar nodos anterior y siguiente alrededor del nodo eliminado
        nodoDoble target = irAPosicion(pos);
        int valor = target.getDato();
        nodoDoble previo = target.getAnterior();
        nodoDoble siguiente = target.getSiguiente();

        previo.setSiguiente(siguiente);
        siguiente.setAnterior(previo);
        target.setAnterior(null);
        target.setSiguiente(null);
        tamanio--;

        return valor;
    }

    /**
     * Busca y elimina la primera ocurrencia del valor x en la lista.
     * @param x valor entero a buscar y eliminar.
     * @return true si el valor existía y fue eliminado, false si no se encontró.
     */
    public boolean eliminarPorValor(int x) {
        if (estaVacia()) {
            return false;
        }
        nodoDoble actual = cabeza;
        while (actual != null && actual.getDato() != x) {
            actual = actual.getSiguiente();
        }

        if (actual == null) {
            return false;
        }
        if (actual == cabeza) {
            eliminarInicio();
            return true;
        }
        if (actual == cola) {
            return eliminarUltimo();
        }

        // Desenlazar el nodo intermedio encontrado
        nodoDoble previo = actual.getAnterior();
        nodoDoble siguiente = actual.getSiguiente();
        previo.setSiguiente(siguiente);
        siguiente.setAnterior(previo);
        actual.setAnterior(null);
        actual.setSiguiente(null);
        tamanio--;
        return true;
    }

    /**
     * Método auxiliar que optimiza la búsqueda de un nodo por índice
     * iniciando desde cabeza o cola según la mitad más cercana.
     */
    private nodoDoble irAPosicion(int pos) {
        nodoDoble aux;
        if (pos < tamanio / 2) {
            aux = cabeza;
            for (int i = 0; i < pos; i++) {
                aux = aux.getSiguiente();
            }
        } else {
            aux = cola;
            for (int i = tamanio - 1; i > pos; i--) {
                aux = aux.getAnterior();
            }
        }
        return aux;
    }

    /**
     * Recorre e imprime la lista desde cabeza hasta cola (sentido normal).
     */
    public String imprimirAdelante() {
        if (estaVacia()) return "Lista vacía";
        StringBuilder sb = new StringBuilder("Cabeza -> ");
        nodoDoble aux = cabeza;
        while (aux != null) {
            sb.append("[").append(aux.getDato()).append("]");
            if (aux.getSiguiente() != null) sb.append(" <-> ");
            aux = aux.getSiguiente();
        }
        sb.append(" <- Cola (Tamaño: ").append(tamanio).append(")");
        return sb.toString();
    }

    /**
     * Recorre e imprime la lista desde cola hasta cabeza (sentido inverso).
     */
    public String imprimirAtras() {
        if (estaVacia()) return "Lista vacía";
        StringBuilder sb = new StringBuilder("Cola -> ");
        nodoDoble aux = cola;
        while (aux != null) {
            sb.append("[").append(aux.getDato()).append("]");
            if (aux.getAnterior() != null) sb.append(" <-> ");
            aux = aux.getAnterior();
        }
        sb.append(" <- Cabeza (Tamaño: ").append(tamanio).append(")");
        return sb.toString();
    }
}
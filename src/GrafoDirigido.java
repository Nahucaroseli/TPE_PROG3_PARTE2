import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrafoDirigido<T> implements Grafo<T> {
	
	private Map<Integer,Set<Arco<T>>> mapa;
	private int cantVertices;
	private int cantArcos;
	
	
	public GrafoDirigido() {
		this.mapa = new HashMap<>();
		this.cantArcos = 0;
		this.cantVertices = 0;
	}
	
	/**
	* Complejidad: O(1), este metodo tiene complejidad O(1)
	* ya que solo agrega un vertice nuevo al mapa y crea una lista vacia q corresponde a los arcos
	* que salen del vertice nuevo. 
	*/
	@Override
	public void agregarVertice(int verticeId) {
		if(!contieneVertice(verticeId)) {
			mapa.put(verticeId, new HashSet<>());
			cantVertices++;
		}

	}

	
	/**
	* Complejidad: O(V+A), este metodo tiene complejidad O(V+A), donde V 
	* hace referencia a la cantidad de vertices y A hace referencia a la cantidad de arcos,
	* debido a que debe realizar dos recorridos sobre los arcos del grafo, el primer recorrido
	* es para borrar los arcos que tienen el vertice a eliminar como destino, y el segundo, es para 
	* borrar los arcos que tienen al vertice que se quiere borrar, como origen.
	*/
	@Override
	public void borrarVertice(int verticeId) {
		if (contieneVertice(verticeId)) {
			// En esta parte se borran todos los arcos que tienen a verticeId como destino
			for (Set<Arco<T>> arcosAdyacentes : mapa.values()) {
				Iterator<Arco<T>> it = arcosAdyacentes.iterator();
				while (it.hasNext()) {
					Arco<T> arco = it.next();
					if (arco.getVerticeDestino() == verticeId) {
						it.remove();
						cantArcos--;
					}
				}
			}

			// Aca se borran todos los arcos que tienen a verticeId como origen
			Set<Arco<T>> arcos = mapa.remove(verticeId);
			for (Arco<T> arco : arcos) {
				Set<Arco<T>> adyacentes = mapa.get(arco.getVerticeDestino());
				adyacentes.remove(new Arco<>(verticeId, arco.getVerticeDestino(), arco.getEtiqueta()));
				cantArcos--;
			}

			cantVertices--;
		}

	}
	
	/**
	* Complejidad: O(1), en este metodo la complejidad es O(1), ya que se comprueba
	* si ambos vertices existen en el grafo, esto es una operacion O(1) en la tabla, despues se 
	* accede a la lista de adyacencia que tambien es una operacion O(1), y por ultimo 
	* se agrega un arco nuevo a la lista, que tambien es una operacion O(1), lo cual 
	* determina que la complejidad computacional total del metodo es O(1)
	*/
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if (contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
			Set<Arco<T>> adyacentes = mapa.get(verticeId1);
			if (adyacentes.add(new Arco<>(verticeId1, verticeId2, etiqueta))) {
				cantArcos++;
			}
		}

	}
	/**
	* Complejidad: O(n), en este metodo la complejidad es O(n), ya que hay que buscar la lista 
	* de arcos adyacentes al verticeId1, y luego buscar el arco que va desde verticeId1 a verticeId2 dentro
	* de la lista. En el peor caso, se tendra que recorrer toda la lista para borrar el arco existente entre
	* los dos vertices.
	*/
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if (contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
			Set<Arco<T>> adyacentes = mapa.get(verticeId1);
			if (adyacentes.remove(obtenerArco(verticeId1,verticeId2))) {
				cantArcos--;
			}
		}
	}
	/**
	* Complejidad: O(1), este metodo tiene una complejidad de O(1), ya que se debe realizar
	* una operacion de busqueda en el mapa usando el verticeId, lo cual es de un solo acceso.
	*/
	@Override
	public boolean contieneVertice(int verticeId) {
		return mapa.containsKey(verticeId);
	}
	/**
	* Complejidad: O(V), este metodo tiene una complejidad de O(V), ya que el metodo contains del mapa debe realizar
	* un recorrido por toda la lista de adyacentes del vertice, para encontrar el arco que llega hasta el verticeId2.
	* Por lo tanto, en el peor caso se tendra que recorrer toda la lista.
	*/
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if (contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
			Set<Arco<T>> adyacentes = mapa.get(verticeId1);
			return adyacentes.contains(obtenerArco(verticeId1,verticeId2));
		}
		return false;
	}
	/**
	* Complejidad: O(A), este metodo tiene una complejidad de O(A), ya que se debe realizar
	* un recorrido por toda la lista de adyacentes del verticeId1, para encontrar el arco que llega hasta el verticeId2.
	* Por lo tanto, en el peor caso se tendra que recorrer toda la lista.
	*/
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
	    if (contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
	        Set<Arco<T>> adyacentes = mapa.get(verticeId1);
	        for (Arco<T> arco : adyacentes) {
	            if (arco.getVerticeDestino() == verticeId2) {
	                return arco;
	            }
	        }
	    }
	    return null;
	}

	/**
	* Complejidad: O(1), este metodo tiene una complejidad de O(1), ya que se debe devolver
	* la cantidad de vertices que existen en el grafo, lo cual es una operacion de un solo acceso.
	*/
	@Override
	public int cantidadVertices() {
		// TODO Auto-generated method stub
		return this.cantVertices;
	}

	/**
	* Complejidad: O(1), este metodo tiene una complejidad de O(1), ya que se debe devolver
	* la cantidad de arcos que existen en el grafo, lo cual es una operacion de un solo acceso.
	*/
	@Override
	public int cantidadArcos() {
		// TODO Auto-generated method stub
		return this.cantArcos;
	}
	/**
	* Complejidad: O(1), La complejidad computacional del método obtenerVertices() es O(1) en 
	* el peor caso, ya que simplemente devuelve 
	* un iterador sobre las claves del mapa subyacente, 
	* que es una operación de tiempo constante en Java.
	*/
	@Override
	public Iterator<Integer> obtenerVertices() {
	    return mapa.keySet().iterator();
	}
	
	/**
	* Complejidad: O(A), La complejidad computacional del método es O(A),
	* ya que en el peor caso se tendra que recorrer toda la lista de adyacencia, para 
	* buscar los adyacentes.
	*/
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
	    if (contieneVertice(verticeId)) {
	        Set<Arco<T>> adyacentes = mapa.get(verticeId);
	        List<Integer> verticesAdyacentes = new ArrayList<>();
	        for (Arco<T> arco : adyacentes) {
	            verticesAdyacentes.add(arco.getVerticeDestino());
	        }
	        return verticesAdyacentes.iterator();
	    }
	    return null;
	}
	/**
	* Complejidad: O(V+A), La complejidad computacional del método es O(V+A), donde V es la
	* cantidad de vertices y A la cantidad de arcos.
	* ya que se tendra que recorrer todo el grafo pasando por todos los vertices y arcos.
	*/
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
	    Set<Arco<T>> arcos = new HashSet<>();
	    for (Set<Arco<T>> adyacentes : mapa.values()) {
	        arcos.addAll(adyacentes);
	    }
	    return arcos.iterator();
	}
	/**
	* Complejidad: O(V+A), La complejidad computacional del método es O(V+A),
	* ya que en el peor caso se encuentra el vertice y se tendra que recorrer toda la lista de adyacencia
	* del vertice para obtener los arcos.
	*/
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
	    if (contieneVertice(verticeId)) {
	        Set<Arco<T>> adyacentes = mapa.get(verticeId);
	        return adyacentes.iterator();
	    }
	    return null;
	}


}

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ServicioCaminos {
	
	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
	}

	

	
	public List<List<Integer>> caminos() {
	    List<List<Integer>> caminos = new ArrayList<>();
	    Set<Arco<?>> arcosVisitados = new HashSet<>();
	    List<Integer> recorrido = new ArrayList<>();
	    recorrido.add(origen);
	    caminosGrafo(origen, arcosVisitados, caminos, recorrido, 0);
	    return caminos;
	}

	private void caminosGrafo(int v, Set<Arco<?>> arcosVisitados, List<List<Integer>> caminos, List<Integer> recorrido, int cantArcos) {
		arcosVisitados.add(new Arco<>(recorrido.get(recorrido.size() - 1), v, null));
	    if (cantArcos > lim) {
	        return;
	    }
	    if (destino == v) {
	        caminos.add(new ArrayList<>(recorrido));
	        return;
	    }
	    Iterator<Integer> it = grafo.obtenerAdyacentes(v);
	    while (it.hasNext()) {
	        int verticeSiguiente = it.next();
	        Arco<?> arco = new Arco<>(v, verticeSiguiente, null);
	        if (!arcoVisitado(arco, arcosVisitados)) {
	        	arcosVisitados.add(arco);
	        	recorrido.add(verticeSiguiente);
	            caminosGrafo(verticeSiguiente, arcosVisitados, caminos, recorrido, cantArcos + 1);
	            recorrido.remove(recorrido.size() - 1);
	            arcosVisitados.remove(arco);
	        }
	    }
	    arcosVisitados.remove(new Arco<>(recorrido.get(recorrido.size() - 1), v, null));
	}

	private boolean arcoVisitado(Arco<?> arco, Set<Arco<?>> arcosVisitados) {
	    for (Arco<?> visitado : arcosVisitados) {
	        if (visitado.getVerticeOrigen() == arco.getVerticeOrigen() && visitado.getVerticeDestino() == arco.getVerticeDestino()) {
	            return true;
	        }
	    }
	    return false;
	}

}

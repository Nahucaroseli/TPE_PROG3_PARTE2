import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ServicioDFS {
	
	private Grafo<?> grafo;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	
	
	public List<Integer> dfsForest() {
	    List<Integer> listaVertices = new ArrayList<>();
	    Set<Integer> verticesVisitados = new HashSet<>();
	    Iterator<Integer> it = grafo.obtenerVertices();
	    while (it.hasNext()) {
	        int vertice = it.next(); 
	        if (!verticesVisitados.contains(vertice)) {
	        	dfs(vertice,verticesVisitados,listaVertices);
	        }
	    }
	    
	    return listaVertices;
	}

	private void dfs(int v, Set<Integer> verticesVisitados, List<Integer> listaVertices) {
	    verticesVisitados.add(v);
	    listaVertices.add(v);
	    Iterator<Integer> it = grafo.obtenerAdyacentes(v);
	    while (it.hasNext()) {
	        int siguienteVertice = it.next(); 
	        if (!verticesVisitados.contains(siguienteVertice)) {
	        	dfs(siguienteVertice,verticesVisitados,listaVertices);
	        }
	    }
	    
	    
	}


}

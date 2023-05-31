import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ServicioBFS {
	
private Grafo<?> grafo;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> bfsForest() {
		List<Integer> vertices = new ArrayList<>();
		Set<Integer> visitados = new HashSet<>();
		List<Integer> fila = new ArrayList<>();
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while(it.hasNext()) {
			int vertice = it.next();
			if(!visitados.contains(vertice)) {
				bfs(vertice,vertices,fila,visitados);
			}
		}
		return vertices;
	}
	
	
	private void bfs(int vertice, List<Integer> vertices,List<Integer> fila, Set<Integer> visitados) {
		visitados.add(vertice);
		fila.add(vertice);
		while(!fila.isEmpty()) {
			int siguiente = fila.get(0);
			fila.remove(0);
			Iterator<Integer> it = grafo.obtenerAdyacentes(siguiente);
			while(it.hasNext()) {
				int adyacente = it.next();
				if(!visitados.contains(adyacente)) {
					vertices.add(adyacente);
					visitados.add(adyacente);
					fila.add(adyacente);
				}
			}
		}
		
	}
	
	
	

}

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Backtracking {
	
	private Grafo<?> grafo;
	private int costoMejorSolucion;
	private List<List<Integer>> mejorSolucion;
	
	
	public Backtracking(Grafo<?> grafo) {
		this.grafo = grafo;
		this.costoMejorSolucion = Integer.MAX_VALUE;
		this.mejorSolucion = new ArrayList<>();
	}
	
	public void backtracking() {
	    List<List<Integer>> solucionActual = new ArrayList<>();
	    List<Integer> caminoActual = new ArrayList<>();
	    caminoActual.add(1);
	    backtracking(1, solucionActual, caminoActual, 0);

	    mostrarSolucion();
	}

	private void backtracking(int estacionActual, List<List<Integer>> solucionActual, List<Integer> caminoActual, int costoActual) {
	    if (solucionActual.size() == grafo.cantidadVertices()-1) {
	        if (costoActual < this.costoMejorSolucion) {
	            costoMejorSolucion = costoActual;
	            mejorSolucion = new ArrayList<>(solucionActual);
	            return;
	        }
	       return;
	    }

	    Iterator<Integer> it = grafo.obtenerAdyacentes(estacionActual);
	    while (it.hasNext()) {
	        int siguienteEstacion = it.next();
	        if (!caminoActual.contains(siguienteEstacion)) {
	            caminoActual.add(siguienteEstacion);
	            int costoTunel = obtenerCostoTunel(estacionActual, siguienteEstacion);
	            List<Integer> tunel = new ArrayList<>();
	            tunel.add(estacionActual);
	            tunel.add(siguienteEstacion);
	            solucionActual.add(tunel);
	            costoActual = costoActual+costoTunel;
	            backtracking(siguienteEstacion, solucionActual, caminoActual,costoActual);
	            costoActual = costoActual-costoTunel;
	            solucionActual.remove(solucionActual.size() - 1);
	            caminoActual.remove(caminoActual.size() - 1);
	        }
	    }
	}

	private int obtenerCostoTunel(int estacion1, int estacion2) {
	    Arco<?> arco = grafo.obtenerArco(estacion1, estacion2);
	    return arco != null ? Integer.parseInt(arco.getEtiqueta().toString()) : Integer.MAX_VALUE;
	}

	private void mostrarSolucion() {
	    System.out.println("Técnica utilizada: Backtracking");
	    System.out.println("Lista de túneles a construir:");

	    for (List<Integer> tunel : mejorSolucion) {
	        int estacion1 = tunel.get(0);
	        int estacion2 = tunel.get(1);
	        System.out.print("E" + estacion1 + "-E" + estacion2+",");
	    }
	    System.out.println("");
	    System.out.println("Cantidad de metros totales a construir: " + costoMejorSolucion);
	    System.out.println("Costo de encontrar la solución: " + calcularCostoSolucion());
	}

	private int calcularCostoSolucion() {
	    int costoSolucion = 0;
	    for (List<Integer> tunel : mejorSolucion) {
	        int estacion1 = tunel.get(0);
	        int estacion2 = tunel.get(1);
	        int costoTunel = obtenerCostoTunel(estacion1, estacion2);
	        costoSolucion += costoTunel;
	    }
	    return costoSolucion;
	}

}

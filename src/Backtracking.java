import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Backtracking {
	
	private Grafo<?> grafo;
	private int costoMejorSolucion;
	private List<List<Arco>> mejorSolucion;
	
	
	public Backtracking(Grafo<?> grafo) {
		this.grafo = grafo;
		this.costoMejorSolucion = Integer.MAX_VALUE;
		this.mejorSolucion = new ArrayList<>();
	}
	
	public void backtracking() {
	    List<List<Arco>> solucionActual = new ArrayList<>();
	    List<Arco> caminoActual = new ArrayList<>();
	    backtracking( solucionActual, caminoActual, 0);

	    mostrarSolucion();
	}

	private void backtracking( List<List<Arco>> solucionActual, List<Arco> caminoActual, int costoActual) {
	    if (solucionActual.size() == grafo.cantidadVertices()) {
	        if (costoActual < this.costoMejorSolucion) {
	            costoMejorSolucion = costoActual;
	            mejorSolucion = new ArrayList<>(solucionActual);
	            return;
	        }
	       return;
	    }

	    Iterator<? extends Arco<?>> it = grafo.obtenerArcos();
	    while (it.hasNext()) {
	        Arco siguienteArco = it.next();
	        if (!caminoActual.contains(siguienteArco)) {
	            caminoActual.add(siguienteArco);

	            int costoTunel =  Integer.parseInt(siguienteArco.getEtiqueta().toString());
	            List<Arco> tunel = new ArrayList<>();
				tunel.add(siguienteArco);
	            solucionActual.add(tunel);
	            costoActual = costoActual+costoTunel;
	            backtracking( solucionActual, caminoActual,costoActual);
	            costoActual = costoActual-costoTunel;
	            solucionActual.remove(solucionActual.size() - 1);
	            caminoActual.remove(caminoActual.size() - 1);
	        }
	    }
	}


	private void mostrarSolucion() {
	    System.out.println("Técnica utilizada: Backtracking");
	    System.out.println("Lista de túneles a construir:");

	    for (List<Arco> tunel : mejorSolucion) {
	        int estacion1 = tunel.get(0).getVerticeOrigen();
	        int estacion2 = tunel.get(0).getVerticeDestino();
	        System.out.print("E" + estacion1 + "-E" + estacion2+",");
	    }
	    System.out.println("");
	    System.out.println("Cantidad de metros totales a construir: " + costoMejorSolucion);
	   // System.out.println("Costo de encontrar la solución: " + calcularCostoSolucion());
	}

	private int calcularCostoSolucion() {
	    int costoSolucion = 0;
	    for (List<Arco> tunel : mejorSolucion) {
			int estacion1 = tunel.get(0).getVerticeOrigen();
			int estacion2 = tunel.get(0).getVerticeDestino();
	        int costoTunel = this.costoMejorSolucion;
	        costoSolucion += costoTunel;
	    }
	    return costoSolucion;
	}

}

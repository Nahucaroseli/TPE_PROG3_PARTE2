import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Backtracking {
	
	private Grafo<?> grafo;
	private int costoMejorSolucion;
	private List<List<Arco>> mejorSolucion;
	private int cantLlamadasRecursivas;
	
	public Backtracking(Grafo<?> grafo) {
		this.grafo = grafo;
		this.costoMejorSolucion = Integer.MAX_VALUE;
		this.mejorSolucion = new ArrayList<>();
		this.cantLlamadasRecursivas = 0;
	}
	
	public void backtracking() {
	    List<List<Arco>> solucionActual = new ArrayList<>();
	    List<Arco> visitados = new ArrayList<>();
	    backtracking( solucionActual, visitados, 0);
	    mostrarSolucion();
	}

	private void backtracking( List<List<Arco>> solucionActual, List<Arco> visitados, int costoActual) {
    	this.cantLlamadasRecursivas++;
    	 if (visitados.size() == grafo.cantidadVertices()) {
    		 if(costoActual< this.costoMejorSolucion) {
    			 costoMejorSolucion = costoActual;
     	        mejorSolucion = new ArrayList<>(solucionActual);
     	        return;
    		 }
    	
    	 }
	    Iterator<? extends Arco<?>> it = grafo.obtenerArcos();
	    while (it.hasNext()) {
	        Arco siguienteArco = it.next();
	        if (!visitados.contains(siguienteArco)) {            
	        	visitados.add(siguienteArco);
	            int costoTunel =  Integer.parseInt(siguienteArco.getEtiqueta().toString());
	            List<Arco> tunel = new ArrayList<>();
				tunel.add(siguienteArco);
	            solucionActual.add(tunel);
	            costoActual = costoActual+costoTunel;
	            System.out.println(costoActual);
	            if (costoActual < this.costoMejorSolucion) {
	            	backtracking(solucionActual, visitados,costoActual);
	            }
 	            solucionActual.remove(solucionActual.size() - 1);
 	            visitados.remove(siguienteArco);
 	            costoActual = costoActual-costoTunel;
	           
	        }
	    }
	}


	private void mostrarSolucion() {
	    System.out.println("Backtracking");
	    for (List<Arco> tunel : mejorSolucion) {
	        int estacion1 = tunel.get(0).getVerticeOrigen();
	        int estacion2 = tunel.get(0).getVerticeDestino();
	        System.out.print("E" + estacion1 + "-E" + estacion2+",");
	    }
	    System.out.println("");
	    System.out.println(costoMejorSolucion+ " kms");
	    System.out.println(this.cantLlamadasRecursivas + " llamadas recursivas");
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

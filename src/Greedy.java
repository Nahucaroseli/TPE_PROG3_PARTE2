import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Greedy {
	
	
	private Grafo<?> grafo;
	private int costoMejorSolucion;
	private List<List<Arco>> mejorSolucion;
	private List<Arco> solucion;
	private int cantIteraciones;
	
	
	public Greedy(Grafo<?> grafo) {
		this.grafo = grafo;
		this.costoMejorSolucion = Integer.MAX_VALUE;
		this.mejorSolucion = new ArrayList<>();
		this.solucion = new ArrayList<>();
		this.cantIteraciones = 0;
	}
	
	public void greedy() {
		this.Greedy();
		mostrarSolucion();

	}
	
	
	private List<List<Arco>> Greedy(){
		Iterator<?> conjunto = grafo.obtenerArcos();
		int costoTotal =  0;
		while(conjunto.hasNext() && mejorSolucion.size()!= grafo.cantidadVertices()){

			List<Arco>lista = seleccion();
			solucion.add(lista.get(0));
			mejorSolucion.add(lista);
			costoTotal += Integer.parseInt(lista.get(0).getEtiqueta().toString());	
		}
		costoMejorSolucion =costoTotal;
		if(mejorSolucion.size()== grafo.cantidadVertices()) {
			return mejorSolucion;
		}
		return null;
	}
	
	
	private void mostrarSolucion() {
	    System.out.println("Greedy");
	    for (List<Arco> tunel : mejorSolucion) {
	        int estacion1 = tunel.get(0).getVerticeOrigen();
	        int estacion2 = tunel.get(0).getVerticeDestino();
	        System.out.print("E" + estacion1 + "-E" + estacion2+",");
	    }
	    System.out.println("");
	    System.out.println(costoMejorSolucion+ " kms");
	    System.out.println(this.cantIteraciones + " iteraciones");
	}


	private List<Arco> seleccion() {
		List<Arco> seleccionados = new ArrayList<>();
	    Arco<?> mejorResultado = null;
	    Iterator it2 = grafo.obtenerArcos();
	    	while (it2.hasNext()) {     
	    		Arco<?> a = (Arco<?>) it2.next();
	    		if(!solucion.contains(a)) {
			        if(mejorResultado==null) {
			        		mejorResultado = a;
			        }else if(Integer.parseInt(mejorResultado.getEtiqueta().toString()) > Integer.parseInt(a.getEtiqueta().toString())) {
			        			mejorResultado = a;
			        }
	    		}
		    }   
	   seleccionados.add(mejorResultado);
	   this.cantIteraciones++;
	   return seleccionados;
	}
}

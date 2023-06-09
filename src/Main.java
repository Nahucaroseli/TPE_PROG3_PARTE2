import java.util.Iterator;

public class Main {
	
	
	public static void main(String [] args) {
		GrafoDirigido grafo = new GrafoDirigido();
		/*
		grafo.agregarVertice(1);
		grafo.agregarVertice(6);
		grafo.agregarVertice(3);
		grafo.agregarVertice(9);
		grafo.agregarVertice(5);
		grafo.agregarVertice(2);
		grafo.agregarVertice(4);
		grafo.agregarVertice(7);
		grafo.agregarVertice(8);
		grafo.agregarVertice(10);
		grafo.agregarArco(1, 6, "1 y 6");
		grafo.agregarArco(6, 9, "6 y 9");
		grafo.agregarArco(2, 3, "2 y 3");
		grafo.agregarArco(9, 8, "9 y 8");
		grafo.agregarArco(3, 1, "3 y 1");
		grafo.agregarArco(2, 5, "2 y 5");
		grafo.agregarArco(2, 7, "2 y 7");
		grafo.agregarArco(4, 7, "4 y 7");
		grafo.agregarArco(5, 8, "5 y 8");
		grafo.agregarArco(6, 4, "6 y 4");
		grafo.agregarArco(8, 3, "8 y 3");
		grafo.agregarArco(3, 9, "3 y 9");
		grafo.agregarArco(1, 10, "1 y 10");
		grafo.agregarArco(6, 5, "6 y 5");
		grafo.agregarArco(6, 7, "6 y 7");
		grafo.agregarArco(7, 8, "7 y 8");
		*/
		
		
		String path = "C:\\Users\\Nahue\\eclipse-workspace\\Prog3_TPE\\src\\datasets\\dataset2.txt";
		String path2 = "C:\\Users\\s7\\IdeaProjects\\TPE P2\\src\\datasets\\dataset1.txt";
		CSVReader reader = new CSVReader(path2);
		GrafoNoDirigido grafoNoDirigido = new GrafoNoDirigido();

		reader.read(grafoNoDirigido);

		Backtracking back = new Backtracking(grafoNoDirigido);
		back.backtracking();
		
		
	/*
		ServicioDFS dfs = new ServicioDFS(grafo);
		ServicioBFS bfs = new ServicioBFS(grafo);
		System.out.println(dfs.dfsForest());
		System.out.println(bfs.bfsForest());


		ServicioCaminos caminos = new ServicioCaminos(grafo,2,6,4);
		System.out.println(caminos.caminos());
		*/

	}

}

import java.util.Objects;

public class Arco<T> {
	private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public int getVerticeOrigen() {
		return verticeOrigen;
	}
	
	public int getVerticeDestino() {
		return verticeDestino;
	}

	public T getEtiqueta() {
		return etiqueta;
	}
	
	
	

	@Override
	public String toString() {
		return "Arco [verticeOrigen=" + verticeOrigen + ", verticeDestino=" + verticeDestino + ", etiqueta=" + etiqueta
				+ "]";
	}

	@Override
	public boolean equals(Object o) {
		Arco<?> arco2 = (Arco<?>) o;
		if ((this.getVerticeOrigen() == arco2.getVerticeOrigen() && (this.getVerticeDestino() == arco2.getVerticeDestino()))) {
			return true;
		}else if((this.getVerticeOrigen() == arco2.getVerticeDestino() && (this.getVerticeDestino() == arco2.getVerticeOrigen()))) {
			return true;
		}
		return false;
	}
	 @Override
	    public int hashCode() {
	        return Objects.hash(verticeOrigen, verticeDestino);
	    }
}

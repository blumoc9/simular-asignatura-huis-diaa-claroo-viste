//Integrantes:
// Santana, Adriana CI: 18.801.197
// Paez, Maria CI: 19.618.874
// Arteaga, Luis CI: 19.696.160
// Colmenarez, Fernando CI: 18.923.926 
package Recurso;
public class Intervalo {
	
private int valor, minimo, maximo;

public Intervalo(int min, int val, int max) {
	if ((min <=val) && (val <= max)) {
		minimo = min; valor = val; maximo = max;
	}else {
		minimo = 0; valor = 12; maximo = 20;
	}
}
public void decrementar() { 
	setValor (getValor() - 1); 
	}
public boolean esDecrementable() {
	return (getValor()>getMinimo());
	}
public boolean esIncrementable() {
	return (getValor() < getMaximo()); 
	}
public int getMaximo() { 
	return maximo; 
	}
public int getMinimo() { 
	return minimo; 
	}
public int getValor() { 
	return valor;
	}
public void incrementar() { 
	setValor (getValor() + 1); 
	}
public boolean isValorCorrecto(int val) {
	return ((minimo <=val) && (val <= maximo)); 
	}
public void setValor(int val) { 
	valor=val; 
	}
}

//Integrantes:
// Santana, Adriana CI: 18.801.197
// Paez, Maira CI: 19.618.874
// Arteaga, Luis CI: 19.696.160
// Colmenarez, Fernando CI: 18.923.926
package Recurso;

public interface Agente {
	public void Recibir(Notificacion notificacion,Agente eminsor);
	public void Enviar(Notificacion notificacion,Agente receptor);
}

package Recurso;

public interface Agente {
	public void Recibir(Notificacion notificacion,Agente eminsor);
	public void Enviar(Notificacion notificacion,Agente receptor);
}

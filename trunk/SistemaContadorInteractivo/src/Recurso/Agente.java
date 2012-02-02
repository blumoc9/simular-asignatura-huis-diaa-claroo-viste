package Recurso;

public interface Agente {
	public void Recibir_Notificacion(Agente agente,Notificacion solicitud);
	public void Enviar_Notificacion(Agente agente,Notificacion solicitud);
}

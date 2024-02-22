import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TallerADT {
    class Persona {
        private static int contadorId = 0;
        private int id;
        private int tiempoLlegada;
        private int tiempoServicio;
        private int tiempoEspera;

        public Persona(int tiempoLlegada){
            this.id = contadorId++;
            this.tiempoLlegada = tiempoLlegada;
            this.tiempoServicio = new Random().nextInt(3300) + 300;
            this.tiempoEspera = 0;
        }

        public int getId(){
            return id;
        }

        public int getTiempoLlegada(){
            return tiempoLlegada;
        }
    
        public int getTiempoServicio(){
            return tiempoServicio;
        }
    
        public int getTiempoEspera(){
            return tiempoEspera;
        }
    
        public void setTiempoEspera(int tiempoEspera){
            this.tiempoEspera = tiempoEspera;
        }

        public String toString(){
            return "ID de Persona = " + id + "\n" + "Tiempo de Llegada = " + tiempoLlegada + "\n"  + "Tiempo de Servicio = " + tiempoServicio + "\n" + "Tiempo de Espera = " + tiempoEspera;
        }
        
    }

    class Agente {
        private static int contadorId = 0;
        private int id;
        private int tiempoOcupado;

        public Agente() {
            this.id = contadorId++;
            this.tiempoOcupado = 0;
        }

        public int getId() {
            return id;
        }

        public int getTiempoOcupado() {
            return tiempoOcupado;
        }
    
        public void aumentarTiempoOcupado(int tiempo) {
            this.tiempoOcupado += tiempo;
        }

        public String toString(){
            return "ID de Agente = " + id + "\n" + "Tiempo Ocupado = " + tiempoOcupado;
        }
    }

    class Fila{
        private List<Persona> personas;

        public Fila() {
            this.personas = new ArrayList<>();
        }

        public void agregarPersona(Persona persona) {
            personas.add(persona);
        }

        public Persona siguientePersona() {
            if (!personas.isEmpty()) {
                return personas.remove(0);
            }
            return null;
        }

        public String toString() {
            return "Fila de Personas=" + personas;
        }
    }
}

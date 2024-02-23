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

        public Persona(int id, int tiempoLlegada, int tiempoServicio) {
            this.id = id;
            this.tiempoLlegada = tiempoLlegada;
            this.tiempoServicio = tiempoServicio;
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
            return "Fila de Personas = " + personas;
        }
    }

    class SimuladorFilas{
        private int numAgentes;
        private Fila fila;
        private Agente[] agentes;
        private int tiempoMaximo;
        private double promedioOcupacion;
        private double promedioEspera;

        public SimuladorFilas(int numAgentes, Persona[] personas, int tiempoMaximo){
            this.numAgentes = numAgentes;
            this.fila = new Fila();
            this.agentes = new Agente[numAgentes];
            this.tiempoMaximo = tiempoMaximo;
            this.promedioOcupacion = 0.0;
            this.promedioEspera = 0.0;

            for(int i = 0; i < numAgentes; i++){
                agentes[i] = new Agente();
            }
            
            for(Persona persona: personas){
                fila.agregarPersona(persona);
            }
            
        }

        //Correr la simulacion
        public void correrSimulation(){
            int tiempo = 0;
            int totalTiempoEspera = 0;
            int totalTiempoOcupado = 0;
            int personasAtendidas = 0;

            while(tiempo < tiempoMaximo){

                for(Agente agente : agentes){

                    if(agente.getTiempoOcupado() == 0){
                        Persona persona = fila.siguientePersona();

                        if (persona != null){
                            persona.setTiempoEspera(tiempo - persona.getTiempoLlegada());
                            totalTiempoEspera += persona.getTiempoEspera();
                            agente.aumentarTiempoOcupado(persona.getTiempoServicio());
                            personasAtendidas++;
                        }
                    } else {
                        agente.aumentarTiempoOcupado(-1);
                        totalTiempoOcupado++;
                    }
                }
                tiempo++;
            }

            //Promedio de ocupacion
            promedioOcupacion = (totalTiempoOcupado / (double) (tiempoMaximo * numAgentes)) * 100;

            //Tiempo Promedio de Atencion a las personas
            if(personasAtendidas > 0){
                promedioEspera = totalTiempoEspera / (double) personasAtendidas;
            }

        }

        public double getPromedioOcupacion(){
            return promedioOcupacion;
        }

        public double getPromedioEspera(){
            return promedioEspera;
        }

    }
       
}





public class PruebasUnitarias {
    
    private static boolean igualdadDoubles(double a, double b) {
        return Math.abs(a - b) < 1E-8;
    }

    public static void testCase1() {
        TallerADT tallerADT = new TallerADT();
        TallerADT.Persona[] personas = { tallerADT.new Persona(0, 0, 5), tallerADT.new Persona(1, 5, 5) };
        TallerADT.SimuladorFilas sim = tallerADT.new SimuladorFilas(1, personas, 10);
        sim.correrSimulation();
        assert(igualdadDoubles(sim.getPromedioOcupacion(), 50.0));
        assert(igualdadDoubles(sim.getPromedioEspera(), 0.0));
    }
    
    public static void testCase2() {
        TallerADT tallerADT = new TallerADT();
        TallerADT.Persona[] personas = { tallerADT.new Persona(0, 0, 5), tallerADT.new Persona(1, 5, 5), tallerADT.new Persona(2, 6, 5), tallerADT.new Persona(3, 9, 5) };
        TallerADT.SimuladorFilas sim = tallerADT.new SimuladorFilas(2, personas, 10);
        sim.correrSimulation();
        assert(igualdadDoubles(sim.getPromedioOcupacion(), 50.0));
        assert(igualdadDoubles(sim.getPromedioEspera(), 3.5));
    }
    
    public static void testCase3() {
        TallerADT tallerADT = new TallerADT();
        TallerADT.Persona[] personas = { tallerADT.new Persona(0, 0, 5), tallerADT.new Persona(1, 5, 5), tallerADT.new Persona(2, 10, 5), tallerADT.new Persona(3, 15, 5) };
        TallerADT.SimuladorFilas sim = tallerADT.new SimuladorFilas(2, personas, 10);
        sim.correrSimulation();
        assert(igualdadDoubles(sim.getPromedioOcupacion(), 100.0));
        assert(igualdadDoubles(sim.getPromedioEspera(), 0.0));
    }
    
    public static void testCase4() {
        TallerADT tallerADT = new TallerADT();
        TallerADT.Persona[] personas = { 
            tallerADT.new Persona(0, 0, 5), tallerADT.new Persona(1, 5, 5), tallerADT.new Persona(2, 10, 5), tallerADT.new Persona(3, 15, 5),
            tallerADT.new Persona(4, 20, 5), tallerADT.new Persona(5, 25, 5), tallerADT.new Persona(6, 30, 5), tallerADT.new Persona(7, 35, 5),
            tallerADT.new Persona(8, 40, 5), tallerADT.new Persona(9, 45, 5), tallerADT.new Persona(10, 50, 5), tallerADT.new Persona(11, 55, 5)
        };
        TallerADT.SimuladorFilas sim = tallerADT.new SimuladorFilas(3, personas, 10);
        sim.correrSimulation();
        assert(igualdadDoubles(sim.getPromedioOcupacion(), 100.0));
        assert(igualdadDoubles(sim.getPromedioEspera(), 0.0));
    }
    
    public static void testCase5() {
        TallerADT tallerADT = new TallerADT();
        TallerADT.Persona[] personas = { 
            tallerADT.new Persona(0, 0, 5), tallerADT.new Persona(1, 5, 5), tallerADT.new Persona(2, 10, 5), tallerADT.new Persona(3, 15, 5),
            tallerADT.new Persona(4, 20, 5), tallerADT.new Persona(5, 25, 5), tallerADT.new Persona(6, 30, 5), tallerADT.new Persona(7, 35, 5),
            tallerADT.new Persona(8, 40, 5), tallerADT.new Persona(9, 45, 5), tallerADT.new Persona(10, 50, 5), tallerADT.new Persona(11, 55, 5)
        };
        TallerADT.SimuladorFilas sim = tallerADT.new SimuladorFilas(1, personas, 10);
        sim.correrSimulation();
        assert(igualdadDoubles(sim.getPromedioOcupacion(), 100.0));
        assert(igualdadDoubles(sim.getPromedioEspera(), 0.0));
    }
    
    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        System.out.println("Pruebas unitarias completadas con exito. (AHUEVOOO)");
    }
}

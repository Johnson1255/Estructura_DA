public class Comparar {

    class Fecha implements Comparable<Fecha>{
        int dia, mes, ano;

        public int compareTo(Fecha f){
            if(ano != f.ano) return ano - f.ano;
            if(mes != f.mes) return mes - f.mes;
            if(dia != f.dia) return dia - f.dia;
            return 0;
        }
    }
}

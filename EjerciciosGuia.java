public class EjerciciosGuia {
    
    public static boolean esMatrizDiagonal(double[][] a){
        if (a.length != a[0].length) return false;
        boolean esDiagonal = true;
        for(int i = 0; i < a.length; i++)
            for(int j = 0; j < a[0].length; j++;)
                if(i != j && a[i][j] != 0){
                    esDiagonal = false;
                    break;
                }
        return esDiagonal;
    }
    
}

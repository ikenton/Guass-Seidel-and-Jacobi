import java.util.Scanner;

public class Project2{
    public static void main(String[] args){

    }

    private void gaussSeidel(){
        /*GaussSeidel(A,b,x) 
         *  kmax = 100
         *  sigma = 10^-10
         *  e = 0.5 * 10^-4
         * 
         * int i, j, k, kmax, n;
         * real diag, sum (arrays?)
         * n = size(A)
         * 
         * for(k = 1 to kmax){
         *  y = x
         *  for(i = 1 to n){         1 might be 0? 
            *  sum = b[i]
            *  diag = a[i][i]
                if |diag| < sigma {
                    the diagonal element is too small
                    return
                }
                for(j = 1 to i-1){
                    sum = sum - (a[i][j] * x[j]);
                }
                for(j = i + 1 to n){
                    sum = sum - (a[i][j]*x[j]);
                }
                x[j] = sum/diag;
            }
         * 
            * output k, x
            * if( |x - y| < e){
            *  output k,x
            *  return
            * }
         * }
         * output max iterations 
        */
    }

    private void jacobi(){
        //psuedocode
        /*Jacobi(A,b,x) 
         *  kmax = 100
         *  sigma = 10^-10
         *  e = 0.5 * 10^-4
         * 
         * int i, j, k, kmax, n;
         * real diag, sum (arrays?)
         * n = size(A)
         * 
         * for(k = 1 to kmax){
         *  y = x
         *  for(i = 1 to n){         1 might be 0? 
            *  sum = b[i]
            *  diag = a[i][i]
                if |diag| < sigma {
                    the diagonal element is too small
                    return
                }
                for(j = 1 to n){
                    if(j != i){
                        sum = sum - (a[i][j]*y[j]);
                    }
                }
                x[j] = sum/diag;
            }
         * 
            * output k, x
            * if( |x - y| < e){
            *  output k,x
            *  return
            * }
         * }
         * output max iterations 
        */
    }

    public static void readFile(){

    }
}

import java.util.Scanner;

public class Project2{
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        int number;
        int response = 0;
        double b[], x[], y[]; 
        double equations[][];

        //ask for user input
        System.out.println("Please enter the number of linear equations.");
        number = kb.nextInt();
        
        while(number > 10 || number <= 1){
            System.out.println("The number you have entered is out of bounds. Please enter a number that is more than 1 and less then or equal to 10");
            number = kb.nextInt();
            System.out.println(number);
        }

        
        System.out.println("Please enter the amount of linear equations.");
        
        while(number > 10 || number <= 1){
            System.out.println("The number you have entered is out of bounds. Please enter a number that is more than 1 and less then or equal to 10");
            number = kb.nextInt();
            System.out.println(number);
        }

        b  = new double[number];
        
        equations = new double[number][4]; //Aij
        x = new double[number];
        y = new double[number]; //not sure if i need this yet


        do{
            System.out.println("Press 1 to enter a file. Press 2 to enter the equation by hand");
            response = kb.nextInt();
        }while(response != 1 || response != 2);

        //load the equations onto array A
        
        guassSeidel(equations, b, x); //error?
        jacobi(equations, b, x);
       
        kb.close();
    }

    public static void gaussSeidel(double[][] equations, double[] b, double[] x){
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

    private void jacobi(double[][] equations, double[] b, double[] x){
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

    public static void loadCoef(int option, double array[][]){

    }
}

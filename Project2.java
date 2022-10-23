import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Project2{
    public static void main(String[] args)throws FileNotFoundException{
        Scanner kb = new Scanner(System.in);
        int number;
        int response = 0;
        double b[], x[]; 
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


        do{
            System.out.println("Press 1 to enter a file. Press 2 to enter the equation by hand");
            response = kb.nextInt();
            System.out.println("response: " + response);
        }while(response != 2 && response != 1); //doesnt take the or condition

        //load the equations onto array A
        loadCoef(response, equations, b);
        for(int i = 0; i < number; i++){
            for(int j = 0; j<4;j++){
                System.out.print(equations[i][j]+" ");
            }
            System.out.println();
        }
        
        //guassSeidel(equations, b, x); 
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

    private static void jacobi(double[][] equations, double[] b, double[] x){
        Scanner kb = new Scanner(System.in);
        System.out.println("--JACOBI START--");
        //psuedocode
        //x is the solution, A is the matrix, b is the vector
        double kmax = 50; //iteration cap
        double delta;
        double e; //desired error
        double temp;
        int i, j, k, n;
        double diag, sum;
        n = equations.length;
        double y[] = new double[n]; //contains the old iterative values


        //ask for desired stopping error
        System.out.println("What is your desired stopping error?"); 
        e = kb.nextDouble();
        
        System.out.println("Please enter your starting guess: ");//x1 = 0 x2 =0 x3 = 0
        for(int p = 0; p < n; p++){
            x[p] = kb.nextDouble();
        }


        for(k = 0; k < kmax; k++){
            y[k] = x[k];
            for(i = 0; i < n; i++){       
                sum = b[i];
                diag = equations[i][i];
                if( Math.abs(diag)< delta ){
                    System.out.println("The diagonal element is too small");
                    return;
                }
                for(j = 0; j < n; j++){
                    if(j != i){
                        sum = sum - (equations[i][j]*y[j]);
                    }
                }
                x[j] = sum/diag;
            }
          
             //output k, x
             if( Math.abs(x[k]-y[k]) < e){
              //output k,x
              return;
            }
            System.out.println("x values: ");
            for(int p = 0; p < 4; p++){
                System.out.println(x[p]);
            }
         }
         System.out.println("Max iterations reached");
        kb.close();
    }

    public static void loadCoef(int option, double array[][], double b[]) throws FileNotFoundException{
        Scanner kb = new Scanner(System.in);
        Scanner read;
        String filePath;
        int number = array.length;

        if(option == 1){
            //file reader
            System.out.println("Please enter the file path: ");
            //set up file reader
            //kb.nextLine();

            filePath = kb.nextLine();
            System.out.println("File path: "+filePath);
            File file = new File(filePath);
            read = new Scanner(file);
            
            //go through file and pick out each int and put it in the array
            ArrayList<Double> tempList = new ArrayList<Double>();
            int count = 0;
            while(read.hasNext()){
                if(read.hasNextDouble()){
                    tempList.add(read.nextDouble());
                    //System.out.println("running :)");
                    System.out.println(tempList.get(count));
                    count++;
                }
                if(read.hasNext() && !read.hasNextDouble()){
                    read.next();
                }
            }

            //list -> array
            for(int i = 0; i < number; i++){
                for(int j = 0; j < 4; j++){
                    array[i][j] = tempList.get(0);
                    tempList.remove(0);
                    if(j == 3){
                        b[i]= array[i][3];
                    }
                }
                System.out.println();
                
                
            }
        }
        else{
            for(int i = 0; i < number; i++){
                System.out.println("\nPlease enter 4 coefficients for equation "+(i+1));
                for(int j = 0; j < 4; j++){
                    
                    array[i][j] = kb.nextInt();
                    if(j == 3){
                        b[i]= array[i][3];
                    }
                }
            }
        }
        kb.close();
    }

    public void checkDiagonal(){
        
    }
}

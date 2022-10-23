import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Project2{
    public static void main(String[] args)throws FileNotFoundException{
        Scanner kb = new Scanner(System.in);
        int number;
        int response = 0;
        double b[], /*xJacobi[],*/ xGuass[]; 
        double equations[][];
        double xJacobi[] = {0,0,0};
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
        
        equations = new double[number][number]; //Aij size nxn
        xJacobi = new double[number];
        xGuass = new double[number];

        do{
            System.out.println("Press 1 to enter a file. Press 2 to enter the equation by hand");
            response = kb.nextInt();
            System.out.println("response: " + response);
        }while(response != 2 && response != 1); //doesnt take the or condition

        //load the equations onto array A
        loadCoef(response, equations, b);
        for(int i = 0; i < number; i++){
            for(int j = 0; j < number;j++){
                System.out.print(equations[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("Please enter your desired stopping error: ");
        double e = kb.nextDouble(); //epsilon
        
        System.out.println("Please enter your "+ number+ " starting guesses: ");//x1 = 0 x2 =0 x3 = 0
        for(int i = 0; i < number; i++){
            xJacobi[i] = kb.nextDouble();
        }
        
        //guassSeidel(equations, b, xGuass); 
        jacobi(equations, b, xJacobi, e);  
       
        kb.close();
    }

    private static void jacobi(double[][] equations, double[] b, double[] x, double e){
        Scanner kb = new Scanner(System.in);
        System.out.println("--JACOBI START--");
        //psuedocode
        //x is the solution, A is the matrix, b is the vector
        double kmax = 50; //iteration cap
        double delta = Math.pow(10, -10);
        //double e = 0.0001; //desired error epsilon
        int i, j, k, n;
        double diag, sum;
        n = equations.length;
        double norm = 0; //sqrt(sum from 1 to k of (x-y)^2)
        double y[] = new double[n]; //contains the old iterative values
    
        /*
        for(int p = 0; p < n; p++){
            x[p] = kb.nextDouble();
        }
        kb.close();*/
        
        for(k = 0; k < kmax; k++){
            norm = 0;
            for(int p = 0; p < n; p++){
                y[p] = x[p];
            }

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
                x[i] = sum/diag;
            }
          
            
            
            //output k (number of iteration) and x which is the values of our iterations
            System.out.print("Iteration: "+(k+1)+" [ ");
            for(int p = 0; p < n; p++){
                System.out.printf("%.4f ",x[p]);
            }
            System.out.println(" ]T");
            //find L2 sqrt(norm)
            //Sum k=1 -> n |x-y|^2
            for(int p = 0; p < n; p++){
                //absolute value doesnt matter because we are just squaring it 
                norm += Math.pow(x[p]-y[p],2);
            }
            System.out.println("L2: "+Math.sqrt(norm));
            if(Math.sqrt(norm) < e){
                System.out.println("Solution: ");
                System.out.print("Iteration: "+(k+1)+"  [ ");
                for(int p = 0; p < n; p++){
                    System.out.printf("%.4f ",x[p]);
                }
                System.out.println(" ]T ");
                System.out.println("L2: "+Math.sqrt(norm));
                return;
            }
            
         }
         System.out.println("Max iterations reached");
        
    }

    /*public static void guassSeidel(double[][] equations, double[] b, double[] xGuass, double e){
        Scanner kb = new Scanner(System.in);
        System.out.println("--Guass-Seidel--");
        //psuedocode
        //x is the solution, A is the matrix, b is the vector
        double kmax = 50; //iteration cap
        double delta = Math.pow(10, -10);
        //double e = 0.0001; //desired error epsilon
        int i, j, k, n;
        double diag, sum;
        n = equations.length;
        double norm = 0; //sqrt(sum from 1 to k of (x-y)^2)
        double y[] = new double[n]; //contains the old iterative values
    
        //ask for desired stopping error
        /*System.out.println("What is your desired stopping error?"); 
        e = kb.nextDouble();
        
        System.out.println("Please enter your starting guess: ");//x1 = 0 x2 =0 x3 = 0
        for(int p = 0; p < n; p++){
            x[p] = kb.nextDouble();
        }
        kb.close();*/
        
        /*for(k = 0; k < kmax; k++){
            norm = 0;
            for(int p = 0; p < n; p++){
                y[p] = xGuass[p];
            }

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
                xGuass[i] = sum/diag;
            }
          
            
            
            //output k (number of iteration) and x which is the values of our iterations
            System.out.print("Iteration: "+(k+1)+" [ ");
            for(int p = 0; p < n; p++){
                System.out.printf("%.4f ",xGuass[p]);
            }
            System.out.println(" ]T");
            //find L2 sqrt(norm)
            //Sum k=1 -> n |x-y|^2
            for(int p = 0; p < n; p++){
                //absolute value doesnt matter because we are just squaring it 
                norm += Math.pow(xGuass[p]-y[p],2);
            }
            System.out.println("L2: "+Math.sqrt(norm));
            if(Math.sqrt(norm) < e){
                System.out.println("Solution: ");
                System.out.print("Iteration: "+(k+1)+"  [ ");
                for(int p = 0; p < n; p++){
                    System.out.printf("%.4f ",xGuass[p]);
                }
                System.out.println(" ]T ");
                System.out.println("L2: "+Math.sqrt(norm));
                return;
            }
            
         }
         System.out.println("Max iterations reached");
        
    }*/

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
                    //System.out.println(tempList.get(count));
                    count++;
                }
                if(read.hasNext() && !read.hasNextDouble()){
                    read.next();
                }
            }

            //list -> array
            for(int i = 0; i < number; i++){
                for(int j = 0; j < number+1; j++){
                    if(j == number){
                        
                        b[i]= tempList.get(0);
                        tempList.remove(0);
                        
                    }else{
                        array[i][j] = tempList.get(0);
                        tempList.remove(0);
                    }
                }
            }
        }
        else{
            for(int i = 0; i < number; i++){
                System.out.println("\nPlease enter 4 coefficients for equation "+(i+1));
                for(int j = 0; j < number+1; j++){
                    
                    if(j == number){
                        b[i]= array[i][number];
                    }else{
                        array[i][j] = kb.nextDouble();
                    }
                }
            }
            
        }
        if(!checkDiagonal(array)){
            System.out.println("ERROR: The coefficients you have entered are not diagonally dominant");
            checkDiagonal(array);
        }
        
    }

    public static boolean checkDiagonal(double[][] coefficients){
        System.out.println("Checking...");
        double sum = 0;
        int length = coefficients.length;
        double diagVal = coefficients[0][0];
        
        for(int i = 0; i < length; i++){
            sum = 0;
            diagVal = coefficients[i][i];
            for(int j = 0; j < length; j++){
                //any value that is not a diagonal value is going into the sum
                if(i != j){ 
                    sum += coefficients[i][j];
                }
            }
            if(diagVal < Math.abs(sum)){
                return false;
            }

        }
        return true;
    }
}

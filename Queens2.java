import java.lang.Math;
import java.util.*;


/* YOU NEED TO ADD YOUR CODE TO THIS CLASS, REMOVING ALL DUMMY CODE
 *
 * DO NOT CHANGE THE NAME OR SIGNATURE OF ANY OF THE EXISTING METHODS
 * (Signature includes parameter types, return types and being static)
 *
 * You can add private methods to this class if it makes your code cleaner,
 * but this class MUST work with the UNMODIFIED Tester2.java class.
 *
 * This is the ONLY class that you can submit for your assignment.
 *
 * MH 2020
 */
public class Queens2
{
    private static int boardSize = 10;
    private static Random rand= new Random();
    
    // inverts the order of a series of genes in the genotype
    public static Integer[] inversionMutate(Integer[] genotype, double p)
    {
        
        int i= rand.nextInt(10);
        int j= rand.nextInt(10);
        int temp;
        if (i>j){
            temp=i;
            i=j;
            j=temp;
        }

        temp=0;
        
        if(rand.nextDouble() <= p){
        for(i=i, j=j; i<j && j>i; i++, j--){
            temp=genotype[i];
            genotype[i]= genotype[j];
            genotype[j]= temp;
        }
    }

        
        return genotype;
    }
    
    /* performs fitness-proportional parent selection
     * also known as 'roulette wheel' selection
     * selects two parents that are different to each other
     */
    public static Integer[][] rouletteSelect(Integer[][] population)
    {
        Integer [][] parents = new Integer [2][boardSize];
        Integer [] fitnesses= new Integer[5];

        fitnesses[0] = Queens.measureFitness(population[0]);
        fitnesses[1] = Queens.measureFitness(population[1]);
        fitnesses[2] = Queens.measureFitness(population[2]);
        fitnesses[3]  = Queens.measureFitness(population[3]);
        fitnesses[4]  = Queens.measureFitness(population[4]);
        
        int sum=0;

        for(int i=0;i<5 ; i++){
            sum+=fitnesses[i];

        }

        int i= rand.nextInt(sum);
        int j= rand.nextInt(sum);

        int first= fitnesses[0];
        int second= first + fitnesses[1];
        int third= second + fitnesses[2];
        int fourth= third + fitnesses[3];
        int fifth= fourth + fitnesses[4];

        int x=0;
        int y=0;

        if (i <= first){
            x=0;
        }

        if (j <= first){
            y=0;
        }
        
        if(i>first && i<= second){
            x=1;
        }
        
        if(j>first && j<= second){
            y=1;
        }

        if(i>second && i<= third){
            x=2;
        }
        
        if(j>second && j<= third){
            y=2;
        }

        if(i>third && i<= fourth){
            x=3;
        }
        
        if(j>third && j<=fourth ){
            y=3;
        }

        if(i>fourth && i<= fifth){
            x=4;
        }
        
        if(j>fourth && j<= fifth){
            y=4;
        }

        
        parents[0]= population[x];
        parents[1]= population[y];

        return parents;


    }
    
    /* creates a new population through λ + μ survivor selection
     * given a population of size n, and a set of children of size m
     * this method will measure the fitness of all individual in the
     * combined population, and return the n fittest individuals
     * as the new population
     */
    public static Integer[][] survivorSelection(Integer[][] population, Integer [][] children)
    {
        Integer [][] newPop = new Integer [10][10];
        
        int [] fitness= new int[30];

        for (int i=0; i<10; i++){
            fitness[i]= Queens.measureFitness(population[i]);

        }

        for(int i=10,j=0; i<30 && j<20; i++,j++){
            fitness[i]= Queens.measureFitness(children[j]);
        }

        for(int i=0; i<10; i++){
            int highest=0;
            int max=0;
            for (int j=0; j<30; j++){
               
                if(fitness[j]>max){
                    
                    max=fitness[j];
                   
                    highest= j;

                }
            }
           
            if(highest<10){
                newPop[i]= population[highest];
                fitness[highest]=0;
              
            }

            else if(highest>=10){
                int childrenHighest=highest-10;
                newPop[i]= children[childrenHighest];
                fitness[highest]=0;
                
            }

        }
        



        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
       /* newPop [0] = new Integer[]{ 10, 6, 4, 2, 8, 5, 9, 1, 3, 7 };
        newPop [1] = new Integer[]{ 9, 4, 3, 1, 2, 5, 10, 7, 8, 6 };
        newPop [2] = new Integer[]{ 9, 4, 3, 1, 2, 5, 10, 7, 8, 6 };
        newPop [3] = new Integer[]{ 9, 5, 6, 10, 8, 7, 1, 3, 2, 4 };
        newPop [4] = new Integer[]{ 9, 5, 6, 10, 8, 7, 1, 3, 2, 4 };
        newPop [5] = new Integer[]{ 3, 2, 7, 4, 10, 1, 8, 9, 6, 5 };
        newPop [6] = new Integer[]{ 10, 9, 8, 6, 7, 2, 3, 4, 1, 5 };
        newPop [7] = new Integer[]{ 7, 8, 9, 1, 10, 2, 3, 4, 5, 6 };
        newPop [8] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        newPop [9] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        */
        
        // END OF YOUR CODE

        
        return newPop;
    }
    
    // counts the number of unique genotypes in the population
    public static int genoDiversity(Integer[][] population)
    {
        int uniqueTypes = 0;
        int count=0;

        for(int i=0; i<10; i++){
            for(int j=i+1; j<10; j++){
                if(Arrays.equals(population[i],population[j])){
                    count++;
                    break;   
                }
            }
            
        }

        
        uniqueTypes= population.length - count;

        

        
        return uniqueTypes;
    }
}

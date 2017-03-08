/*
 *
 * Project 2
 * Evolutionary Computing / UAA / Spring 2017
 * Dr. Moore
 *
 * Author     : Jacob See (jdsee@alaska.edu)
 *						: Neil Reutov (nreutov3@alaska.edu)
 *
 */

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.PrintWriter;
import java.util.Scanner;

public class FunctionMinimizer{

  public double overallLearningRate(float n){
    return 1.0 / Math.sqrt(2 * n);
  }

  public double coordinateLearningRate(float n){
    return 1.0 / Math.sqrt(2 * Math.sqrt(n));
  }

  public static void main(String args[]){
    //Set up our two strings from which to evolve the LCS.

    //Create and initialize a random population of candidates

    int numParents = 3;
    int numOffspring = 21;
    int terminationCondition = 10000;
    Random r = new Random();

    ArrayList<Candidate> parents = new ArrayList<Candidate>();

    for(int i = 0; i < numParents; i++){
      parents.add(new Candidate(r));
    }

    System.out.println("We are attempting to maximize fitness within "+terminationCondition+" iterations.");

    System.out.println("\nGeneration\t|\tAvg. Fitness\t|\tHigh Fitness");
    System.out.println("----------------------------------------------");

    // Begin evolution

    int generation = 0;
    ArrayList<Double> generationHistory = new ArrayList<Double>();

    try( PrintWriter out = new PrintWriter("results.csv") ){

      while(generation < terminationCondition){

        generation++;

        int fitnessSum = 0;
        double probabilitySum = 0;
        double highestFitness = 0;

        // Need to compute all fitnesses and the sum of all fitnesses for roulette selection. Also check for the perfect candidate.

        for(int i = 0; i < numParents; i++){
          parents.get(i).calculateFitness();
          fitnessSum += parents.get(i).fitness;
          if(parents.get(i).fitness > highestFitness){
            highestFitness = parents.get(i).fitness;
          }
        }

        System.out.println(generation + "\t\t|\t" + String.format("%.2f", (float)fitnessSum / numParents) + " \t\t|\t" + highestFitness);

        generationHistory.add((double)fitnessSum / numParents);

        // Set the population up for roulette selection.

        ArrayList<Candidate> children = new ArrayList<Candidate>();

        /*for(int i = 0; i < populationSize; i++){
          parents.get(i).rouletteSlice = probabilitySum + ((double)temp.get(i).fitness / fitnessSum);
          probabilitySum = parents.get(i).rouletteSlice;
        }*/

        // Begin spawning next generation of candidates

        while(children.size() < numOffspring){

          // Begin roulette selection

          /*Candidate selectedFirst = null;
          Candidate selectedSecond = null;
          while(selectedFirst == null || selectedSecond == null){
            double rouletteBall = ThreadLocalRandom.current().nextDouble(1);
            for(int i = 0; i < populationSize; i++){
              if((i > 0 && (rouletteBall < parents.get(i).rouletteSlice && rouletteBall > parents.get(i - 1).rouletteSlice)) || (i < 0 && (rouletteBall < parents.get(i).rouletteSlice))) {
                if(selectedFirst == null){
                  selectedFirst = parents.get(i);
                }else{
                  selectedSecond = parents.get(i);
                }
              }
            }
          }*/

          // It has been recommended to use discrete recombination on the control parameters and intermediary recombination on the strategy parameters.


          // Begin recombination of selected candidates to create child candidate

          Integer.toString(ThreadLocalRandom.current().nextInt(0, numParents + 1)/numParents);

          //int slicePoint = ThreadLocalRandom.current().nextInt(1, bitLength); //Crossover at least 1 char at the very beginning or very end, never before or after the entire bitstring
          //String newBitstring1 = selectedFirst.bitstring.substring(0, slicePoint) + selectedSecond.bitstring.substring(slicePoint);
					//String newBitstring2 = selectedSecond.bitstring.substring(0, slicePoint) + selectedFirst.bitstring.substring(slicePoint);


          // Begin mutation of child candidate

          /*String bitStringCopy = "";
          for(int i = 0; i < selectedFirst.bitstring.length(); i++){
            if(ThreadLocalRandom.current().nextDouble(1) < bitMutationRate){
              bitStringCopy += selectedFirst.bitstring.charAt(i) == '0' ? '1' : '0';
            }else{
              bitStringCopy += selectedFirst.bitstring.charAt(i);
            }
          }
          String newBitstring = bitStringCopy;

					if(Collections.frequency(usedBitstrings, newBitstring) < 200){ // Limit a single bitstring to 200 members of the next generation, to encourage diversity.
						population.add(new Candidate(newBitstring));
						usedBitstrings.add(newBitstring);
					}*/


        }

        parents.clear();

        //Best children turn into parents here

        children.clear();

        probabilitySum = 0;
        fitnessSum = 0;

      }

    }
    catch(Exception e){
      System.out.println("There was a problem writing output data. Exiting.");
      System.exit(0);
    }

    // Let's actually figure out what that bitstring represents.

    //System.out.println("\nPerfect candidate " + perfectCandidate.bitstring + " found in generation " + generation + " with a fitness of " + perfectCandidate.fitness + " yields LCS '" + perfectCandidate.toString(testStringA.length() < testStringB.length() ? testStringA : testStringB) + "'.\n");

  }

}

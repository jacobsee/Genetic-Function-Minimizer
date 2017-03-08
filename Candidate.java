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

import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class Candidate{

  public Random r;
  public double x_1 = 0;
  public double x_2 = 0;
  public double sigma_1 = 1;
  public double sigma_2 = 1;
  public double fitness = 0;
  public double rouletteSlice = 0;

  public Candidate(Random rnd){
    r = rnd;
    x_1 = r.nextGaussian();
    x_2 = r.nextGaussian();
  }

  public Candidate(Random rnd, double x1, double x2, double sigma1, double sigma2){
    r = rnd;
    x_1 = x1;
    x_2 = x2;
    sigma_1 = sigma1;
    sigma_2 = sigma2;
  }

  public Candidate clone(){
    Candidate c = new Candidate(r, x_1, x_2, sigma_1, sigma_2);
    return c;
  }

  // Set the fitness value for this candidate.

  public void calculateFitness(){
    fitness = 21.5 + (x_1 * Math.sin(4 * Math.PI * x_1)) + (x_2 * Math.sin(20 * Math.PI * x_2));
  }

}

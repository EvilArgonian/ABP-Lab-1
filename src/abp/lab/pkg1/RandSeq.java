/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.lab.pkg1;

import java.util.Random;

/**
 *
 * @author pmele
 */
public class RandSeq {
    private String seq;

    public RandSeq(int length) throws Exception {
        double[] probWeight = new double[] {.25, .25, .25, .25};
        this.seq = generateSeq(length, probWeight);
        
    }

    public RandSeq(int length, double[] probWeight) throws Exception {
        this.seq = generateSeq(length, probWeight);
    }
    
    public RandSeq(int length, int[] probWeight) throws Exception {
        double[] probWeightAsDoubles = new double[4];
        for (int i = 0; i < probWeight.length; i++) {
            probWeightAsDoubles[i] = probWeight[i];
        }
        this.seq = generateSeq(length, probWeightAsDoubles);
    }
    
    private String generateSeq(int length, double[] probWeight) throws Exception {
        if (length < 1) {
            throw new Exception("Length must be above 0.");
        }
        if (probWeight.length != 4) {
            throw new Exception("Must include exactly 4 probability weights.");
        }
        
        double probSum = 0.0;
        double[] probBounds = new double[4];
        for (int i = 0; i < 4; i++) {
            if (probWeight[i] < 0) {
                probWeight[i] = 0;
            }
            probSum += probWeight[i];
            probBounds[i] = probSum;
        }
        String sequence = "";
        Random rand = new Random();
        
        for (int i = 0; i < length; i++){
            double key = rand.nextDouble()*probSum;
            if (key < probBounds[0]) {
                sequence += "A";
            } else if (key < probBounds[1]) {
                sequence += "C";
            } else if (key < probBounds[2]) {
                sequence += "G";
            } else if (key < probBounds[3]) {
                sequence += "T";
            } else {
                throw new Exception("Probability issue.");
            }
        }
        
        return sequence;
    }
    
    public String toString() {
        return seq;
    }
    
}

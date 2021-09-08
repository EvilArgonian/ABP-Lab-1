/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.lab.pkg1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author pmele
 */
public class ABPLab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        int length = 3; //3-mers
        double[] weights = {.25, .25, .25, .25}; // A, C, G, T evenly distributed
        
        HashMap<String, Integer> seqTracker = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            String seq = new RandSeq(length, weights).toString();
            if (seqTracker.containsKey(seq)) {
                seqTracker.put(seq, seqTracker.get(seq) + 1);
            } else {
                seqTracker.put(seq, 1);
            }
        }
        
        if (seqTracker.containsKey("AAA")) {
            System.out.println("AAA generated: " + seqTracker.get("AAA") + "; Expected roughly 15.625");
        } else {
            System.out.println("AAA generated: 0; Expected roughly 15.625");
        }
                
        //Q: How often would you expect to see this 3mer by chance?  Is Java’s number close to the number that you would expect?
        //A: Very close to .25*.25*.25*1000, or 15.625, times. 
        //Several runs of this code have produced results in the range of 10 to 25, which seems reasonably close.

        weights[0] = .12; // A, C, G, T not evenly distributed
        weights[1] = .38;
        weights[2] = .39;
        weights[3] = .11;
        
        seqTracker = new HashMap<>(); // Reset tracker
        for (int i = 0; i < 1000; i++) {
            String seq = new RandSeq(length, weights).toString();
            if (seqTracker.containsKey(seq)) {
                seqTracker.put(seq, seqTracker.get(seq) + 1);
            } else {
                seqTracker.put(seq, 1);
            }
        }
        
        if (seqTracker.containsKey("AAA")) {
            System.out.println("AAA generated: " + seqTracker.get("AAA") + "; Expected roughly 1.728");
        } else {
            System.out.println("AAA generated: 0; Expected roughly 1.728");
        }
                
        //Q: What is the expected frequency now of “AAA”?  Does Java produce “AAA” at close to the expected frequency?
        //A: Very close to .12*.12*.12*1000, or 1.728, functionally 1 or 2 times. 
        //Several runs of this code have produced results in the range of 0 and 3, which again seems reasonably close.
        
        // Extra credit
        
        weights[0] = .12; // A, C, G, T not evenly distributed
        weights[1] = .38;
        weights[2] = .39;
        weights[3] = .11;
        
        ArrayList<HashMap> stats = new ArrayList<>();
        /*
        for (int run = 0; run < 10000; run++) {
            
            seqTracker = new HashMap<>(); //Reset seqTracker
            for (int i = 0; i < 10000; i++) {
                String seq = new RandSeq(length, weights).toString();
                if (seqTracker.containsKey(seq)) {
                    seqTracker.put(seq, seqTracker.get(seq) + 1);
                } else {
                    seqTracker.put(seq, 1);
                }
            }
            
            stats.add(seqTracker);
            
            /*
            if (seqTracker.containsKey("AAA")) {
                stats.add(seqTracker.get("AAA"));
            } else {
                stats.add(0);
            }*/
        //}
        
        HashMap<String, Double> expectedFreqs = new HashMap<>();
        for (int p1 = 0; p1 < 4; p1++) {
            for (int p2 = 0; p2 < 4; p2++) {
                for (int p3 = 0; p3 < 4; p3++) {
                    
                    String seq = "";
                    for (int pos : new int[]{p1, p2, p3}) { //Constructs sequence
                        switch(pos){
                            case (0): seq += "A";
                                break;
                            case (1): seq += "C";
                                break;
                            case (2): seq += "G";
                                break;
                            case (3): seq += "T";
                                break;
                        }
                    }
                    
                    double freq = 10000*weights[p1]*weights[p2]*weights[p3];
                    expectedFreqs.put(seq, freq);
                }
            }
        }
        for (String key : expectedFreqs.keySet()) {
            System.out.println("Expecting " + expectedFreqs.get(key) + " occurrences of " + key);
        }
        
        
        
        //double chiSquareValue= 0.0; //Get real value
        
        //ChiSquareUtils.pochisq(chiSquareValue, ((10000-1)*(4^3-1)));
    }
}

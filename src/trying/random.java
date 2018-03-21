/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trying;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author NAYA
 */
public class random {
    static ArrayList<Integer> picNames = new ArrayList<>();
    static int giveRandBack(){
    for(int i = 1 ; i < 11; ++i){
           picNames.add(i);
     }
    Collections.shuffle(picNames);
    return picNames.get(0);
    }
}

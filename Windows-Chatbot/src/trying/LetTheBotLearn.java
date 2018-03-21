/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trying;
import java.util.ArrayList;
import java.util.Scanner;

import static trying.Trying.findMatch;
/**
 *
 * @author NAYA
 */
public class LetTheBotLearn {
    // declarations
    static String keyword = "";
    static String reply = "";
    final  static ArrayList<String> UserMadekeyword = new ArrayList<>() ;
    final  static ArrayList<String> UserMadeResponse = new ArrayList<>();
    
    
    // Function to add the user given data base
    public static void addDataBase(String keyword)
        {
            UserMadekeyword.add(keyword);
            //return UserMadekeyword;
       
        }
    public static void addDataBaseResponse(String UserAddedResponse)
        {
            
            UserMadeResponse.add(UserAddedResponse);
            //return UserMadeResponse;
       
        }
    
    //if keyword not found this function is called
    static void EnterKeyWord(){
        Scanner in = new Scanner(System.in);
        boolean resp = true;
        while(resp){
            System.out.print(">> ENTER A KEYWORD :: ");
            keyword = in.nextLine();
            // to check whether the user wrote correct keyword
            System.out.println(">> SO THE KEYWORD IS :: " + keyword);
            reply = in.nextLine();
            // if the reply is false
            if(reply.toLowerCase().contains("no")){
                continue;
            }
            else{ 
                  if(findMatch(keyword).isEmpty()){
                    resp = false;
                   }
                  else{
                      System.out.println("THIS KEYWORD ALREADY EXIST");
                      continue;
                  }
                 boolean temp = true;
                 while(temp){
                    System.out.print(">> THE RESPONSE IS :: ");
                    String UserAddedResponse = in.nextLine();
                    // to check whether the user wrote correct keyword
                    System.out.println(">> SO THE RESPONSE IS ::" + UserAddedResponse);
                    String reply1 = in.nextLine();
                    // if the reply is false
                    if(reply1.toLowerCase().contains("no")){
                       continue;
                    }
                    else{
                        //caliing function for  adding user given database
                        
                        addDataBase(keyword);
                        addDataBaseResponse(UserAddedResponse);
                        System.out.println(UserMadekeyword + " " + UserMadeResponse);
                        temp = false;
                    }
                    
                    
                    
                
               
            }
        }
        
    }

}
}

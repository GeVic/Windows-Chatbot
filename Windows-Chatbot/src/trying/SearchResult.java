/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trying;

/**
 *
 * @author NAYA
 */
public class SearchResult extends FileLocation {
    
    static void ChromeResult(String sinput){
    String UserQuery = sinput;
    UserQuery = UserQuery.replace(' ', '+');
    try {
        Runtime.getRuntime().exec(new String[]{"cmd","/c","start chrome https://www.google.com/search?q="+UserQuery});
    }
    catch(Exception e){System.out.println("SOMETHING WENT WRONG");}
    
    } 
}

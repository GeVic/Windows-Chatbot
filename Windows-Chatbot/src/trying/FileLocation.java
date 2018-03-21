
package trying;

import java.util.*;
import java.io.File;



// class to search for file location in given directory
public class FileLocation 
{   
    static String FilePath = "";
    static  String findFile(String name,File file)
    {   
        File[] list = file.listFiles();
        
        if(list!=null)
        for (File fil : list)
        {   
           // System.out.println(fil);
            if (fil.isDirectory())
            {
                findFile(name,fil);
            }
            else if (name.equalsIgnoreCase(fil.getName()))
            {  
                fil.getParentFile();
                FilePath = fil.toString();
            }
        }
        
       return FilePath;
    }
    static String ReturnPath(String FileName) 
    {
        String File;
        Scanner scan = new Scanner(System.in);
        System.out.print(">> ENTER DIRECTORY ::");
        String directory = scan.next();
        String dir = directory + ":" +"\\";
        File = findFile(FileName,new File(dir));
        return File;
    }
}
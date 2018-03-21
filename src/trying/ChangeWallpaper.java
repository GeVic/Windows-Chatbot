package trying;


import com.sun.jna.Library;
import com.sun.jna.Native;

import com.sun.jna.win32.W32APIOptions;
public class ChangeWallpaper extends random{    
        private static String noOfImages;
        private static String name[] = new String[11];
        private static String path = null;
        public static interface User32 extends Library {
            User32 INSTANCE = (User32) Native.loadLibrary("user32",User32.class,W32APIOptions.DEFAULT_OPTIONS);        
            boolean SystemParametersInfo (int one, int two, String s ,int three);         
        }
       static void wallPaperChanged() {
           
           
           for(int i = 1 ; i < 11 ; ++i){
               name[i] = "D:\\WALLPAPERS\\" + Integer.toString(i) + ".jpg";
               
           }
           int indexOfNamw = random.giveRandBack();
           path = name[indexOfNamw];
           User32.INSTANCE.SystemParametersInfo(0x0014, 0, path, 1);
          }
 }
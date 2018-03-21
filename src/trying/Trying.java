
package trying;
// to import the buffered input streams
import java.io.*;
// to import the java util classes which incldes the random class 
import java.util.*;
// to import the vector class 
// importing regex
import java.util.regex.*;
// to open chrome 
import java.awt.Desktop;
import java.net.URI;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.StringUtils;






public class Trying extends SearchResult {
    // knowledge base for the bot 
    private static final String base[][][] = ZatKnowledgeBase.ZatBase();
    static int count = 0;
    
    private static final String transposeList[][] = {
			{"I'M", "YOU ARE"},
			{"AM", "ARE"},
			{"WERE", "WAS"},
			{"ME", "YOU"},
			{"YOURS", "MINE"},
			{"YOUR", "MY"},
			{"I HAVE", "YOU HAVE"},
			{"I", "YOU"},
			{"AREN'T", "AM NOT"},
			{"WERE NOT", "WAS NOT"},
			{"I HAD", "YOU HAD"},
			{"DAD", "FATHER"},
			{"MOM", "MOTHER"},
			{"DREAMS", "DREAM"},
			{"MYSELF", "YOURSELF"}
		};


    final static int respnum = 3;
    final static int inputnum = 1;
    private static String sResponse = "";
    private static String prevInput = new String();
    private static String sinput = " ";
    private static String sSubject = "";
    private static String prvresponse = "";
    private static ArrayList<String> responses = new ArrayList<>(respnum);
    private static ArrayList<Integer> answer = new ArrayList<>(respnum);

    

    static boolean resgoogle;

    private static final ArrayList<String> responseList = new ArrayList<>(respnum);
   
    // find match function
    static ArrayList findMatch(String str)
    {       
            responseList.clear();
            answer.clear();
            String bestKeyword = "";
            str = str.toUpperCase();
            
            str = insert_spaces(str);
            for(int i = 0 ; i < base.length ; i++)
            {  
                // Keyword list 
                String[] keyWordList = base[i][0];
                // Loop on keyword list
                for(int j = 0; j < keyWordList.length; ++j){
                    
                    String keyword = keyWordList[j];
                    char firstChar = keyword.charAt(0);
                    char lastChar = keyword.charAt(keyword.length()- 1);
                    keyword = keyword.replace("_", "");
                    // To enhance serach 
                    keyword = insert_spaces(keyword);
                    int keyPos = sinput.indexOf(keyword);
                //System.out.println(keyWordList[j]);
               
                
                //Checks the data base to search for the result
                //System.out.println(str);
                //System.out.println(str.indexOf("I DIDN'T MEAN"));
                if( str.contains(keyword))
                 {
                     System.out.println(str + " " + keyword);
                     if(KeyLoc(keyword, firstChar, lastChar, keyPos) )
		     {
			continue;
	             }
                   
                     
                     if(keyword.length() > bestKeyword.length()){
                         bestKeyword = keyword;
                         answer.clear();
                         answer.add(i);
                         //System.out.println(bestKeyword + " ffs ");
                     }
                     
                     else if(keyword.length() == bestKeyword.length()) { 
                    
                         
                         answer.add(i);
                         
                     }
                 
                 }
                System.out.println(answer);
                
                }
        }
            
            
           if(answer.size() > 0){
               
                //Collections.shuffle(answer);
                int answerIndex = answer.get(0);
                int respsize = base[answerIndex][1].length ;

                    for(int j = 0 ; j < respnum ; ++j )
                    {
                         responseList.add(base[answerIndex][1][j]);
                    }
               
            }
            
            
            
        System.out.println(responseList);
        return responseList;    
    }
    
    static boolean KeyLoc(String keyword, char firstChar, char lastChar,int pos){
    boolean WrongPos = false;
	pos += keyword.length();
	if( (firstChar == '_' && lastChar == '_' && !sinput.equals(keyword)) ||
		(firstChar != '_' && lastChar == '_' && pos != sinput.length()) ||
		(firstChar == '_' && lastChar != '_' && pos == sinput.length()) )
	{
		WrongPos = true;
	}
	return WrongPos;
}

    
    // Function to add spaces to the input keyword for
    // enhancing the search 
    static String insert_spaces(String word){
        StringBuffer tempstr = new StringBuffer(word);
        tempstr.insert(0, ' ');
        tempstr.insert(tempstr.length(), ' ');
        return tempstr.toString();
    }
    // find the smallest integer number
    public static int FindSmallest (int[] arr1){//start method

       int index = 0;
       int min = arr1[index];
       for (int i=1; i<arr1.length; i++){

           if (arr1[i] < min ){
               min = arr1[i];
               index = i;
           }


       }
       return index ;

}

    // Function to transpose string
    static String transposeFunction(String sinput)
	{       
                sinput = sinput + " ";
		if(sResponse.contains("*"))
		{
			// extracting from input
			sSubject = find_subject(sinput); 
                        if(sSubject.length() == 0){
                            return sResponse;
                        }
			// processing subject
			sSubject = transpose(sSubject); 
                        sSubject = " " + sSubject;
                        //System.out.println(sSubject);
			sResponse = sResponse.replace("*", sSubject);
		}
                return sResponse;
	}
    // this finds the subject of the string from transpose function
    static String find_subject(String FileInput)
	{
		sSubject = ""; // resets subject variable
		StringBuffer buffer = new StringBuffer(FileInput);
		buffer.deleteCharAt(0);
		FileInput = buffer.toString();              
                int pos = -1;
                for(int i = 0; i < transposeList.length; ++i){
                      transposeList[i][0] = insert_spaces(transposeList[i][0]);
                      
                      if(FileInput.toUpperCase().contains(transposeList[i][0])){
                            pos = FileInput.toUpperCase().indexOf(transposeList[i][0]);
                            //System.out.println(pos + " " + FileInput.length());
                            break;
                      }
                     
                }
             
		if(pos != -1)
		{
			sSubject = FileInput.substring(pos,FileInput.length());		
		}
                else {sSubject = "";
                //System.out.println("fdgdf");
                }
               
                return sSubject;
	}
	
    // implementing the 'sentence transposition' feature
    public static String transpose( String str )
	{       String str1 = "";
		for(int i = 0; i < transposeList.length; ++i)
		{      
                       
                       
                       if(str.toUpperCase().contains(transposeList[i][0])){
                           
                            str1 = str.toUpperCase();
                            String first = transposeList[i][0];
                            insert_spaces(first);
                            String second = transposeList[i][1];
                            insert_spaces(second);
                            second = second + " ";
                            //System.out.println(first + " " + second);
                            
                            str1 = str1.replace(first, second);
                            
                            
                            break;
                       }
                    }   
		
		   return str1;
	        }

    
    // function search in another database for response 
    static String FindMatchInAnother(String sinput){
        for(int i = 0 ; i < LetTheBotLearn.UserMadekeyword.size(); i++){
            if(LetTheBotLearn.UserMadekeyword.get(i).contains(sinput)){
                sResponse = LetTheBotLearn.UserMadeResponse.get(i);
            }
        }
        return sResponse;
    }
    // Function to clean string
    static String cleanString(String sinput){
        sinput = sinput.replaceAll("[^\\w\\d\\s']","");            
        sinput = sinput.replaceAll("( )+", " ");
        return sinput;
    }

    // Function if empty string repeated
    static boolean emptyRepeat(String prevInput, String sinput){
        
            return prevInput.equalsIgnoreCase(sinput);
  
    }

    // Function to handle Respone by machine 
    static ArrayList Response(ArrayList responses){
       
        Collections.shuffle(responses);
        
        return responses;
                
    }
    
    // Function to handle google search result 
    static String SearchPattern(String sinput){
        String searchQuery = "";
        Pattern pattern1 = Pattern.compile("(?:search|lookup|google)\\s+(.*)");
        Pattern pattern2 = Pattern.compile("(.*)\\s+(?:search|lookup|google)");
        Matcher m = pattern1.matcher(sinput);
        Matcher m1 = pattern2.matcher(sinput);
        if(m.find()){
             searchQuery = m.group(1);
        }
        else if(m1.find()){
            searchQuery = m1.group(1);
        }
       return searchQuery; 
    }
    
    //Function to handle file opening result 
    static String FilePattern(String sinput){
        String fileQuery = "";
        Pattern pattern1 = Pattern.compile("open\\s+(.*)");
        Pattern pattern2 = Pattern.compile("(.*)\\s+open");
        
        Matcher m = pattern1.matcher(sinput);
        Matcher m1 = pattern2.matcher(sinput);
        
        if(m.find()){
            
             fileQuery = m.group(1);
        }

        else if(m1.find()){
         
            fileQuery = m1.group(1);
        }
     
       return fileQuery; 
        
    }
    
    // Function to get abstract response 
    static String AbstractResponse(String Key){
         responses = findMatch(Key);
         responses = Response(responses);
         return (String)responses.get(0);
    }
    
    // Function to handle previous response
    static String prevResponse(){
        String lastresponse = sResponse;
        return lastresponse;
    }
        
    // Function to check for similar inputs
    static boolean similar_Inputs(String prevInput,String sinput){
        return sinput.contains(prevInput);
    }
    
    // user input function
    static String userConversation() throws Exception{
        
       
            sResponse = "";
            
            System.out.print(">> ");
            prevInput = sinput;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            sinput = in.readLine();
            
            String FileInput = new String(sinput);
            // Calling function to clean string 
            sinput  = cleanString(sinput);
            // String for google search
            String Google = new String(sinput);
     
            // Calling find Match method
            responses = findMatch(sinput);
            
            
                        
            // to handle chrome request
            if(Google.toLowerCase().contains("chrome") && Google.toLowerCase().contains("open")){
               try{
                Desktop.getDesktop().browse(new URI("http://www.google.com"));
                sResponse = AbstractResponse("CHROME OPEN**");                               
               } catch(Exception g){sResponse = AbstractResponse("CHROME FAILED**");}
          }
            
            // to handle open file request 
            else if(FileInput.toLowerCase().matches("open\\s+(.*)") || FileInput.toLowerCase().matches("(.*)\\s+open") || FileInput.toLowerCase().matches(".*(?:open|run).*(\\w+\\.\\w+)")){
               try{ 
                FileInput = FilePattern(FileInput.toLowerCase());
                String path = FileLocation.ReturnPath(FileInput);
                Desktop.getDesktop().open(new File(path));
                sResponse = AbstractResponse("FILE OPENED**");       
             }catch(Exception e){ sResponse = AbstractResponse("FILE NOT FOUND**");}
                
              
            }
            
            // to know the present date and day
            else if(sinput.toLowerCase().contains("date") && sinput.toLowerCase().contains("time") || sinput.toLowerCase().contains("dandt") && sinput.toLowerCase().contains("tandd")){
                sResponse = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
            }
            // to know only date
            else if(sinput.toLowerCase().contains("date") || sinput.toLowerCase().contains("dt") || sinput.toLowerCase().contains("day")){
                sResponse = new Date().toString().substring(0, 10);
            }
            
            //to know only time 
            else if(sinput.toLowerCase().contains("time") || sinput.toLowerCase().contains("tm")){
                sResponse = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            }
            
            // if the chrome is close 
            else if(sinput.toLowerCase().contains("chrome") && sinput.toLowerCase().contains("close")){
                       sResponse = AbstractResponse("CHROME CLOSED**");
            }
            
            // to surf internet
            else if(Google.toLowerCase().matches("(search|lookup|google)\\s+(.*)") || Google.toLowerCase().matches("(.*)\\s+(search|lookup|google)") ){
                sResponse = AbstractResponse("GOOGLE SEARCH**");
                Google = SearchPattern(Google);
                SearchResult.ChromeResult(Google);  
            }
            
            // to change desktop background
            else if(sinput.toLowerCase().contains("desktop background") && (sinput.toLowerCase().contains("dont change")) || (sinput.toLowerCase().contains("db")) && sinput.toLowerCase().contains("dont change")){
                sResponse = AbstractResponse("DONT DO**");
                
            }
            
            // to change desktop background
            else if((sinput.toLowerCase().contains("desktop background") && (sinput.toLowerCase().contains("change"))) || ((sinput.toLowerCase().contains("db")) && sinput.toLowerCase().contains("change"))){
                sResponse = AbstractResponse("WALLPAPER**");
                ChangeWallpaper.wallPaperChanged();
            }
            
            // condition to check if user has repeated himself/herself
            else if(prevInput.equalsIgnoreCase(sinput) && similar_Inputs(prevInput, sinput) && sinput.length() != 0){
                
                sResponse = AbstractResponse("REPETITION T2**");
            }

            
             // condition to handle repeated empty input
            else if(sinput.length() == 0 && emptyRepeat(prevInput, sinput)){
                sResponse = AbstractResponse("NULL INPUT REPETITION**");
            }
            
            // condition to handle empty String
            else if(sinput.length() == 0){
                sResponse = AbstractResponse("NULL INPUT**");
            }
            
            
            // condition to handle unknown knowledge base
            else if(responses.isEmpty()){
               //sResponse = AbstractResponse("NOT UNDERSTOOD**");
               if(count != 0){
                   sResponse = FindMatchInAnother(sinput);
               }
               else{
                        count++;               
                        System.out.println("I HAVE NO IDEA OF WHAT YOU ARE TALKING ABOUT, WOULD YOU LIKE TO TEACH ME");
                        System.out.print(">> ");
                        String sReply = in.readLine(); 
                        if(sReply.toLowerCase().contains("yes") ||sResponse.toLowerCase().contains("yes")){
                            LetTheBotLearn.EnterKeyWord();
                            // seaching another database for the keyword
                            
                            //System.out.println(LetTheBotLearn.UserMadekeyword);
                        }
                   
                    }
              
               
            }
            
            // handles repeatedness of responses
            else
            {   
                responses = Response(responses);
                sResponse = responses.get(0);
                if(sResponse.contains("*")){
                       sResponse =  transposeFunction(FileInput);
                       //System.out.println(sResponse);
                }
         
                // if the previous response is same for the same question
                if(sResponse.equalsIgnoreCase(prvresponse))
                {
                    responses.remove(0);
                    responses = Response(responses);
                    sResponse = responses.get(0);
                    
                    
                }
               
            }
           
            
        // returns the bots reply
        return sResponse;
    }    
        
    
    
    // main function 
    public static void main(String[] args) throws Exception{
        
        // calling User and Machine conversation
        while(true){
        String FinalResponse = userConversation();
        System.out.println(FinalResponse);
        if( sinput.contains("bye") || sinput.contains("goodbye") || sinput.contains("goodnight")){
            break;
        }
      
        }
    
    }




}



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.BufferedReader;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ArrayList<String> words=readWords("Dadak/res/words.txt");//step4
        //count words with hasgmap
        HashMap<String, Integer> wordCounter = buildHashMap(words);//step5
        createHTMLFile(wordCounter);//step6
        //Step 9:creating the arrayList WordFrequency objects 
        
        //step 9 : populating the ArrayList
    
       
      
        ArrayList<WordFrequency> wordFrequencyList = new ArrayList<>();
        for(String key: wordCounter.keySet()){
            //Integer wordFrequency = wordCounter.get(key);
            WordFrequency wordFrequency = new WordFrequency(key, wordCounter.get(key));
            wordFrequencyList.add(wordFrequency);}
        Collections.sort(wordFrequencyList);
        for(WordFrequency wordFrequency: wordFrequencyList){
            System.out.println("List :" + wordFrequency.getWord()+ "" + wordFrequency.getWordCount());
        }

        //step 10 
        createSortedHTMLFile(wordFrequencyList);

    }

    //step4 read input file
    private static ArrayList<String> readWords(String fileName){
        File file =new File(fileName);
        ArrayList<String>wordList = new ArrayList<>();

        try {
            FileReader reader = new FileReader (file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            String line = bufferedReader.readLine();
            while(line!=null){
                String[] words = line.split("[ .,]+");
                for (String word: words)
                {
                    if(word.trim().length()>0)
                    {
                        wordList.add(word.toLowerCase());
                    }

                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return wordList;
    }

    //step 5  build hasmap function
    private static HashMap<String, Integer> buildHashMap(ArrayList<String> words){

        HashMap<String, Integer> wordCounter = new HashMap<>();
        for(String word: words)
        {
            Integer count = wordCounter.get(word);
            if(count==null){
                wordCounter.put(word, 1);

            } else{
                wordCounter.put(word, count+1);
            }
        }
        return wordCounter;
    }

    //step 6: Cretae HTML file

    private static void createHTMLFile(HashMap<String, Integer> wordCounter){
        File file = new File ("Dadak/res/word.html");
        try{
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
            final String css = "<style>"
                        + "td, th {border: solid}" 
                        + " table, td, th {border-collapse: collapse}"
                        + "</style>";
            builder.append(css).append("\n");
            builder. append("<h1> Word Counts</h1>");

            builder.append("<table>");
 
            for (String key:wordCounter.keySet()){
                builder.append("<tr>");
                builder.append("<td>"+key + "</td>");
                builder.append("<td>"+wordCounter.get(key)+"</td>");
                builder.append("</tr>");

            }

            builder.append("<table>");
            fileWriter.append(builder.toString());
            fileWriter.close();

        }catch(IOException e){
            e.printStackTrace();
        }

        for(String keyWord: wordCounter.keySet()){
            System.out.println(keyWord + ":" + wordCounter.get(keyWord));
        }
        

    }

    //Step 10 Create HTML function for sorted html
    private static void createSortedHTMLFile(ArrayList<WordFrequency> wordFrequencyList){
        File fileSort = new File ("Dadak/res/sorted.html");
        HashMap<String, Integer> wordCounter = new HashMap<>();
        try{
            FileWriter fileWriter = new FileWriter(fileSort);
            StringBuilder builder = new StringBuilder();
            final String css = "<style>"
                        + "td, th {border: solid}" 
                        + " table, td, th {border-collapse: collapse}"
                        + "</style>";
            builder.append(css).append("\n");
            builder. append("<h1> Word Counts</h1>");

            builder.append("<table>");
            for(WordFrequency wordFrequency: wordFrequencyList){
                builder.append("<tr>");
                builder.append("<td>"+wordFrequency.getWord() + "</td>");
                builder.append("<td>"+wordFrequency.getWordCount()+"</td>");
                builder.append("</tr>");
                
            }
            

            builder.append("<table>");
            fileWriter.append(builder.toString());
            fileWriter.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        
        
        

    }

    

    
}

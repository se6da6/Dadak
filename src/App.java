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
        /**
         * 
         */
        ArrayList<String> words=readWords("res/words.txt");//step4
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
        //To sort the contents of the ArrayList in increasing order of the wordcount. 
        Collections.sort(wordFrequencyList);
        for(WordFrequency wordFrequency: wordFrequencyList){
            System.out.println("List :" + wordFrequency.getWord()+ "" + wordFrequency.getWordCount());
        }

        //step 10 
        createSortedHTMLFile(wordFrequencyList);
        //step 14
        ArrayList<String> paragraphWords=readParagraphWords("res/paragraph.txt");
        // Step 15
        HashMap<String, Integer> wordParagraphCounter = buildParagraphHashMap(paragraphWords);
        //step 16
        createParagraphHTMLFile(wordParagraphCounter);
        //step 18-19
        ArrayList<ParagraphFrequency> paragraphFrequencyList = new ArrayList<>();
        for(String key: wordParagraphCounter.keySet()){
            ParagraphFrequency paragraphFrequency = new ParagraphFrequency(key, wordParagraphCounter.get(key));
            paragraphFrequencyList.add(paragraphFrequency);}
        //step 20
        //Sorting the contents of the ArrayList in increasing order of the wordcount. 
        Collections.sort(paragraphFrequencyList);
        for(ParagraphFrequency paragraphFrequency: paragraphFrequencyList){
            System.out.println("List :" + paragraphFrequency.getWord()+ "" + paragraphFrequency.getWordCount());
        }
        //step 20 
        createParagraphSortedHTMLFile(paragraphFrequencyList);

    }
    //step4 read input file
    /**
     * We create a fucntion which read the contents of the words.txt file, separate it into words (removing all blanks and punctuation), 
     * and store the words in an ArrayList.
     * @param fileName 
     * @return wordlist whicg is the arraylist
     */
    
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
    /**
     * In this function we create a HashMap that stores the number of occurrences of each word that
     * appears in the original text file. The keys to the HashMap is the words from the text file. 
     * 
     * @param words
     * @return wordCounter The values returned are the number of occurrences of the key word in the text file.
     */
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
    /**
     * In this function we create an HTML file named words.html that contains a table consisting of a word column and a wordcount column.
     * We iterate through the HashMap. Assuming the HashMap is named
     * wordCount, the following code will iterate through all the keys.
     * @param wordCounter
     */

    private static void createHTMLFile(HashMap<String, Integer> wordCounter){
        File file = new File ("res/word.html");
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
    /**
     * We create Create a second HTML file called sortedWords.html that consists of a table of words and word counts, 
     * arranged in ascending wordcount order.the contents of the ArrayList in increasing order of the wordcount. 
     * 
     * @param wordFrequencyList
     */
    private static void createSortedHTMLFile(ArrayList<WordFrequency> wordFrequencyList){
        File fileSort = new File ("res/sorted.html");
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
    //step 14
    /**
     * We create the  code to read the contents of the paragraph.txt file, 
     * separate it into words (removing all blanks and punctuation), and store the words in an ArrayList
     * @param fileName
     * @return wordParagraphList which is the arraylist
     */
    private static ArrayList<String> readParagraphWords(String fileName){
        File file =new File(fileName);
        ArrayList<String>wordParagraphList = new ArrayList<>();
    
        try {
            FileReader reader = new FileReader (file);
            BufferedReader bufferedReader = new BufferedReader(reader);
                
            String line = bufferedReader.readLine();
            while(line!=null){
                String[] paragraphWords = line.split("[ .,]+");
                for (String word: paragraphWords)
                {
                    if(word.trim().length()>0)
                    {
                        wordParagraphList.add(word.toLowerCase());
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
        return wordParagraphList;
    }
    

    //step 15
    /**
     * We created a HashMap that stores the number of occurrences of each word that appears in the original text file. 
     * The keys to the HashMap should be the words from the text file. 
     * 
     * @param wordsParagraph
     * @return wordParagraphCounter are the number of occurrences of the key word in the text file.
     */
    private static HashMap<String, Integer> buildParagraphHashMap(ArrayList<String> wordsParagraph){

        HashMap<String, Integer> wordParagraphCounter = new HashMap<>();
        for(String word: wordsParagraph)
        {
            Integer count = wordParagraphCounter.get(word);
            if(count==null){
                wordParagraphCounter.put(word, 1);
    
            } else{
                wordParagraphCounter.put(word, count+1);
            }
        }
        return wordParagraphCounter;
    }

    //step 16
    /**
     * We created an HTML file named paragraph.html that contains a table consisting of a two columns, 
     * one that has the word and the other that lists the count for that word
     * @param wordParagraphCounter
     */
    private static void createParagraphHTMLFile(HashMap<String, Integer> wordParagraphCounter){
        File fileParagraph = new File ("res/paragraph.html");
        try{
            FileWriter fileParagraphWriter = new FileWriter(fileParagraph);
            StringBuilder builder = new StringBuilder();
            final String css = "<style>"
                        + "td, th {border: solid}" 
                        + " table, td, th {border-collapse: collapse}"
                        + "</style>";
            builder.append(css).append("\n");
            builder. append("<h1> Word Counts</h1>");
    
            builder.append("<table>");
     
            for (String key:wordParagraphCounter.keySet()){
                builder.append("<tr>");
                builder.append("<td>"+key + "</td>");
                builder.append("<td>"+wordParagraphCounter.get(key)+"</td>");
                builder.append("</tr>");
    
            }
    
            builder.append("<table>");
            fileParagraphWriter.append(builder.toString());
            fileParagraphWriter.close();
    
        }catch(IOException e){
            e.printStackTrace();
        }
    
        for(String keyWord: wordParagraphCounter.keySet()){
            System.out.println(keyWord + ":" + wordParagraphCounter.get(keyWord));
        }
            
    
    }

    //step 20
    /**
     * 
     * We created a second HTML file called sortedParagraphWords.html 
     * that consists of a table of words and word counts. 
     * arranged in ascending wordcount order.
     * @param paragraphFrequencyList
     */
    private static void createParagraphSortedHTMLFile(ArrayList<ParagraphFrequency> paragraphFrequencyList){
        File fileParagraphSort = new File ("res/sortedParagraphWords.html");
        try{
            FileWriter fileWriter = new FileWriter(fileParagraphSort);
            StringBuilder builder = new StringBuilder();
            final String css = "<style>"
                        + "td, th {border: solid}" 
                        + " table, td, th {border-collapse: collapse}"
                        + "</style>";
            builder.append(css).append("\n");
            builder. append("<h1> Word Counts</h1>");
    
            builder.append("<table>");
            for(ParagraphFrequency paragraphFrequency: paragraphFrequencyList){
                builder.append("<tr>");
                builder.append("<td>"+paragraphFrequency.getWord() + "</td>");
                builder.append("<td>"+paragraphFrequency.getWordCount()+"</td>");
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

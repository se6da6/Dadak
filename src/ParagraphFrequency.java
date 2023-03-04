/**
 * We created a new class named ParagraphFrequency to represent a word and its word count. 
 * We added its constructor, get and set methods, and a toString method. 
 * Modified the class so that it implements the Comparable interface. 
 * With the compareTo function the class supports comparisons based on the word count.
 */
public class ParagraphFrequency implements Comparable<ParagraphFrequency>{
    String word;
    Integer wordCount;
    
    public ParagraphFrequency(String word, Integer wordCount){
        this.word = word;
        this.wordCount = wordCount;
    }
    
    public String getWord()
    {
        return word;
    }
    
    public void setWord(String newWord){
        this.word = newWord;
    }
        
    public Integer getWordCount(){
        return wordCount;
    }
    
    public void setWordCount(Integer newWordCounter){
        this.wordCount = newWordCounter;
    }
    
    @Override
    public String toString(){
        return "\n" + word + wordCount.toString();
    }
    
    @Override
    public int compareTo(ParagraphFrequency otherWordCounter){
        int compareValue = 1;
        if (this.wordCount>otherWordCounter.wordCount) {
             compareValue= 1;
        }
                 
        if (this.wordCount<otherWordCounter.wordCount) 
        {
            compareValue= -1;
        } 
        if(this.wordCount == otherWordCounter.wordCount){
            compareValue =0;
        }
        return compareValue;
    }
}
    


public class WordFrequency implements Comparable<WordFrequency>{
    String word;
    Integer wordCount;

    public WordFrequency(String word, Integer wordCount){
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
    public int compareTo(WordFrequency otherWordCounter){
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

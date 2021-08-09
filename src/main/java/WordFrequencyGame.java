import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";
    public static final int INITIAL_WORD_COUNT = 1;

    public String getResult(String sentence) {

        if (isSingleWord(sentence)) {
            return sentence + " 1";
        }
        try {
            String[] words = sentence.split(BLANK_SPACE);
            List<WordInfo> wordFrequencies = calculateWordFrequency(words);
            return consolidateWordFrequencies(wordFrequencies);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private boolean isSingleWord(String sentence) {
        return sentence.split(BLANK_SPACE).length == INITIAL_WORD_COUNT;
    }

    private List<WordInfo> calculateWordFrequency(String[] words) {
        List<String> distinctWords = Arrays.stream(words).distinct().collect(Collectors.toList());
        List<WordInfo> wordInfo = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int wordCount = (int) Arrays.stream(words)
                    .filter(word -> word.equals(distinctWord))
                    .count();
            wordInfo.add(new WordInfo(distinctWord, wordCount));
        });
        return wordInfo;
    }

    private String consolidateWordFrequencies(List<WordInfo> wordFrequencies) {
        return wordFrequencies
                .stream()
                .sorted((firstWord, secondWord) -> secondWord.getCount() - firstWord.getCount())
                .map(word -> word.getWord() + " " + word.getCount())
                .collect(Collectors.joining("\n"));
    }

}

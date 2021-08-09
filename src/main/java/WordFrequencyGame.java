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
            int wordCount = (int) Arrays.stream(words).filter(word -> word.equals(distinctWord)).count();
            wordInfo.add(new WordInfo(distinctWord, wordCount));
        });
        return wordInfo;
    }

    private String consolidateWordFrequencies(List<WordInfo> wordFrequencies) {
        wordFrequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo w : wordFrequencies) {
            String s = w.getWord() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

}

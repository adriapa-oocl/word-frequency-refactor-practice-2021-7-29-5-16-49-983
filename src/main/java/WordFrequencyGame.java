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

            List<WordInfo> wordInfoList = calculateWordFrequency(sentence);

            wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (WordInfo w : wordInfoList) {
                String s = w.getWord() + " " + w.getWordCount();
                joiner.add(s);
            }
            return joiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private boolean isSingleWord(String sentence) {
        return sentence.split(BLANK_SPACE).length == INITIAL_WORD_COUNT;
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(BLANK_SPACE));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());

        List<WordInfo> wordInfos = new ArrayList<>();
        distinctWords.forEach(distinctWord -> {
            int wordCount = (int) words.stream().filter(word -> word.equals(distinctWord)).count();
            wordInfos.add(new WordInfo(distinctWord, wordCount));
        });

        return wordInfos;
    }

}

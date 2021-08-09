import java.util.*;

public class WordFrequencyGame {

    public static final String BLANK_SPACE = "\\s+";
    public static final int INITIAL_WORD_COUNT = 1;

    public String getResult(String sentence) {


        if (isSingleWord(sentence)) {
            return sentence + " 1";
        } else {

            try {

                List<WordInfo> wordInfoList = getWordInfos(sentence);

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
    }

    private boolean isSingleWord(String sentence) {
        return sentence.split(BLANK_SPACE).length == INITIAL_WORD_COUNT;
    }

    private List<WordInfo> getWordInfos(String sentence) {
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(BLANK_SPACE);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, INITIAL_WORD_COUNT);
            wordInfoList.add(wordInfo);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordInfo>> map = getListMap(wordInfoList);

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(wordInfo);
        }
        wordInfoList = list;
        return wordInfoList;
    }


    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getWord(), arr);
            } else {
                map.get(wordInfo.getWord()).add(wordInfo);
            }
        }


        return map;
    }


}

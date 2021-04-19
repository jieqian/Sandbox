package com.sandbox.algorithm.similarity;

import org.xm.Similarity;
import org.xm.similarity.text.CosineSimilarity;
import org.xm.similarity.text.TextSimilarity;

public class Sandbox {
    public static void main(String[] args) throws Exception {
        getTextSimilarityScore();
    }

    public static void getSentenceSimilarityScore() throws Exception {
//        String sentence1 = "WE官宣：与打野beishang成功续约";
//        String sentence2 = "we官宣：打野beishang续约";

        String sentence1 = "肇俊哲卸任辽宁队领队";
        String sentence2 = "辽宁队领队肇俊哲卸任";

        double morphoSimilarityResult = Similarity.morphoSimilarity(sentence1, sentence2);
        double editDistanceResult = Similarity.editDistanceSimilarity(sentence1, sentence2);
        double standEditDistanceResult = Similarity.standardEditDistanceSimilarity(sentence1,sentence2);
        double gregeorEditDistanceResult = Similarity.gregorEditDistanceSimilarity(sentence1,sentence2);

        System.out.println(sentence1 + " vs " + sentence2 + " 词形词序句子相似度值：" + morphoSimilarityResult);
        System.out.println(sentence1 + " vs " + sentence2 + " 优化的编辑距离句子相似度值：" + editDistanceResult);
        System.out.println(sentence1 + " vs " + sentence2 + " 标准编辑距离句子相似度值：" + standEditDistanceResult);
        System.out.println(sentence1 + " vs " + sentence2 + " gregeor编辑距离句子相似度值：" + gregeorEditDistanceResult);
    }

    public static void getTextSimilarityScore() throws Exception {
        String text1 = "similarity是由一系列算法组成的Java版相似度计算工具包，目标是传播自然语言处理中相似度计算方法。similarity具备工具实用、性能高效、架构清晰、语料时新、可自定义的特点。";
        String text2 = "similarity是由一系列算法组成的相似度计算工具包，目标是传播自然语言处理中相似度计算方法。";
//        String text3 = "similarity是由一系列算法组成的Java版相似度计算工具包，目标是传播自然语言处理中相似度计算方法。similarity具备工具实用、性能高效、架构清晰、语料时新、可自定义的特点。";
        TextSimilarity similarity = new CosineSimilarity();
        double score1pk2 = similarity.getSimilarity(text1, text2);

//        double score2pk2 = similarity.getSimilarity(text2, text2);

        System.out.println(text1 + " 和 " + text2 + " 的相似度分值：\n" + score1pk2);

//        System.out.println(text2 + " 和 " + text2 + " 的相似度分值：" + score2pk2);


    }
}

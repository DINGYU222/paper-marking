package com.papermaking.util;

import java.util.*;

/**
 * 自动组卷的工具类
 *
 * @author 彭燕
 */
public class AutoGeneratingPaper {

    /**
     * 自动组卷算法
     *
     * @param selectQuestionNum 是一个int数组 表示前端传过来的题目个数 [0]选择题 【1】填空题 【2】简答题 【3】判断题
     * @param kids              表示选择的知识点的id集合
     * @param pid               试卷id
     * @param cid               课程id
     */
    public static void createPaper(int[] selectQuestionNum, String[] kids, Integer pid, Integer cid) {
        if (selectQuestionNum == null || kids == null || pid == null || cid == null)
            throw new RuntimeException("非法参数");
        //每个知识点所需抽取的试题数量
        int[] eachPointNum = new int[selectQuestionNum.length];
        //临时的集合 用来存放数据库中的
        Map<String, Map<String, Double>> xuanzeTemp = new HashMap<>();
        Map<String, Map<String, Double>> tiankongTemp = new HashMap<>();
        Map<String, Map<String, Double>> jiandaTemp = new HashMap<>();
        Map<String, Map<String, Double>> panduanTemp = new HashMap<>();

        Map<String, Set<String>> xuanzelist = new HashMap<>();
        Map<String, Set<String>> tiankonglist = new HashMap<>();
        Map<String, Set<String>> jiandalist = new HashMap<>();
        Map<String, Set<String>> panduanlist = new HashMap<>();
        //最终存放的题目id
        List<Integer> exam = new ArrayList<>();

        //计算得到每知识点所需抽取的试题数量
        calculateEachPointNumber(eachPointNum, selectQuestionNum, kids.length);



    }

    /**
     * 为了每个知识点选择的题目平均分布
     * 所以需要首先计算每个知识点 选择几道题目
     * 比如 如果选择了10个知识点 并且在选择题选10题 那么为了平均每个知识点应该选择一题
     * 下面的【x】 表示数组下标 x   【0】表示下标为0 即为数组第一个元素表示每个知识点应该选择几题选择题
     *
     * @param eachPointNum      存放每个知识点需要选择的题目个数，[0]选择题 【1】填空题 【2】简答题 【3】判断题
     * @param selectQuestionNum
     * @param length            表示知识点个数
     */
    //1个知识点 1题 每个知识点选一个
    //2个知识点 1题
    //10个知识点 20题
    private static void calculateEachPointNumber(int[] eachPointNum, int[] selectQuestionNum, int length) {
        for (int i = 0; i < eachPointNum.length; i++) {
            if (selectQuestionNum[i] != 0) {
                //如果选择的题目个数 比知识点多 并且可以整除 比如20个题目 10个知识点 那么计算应该为20/10
                if (selectQuestionNum[i] > length && selectQuestionNum[i] % length == 0) {
                    eachPointNum[i] = selectQuestionNum[i] / length;
                } else {
                    //21题 10个知识点 每个知识点选2+1=3题
                    eachPointNum[i] = selectQuestionNum[i] / length + 1;
                }
            } else {
                //比如用户不需要选择题 那么selectQuestionNum【0】为0 所以我们每个知识点选择的选择题题目也应该0
                eachPointNum[i] = 0;
            }
        }
    }

    //TODO：
}

package com.papermaking.util;

import com.papermaking.pojo.KnowledgePoint;
import com.papermaking.pojo.Question;

import java.util.*;

/**
 * 自动组卷的工具类
 *
 * @author 彭燕
 */
public class AutoGeneratingPaper {

    private static double wave = 0.3;

    /**
     * 自动组卷算法
     *
     * @param selectQuestionNum 是一个int数组 表示前端传过来的题目个数 [0]选择题 【1】填空题 【2】简答题 【3】判断题
     */
    public static List<Integer> createPaper(int[] selectQuestionNum, List<KnowledgePoint> knowledgePoints, double difflevel) {
        if (selectQuestionNum == null || knowledgePoints == null)
            throw new RuntimeException("非法参数");
        //每个知识点所需抽取的试题数量
        int[] eachPointNum = new int[selectQuestionNum.length];

        Map<Integer, Set<Integer>> xuanzelist = new HashMap<>();
        Map<Integer, Set<Integer>> tiankonglist = new HashMap<>();
        Map<Integer, Set<Integer>> jiandalist = new HashMap<>();
        Map<Integer, Set<Integer>> panduanlist = new HashMap<>();

        //计算得到每知识点所需抽取的试题数量
        calculateEachPointNumber(eachPointNum, selectQuestionNum, knowledgePoints.size());

        //这一步在论文中叫洗牌，但实际开发中发现于论文情况不同，这一步的目的是构造四个map，每个map是 key做为知识点，value是题号
        mix(xuanzelist, tiankonglist, jiandalist, panduanlist, knowledgePoints);

        //调用不重复的随机数序列算法，将最终结果存入exam数组
        //最终存放的题目id
        List<Integer> exam = getExam(eachPointNum, selectQuestionNum, xuanzelist, tiankonglist, jiandalist, panduanlist);

        for (int i = 0; i < 2; i++) {
            //试卷难度系数计算
            double realDiffLevel = CaluDiffLevel(exam, knowledgePoints, selectQuestionNum);
            //重新生成试卷
            if (Math.abs(realDiffLevel - difflevel) > wave) {
                //调用不重复的随机数序列算法，将最终结果存入exam数组
                exam = getExam(eachPointNum, selectQuestionNum, xuanzelist, tiankonglist, jiandalist, panduanlist);
            }else
                return exam;
        }
        //第三次还是不符合 直接返回null 表示生成失败
        double realDiffLevel = CaluDiffLevel(exam, knowledgePoints, selectQuestionNum);
        if (Math.abs(realDiffLevel - difflevel) > wave) {
            return null;
        } else {
            return exam;
        }
    }

    private static double CaluDiffLevel(List<Integer> exam, List<KnowledgePoint> knowledgePoints, int[] selectQuestionNum) {
        double diffLevel = 0.0;
        for (Integer qId : exam) {
            for (KnowledgePoint knowledgePoint : knowledgePoints) {
                for (Question question : knowledgePoint.getQuestions()) {
                    if (question.getqId() == qId) {
                        diffLevel += question.getqDifflevel();
                        break;
                    }
                }
            }
        }
        double num = 0.0;
        for (int i = 0; i < selectQuestionNum.length; i++) {
            num += selectQuestionNum[i];
        }
        return diffLevel / num;
    }

    private static List<Integer> getExam(int[] eachPointNum, int[] selectQuestionNum, Map<Integer, Set<Integer>> xuanzelist, Map<Integer, Set<Integer>> tiankonglist, Map<Integer, Set<Integer>> jiandalist, Map<Integer, Set<Integer>> panduanlist) {
//        int[] selectQuestionNumCopy = new int[selectQuestionNum.length];
        List<Integer> exam = new ArrayList<>();

        int[] selectQuestionNumCopy = Arrays.copyOf(selectQuestionNum, selectQuestionNum.length);
        while (selectQuestionNumCopy[0] > 0) {
            //每个知识点选eachPointNum[0]道选择题目
            for (Map.Entry<Integer, Set<Integer>> entry : xuanzelist.entrySet()) {
                Set<Integer> set = entry.getValue();
                Integer[] qids = new Integer[set.size()];
                set.toArray(qids);
                int k = 0;
                int temp = eachPointNum[0];
                if (set.size() < eachPointNum[0]) {
                    temp = set.size();
                }
                while (temp > 0) {
                    if (qids.length == 0) break;
                    int rand = qids.length - 1 - k == 0 ? 0 : new Random().nextInt(qids.length - 1 - (k));
                    exam.add(qids[rand]);
                    selectQuestionNumCopy[0]--;
                    if (selectQuestionNumCopy[0] <= 0) break;
                    //rand被最后一位覆盖,并且rand的取值范围要小1
                    qids[rand] = qids[qids.length - 1 - k];
                    k++;
                    //要选择的数减一
                    temp--;
                }
                if (selectQuestionNumCopy[0] <= 0) break;
            }
        }

        while (selectQuestionNumCopy[1] > 0) {
            //每个知识点选eachPointNum[0]道题目
            for (Map.Entry<Integer, Set<Integer>> entry : tiankonglist.entrySet()) {

                Set<Integer> set = entry.getValue();
                Integer[] qids = new Integer[set.size()];
                set.toArray(qids);
                int k = 0;
                int temp = eachPointNum[1];
                if (set.size() < eachPointNum[1]) {
                    temp = set.size();
                }
                while (temp > 0) {
                    int rand = qids.length - 1 - k == 0 ? 0 : new Random().nextInt(qids.length - 1 - (k));
                    exam.add(qids[rand]);
                    selectQuestionNumCopy[1]--;
                    if (selectQuestionNumCopy[1] <= 0) break;
                    //rand被最后一位覆盖,并且rand的取值范围要小1
                    qids[rand] = qids[qids.length - 1 - k];
                    k++;
                    //要选择的数减一
                    temp--;
                }
                if (selectQuestionNumCopy[1] <= 0) break;
            }
        }

        while (selectQuestionNumCopy[2] > 0) {
            //每个知识点选eachPointNum[0]道题目
            for (Map.Entry<Integer, Set<Integer>> entry : jiandalist.entrySet()) {

                Set<Integer> set = entry.getValue();
                Integer[] qids = new Integer[set.size()];
                set.toArray(qids);
                int k = 0;
                int temp = eachPointNum[1];
                if (set.size() < eachPointNum[2]) {
                    temp = set.size();
                }
                while (temp > 0) {

                    int rand = qids.length - 1 - k == 0 ? 0 : new Random().nextInt(qids.length - 1 - (k));
                    exam.add(qids[rand]);
                    selectQuestionNumCopy[2]--;
                    if (selectQuestionNumCopy[2] <= 0) break;
                    //rand被最后一位覆盖,并且rand的取值范围要小1
                    qids[rand] = qids[qids.length - 1 - k];
                    k++;
                    //要选择的数减一
                    temp--;
                }
                if (selectQuestionNumCopy[2] <= 0) break;
            }
        }

        while (selectQuestionNumCopy[3] > 0) {
            //每个知识点选eachPointNum[0]道题目
            for (Map.Entry<Integer, Set<Integer>> entry : panduanlist.entrySet()) {

                Set<Integer> set = entry.getValue();
                Integer[] qids = new Integer[set.size()];
                set.toArray(qids);
                int k = 0;
                int temp = eachPointNum[1];
                if (set.size() < eachPointNum[3]) {
                    temp = set.size();
                }
                while (temp > 0) {
                    int rand = qids.length - 1 - k == 0 ? 0 : new Random().nextInt(qids.length - 1 - (k));
                    exam.add(qids[rand]);
                    selectQuestionNumCopy[3]--;
                    if (selectQuestionNumCopy[3] <= 0) break;
                    //rand被最后一位覆盖,并且rand的取值范围要小1
                    qids[rand] = qids[qids.length - 1 - k];
                    k++;
                    //要选择的数减一
                    temp--;
                }
                if (selectQuestionNumCopy[3] <= 0) break;
            }
        }
        return exam;
    }

    private static void mix(Map<Integer, Set<Integer>> xuanzelist, Map<Integer, Set<Integer>> tiankonglist, Map<Integer, Set<Integer>> jiandalist, Map<Integer, Set<Integer>> panduanlist, List<KnowledgePoint> knowledgePoints) {
        //遍历被选中的知识点下的所有题目
        for (KnowledgePoint knowledgePoint : knowledgePoints) {

            Set<Integer> xuanzeSet = new HashSet<>();
            Set<Integer> tiankongSet = new HashSet<>();
            Set<Integer> jiandaSet = new HashSet<>();
            Set<Integer> panduanSet = new HashSet<>();

            for (Question question : knowledgePoint.getQuestions()) {
                switch (question.getqType()) {
                    case "选择题":
                        xuanzeSet.add(question.getqId());
                        break;
                    case "填空题":
                        tiankongSet.add(question.getqId());
                        break;
                    case "简答题":
                        jiandaSet.add(question.getqId());
                        break;
                    case "判断题":
                        panduanSet.add(question.getqId());
                        break;
                }
            }

            xuanzelist.put(knowledgePoint.getkId(), xuanzeSet);
            tiankonglist.put(knowledgePoint.getkId(), tiankongSet);
            jiandalist.put(knowledgePoint.getkId(), jiandaSet);
            panduanlist.put(knowledgePoint.getkId(), panduanSet);

        }
    }

    private static void getQuestionIdAndDiffLevel(List<KnowledgePoint> knowledgePoints, Map<Integer, Map<Integer, Double>> xuanzeTemp, Map<Integer, Map<Integer, Double>> tiankongTemp, Map<Integer, Map<Integer, Double>> jiandaTemp, Map<Integer, Map<Integer, Double>> panduanTemp) {
        //遍历被选中的知识点下的所有题目
        for (KnowledgePoint knowledgePoint : knowledgePoints) {

            HashMap<Integer, Double> xuanzemap = new HashMap<>();
            HashMap<Integer, Double> tiankongmap = new HashMap<>();
            HashMap<Integer, Double> jiandamap = new HashMap<>();
            HashMap<Integer, Double> panduanmap = new HashMap<>();

            for (Question question : knowledgePoint.getQuestions()) {
                switch (question.getqType()) {
                    case "选择题":
                        xuanzemap.put(question.getqId(), question.getqDifflevel());
                        break;
                    case "填空题":
                        tiankongmap.put(question.getqId(), question.getqDifflevel());
                        break;
                    case "简答题":
                        jiandamap.put(question.getqId(), question.getqDifflevel());
                        break;
                    case "判断题":
                        panduanmap.put(question.getqId(), question.getqDifflevel());
                        break;
                }
            }
            xuanzeTemp.put(knowledgePoint.getkId(), xuanzemap);
            tiankongTemp.put(knowledgePoint.getkId(), tiankongmap);
            jiandaTemp.put(knowledgePoint.getkId(), jiandamap);
            panduanTemp.put(knowledgePoint.getkId(), panduanmap);

        }
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
                if (selectQuestionNum[i] >= length && selectQuestionNum[i] % length == 0) {
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

}

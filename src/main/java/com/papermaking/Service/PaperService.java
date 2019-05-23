package com.papermaking.Service;

import com.papermaking.PaperMakingApplication;
import com.papermaking.common.util.SnowflakeIdWorker;
import com.papermaking.mapper.PaperMapper;
import com.papermaking.pojo.Paper;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

@Service
@Transactional
public class PaperService {
    @Autowired
    private PaperMapper paperMapper;

    public Integer insert(Paper paper) {
        paperMapper.insert(paper);
        return paper.getpId();
    }

    /**
     * @param Y    用户输入的题目数量 默认顺序 选择题 填空题 简答题 判断题
     * @param kids 用户输入的知识点的id序号
     */
    public void createPaper(int[] Y, String[] kids, Integer pid, String cid) {
        //每知识点所需抽取的试题数量
        int[] eachPointNum = new int[Y.length];
        //temp
        Map<String, Map<String, Double>> xuanzeTemp = new HashMap<>();
        Map<String, Map<String, Double>> tiankongTemp = new HashMap<>();
        Map<String, Map<String, Double>> jiandaTemp = new HashMap<>();
        Map<String, Map<String, Double>> panduanTemp = new HashMap<>();

        Map<String, Set<String>> xuanzelist = new HashMap<>();
        Map<String, Set<String>> tiankonglist = new HashMap<>();
        Map<String, Set<String>> jiandalist = new HashMap<>();
        Map<String, Set<String>> panduanlist = new HashMap<>();

        List<String> exam = new ArrayList<>();

        //计算得到每知识点所需抽取的试题数量
        calculateEachPointNumber(eachPointNum, Y, kids.length);

        //遍历数据库，将试题号和难度系数存入数组temp
        getQuestionIdAndDiffLevel(questionBankMapper.findAllByCid(cid), kids, xuanzeTemp, tiankongTemp, jiandaTemp, panduanTemp);

        mix(xuanzeTemp, tiankongTemp, jiandaTemp, panduanTemp, xuanzelist, tiankonglist, jiandalist, panduanlist);

        //调用不重复的随机数序列算法，将最终结果存入exam数组
        getExam(exam, eachPointNum, Y, xuanzelist, tiankonglist, jiandalist, panduanlist);

        extractingMapper.save(pid, exam);
    }

    private void getExam(List<String> exam, int[] eachPointNum, int[] Y, Map<String, Set<String>> xuanzelist, Map<String, Set<String>> tiankonglist, Map<String, Set<String>> jiandalist, Map<String, Set<String>> panduanlist) {
        while (Y[0] > 0) {
            //每个知识点选eachPointNum[0]道选择题目
            for (Map.Entry<String, Set<String>> entry : xuanzelist.entrySet()) {
                Set<String> set = entry.getValue();
                String[] qids = new String[set.size()];
                set.toArray(qids);
                int k = 0;
                int temp = eachPointNum[0];
                while (temp > 0) {
                    if (qids.length == 0) break;
                    int rand = qids.length - 1 - k == 0 ? 0 : new Random().nextInt(qids.length - 1 - (k));
                    exam.add(qids[rand]);
                    Y[0]--;
                    if (Y[0] <= 0) break;
                    //rand被最后一位覆盖,并且rand的取值范围要小1
                    qids[rand] = qids[qids.length - 1 - k];
                    k++;
                    //要选择的数减一
                    temp--;
                }
                if (Y[0] <= 0) break;
            }
        }

        while (Y[1] > 0) {
            //每个知识点选eachPointNum[0]道题目
            for (Map.Entry<String, Set<String>> entry : tiankonglist.entrySet()) {

                Set<String> set = entry.getValue();
                String[] qids = new String[set.size()];
                set.toArray(qids);
                int k = 0;
                int temp = eachPointNum[1];
                while (temp > 0) {
                    int rand = qids.length - 1 - k == 0 ? 0 : new Random().nextInt(qids.length - 1 - (k));
                    exam.add(qids[rand]);
                    Y[1]--;
                    if (Y[1] <= 0) break;
                    //rand被最后一位覆盖,并且rand的取值范围要小1
                    qids[rand] = qids[qids.length - 1 - k];
                    k++;
                    //要选择的数减一
                    temp--;
                }
                if (Y[1] <= 0) break;
            }
        }

        while (Y[2] > 0) {
            //每个知识点选eachPointNum[0]道题目
            for (Map.Entry<String, Set<String>> entry : jiandalist.entrySet()) {

                Set<String> set = entry.getValue();
                String[] qids = new String[set.size()];
                set.toArray(qids);
                int k = 0;
                int temp = eachPointNum[1];
                while (temp > 0) {

                    int rand = qids.length - 1 - k == 0 ? 0 : new Random().nextInt(qids.length - 1 - (k));
                    exam.add(qids[rand]);
                    Y[2]--;
                    if (Y[2] <= 0) break;
                    //rand被最后一位覆盖,并且rand的取值范围要小1
                    qids[rand] = qids[qids.length - 1 - k];
                    k++;
                    //要选择的数减一
                    temp--;
                }
                if (Y[2] <= 0) break;
            }
        }

        while (Y[3] > 0) {
            //每个知识点选eachPointNum[0]道题目
            for (Map.Entry<String, Set<String>> entry : panduanlist.entrySet()) {

                Set<String> set = entry.getValue();
                String[] qids = new String[set.size()];
                set.toArray(qids);
                int k = 0;
                int temp = eachPointNum[1];
                while (temp > 0) {
                    int rand = qids.length - 1 - k == 0 ? 0 : new Random().nextInt(qids.length - 1 - (k));
                    exam.add(qids[rand]);
                    Y[3]--;
                    if (Y[3] <= 0) break;
                    //rand被最后一位覆盖,并且rand的取值范围要小1
                    qids[rand] = qids[qids.length - 1 - k];
                    k++;
                    //要选择的数减一
                    temp--;
                }
                if (Y[3] <= 0) break;
            }
        }

    }

    private void mix(Map<String, Map<String, Double>> xuanzeTemp, Map<String, Map<String, Double>> tiankongTemp, Map<String, Map<String, Double>> jiandaTemp, Map<String, Map<String, Double>> panduanTemp, Map<String, Set<String>> xuanzelist, Map<String, Set<String>> tiankonglist, Map<String, Set<String>> jiandalist, Map<String, Set<String>> panduanlist) {
        for (Map.Entry<String, Map<String, Double>> entry : xuanzeTemp.entrySet()) {
            Set<String> set = new HashSet<>();
            for (String s : entry.getValue().keySet()) {
                set.add(s);
            }
            xuanzelist.put(entry.getKey(), set);
        }

        for (Map.Entry<String, Map<String, Double>> entry : tiankongTemp.entrySet()) {
            Set<String> set = new HashSet<>();
            for (String s : entry.getValue().keySet()) {
                set.add(s);
            }
            tiankonglist.put(entry.getKey(), set);
        }

        for (Map.Entry<String, Map<String, Double>> entry : jiandaTemp.entrySet()) {
            Set<String> set = new HashSet<>();
            for (String s : entry.getValue().keySet()) {
                set.add(s);
            }
            jiandalist.put(entry.getKey(), set);
        }

        for (Map.Entry<String, Map<String, Double>> entry : panduanTemp.entrySet()) {
            Set<String> set = new HashSet<>();
            for (String s : entry.getValue().keySet()) {
                set.add(s);
            }
            panduanlist.put(entry.getKey(), set);
        }
    }

    private void getQuestionIdAndDiffLevel(List<QuestionBank> questions, String[] kids, Map<String, Map<String, Double>> xuanzeTemp, Map<String, Map<String, Double>> tiankongTemp, Map<String, Map<String, Double>> jiandaTemp, Map<String, Map<String, Double>> panduanTemp) {
        //遍历所有的从前端取到的知识点
        for (String s : kids) {
            HashMap<String, Double> xuanzemap = new HashMap<>();
            HashMap<String, Double> tiankongmap = new HashMap<>();
            HashMap<String, Double> jiandamap = new HashMap<>();
            HashMap<String, Double> panduanmap = new HashMap<>();

            //遍历所有的问题
            for (QuestionBank question : questions) {
                //如果这个题目属于这个知识点
                if (question.getKid().equals(s)) {
                    if ("选择题".equals(question.getType()))
                        xuanzemap.put(question.getQid(), question.getQuestionRating());
                    else if ("填空题".equals(question.getType()))
                        tiankongmap.put(question.getQid(), question.getQuestionRating());
                    else if ("简答题".equals(question.getType()))
                        jiandamap.put(question.getQid(), question.getQuestionRating());
                    else panduanmap.put(question.getQid(), question.getQuestionRating());

                }
            }
            //因为之后运算需要判断
            xuanzeTemp.put(s, xuanzemap);
            tiankongTemp.put(s, tiankongmap);
            jiandaTemp.put(s, jiandamap);
            panduanTemp.put(s, panduanmap);

        }
    }

    private void calculateEachPointNumber(int[] eachPointNum, int[] Y, int length) {
        for (int i = 0; i < eachPointNum.length; i++) {
            if (Y[i] != 0)
                eachPointNum[i] = Y[i] / length + 1;
            else eachPointNum[i] = 0;
        }

    }

    public void createDoc(String pid, String path) throws IOException {

        TestPaper paper = mapper.findByid(pid);
        //找到所有的问题
        List<QuestionBank> questions = mapper.findQuestionByPid(pid);
        List<QuestionBank> xuanzelist = new ArrayList<>();
        List<QuestionBank> tiankonglist = new ArrayList<>();
        List<QuestionBank> jiandalist = new ArrayList<>();
        List<QuestionBank> panduanlist = new ArrayList<>();

        for (QuestionBank question : questions) {
            if ("选择题".equals(question.getType())) {
                xuanzelist.add(question);
            } else if ("填空题".equals(question.getType())) {
                tiankonglist.add(question);
            } else if ("判断题".equals(question.getType())) {
                panduanlist.add(question);
            } else jiandalist.add(question);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("paperName", paper.getName());
        map.put("Socre", paper.getScore());
        map.put("xuanzeNum", xuanzelist.size());
        map.put("tiankongNum", tiankonglist.size());
        map.put("panduanNum", panduanlist.size());
        map.put("jiandaNum", xuanzelist.size());

        map.put("xuanzelist", xuanzelist);
        map.put("tiankonglist", tiankonglist);
        map.put("jiandalist", jiandalist);
        map.put("panduanlist", panduanlist);

        map.put("paperSocre", paper.getScore());
        map.put("xuanzeSocre", paper.getChoiceSocre());
        map.put("tiankongSocre", paper.getFillBlankSocre());
        map.put("panduanSocre", paper.getJudgeSocre());
        map.put("jiandaSocre", paper.getAnswerSocre());

        //创建配置实例
        Configuration configuration = new Configuration();

        //设置编码
        configuration.setDefaultEncoding("UTF-8");

        //ftl模板文件
        configuration.setClassForTemplateLoading(PaperMakingApplication.class, "freemarker");

        //获取模板
        Template template = configuration.getTemplate("2.ftl");

        path = path.replaceAll(" \\ ", " / ");
        //输出文件
        File outFile = new File(path + "/" + pid + paper.getName() + ".doc");

        //如果输出目标文件夹不存在，则创建
        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }
        //将模板和数据模型合并生成文件
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));

        //将模板和数据模型合并生成文件
        //生成文件
        try {
            template.process(map, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        //关闭流
        out.flush();
        out.close();

    }

    public void deleteByCid(String cid) {
        mapper.deleteByCid(cid);
    }

    public void createPaper02(String pid, String[] xuanzelist, String[] tiankonglist, String[] jiandalist, String[] panduanlist) {
        if (xuanzelist != null)
            extractingMapper.save(pid, Arrays.asList(xuanzelist));
        if (tiankonglist != null)
            extractingMapper.save(pid, Arrays.asList(tiankonglist));
        if (panduanlist != null)
            extractingMapper.save(pid, Arrays.asList(panduanlist));
        if (jiandalist != null)
            extractingMapper.save(pid, Arrays.asList(jiandalist));
        System.out.println("111");
    }
}

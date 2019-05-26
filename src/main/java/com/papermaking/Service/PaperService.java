package com.papermaking.Service;

import com.papermaking.PaperMakingApplication;
import com.papermaking.mapper.KnowledgePointMapper;
import com.papermaking.mapper.PaperMapper;
import com.papermaking.pojo.KnowledgePoint;
import com.papermaking.pojo.Paper;
import com.papermaking.pojo.Question;
import com.papermaking.util.AutoGeneratingPaper;
import freemarker.template.Configuration;
import freemarker.template.Template;
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

    @Autowired
    private KnowledgePointMapper knowledgePointMapper;

    @Autowired
    private AutoGeneratingPaper autoGeneratingPaper;

    public Integer insert(Paper paper) {
        paperMapper.insert(paper);
        return paper.getpId();
    }

    /**
     * @param selectQuestionNum 用户输入的题目数量 默认顺序 选择题 填空题 简答题 判断题
     * @param kids              用户输入的知识点的id序号
     */
    public boolean createPaper(int[] selectQuestionNum, String[] kids, Integer pid, String cid, double difflevel) {
        List<KnowledgePoint> knowledgePoints = new ArrayList<>();
        //为了图快才使用循坏 不建议使用循环，应该直接使用一条sql去查询
        for (String kid : kids) {
            knowledgePoints.add(knowledgePointMapper.selectByPrimaryKey(Integer.parseInt(kid)));
        }
        //获得随机选择的题号
        List<Integer> qIds = autoGeneratingPaper.createPaper(selectQuestionNum, knowledgePoints, difflevel);
        if (qIds == null)
            return false;
        else {
            paperMapper.insertPaperQuestion(pid, qIds);
            return true;
        }

    }

    public List<Paper> selectAll() {
        return paperMapper.selectAll();
    }

    public Paper selectByPrimaryKey(Integer pId) {
        return paperMapper.selectByPrimaryKey(pId);
    }

    public void deleteByPrimaryKey(Integer pId) {
        Paper paper = paperMapper.selectByPrimaryKey(pId);
        List<Integer> pIds = new ArrayList<>();
        pIds.add(paper.getpId());
        paperMapper.deleteByPrimaryKey(pId);
        paperMapper.deletePaperQuestionByPids(pIds);
    }

    public void createDoc(String pid, String path) throws IOException {

        Paper paper = paperMapper.selectByPrimaryKey(Integer.parseInt(pid));
        //找到所有的问题
        List<Question> xuanzelist = new ArrayList<>();
        List<Question> tiankonglist = new ArrayList<>();
        List<Question> jiandalist = new ArrayList<>();
        List<Question> panduanlist = new ArrayList<>();

        for (Question question : paper.getQuestions()) {
            if ("选择题".equals(question.getqType())) {
                xuanzelist.add(question);
            } else if ("填空题".equals(question.getqType())) {
                tiankonglist.add(question);
            } else if ("判断题".equals(question.getqType())) {
                panduanlist.add(question);
            } else jiandalist.add(question);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("paperName", paper.getpName());
        map.put("Socre", paper.getpScore());
        map.put("xuanzeNum", xuanzelist.size());
        map.put("tiankongNum", tiankonglist.size());
        map.put("panduanNum", panduanlist.size());
        map.put("jiandaNum", xuanzelist.size());

        map.put("xuanzelist", xuanzelist);
        map.put("tiankonglist", tiankonglist);
        map.put("jiandalist", jiandalist);
        map.put("panduanlist", panduanlist);

        map.put("paperSocre", paper.getpScore());
        map.put("xuanzeSocre", paper.getpChoicesocre());
        map.put("tiankongSocre", paper.getpFillblanksocre());
        map.put("panduanSocre", paper.getpJudgesocre());
        map.put("jiandaSocre", paper.getpAnswersocre());

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
        File outFile = new File(path + "/" + pid + paper.getpName() + ".doc");

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

    //    public void deleteByCid(String cid) {
//        mapper.deleteByCid(cid);
//    }
//
    public void createPaper02(Integer pid, String[] xuanzelist, String[] tiankonglist, String[] jiandalist, String[] panduanlist) {
        if (xuanzelist != null) {
            List<Integer> list = new ArrayList<>();
            for (String s : xuanzelist) {
                list.add(Integer.parseInt(s));
            }
            paperMapper.insertPaperQuestion(pid, list);
        }

        if (tiankonglist != null) {
            List<Integer> list = new ArrayList<>();
            for (String s : tiankonglist) {
                list.add(Integer.parseInt(s));
            }
            paperMapper.insertPaperQuestion(pid, list);
        }

        if (panduanlist != null) {
            List<Integer> list = new ArrayList<>();
            for (String s : jiandalist) {
                list.add(Integer.parseInt(s));
            }
            paperMapper.insertPaperQuestion(pid, list);
        }

        if (jiandalist != null) {
            List<Integer> list = new ArrayList<>();
            for (String s : panduanlist) {
                list.add(Integer.parseInt(s));
            }
            paperMapper.insertPaperQuestion(pid, list);
        }
    }
}

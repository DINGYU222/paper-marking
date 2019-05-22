import com.papermaking.PaperMakingApplication;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class testFreeMark {
    @Test
    public void test() throws IOException {

        //创建配置实例
        Configuration configuration = new Configuration();

        //设置编码
        configuration.setDefaultEncoding("UTF-8");


        //ftl模板文件
        configuration.setClassForTemplateLoading(PaperMakingApplication.class,"");

        //获取模板
        Template template = configuration.getTemplate("YYYYY.ftl");

        //输出文件
        File outFile = new File("C:\\Users\\70241\\Desktop\\笔记\\b.doc");

        //如果输出目标文件夹不存在，则创建
        if (!outFile.getParentFile().exists()){
            outFile.getParentFile().mkdirs();
        }

        //将模板和数据模型合并生成文件
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));

        Map<String,Object> map = new HashMap<>();
        map.put("username","dyuyuyu");
        map.put("password","1231312");

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

    @Test
    public void test2(){

    }

    @Test
    public void test01(){
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }


}

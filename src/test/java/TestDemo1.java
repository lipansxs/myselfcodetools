import com.sxs.config.Config;
import com.sxs.pojo.OptionFileBean;
import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @program: selftools
 * @description:
 * @author: LiPan
 * @create: 2020-07-22 21:09
 **/
public class TestDemo1 {

//    @Test
//    public void test1() throws IOException {
//        Properties p = Resources.getResourceAsProperties("test.properties");
//        System.out.println(p.get("a"));
//    }
//
//    @Test
//    public void test2() throws IOException {
//        Yaml yaml = new Yaml();
//        Map<String, Map<String, Map<String, List<String>>>> result = yaml.load(Resources.getResourceAsStream("config.yml"));
//        System.out.println(result.get("file").get("delete").get("name").getClass());
//        System.out.println(result.keySet());
//    }
//
//    @Test
//    public void test3() throws IOException {
//        Yaml yaml = new Yaml(new Constructor(OptionFileBean.class));
//
//        OptionFileBean fileBean = yaml.load(Resources.getResourceAsStream("config.yml"));
//
//        System.out.println(fileBean);
//    }
//
//    @Test
//    public void test4() throws IOException {
//        Yaml yaml = new Yaml(new Constructor(Config.class));
//        Config config = yaml.load(Resources.getResourceAsStream("config.yml"));
//        System.out.println(config);
//    }
//
//    @Test
//    public void test5() {
//        System.out.println(Config.getConfig());
//    }
//
//    @Test
//    public void test6() {
//        System.out.println(Config.getConfig().getFile().getDelete().get("suffixs"));
//        System.out.println(Config.getConfig().getFile().getDelete().get("suffixs"));
////        System.out.println(String.valueOf(Config.getConfig().getFile().getRename().get("regex")));
//    }
//
//    @Test
//    public void test7() {
//        System.out.println(Config.getConfig().getFile());
////        System.out.println(Config.getConfig().getFile().getRename().get("regex"));
//    }

    @Test
    public void test8() {
        System.out.println(Config.getConfig().getFile().getRename());
    }
}

package com.sxs.strategeimpl.deleteStrageImpl;

import com.sxs.config.Config;
import com.sxs.stratege.DelStratege;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: DesignPattern
 * @description: 通过文件名后缀来删除文件的策略
 * @author: 树先生
 * @create: 2020-03-22 21:05
 **/
public class DelBySuffixStrategeImpl implements DelStratege {

    /**
     * 文件名后缀
     */
    private String suffix;

    /**
     * 用来写日志的对象
     */
    private Logger logger = Logger.getLogger(DelBySuffixStrategeImpl.class);

    /**
     * 用来获取文件名后缀的Matcher对象
     */
    private Matcher matcherFileSuffix;

    /**
     * 用来获取配置文件中后缀所组成的Matcher对象
     */
    private Matcher matcher;
    private Pattern pattern;


    /**
     * 全局配置对象
     */
    private Config config = Config.getConfig();

    public DelBySuffixStrategeImpl() {
        StringBuilder sb = new StringBuilder();
        // 获取所有的电视名标签
//        for (Element ele: XMLUtil.getEleByTagName("delete-by-suffix").elements("suffix")){
        for(String suffix: config.getFile().getDelete().get("suffixs")) {
            // 构建正则表达式
            sb.append(suffix.strip() + "|");
        }
        // 删除最后一个 | 字符
        sb.deleteCharAt(sb.lastIndexOf("|"));

        // 编译正则表达式
        this.matcher = Pattern.compile(sb.toString()).matcher("");
    }

    @Override
    public boolean delete(File file) {
        this.suffix = getFileSuffix(file.getName());

        // 获取文件名后缀失败
        if (this.suffix == null) {
            logger.error(file.getName() + "获取文件名后缀失败！");
            return false;
        }
        // 设置匹配字符串为文件名后缀
        this.matcher.reset(this.suffix);

        // 如果匹配成功就删除
        if (this.matcher.find()) {
            return file.delete();
        }
        logger.info(file.getName() + " 后缀匹配不成功！");
        return false;
    }

    /**
     * 获取文件名的后缀
     * @param fileName
     * @return
     */
    private String getFileSuffix(String fileName){

        this.matcherFileSuffix = pattern.compile("^.*?\\.(.*)$").matcher(fileName);

        if (matcherFileSuffix.find()) {
            return this.matcherFileSuffix.group(1);
        }
        return null;
    }
}

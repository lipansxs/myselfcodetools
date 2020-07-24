package com.sxs.strategeimpl.deleteStrageImpl;

import com.sxs.config.Config;
import com.sxs.stratege.DelStratege;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: DesignPattern
 * @description: 通过文件名来删除文件的策略
 * @author: 树先生
 * @create: 2020-03-22 21:03
 **/
public class DelByNameStrategeImpl implements DelStratege {

    /**
     * 正则表达式需要用到的Matcher对象
     */
    private Matcher matcher;

    /**
     * 全局配置对象
     */
    private Config config = Config.getConfig();

    /**
     * 初始化Matcher对象
     */
    public DelByNameStrategeImpl() {

        StringBuilder sb = new StringBuilder();
        // 获取所有的电视名标签
        for (String name: config.getFile().getDelete().get("names")){

            // 构建正则表达式
            sb.append(name.strip() + "|");
        }
        // 删除最后一个 | 字符
        sb.deleteCharAt(sb.lastIndexOf("|"));

        // 编译正则表达式
        this.matcher = Pattern.compile(sb.toString()).matcher("");
    }

    @Override
    public boolean delete(File file) {
        boolean result = false;
        this.matcher.reset(file.getName());

        if (this.matcher.find()) {
            result = file.delete();
        }
        return result;
    }

}

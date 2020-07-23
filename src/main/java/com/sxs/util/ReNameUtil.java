package com.sxs.util;

import com.sxs.config.Config;
import com.sxs.stratege.ReNameStratege;
import com.sxs.strategeimpl.ReNameByRegexStrategeImpl;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * @program: DesignPattern
 * @description: 修改文件名的工具类
 * @author: 树先生
 * @create: 2020-03-22 21:11
 **/
public class ReNameUtil {

    private static Logger logger = Logger.getLogger(ReNameUtil.class);

    /**
     * 全局配置文件
     */
    private static Config config = Config.getConfig();

    /**
     * 通过传入不同的重命名策略来重命名文件
     * @param reNameStratege 重命名文件的策略
     * @param root 需要修改的文件的文件夹根目录
     */
    public static void reName(ReNameStratege reNameStratege, File root){
        File file = root;


        if (file.isDirectory()) {
            // 修改文件名的格式为：第一组 + 第二组
            for (File fileTmp : file.listFiles()) {
                reName(reNameStratege, fileTmp);
            }
            return;
        }

        // 如果是文件就直接修改文件名,格式为：数字 + 后缀
        reNameStratege.reName(file);
        return;

    }

    public static void reNameByRegex(){
        reName(new ReNameByRegexStrategeImpl(), new File(config.getFile().getPath()));
    }
}

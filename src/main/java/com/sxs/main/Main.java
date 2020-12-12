package com.sxs.main;

import com.sxs.config.Config;
import com.sxs.util.DeleteUtil;
import com.sxs.util.ReNameUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @program: DesignPattern
 * @description: 测试
 * @author: 树先生
 * @create: 2020-03-22 19:13
 **/
@Slf4j
public class Main {

    private static Config config = Config.getConfig();

    /**
     * 删除文件和修改文件名流程方法
     */
    private static void delAndRenameSequence() {
        if (config.getFile().getDelete() != null) {
            if (config.getFile().getDelete().get("names") != null) {
                log.info("开始通过名字删除文件！！！");
                DeleteUtil.delByName();
            }

            if (config.getFile().getDelete().get("suffixs") != null) {
                log.info("开始通过后缀删除文件！！！");
                DeleteUtil.delBySuffix();
            }
        }

        if (config.getFile().getRename() != null) {
            log.info("开始通过正则表达式重命名文件！！！");
            ReNameUtil.reNameByRegex();
        }
    }

    public static void main(String[] args) {
        delAndRenameSequence();
    }
}

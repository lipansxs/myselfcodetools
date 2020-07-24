package com.sxs.strategeimpl.reNameStrageImpl;

import com.sxs.config.Config;
import com.sxs.stratege.ReNameStratege;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: DesignPattern
 * @description: 通过读取配置文件的正则表达式来修改文件名策略
 * @author: 树先生
 * @create: 2020-03-22 21:06
 **/
public class ReNameByRegexStrategeImpl implements ReNameStratege {

    /**
     * 用来记录重命名日志
     */
    private static Logger logger = Logger.getLogger(ReNameByRegexStrategeImpl.class);

    /**
     * 正则表达式匹配用的Matcher对象
     */
    private Matcher matcher;

    /**
     * 修改成功的文件名匹配用的Matcher对象
     */
    private Matcher successMatcher;

    /**
     * 全局配置文件
     */
    private Config config = Config.getConfig();

    public ReNameByRegexStrategeImpl(){
        this.matcher = Pattern.compile(config.getFile().getRename().get("regex")).matcher("");
        this.successMatcher = Pattern.compile(config.getFile().getRename().get("success.regex")).matcher("");
    }

    @Override
    public boolean reName(File file) {
        // 文件名
        String fileName = null;
        // 视频文件后缀
        String suffix = null;

        // 返回结果
        boolean result = false;

        // 把matcher对象的匹配字符串设置为文件的名字
        this.matcher.reset(file.getName());
        this.successMatcher.reset(file.getName());

        // 如果已经修改过了，就不在修改了
        if (this.successMatcher.find()) {
            logger.info(file.getName() + "已经修改成功了！");
        }else if (this.matcher.find()) {
            // 如果能够匹配，就获取到匹配的字符串
            // 获取电视剧的集数，如果集数字符串长度为1位，就再前面加一个0
            if (this.matcher.groupCount() == 2) {
                String matchStr = this.matcher.group("name"); // 获取集数即文件名
                suffix = this.matcher.group("suffix"); // 获取视频文件的后缀
                // 匹配电视剧的集数
                fileName = matchStr.length() < 2? "0" + matchStr:matchStr;

                result = file.renameTo(new File(file.getParent() + "/" + fileName + "." +  suffix));

                if (result) {
                    logger.info(file.getName() + "修改为 ->" + fileName + "." +  suffix + "成功！");
                }else{
                    logger.info(file.getName() + "修改为 ->" + fileName + "." +  suffix + "失败！");
                }

            }
        }else{
            logger.error("修改文件名失败，正则表达式匹配失败：" + file.getName());
        }
        return result;
    }
}

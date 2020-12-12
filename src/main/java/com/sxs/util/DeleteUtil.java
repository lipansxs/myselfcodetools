package com.sxs.util;

import com.sxs.config.Config;
import com.sxs.stratege.DelStratege;
import com.sxs.strategeimpl.deleteStrageImpl.DelByNameStrategeImpl;
import com.sxs.strategeimpl.deleteStrageImpl.DelBySuffixStrategeImpl;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * @program: DesignPattern
 * @description: 删除文件的工具类
 * @author: 树先生
 * @create: 2020-03-22 21:11
 **/
public class DeleteUtil {

    /**
     * 用来记录日志的对象
     */
    private static Logger logger = Logger.getLogger(DeleteUtil.class);

    /**
     * 全局配置文件
     */
    private static Config config = Config.getConfig();

    /**
     * 通过传入不同的删除策略运用不同的策略删除文件
     *
     * @param delStratege 删除文件的策略
     */
    public static void delete(DelStratege delStratege) {
        File file = new File(config.getFile().getPath());

        // 通过另外一个方法进行递归删除
        delete(file, delStratege);
    }

    /**
     * 实际上用来删除文件的方法
     *
     * @param file
     */
    private static void delete(File file, DelStratege delStratege) {

        // 如果file是一个文件 就直接删除 然后返回
        if (file.isFile()) {
            logger.info(file.getName() + (delStratege.delete(file) ? "删除成功！" : "删除失败！(不在删除列表)"));
            return;
        }

        // 如果file是一个目录，就循环遍历所有的文件进行删除
        for (File fileTmp : file.listFiles()) {
            // 对文件夹下的每一个文件再次调用这个方法，进行递归删除
            delete(fileTmp, delStratege);
        }
    }


    /**
     * 通过文件名策略来删除文件
     */
    public static void delByName() {
        delete(new DelByNameStrategeImpl());
    }

    /**
     * 通过文件后缀来删除文件
     */
    public static void delBySuffix() {
        delete(new DelBySuffixStrategeImpl());
    }

    /**
     * 通过文件大小删除（删除空文件或文件夹）
     * 如果文件大小<=size就删除
     *
     * @param file 需要删除文件的根目录
     * @param size 指定需要删除的文件的大小
     */
    public static void delBySize(File file, Integer size) {
        /**
         * 如果文件的大小为<= size就删除（如果是目录也一样）
         */

        // 如果size为null,就默认为0
        if (null == size) {
            size = 0;
        }

        if (file.length() < size) {

            // 如果是文件夹，就要先删除文件夹下面的文件再删除文件夹,递归删除
            if (file.isDirectory()) {
                for (File fileTmp : file.listFiles()) {
                    delBySize(fileTmp, size);
                }
            }

            // 如果删除成功就打印日志
            if (file.delete()) {
                logger.info(file.getName() + " 大小 <= " + size + " 删除成功！");
                return;
            }
        }
        logger.info(file.getName() + " 大小 > " + size + " 删除失败！");
    }

}

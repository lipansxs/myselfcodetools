package com.sxs.stratege;

import org.dom4j.Element;

import java.io.File;

public interface DelStratege {

    /**
     * 删除文件
     * @param file 需要删除的文件
     * @return 删除成功返回true 失败返回false
     */
    public boolean delete(File file);
}

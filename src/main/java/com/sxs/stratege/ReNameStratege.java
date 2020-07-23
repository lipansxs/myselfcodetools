package com.sxs.stratege;

import org.dom4j.Element;

import java.io.File;

public interface ReNameStratege {

    /**
     * 修改文件名
     * @param file 需要修改名字的文件
     * @return 如果修改成功返回true 否则返回false
     */
    public boolean reName(File file);
}

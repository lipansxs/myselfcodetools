package com.sxs.config;

import com.sxs.pojo.OptionFileBean;
import lombok.Data;
import org.apache.ibatis.io.Resources;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;

/**
 * @program: selftools
 * @description: 整个工具的配置类
 * @author: LiPan
 * @create: 2020-07-23 13:11
 **/
@Data
public class Config {
    private OptionFileBean file;

    public static Config getConfig() {
        Yaml yaml = new Yaml(new Constructor(Config.class));
        Config config = null;
        try {
            config = yaml.load(Resources.getResourceAsStream("config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}

package com.shuframework.boot2.es.enums;

/**
 * @author shuheng
 */
public enum IndexEnum {

    BOOK1("test_book2", "test"),

    SHQ_RULES("shq_rules", "服务端扫描规则库"),

    SHQ_WHITE_MODULES("shq_white_modules", "白名单"),

    SHQ_GAME_MODULES("shq_game_modules", "游戏模块：某款游戏 相关 文件规则"),

    SHQ_GAME_CONFIGS("shq_game_configs", "游戏全局配置"),

    SHQ_POSITIVESCANS("shq_positivescans", "客户端扫描规则");

    IndexEnum(String index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    private String index;
    private String desc;

    public String getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }
}

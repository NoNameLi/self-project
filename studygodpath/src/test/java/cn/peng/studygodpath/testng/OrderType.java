package cn.peng.studygodpath.testng;

/**
 * Created by remote on 2017/10/19.
 */
enum OrderType {
    /**
     * 安装单
     */
    install("安装"),
    /**
     * 维修单
     */
    maintain("维修"),
    /**
     * 投诉单
     */
    complaint("投诉"),
    /**
     * 事件单
     */
    event("投诉"),;

    public String name;

    private OrderType(String name) {
        this.name = name;
    }
}

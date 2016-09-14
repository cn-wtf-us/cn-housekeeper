package cn.feicui.com.houserkeeper.entity;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class TelClassInfo {
    private final String name;
    private final int idx;

    public int getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public TelClassInfo(String name, int idx) {

        this.name = name;
        this.idx = idx;
    }
}

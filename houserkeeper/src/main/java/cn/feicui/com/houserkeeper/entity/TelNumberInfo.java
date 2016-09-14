package cn.feicui.com.houserkeeper.entity;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class TelNumberInfo {
    private final String name;
    private final String number;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public TelNumberInfo(String name, String number) {

        this.name = name;
        this.number = number;
    }
}

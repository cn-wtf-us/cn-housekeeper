package cn.feicui.com.houserkeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.feicui.com.houserkeeper.R;
import cn.feicui.com.houserkeeper.entity.TelClassInfo;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class TelClassAdapter extends BaseAdapter {

    private final ArrayList<TelClassInfo> telClassInfos;
    private final LayoutInflater inflater;

    public TelClassAdapter(Context context, ArrayList<TelClassInfo> telClassInfos) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.telClassInfos = telClassInfos;
    }

    @Override
    public int getCount() {
        return telClassInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return telClassInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.item_tel_class, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        tv.setText(telClassInfos.get(position).getName());
        return convertView;
    }
}

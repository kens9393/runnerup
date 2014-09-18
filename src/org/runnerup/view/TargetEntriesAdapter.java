package org.runnerup.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.runnerup.R;

import java.util.HashSet;

/**
 * Created by jonas on 9/18/14.
 */
class TargetEntriesAdapter extends BaseAdapter {

    String[] entries;
    LayoutInflater inflator;
    HashSet<String> disabled;

    TargetEntriesAdapter(Context ctx) {
        inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        entries = ctx.getResources().getStringArray(R.array.targetEntries);
    }

    void addDisabled(int i) {
        if (disabled == null)
            disabled = new HashSet<String>();
        if (i < entries.length)
            disabled.add(entries[i]);
    }

    @Override
    public int getCount() {
        return entries.length;
    }

    @Override
    public Object getItem(int position) {
        if (position < entries.length)
            return entries[position];
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String str = (String) getItem(position);
        if (convertView == null) {
            convertView = inflator.inflate(android.R.layout.simple_spinner_dropdown_item, parent,
                    false);
        }
        TextView ret = (TextView) convertView.findViewById(android.R.id.text1);
        ret.setText(str);

        if (disabled != null && disabled.contains(str))
            convertView.setEnabled(false);
        else
            convertView.setEnabled(true);

        return convertView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return disabled == null || disabled.size() == 0;
    }

    @Override
    public boolean isEnabled(int position) {
        if (disabled == null)
            return true;

        String str = (String) getItem(position);
        if (str == null)
            return true;

        if (disabled.contains(str))
            return false;

        return true;
    }
}

package org.lsh.registerlogin;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class adapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;
    private ArrayList<String> itext =new ArrayList<String>();
    public adapter(FragmentManager fm, BluetoothSPP bt) {
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new FragmentWork());
        items.add(new Web());
        items.add(new settting(bt));

        itext.add("할일");
        itext.add("게시판");
        itext.add("세팅");
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itext.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}

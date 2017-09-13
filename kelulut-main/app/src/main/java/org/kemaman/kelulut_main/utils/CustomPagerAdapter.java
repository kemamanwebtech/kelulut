package org.kemaman.kelulut_main.utils;

/**
 * Created by raf on 2/3/17.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.kemaman.kelulut_main.dto.QuestionDTO;
import java.util.ArrayList;

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;

    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    // @Override
    public Object instantiateItem(ViewGroup collection, int position, ArrayList<QuestionDTO> listQuestion ) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(listQuestion.size(), collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

//    @Override
//    public int getCount() {
//        return listQuestion.size();
//    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        ModelObject customPagerEnum = ModelObject.values()[position];
//        return mContext.getString(customPagerEnum.getTitleResId());
//    }

    public int getCount(){
        return 1;
    }
}
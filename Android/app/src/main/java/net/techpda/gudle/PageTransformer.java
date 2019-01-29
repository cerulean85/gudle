package net.techpda.gudle;

import android.support.v4.view.ViewPager;
import android.view.View;

public class PageTransformer implements ViewPager.PageTransformer {
    private View firstImage, secondImage, thirdImage;

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();

    }
}
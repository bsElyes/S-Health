package main.health.com.s_health.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import main.health.com.s_health.R;

public class SIntro extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(AppIntroFragment.newInstance("SHealth", "Bkabkabaa", R.drawable.london_bridge, Color.parseColor("#10e954")));
        addSlide(AppIntroFragment.newInstance("SHealth", "Bkabkabaa", R.drawable.karlov_bridge, Color.parseColor("#e91043")));
        addSlide(AppIntroFragment.newInstance("SHealth", "Bkabkabaa", R.drawable.golden_gate, Color.parseColor("#106ce9")));
        setFadeAnimation();

        //setCustomTransformer(new ParallaxTransform());
    }

    private void loadMainActivity(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    public class ParallaxTransform implements ViewPager.PageTransformer {
        private float parallaxSpeed = 2.6f;

        @Override
        public void transformPage(View view, float pagePos) {
            if (view.getTag() == null || !(view.getTag() instanceof ViewHolder)) { return; }
            View parallaxView = ((ViewHolder) view.getTag()).getParallaxView();

            if (parallaxView == null) { return; }
            if (pagePos <= -2 || pagePos >= 2) { return; }
            parallaxView.setTranslationX(-pagePos * parallaxSpeed * view.getWidth());
        }

        public void setParallaxSpeed(float parallaxSpeed) {
            this.parallaxSpeed = parallaxSpeed;
        }
    }

    public class ViewHolder {
        private View parallaxView;

        private ViewHolder() { this.parallaxView = null; }
        public ViewHolder(View parallaxView) { this.parallaxView = parallaxView; }
        public View getParallaxView() { return parallaxView; }
        public void setParallaxView(View parallaxView) { this.parallaxView = parallaxView; }
    }
}

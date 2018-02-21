package com.error_found.kotdroid.cgscopy.views.activities;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.error_found.kotdroid.cgscopy.R;

import butterknife.BindView;

public class AnimatorActivity extends BaseActivity {


    @BindView(R.id.tv_ball)
    TextView tvBall;
    @BindView(R.id.tv_fade_in)
    TextView tvFadeIn;
    @BindView(R.id.tv_fade_out)
    TextView tvFadeOut;

    @BindView(R.id.tv_overshoot_slide_down)
    TextView tvOverShootSlideDown;
    @BindView(R.id.tv_overshoot_slide_up)
    TextView tvOverShootSlideUp;

    @BindView(R.id.tv_slide_down)
    TextView tvSlideDown;
    @BindView(R.id.tv_slide_up)
    TextView tvSlideUp;

    @BindView(R.id.tv_slide_left_in)
    TextView tvSlideLeftIn;
    @BindView(R.id.tv_slide_left_out)
    TextView tvSlideLeftOut;

    @BindView(R.id.tv_slide_right_in)
    TextView tvSlideRightIn;
    @BindView(R.id.tv_slide_right_out)
    TextView tvSlideRightOut;

    ObjectAnimator fadeInAnimator, fadeOutAnimator, overShootSlideDownAnimator, overShootSlideUpAnimator, slideDownAnimator, slideUpAnimator, slideLeftInAnimator, slideLeftOutAnimator, slideRightInAnimator, slideRightOutAnimator;
    AnimatorSet scaleFadeInAnimatorSet, scaleFadeOutAnimatorSet, slideLeftInFadeInAnimatorSet, slideLeftOutFadeOutAnimatorSet, slideRightInFadeInAnimatorSet, slideRightOutFadeOutAnimatorSet;


    @Override
    protected void init() {
        loadingAnimatorFromXML();
        settingAnimators();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_animator;
    }


    @Override
    public void nameError(String err) {

    }

    @Override
    public void contactError(String err) {

    }

    @Override
    public void sessionIdErr() {

    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    private void settingAnimators() {
        // checking object animator
        fadeInAnimator.setTarget(tvFadeIn);
        fadeInAnimator.setDuration(5000);
        fadeInAnimator.setRepeatCount(ValueAnimator.INFINITE);
        fadeInAnimator.start();
        fadeOutAnimator.setTarget(tvFadeOut);
        fadeOutAnimator.setDuration(5000);
        fadeOutAnimator.setRepeatCount(ValueAnimator.INFINITE);
        fadeOutAnimator.start();

        overShootSlideDownAnimator.setTarget(tvOverShootSlideDown);
        overShootSlideDownAnimator.setDuration(5000);
        overShootSlideDownAnimator.setRepeatCount(ValueAnimator.INFINITE);
        overShootSlideDownAnimator.start();

        overShootSlideUpAnimator.setTarget(tvOverShootSlideUp);
        overShootSlideUpAnimator.start();

        slideDownAnimator.setTarget(tvSlideDown);
        slideDownAnimator.setDuration(3000);
        slideDownAnimator.setRepeatCount(ValueAnimator.INFINITE);
        slideDownAnimator.start();
        slideUpAnimator.setTarget(tvSlideUp);
        slideUpAnimator.start();

        slideLeftInAnimator.setTarget(tvSlideLeftIn);
        slideLeftInAnimator.start();
        slideLeftOutAnimator.setTarget(tvSlideLeftOut);
        slideLeftOutAnimator.start();

        slideRightInAnimator.setTarget(tvSlideRightIn);
        slideRightInAnimator.start();
        slideRightOutAnimator.setTarget(tvSlideRightOut);
        slideRightOutAnimator.start();

//        scaleFadeInAnimatorSet.setTarget(tvFadeIn);
//        scaleFadeInAnimatorSet.start();


        //keyframes
//        Keyframe kf0=Keyframe.ofFloat(0f,0f);
//        Keyframe kf1=Keyframe.ofFloat(.5f,360f);
//        Keyframe kf2=Keyframe.ofFloat(1f,0f);
//
//        PropertyValuesHolder pvhRotation=PropertyValuesHolder.ofKeyframe("rotation",kf0,kf1,kf2);
//        ObjectAnimator rotationAnimator=ObjectAnimator.ofPropertyValuesHolder(tvFadeIn,pvhRotation);
//        rotationAnimator.setDuration(5000);
//        rotationAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        rotationAnimator.start();






    }

    private void loadingAnimatorFromXML() {


//        animators
        fadeInAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.fade_in);
        fadeOutAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.fade_out);

        overShootSlideDownAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.overshoot_slide_down);
        overShootSlideUpAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.overshoot_slide_up);

        slideDownAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.slide_down);
        slideUpAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.slide_up);

        slideLeftInAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.slide_left_in);
        slideLeftOutAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.slide_left_out);

        slideRightInAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.slide_right_in);
        slideRightOutAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.slide_right_out);


        //animator set
        scaleFadeInAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.scale_fade_in);
        scaleFadeOutAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.scale_fade_out);

        slideLeftInFadeInAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.slide_left_in_fade_in);
        slideLeftOutFadeOutAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.slide_left_out_fade_out);

        slideRightInFadeInAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.slide_right_in_fade_in);
        slideRightOutFadeOutAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.slide_right_out_fade_out);
    }
}

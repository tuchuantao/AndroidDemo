package com.kevin.demo.module.lottie;


import android.graphics.drawable.Animatable;

import androidx.annotation.Nullable;

import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationBackendDelegate;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.drawable.AnimationListener;
import com.facebook.imagepipeline.image.ImageInfo;

/**
 * 控制webp循环次数
 * <p>
 * Created by tuchuantao on 2020/11/18
 */
public class LoopCountModifyControllerLis extends BaseControllerListener<ImageInfo> {

  public static final int SINGLE_PLAY_COUNT = 1;

  private Animatable mAnimatable;
  private AnimationListener mAnimListener;
  private int mLoopCount;

  public LoopCountModifyControllerLis(int loopCount) {
    this.mLoopCount = loopCount;
  }

  public LoopCountModifyControllerLis(int loopCount, AnimationListener animListener) {
    this.mLoopCount = loopCount;
    this.mAnimListener = animListener;
  }

  public Animatable getAnimatable() {
    return mAnimatable;
  }

  @Override
  public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo,
                              @Nullable Animatable animatable) {
    super.onFinalImageSet(id, imageInfo, animatable);
    this.mAnimatable = animatable;
    if (animatable instanceof AnimatedDrawable2) {
      AnimatedDrawable2 animatedDrawable2 = (AnimatedDrawable2) animatable;
      animatedDrawable2.setAnimationBackend(
          new LoopCountModifyingBackend(animatedDrawable2.getAnimationBackend(), mLoopCount));
      if (mAnimListener != null) {
        animatedDrawable2.setAnimationListener(mAnimListener);
      }
    }
  }
}

class LoopCountModifyingBackend extends AnimationBackendDelegate {

  private int mLoopCount;

  public LoopCountModifyingBackend(
      @androidx.annotation.Nullable AnimationBackend animationBackend,
      int loopCount) {
    super(animationBackend);
    mLoopCount = loopCount;
  }

  @Override
  public int getLoopCount() {
    return mLoopCount;
  }
}

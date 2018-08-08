package com.example.kuirin.androidtvbl;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v17.leanback.widget.BaseCardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuCardView extends BaseCardView {
    private ImageView mImageView;
    private ViewGroup mInfoArea;
    private TextView mTitleView;
    private boolean mAttachedToWindow;
    ObjectAnimator mFadeInAnimator;

    public MenuCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.buildImageCardView(attrs, defStyleAttr, android.support.v17.leanback.R.style.Widget_Leanback_ImageCardView);
    }

    private void buildImageCardView(AttributeSet attrs, int defStyleAttr, int defStyle) {
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        inflater.inflate(R.layout.main_menu, this);
        TypedArray cardAttrs = this.getContext().obtainStyledAttributes(attrs, android.support.v17.leanback.R.styleable.lbImageCardView, defStyleAttr, defStyle);
        this.mImageView = this.findViewById(R.id.menu_img);
        if (this.mImageView.getDrawable() == null) {
            this.mImageView.setVisibility(INVISIBLE);
        }

        this.mFadeInAnimator = ObjectAnimator.ofFloat(this.mImageView, "alpha", new float[]{1.0F});
        this.mFadeInAnimator.setDuration((long) this.mImageView.getResources().getInteger(17694720));
        this.mInfoArea = this.findViewById(R.id.menu_field);

        this.mTitleView = this.findViewById(R.id.menu_title);


        Drawable background = cardAttrs.getDrawable(android.support.v17.leanback.R.styleable.lbImageCardView_infoAreaBackground);
        if (null != background) {
            this.setInfoAreaBackground(background);
        }
        cardAttrs.recycle();
    }

    public MenuCardView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MenuCardView(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v17.leanback.R.attr.imageCardViewStyle);
    }

    public final ImageView getMainImageView() {
        return this.mImageView;
    }

    public void setMainImageAdjustViewBounds(boolean adjustViewBounds) {
        if (this.mImageView != null) {
            this.mImageView.setAdjustViewBounds(adjustViewBounds);
        }
    }

    public void setMainImageScaleType(ImageView.ScaleType scaleType) {
        if (this.mImageView != null) {
            this.mImageView.setScaleType(scaleType);
        }

    }

    public void setMainImage(Drawable drawable) {
        this.setMainImage(drawable, true);
    }

    public void setMainImage(Drawable drawable, boolean fade) {
        if (this.mImageView != null) {
            this.mImageView.setImageDrawable(drawable);
            if (drawable == null) {
                this.mFadeInAnimator.cancel();
                this.mImageView.setAlpha(1.0F);
                this.mImageView.setVisibility(INVISIBLE);
            } else {
                this.mImageView.setVisibility(VISIBLE);
                if (fade) {
                    this.fadeIn();
                } else {
                    this.mFadeInAnimator.cancel();
                    this.mImageView.setAlpha(1.0F);
                }
            }

        }
    }

    public void setMainImageDimensions(int width, int height) {
        android.view.ViewGroup.LayoutParams lp = this.mImageView.getLayoutParams();
        lp.width = width;
        lp.height = height;
        this.mImageView.setLayoutParams(lp);
    }

    public Drawable getMainImage() {
        return this.mImageView == null ? null : this.mImageView.getDrawable();
    }

    public Drawable getInfoAreaBackground() {
        return this.mInfoArea != null ? this.mInfoArea.getBackground() : null;
    }

    public void setInfoAreaBackground(Drawable drawable) {
        if (this.mInfoArea != null) {
            this.mInfoArea.setBackground(drawable);
        }

    }

    public void setInfoAreaBackgroundColor(@ColorInt int color) {
        if (this.mInfoArea != null) {
            this.mInfoArea.setBackgroundColor(color);
        }

    }

    public void setTitleText(CharSequence text) {
        if (this.mTitleView != null) {
            this.mTitleView.setText(text);
        }
    }

    public CharSequence getTitleText() {
        return this.mTitleView == null ? null : this.mTitleView.getText();
    }

    private void fadeIn() {
        this.mImageView.setAlpha(0.0F);
        if (this.mAttachedToWindow) {
            this.mFadeInAnimator.start();
        }

    }

    public boolean hasOverlappingRendering() {
        return false;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        if (this.mImageView.getAlpha() == 0.0F) {
            this.fadeIn();
        }

    }

    protected void onDetachedFromWindow() {
        this.mAttachedToWindow = false;
        this.mFadeInAnimator.cancel();
        this.mImageView.setAlpha(1.0F);
        super.onDetachedFromWindow();
    }
}

package com.example.kuirin.androidtvbl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.TitleViewAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuTitleView extends RelativeLayout implements TitleViewAdapter.Provider {
    private final TextView mTitleView;
    private CharSequence mTitle;

    private final TitleViewAdapter mTitleViewAdapter = new TitleViewAdapter() {
        @Override
        public View getSearchAffordanceView() {
            return null;
        }

        @Override
        public void setTitle(CharSequence titleText) {
            MenuTitleView.this.setTitle(mTitle);
        }

        @Override
        public void setBadgeDrawable(Drawable drawable) {
            //MenuTitleView.this.setBadgeDrawable(drawable);
        }

        @Override
        public void setOnSearchClickedListener(OnClickListener listener) {
            // mSearchOrbView.setOnClickListener(listener);
        }

        @Override
        public void updateComponentsVisibility(int flags) {
            /*if ((flags & BRANDING_VIEW_VISIBLE) == BRANDING_VIEW_VISIBLE) {
                updateBadgeVisibility(true);
            } else {
                mAnalogClockView.setVisibility(View.GONE);
                mTitleView.setVisibility(View.GONE);
            }*/

            int visibility = (flags & SEARCH_VIEW_VISIBLE) == SEARCH_VIEW_VISIBLE
                    ? View.VISIBLE : View.INVISIBLE;
            // mSearchOrbView.setVisibility(visibility);
        }

        private void updateBadgeVisibility(boolean visible) {
            if (visible) {
                //    mAnalogClockView.setVisibility(View.VISIBLE);
                mTitleView.setVisibility(View.VISIBLE);
            } else {
                //   mAnalogClockView.setVisibility(View.GONE);
                mTitleView.setVisibility(View.GONE);
            }
        }
    };

    public MenuTitleView(Context context) {
        this(context, null);
    }

    public MenuTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuTitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View root  = LayoutInflater.from(context).inflate(R.layout.menu_title_view, this);
        mTitleView = root.findViewById(R.id.title_tv);
        mTitle = "aaaa";
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        if (title != null && mTitleView != null) {
            mTitleView.setText(title);
            mTitleView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public TitleViewAdapter getTitleViewAdapter() {
        return mTitleViewAdapter;
    }
}

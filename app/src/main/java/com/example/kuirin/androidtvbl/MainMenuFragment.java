package com.example.kuirin.androidtvbl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.app.VerticalGridSupportFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridPresenter;
import android.util.Log;
import android.view.ViewGroup;

public class MainMenuFragment extends VerticalGridSupportFragment {

    private static final int NUM_COLUMNS = 6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            prepareEntranceTransition();
        }
        setupData();

        setupFragment();

        setupTitleView();
    }

    private void setupTitleView() {
//        setTitleView(new MenuTitleView(getActivity()));
    }

    private void setupData() {
        ArrayObjectAdapter adapter = new ArrayObjectAdapter(new PizzaCardPresenter());
        setAdapter(adapter);

        adapter.add(new MenuModel("menu 1", R.mipmap.ic_launcher));
        adapter.add(new MenuModel("menu 2", R.mipmap.ic_launcher));
        adapter.add(new MenuModel("menu 3", R.mipmap.ic_launcher));

    }

    private void setupFragment() {
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter();
        gridPresenter.setNumberOfColumns(NUM_COLUMNS);
        setGridPresenter(gridPresenter);

        // After 500ms, start the animation to transition the cards into view.
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startEntranceTransition();
            }
        }, 500);

//        setOnSearchClickedListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(getActivity(), SearchActivity.class);
////                startActivity(intent);
//            }
//        });

        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }


    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof MenuModel) {
                Log.d("ItemViewClickedListener", ((MenuModel) item).getTitle());
            }
//            if (item instanceof Video) {
//                Video video = (Video) item;
//
//                Intent intent = new Intent(getActivity(), VideoDetailsActivity.class);
//                intent.putExtra(VideoDetailsActivity.VIDEO, video);
//
//                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        getActivity(),
//                        ((ImageCardView) itemViewHolder.view).getMainImageView(),
//                        VideoDetailsActivity.SHARED_ELEMENT_NAME).toBundle();
//                getActivity().startActivity(intent, bundle);
//            }
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
        }
    }

    private class PizzaCardPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            return new ViewHolder(new MenuCardView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            MenuCardView view = (MenuCardView) viewHolder.view;
            Context context = view.getContext();
            if (item instanceof MenuModel) {
                MenuModel menu = (MenuModel) item;
                String title = menu.getTitle();

                view.setTitleText(title);
                view.setMainImage(context.getDrawable(menu.getImgResourceId()));
            }
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {

        }
    }
}


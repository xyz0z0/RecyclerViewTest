package xyz.xyz0z0.recyclerviewtest;

import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LoadMoreDelegate {

    private final LoadMoreSubject mLoadMoreSubject;


    public LoadMoreDelegate(LoadMoreSubject loadMoreSubject) {
        mLoadMoreSubject = loadMoreSubject;
    }


    public void attach(RecyclerView recyclerView) {
        final LinearLayoutManager linear = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new EndlessScrollListener(linear, mLoadMoreSubject));
    }


    public interface LoadMoreSubject {
        boolean isLoading();
        void onLoadMore();
    }


    private static class EndlessScrollListener extends RecyclerView.OnScrollListener {

        private static final int VISIBLE_THRESHOLD = 6;
        private final LinearLayoutManager mLayoutManager;
        private final LoadMoreSubject mLoadMoreSubject;


        public EndlessScrollListener(LinearLayoutManager layoutManager, LoadMoreSubject loadMoreSubject) {
            mLayoutManager = layoutManager;
            mLoadMoreSubject = loadMoreSubject;
        }


        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy < 0 || mLoadMoreSubject.isLoading()) {
                return;
            }
            final int itemCount = mLayoutManager.getItemCount();
            final int lastVisiblePosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
            final boolean isBottom = (lastVisiblePosition >= itemCount - VISIBLE_THRESHOLD);
            if (isBottom) {
                mLoadMoreSubject.onLoadMore();
            }
            Log.d("cxg", "onScrolled " + itemCount + " " + lastVisiblePosition + " " + isBottom);
        }
    }

}
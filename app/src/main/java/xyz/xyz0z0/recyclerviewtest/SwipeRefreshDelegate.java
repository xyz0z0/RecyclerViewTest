package xyz.xyz0z0.recyclerviewtest;

import android.content.Context;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SwipeRefreshDelegate {

    private final RefreshSubject refreshSubject;
    private SwipeRefreshLayout swipeRefreshLayout;


    public SwipeRefreshDelegate(RefreshSubject refreshSubject) {
        this.refreshSubject = refreshSubject;
    }


    public void setRefresh(boolean requestDataRefresh) {
        if (swipeRefreshLayout == null) {
            return;
        }
        if (requestDataRefresh) {
            swipeRefreshLayout.setRefreshing(true);
        } else {
            swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (swipeRefreshLayout != null) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }, 50);
        }
    }


    public void attach(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        Context context = swipeRefreshLayout.getContext();
        swipeRefreshLayout.setColorSchemeColors(context.getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                refreshSubject.onRefresh();
            }
        });
    }


    public interface RefreshSubject {
        void onRefresh();
    }

}

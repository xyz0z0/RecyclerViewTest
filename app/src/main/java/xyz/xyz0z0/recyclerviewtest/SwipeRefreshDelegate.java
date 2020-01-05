package xyz.xyz0z0.recyclerviewtest;

import android.content.Context;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SwipeRefreshDelegate {

    private final RefreshSubject refreshSubject;


    public SwipeRefreshDelegate(RefreshSubject refreshSubject) {
        this.refreshSubject = refreshSubject;
    }


    public void attach(SwipeRefreshLayout swipeRefreshLayout) {
        Context context = swipeRefreshLayout.getContext();
        swipeRefreshLayout.setColorSchemeColors(context.getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                refreshSubject.refresh();
            }
        });
    }


    public interface RefreshSubject {
        void refresh();
    }

}

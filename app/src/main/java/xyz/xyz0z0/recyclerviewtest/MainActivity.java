package xyz.xyz0z0.recyclerviewtest;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.ThreadUtils;
import com.drakeet.multitype.MultiTypeAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean isLoading = false;
    private boolean loadMoreEnable = true;
    private RecyclerView recyclerView;
    private List<FruitItem> fruitItemList;
    private FoodAdapter foodAdapter;
    private Button btnLoadData;
    private Button btnAddData;
    private Button btnAddEmpty;
    private SwipeRefreshDelegate swipeRefreshDelegate;
    private LoadMoreDelegate loadMoreDelegate;
    private int pageIndex = 1;

    private MultiTypeAdapter adapter;
    private ArrayList<Object> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initSwipeRefresh();
        adapter = new MultiTypeAdapter();
        items = new ArrayList<>();

        adapter.register(FruitItem.class, new FruitViewBinder());
        adapter.register(ImageItem.class, new ImageViewBinder());
        adapter.register(Loading.class, new LoadingViewBinder());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        //
        loadMoreDelegate = new LoadMoreDelegate(new LoadMoreDelegate.LoadMoreSubject() {
            @Override public boolean isLoading() {
                if (loadMoreEnable) {
                    return isLoading;
                } else {
                    return true;
                }

            }


            @Override public void onLoadMore() {
                Log.d("cxg", "onLoadMore");
                isLoading = true;
                loadMoreData();
            }
        });
        loadMoreDelegate.attach(recyclerView);

        adapter.setItems(items);
        refresh();
    }


    private void refresh() {
        swipeRefreshDelegate.setRefresh(true);
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<ImageItem>>() {
            @Override public List<ImageItem> doInBackground() throws Throwable {
                pageIndex = 1;
                isLoading = false;
                loadMoreEnable = true;
                // loading.setShow(loadMoreEnable);
                // adapter.notifyItemChanged(startPos);
                SystemClock.sleep(1000);
                return getList(pageIndex);
            }


            @Override public void onSuccess(List<ImageItem> result) {
                Log.d("cxg", "refresh data start");
                swipeRefreshDelegate.setRefresh(false);
                items.clear();
                items.addAll(result);

                adapter.notifyDataSetChanged();
                Log.d("cxg", "refresh data end");
            }
        });
    }


    private List<ImageItem> getList(int pageIndex) {
        List<ImageItem> imageItems = new ArrayList<>();
        if (pageIndex == 12) {
            return imageItems;
        }
        for (int i = 0; i < 1; i++) {
            ImageItem item = new ImageItem();
            item.setTitle("第" + pageIndex + "页 " + "标题 " + (i + 1));
            imageItems.add(item);
        }
        return imageItems;
    }


    private void loadMoreData() {
        new Thread(new Runnable() {
            @Override public void run() {
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        if (items.size() > 0 && !(items.get(items.size() - 1) instanceof Loading)) {
                            items.add(new Loading(true));
                            adapter.notifyItemInserted(items.size());
                        }
                    }
                });
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<ImageItem> images = getList(pageIndex + 1);
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        int startPos = items.size() - 1;
                        if (images.size() == 0) {
                            // 没有更多数据了
                            isLoading = false;
                            loadMoreEnable = false;
                            ((Loading) items.get(startPos)).setShow(false);
                            adapter.notifyItemChanged(startPos);
                            return;
                        }
                        // items.remove(startPos);
                        // adapter.notifyItemRemoved(startPos);
                        items.addAll(startPos, images);
                        adapter.notifyItemRangeInserted(startPos, images.size());
                        // adapter.notifyItemRangeInserted(startPos, images.size());
                        isLoading = false;
                        pageIndex++;
                    }
                });
            }
        }).start();

    }


    private List<FruitItem> getNewFoods() {
        List<FruitItem> items = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            FruitItem item = new FruitItem();
            item.setName("这是水果 " + i);
            item.setDesc("这是 " + i + " 号水果的详情");
            items.add(item);
        }
        return items;
    }


    private void initSwipeRefresh() {
        swipeRefreshDelegate = new SwipeRefreshDelegate(new SwipeRefreshDelegate.RefreshSubject() {
            @Override public void onRefresh() {
                Log.d("cxg", "onRefresh");
                refresh();
            }
        });
        swipeRefreshDelegate.attach(findViewById(R.id.srlMain));
    }


    private void initView() {
        recyclerView = findViewById(R.id.rvMain);
        btnLoadData = findViewById(R.id.btn_load_data);
    }

}

package xyz.xyz0z0.recyclerviewtest;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshDelegate.RefreshSubject {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<FoodItem> foodItemList;
    private FoodAdapter foodAdapter;
    private Button btnLoadData;
    private Button btnAddData;
    private Button btnAddEmpty;
    private SwipeRefreshDelegate swipeRefreshDelegate;
    private LoadMoreDelegate loadMoreDelegate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initSwipeRefresh();

        foodItemList = new ArrayList<>();

        foodAdapter = new FoodAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(foodAdapter);
        btnLoadData.setOnClickListener(v -> {
            foodAdapter.setData(getNewFoods());
        });
        btnAddData = findViewById(R.id.btn_add_data);
        btnAddData.setOnClickListener(v -> {
            List<FoodItem> items = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                FoodItem item = new FoodItem();
                item.setName("这是新加的水果 " + i);
                item.setDesc("这是新加的 " + i + " 号水果的详情");
                items.add(item);
            }
            foodAdapter.addData(items);
        });
        initLoadMore();
        btnAddEmpty = findViewById(R.id.btn_add_empty);
        btnAddEmpty.setOnClickListener(v -> {
            List<FoodItem> items = new ArrayList<>();
            foodAdapter.setData(items);
        });
    }

    boolean isLoading = false;

    private void initLoadMore() {
        loadMoreDelegate = new LoadMoreDelegate(new LoadMoreDelegate.LoadMoreSubject() {

            @Override public boolean isLoading() {
                return isLoading;
            }


            @Override public void onLoadMore() {
                Log.d("cxg", "onLoadMore");
                loadMoreData();
            }
        });
        loadMoreDelegate.attach(recyclerView);
    }

    private int count = 0;

    private void loadMoreData(){
        count++;
        if (count > 5){
            isLoading = false;

            return;
        }
        isLoading = true;
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        foodAdapter.addData(getNewFoods());
                        isLoading = false;
                    }
                });
            }
        }).start();
    }


    private List<FoodItem> getNewFoods() {
        List<FoodItem> items = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FoodItem item = new FoodItem();
            item.setName("这是水果 " + i);
            item.setDesc("这是 " + i + " 号水果的详情");
            items.add(item);
        }
        return items;
    }


    private void initSwipeRefresh() {
        swipeRefreshDelegate = new SwipeRefreshDelegate(this);
        swipeRefreshDelegate.attach(swipeRefreshLayout);
    }


    private void initView() {
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        recyclerView = findViewById(R.id.recycler_view);
        btnLoadData = findViewById(R.id.btn_load_data);
    }


    @Override public void refresh() {
        Log.d("cxg", "onRefresh");
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        foodAdapter.setData(getNewFoods());
                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }
        }).start();
    }
}

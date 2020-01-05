package xyz.xyz0z0.recyclerviewtest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int LOAD_MORE_TYPE = -1;
    private List<T> items;


    public void setData(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    public void addData(List<T> data) {
        if (items == null) {
            items = new ArrayList<>();
        }
        int lastPosition = items.size();
        this.items.addAll(data);
        notifyItemRangeInserted(lastPosition, data.size());
    }


    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LOAD_MORE_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
            return new LoadMoreHolder(view);
        } else {
            return createHolder(parent, viewType);
        }
    }


    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(holder.getAdapterPosition()) == LOAD_MORE_TYPE) {
            Log.d("cxg", "onBindViewHolder load more");
        } else {
            bindHolder(holder, items.get(holder.getAdapterPosition()));
        }
    }


    @Override public int getItemViewType(int position) {
        if (position >= getItemCount() - 1) {
            return LOAD_MORE_TYPE;
        } else {
            return getItemType(position);
        }
    }


    protected abstract int getItemType(int position);


    @Override public int getItemCount() {
        if (items == null || items.size() == 0) {
            return 0;
        } else {
            return items.size() + 1;
        }
    }


    abstract void bindHolder(@NonNull RecyclerView.ViewHolder holder, T item);

    abstract RecyclerView.ViewHolder createHolder(@NonNull ViewGroup parent, int viewType);


    class LoadMoreHolder extends RecyclerView.ViewHolder {
        public LoadMoreHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

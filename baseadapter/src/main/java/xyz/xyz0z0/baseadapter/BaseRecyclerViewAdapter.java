package xyz.xyz0z0.baseadapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int LOAD_MORE_TYPE = 100;
    private int LOAD_COMPLETE_TYPE = 101;
    private List<T> items = new ArrayList<>();
    private boolean isLoadComplete = false;

    public void setData(List<T> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        int lastPosition = items.size();
        this.items.addAll(data);
        notifyItemRangeInserted(lastPosition, data.size());
    }

    public boolean getLoadComplete() {
        return isLoadComplete;
    }

    public void setLoadComplete(boolean b) {
        isLoadComplete = b;
        notifyItemChanged(getItemCount() - 1);
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LOAD_MORE_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
            return new LoadMoreHolder(view);
        } else if (viewType == LOAD_COMPLETE_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_complete, parent, false);
            return new LoadMoreHolder(view);
        } else {
            return createHolder(parent, viewType);
        }
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(holder.getAdapterPosition()) == LOAD_MORE_TYPE) {
            Log.d("cxg", "onBindViewHolder LOAD_MORE_TYPE");
        } else if (getItemViewType(holder.getAdapterPosition()) == LOAD_COMPLETE_TYPE) {
            Log.d("cxg", "onBindViewHolder LOAD_COMPLETE_TYPE");
        } else {
            bindHolder(holder, items.get(holder.getAdapterPosition()));
        }
    }

    @Override public int getItemViewType(int position) {
        if (position >= getItemCount() - 1) {
            return isLoadComplete ? LOAD_COMPLETE_TYPE : LOAD_MORE_TYPE;
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

    protected abstract void bindHolder(@NonNull RecyclerView.ViewHolder holder, Object item);

    protected abstract RecyclerView.ViewHolder createHolder(@NonNull ViewGroup parent, int viewType);

    class LoadMoreHolder extends RecyclerView.ViewHolder {
        public LoadMoreHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

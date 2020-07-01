package xyz.xyz0z0.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import xyz.xyz0z0.baseadapter.BaseRecyclerViewAdapter;

public class FoodAdapter extends BaseRecyclerViewAdapter<FruitItem> {

    public FoodAdapter() {

    }

    @Override protected int getItemType(int position) {
        return 0;
    }

    @Override protected void bindHolder(@NonNull RecyclerView.ViewHolder holder, Object item) {
        ViewHolder viewHolder = (ViewHolder) holder;
        FruitItem fruitItem = (FruitItem)item;
        viewHolder.tvIndex.setText(String.valueOf(holder.getAdapterPosition()));
        viewHolder.tvTitle.setText(fruitItem.getName());
        viewHolder.tvDesc.setText(fruitItem.getDesc());
    }

    @Override protected RecyclerView.ViewHolder createHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDesc;
        private TextView tvIndex;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvIndex = itemView.findViewById(R.id.tv_index);
        }
    }
}

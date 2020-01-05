package xyz.xyz0z0.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodAdapter extends BaseRecyclerViewAdapter<FoodItem> {

    public FoodAdapter() {

    }


    @Override protected int getItemType(int position) {
        return 0;
    }


    @Override void bindHolder(@NonNull RecyclerView.ViewHolder holder, FoodItem item) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvIndex.setText(String.valueOf(holder.getAdapterPosition()));
        viewHolder.tvTitle.setText(item.getName());
        viewHolder.tvDesc.setText(item.getDesc());
    }


    @Override RecyclerView.ViewHolder createHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDesc;
        private TextView tvIndex;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvIndex = itemView.findViewById(R.id.tv_index);
        }
    }
}
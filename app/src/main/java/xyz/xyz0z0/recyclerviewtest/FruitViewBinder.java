package xyz.xyz0z0.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.drakeet.multitype.ItemViewBinder;
import org.jetbrains.annotations.NotNull;

class FruitViewBinder extends ItemViewBinder<FruitItem, FruitViewBinder.ViewHolder> {

    @NotNull @Override public ViewHolder onCreateViewHolder(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.item_food, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override public void onBindViewHolder(@NotNull ViewHolder viewHolder, FruitItem fruitItem) {
        viewHolder.tvTitle.setText(fruitItem.getName());
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "Click itemView", Toast.LENGTH_SHORT).show();
            });
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTitle.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "Click title", Toast.LENGTH_SHORT).show();
            });
        }

    }
}

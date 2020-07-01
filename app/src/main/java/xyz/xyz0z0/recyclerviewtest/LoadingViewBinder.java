package xyz.xyz0z0.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.drakeet.multitype.ItemViewBinder;
import org.jetbrains.annotations.NotNull;

class LoadingViewBinder extends ItemViewBinder<Loading, LoadingViewBinder.ViewHolder> {

    @NotNull @Override public ViewHolder onCreateViewHolder(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.item_loading, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override public void onBindViewHolder(@NotNull ViewHolder viewHolder, Loading item) {
        viewHolder.itemView.setVisibility(item.getShow() ? View.VISIBLE : View.INVISIBLE);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // itemView.setOnClickListener(v -> {
            //     Toast.makeText(itemView.getContext(), "Click itemView", Toast.LENGTH_SHORT).show();
            // });
            // tvTitle = itemView.findViewById(R.id.tvTitle);
            // tvTitle.setOnClickListener(v -> {
            //     Toast.makeText(itemView.getContext(), "Click title", Toast.LENGTH_SHORT).show();
            // });
        }

    }
}

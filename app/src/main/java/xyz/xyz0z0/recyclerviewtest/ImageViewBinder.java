package xyz.xyz0z0.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.drakeet.multitype.ItemViewBinder;
import org.jetbrains.annotations.NotNull;

class ImageViewBinder extends ItemViewBinder<ImageItem, ImageViewBinder.ViewHolder> {

    @NotNull @Override public ViewHolder onCreateViewHolder(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.item_image, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override public void onBindViewHolder(@NotNull ViewHolder viewHolder, ImageItem item) {
        viewHolder.tvTitle.setText(item.getTitle());
        viewHolder.ivImage.setImageResource(R.drawable.ic_launcher_background);
        viewHolder.tvDelete.setVisibility(item.isShowDeleteIcon() ? View.VISIBLE : View.INVISIBLE);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDelete;
        private ImageView ivImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDelete = itemView.findViewById(R.id.tvDelete);
            ivImage = itemView.findViewById(R.id.ivImage);
            itemView.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "Click itemView", Toast.LENGTH_SHORT).show();
            });
            tvTitle.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "Click title", Toast.LENGTH_SHORT).show();
            });
        }

    }
}

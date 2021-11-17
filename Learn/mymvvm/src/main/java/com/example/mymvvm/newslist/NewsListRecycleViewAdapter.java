package com.example.mymvvm.newslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymvvm.R;
import com.example.mymvvm.ViewModel.NewsListViewModel;
import com.example.mymvvm.api.NewsListBean;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class NewsListRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsListBean.Contentist> mitems;
    private Context context;

    private final int VIEW_TYPE_PICTURE_TITLE = 1;
    private final int VIEW_TYPE__TITLE = 2;

    public NewsListRecycleViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NewsListBean.Contentist> items) {
        mitems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mitems != null && mitems.get(position).imageurls != null
        && mitems.get(position).imageurls.size() > 1) {
            return VIEW_TYPE_PICTURE_TITLE;
        }
        return VIEW_TYPE__TITLE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_PICTURE_TITLE) {
            view = LayoutInflater.from(context).inflate(R.layout.picture_title_view, parent, false);
            return new PictureTitleViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.title_view, parent, false);
            return  new TitleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(mitems.get(position).link);
        if (holder instanceof PictureTitleViewHolder) {
            ((PictureTitleViewHolder)holder).textView.setText(mitems.get(position).title);
            Glide.with(((PictureTitleViewHolder) holder).imageView.getContext())
                    .load(mitems.get(position).imageurls.get(0))
                    .transition(withCrossFade())
                    .into(((PictureTitleViewHolder) holder).imageView);
        } else {
            ((TitleViewHolder)holder).textView.setText(mitems.get(position).title);
        }
    }

    @Override
    public int getItemCount() {
        return mitems == null ? 0 : mitems.size();
    }

    private class PictureTitleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public PictureTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
            imageView = itemView.findViewById(R.id.item_image);
        }
    }

    private class TitleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
        }
    }

}

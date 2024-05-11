package com.example.eatsy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Custom adapter
 * Author: Lin Xi
 */
public class ListDataAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Post> postList;

    public ListDataAdapter(Context context, List<Post> postList) {
        mContext = context;
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList == null ? 0 : postList.size();
    }

    @Override
    public Object getItem(int i) {
        return getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.pat_manager_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Post partBean = postList.get(i);
        if(partBean.getImages()!=null && !partBean.getImages().isEmpty()){
            String imagePath = partBean.getImages().get(0);
            Picasso.get().load(imagePath).into(viewHolder.food);
        }else{
            viewHolder.food.setBackground(Drawable.createFromPath("?android:attr/selectableItemBackground"));
        }
        viewHolder.postTitle.setText(partBean.getPostTitle());
        // Set click event
        view.setOnClickListener(v -> {
            // Getting postId
            String postId = partBean.getId();
            // Create Intent and transfer postId
            Intent intent = new Intent(mContext, PostDetailActivity.class);
            intent.putExtra("postId", Integer.parseInt(postId));
            mContext.startActivity(intent);
        });

        return view;
    }

    static
    class ViewHolder {
        View mView;
        ImageButton food;
        TextView postTitle;

        View lineBar;

        ViewHolder(View view) {
            this.mView = view;
            this.lineBar = view.findViewById(R.id.lineBar);
            this.food = view.findViewById(R.id.food);
            this.postTitle = view.findViewById(R.id.postTitle);
        }
    }
}

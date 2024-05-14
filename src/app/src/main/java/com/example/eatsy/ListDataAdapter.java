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
 * Custom adapter class for a ListView to display posts.
 * This adapter is responsible for providing the views that represent each post in a data set.
 * Author: Lin Xi(u7777752)
 */
public class ListDataAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Post> postList;
    /**
     * Constructor for the ListDataAdapter.
     * @param context The current context.
     * @param postList A list of Post objects to be displayed.
     */
    public ListDataAdapter(Context context, List<Post> postList) {
        mContext = context;
        this.postList = postList;
    }
    /**
     * Returns the number of items in the data set.
     * @return The total number of posts in the list.
     */
    @Override
    public int getCount() {

        return postList == null ? 0 : postList.size();
    }
    /**
     * Gets the data item associated with the specified position in the data set.
     * @param i Position of the item whose data we want.
     * @return The Post object at the specified position.
     */
    @Override
    public Object getItem(int i) {

        return getItem(i);
    }
    /**
     * Gets the row id associated with the specified position in the list.
     * @param i Position of the item.
     * @return The position of the item within the adapter's data set.
     */
    @Override
    public long getItemId(int i) {

        return i;
    }
    /**
     * Gets a View that displays the data at the specified position in the data set.
     * @param i The position of the item within the adapter's data set of the item whose view we want.
     * @param view The old view to reuse, if possible.
     * @param viewGroup The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
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
    /**
     * ViewHolder class for holding view references.
     * This class is a static inner class to hold the views associated with a list item.
     */
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

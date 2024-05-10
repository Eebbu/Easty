package com.example.eatsy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts;
    private Context context;
    private OnItemClickListener onItemClickListener; // Click event listener

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    // Set up click event listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private ImageView imageView;
        private TextView descriptionTextView;
        private TextView usernameTextView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.imageView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            usernameTextView = itemView.findViewById(R.id.username);

            // Set a click event listener for itemView in the constructor
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(Post post) {
            titleTextView.setText(post.getPostTitle());
            if (post.getImages() != null && post.getImages().size() > 0){
                Picasso.get()
                        .load(post.getImages().get(0))
                        .placeholder(R.drawable.baseline_find_replace_24)
                        .into(imageView);
            }else{
                Picasso.get()
                        .load(R.drawable.foodwant) // Load the resource ID of a specific image
                        .into(imageView);
            }
            descriptionTextView.setText(post.getPostDescription());
            usernameTextView.setText(post.getUserName());
        }
    }

    // Internal interface for handling click events
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}

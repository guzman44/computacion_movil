package co.movil.computacion.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.movil.computacion.R;
import co.movil.computacion.model.ModelFeed;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.ViewHolderFeed> {

    Context context;
    ArrayList<ModelFeed> feedList = new ArrayList<>();
    RequestManager glide;

    public AdapterFeed() {
    }
    public AdapterFeed(Context context,ArrayList<ModelFeed> feedList ) {
        this.context = context;
        this.feedList = feedList;
        glide = Glide.with(context);
    }
    @NonNull
    @Override
    public AdapterFeed.ViewHolderFeed onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_feed,parent,false);
        ViewHolderFeed viewHolderFeed = new ViewHolderFeed(view);
        return viewHolderFeed;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFeed.ViewHolderFeed holder, int position) {
        final ModelFeed feed = feedList.get(position);

        holder.tvNameFeed.setText(feed.getName());
        holder.tvTimeFeed.setText(feed.getTime());
        holder.tvLikesFeed.setText(String.valueOf(feed.getLikes()));
        holder.tvCommentsFeed.setText(String.valueOf(feed.getComments()));
        holder.tvStatusFeed.setText(feed.getStatus());




        glide.load(feed.getPropic()).into(holder.ivProfileFeed);


        if(feed.getPostpic() == 0){
            holder.ivPostFeed.setVisibility(View.GONE);
        }else{
            holder.ivPostFeed.setVisibility(View.VISIBLE);
            glide.load(feed.getPostpic()).into(holder.ivPostFeed);
        }

    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public class ViewHolderFeed extends  RecyclerView.ViewHolder{


        TextView tvNameFeed, tvTimeFeed, tvLikesFeed, tvCommentsFeed, tvStatusFeed;
        ImageView ivProfileFeed,  ivPostFeed;


        public ViewHolderFeed(@NonNull View itemView) {
            super(itemView);
            ivProfileFeed = (ImageView)itemView.findViewById(R.id.ivProfileFeed);
            ivPostFeed = (ImageView)itemView.findViewById(R.id.ivPostFeed);

            tvNameFeed = (TextView)itemView.findViewById(R.id.tvNameFeed);
            tvTimeFeed = (TextView)itemView.findViewById(R.id.tvTimeFeed);
            tvLikesFeed = (TextView)itemView.findViewById(R.id.tvLikesFeed);
            tvCommentsFeed = (TextView)itemView.findViewById(R.id.tvCommentFeed);
            tvStatusFeed = (TextView)itemView.findViewById(R.id.tvStatusFeed);


        }
    }
}

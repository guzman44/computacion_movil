package co.movil.computacion.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.dtos.PublicacionesDTO;


public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.ViewHolderFeed> {

    Context context;
    List<PublicacionesDTO> feedList = new ArrayList<>();
    RequestManager glide;
    ViewComponent vc;

    public AdapterFeed() {
    }

    public AdapterFeed(Context context, List<PublicacionesDTO> feedList ) {
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
    public void onBindViewHolder(@NonNull final AdapterFeed.ViewHolderFeed holder, int position) {
        final PublicacionesDTO feed = feedList.get(position);
        if(feed.getNombre()!= null){
            holder.tvNameFeed.setText(feed.getNombre().toString());
        }else{
            holder.tvNameFeed.setText(feed.getUserName().toString());
        }
        if(feed.getAvatar()!=null){
            byte[] decodedString = Base64.decode(feed.getAvatar(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.ivProfileFeed.setImageBitmap(decodedByte);
        }

        holder.tvTimeFeed.setText(feed.getFechaIngresoMostrar().toString());
        holder.tvLikesFeed.setText(String.valueOf(1));
        holder.tvStatusFeed.setText(feed.getComentario());

        if(feed.getImagen() == ""){
            holder.ivPostFeed.setVisibility(View.GONE);
        }else{
            byte[] decodedString = Base64.decode(feed.getImagen(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.ivPostFeed.setVisibility(View.VISIBLE);
            holder.ivPostFeed.setImageBitmap(decodedByte);
            //glide.load(feed.getImagen()).into(holder.ivPostFeed);
        }

        holder.rlLikeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (feed.isClicked())
                {
                    feed.removeLike();
                    feed.setClicked(false);
                }
                else
                {
                    feed.addLike();
                    feed.setClicked(true);
                }
                Log.i( "Likes: ", "" + feed.getLikes() );
                updateLikes();
            }

            private void updateLikes() {
                holder.tvLikesFeed.setText(String.valueOf(feed.getLikes()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public class ViewHolderFeed extends  RecyclerView.ViewHolder{


        TextView tvNameFeed, tvTimeFeed, tvLikesFeed, tvCommentsFeed, tvStatusFeed;
        ImageView ivProfileFeed,  ivPostFeed;
        RelativeLayout rlLikeAction;

        public ViewHolderFeed(@NonNull final View itemView) {
            super(itemView);
            ivProfileFeed = (ImageView)itemView.findViewById(R.id.ivProfileFeed);
            ivPostFeed = (ImageView)itemView.findViewById(R.id.ivPostFeed);

            tvNameFeed = (TextView)itemView.findViewById(R.id.tvNameFeed);
            tvTimeFeed = (TextView)itemView.findViewById(R.id.tvTimeFeed);
            tvLikesFeed = (TextView)itemView.findViewById(R.id.tvLikesFeed);
           // tvCommentsFeed = (TextView)itemView.findViewById(R.id.tvCommentFeed);
            tvStatusFeed = (TextView)itemView.findViewById(R.id.tvStatusFeed);
            rlLikeAction = (RelativeLayout)itemView.findViewById(R.id.likeAction);
        }

    }
}

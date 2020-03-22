package co.movil.computacion.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import co.movil.computacion.R;
import co.movil.computacion.model.ModelEvent;
import co.movil.computacion.model.ModelFeed;

public class AdapterSearch  extends RecyclerView.Adapter<AdapterSearch.ViewHolderSearch> {

    Context context;
    List<ModelEvent> eventList;
    RequestManager glide;


    public AdapterSearch(Context context, List<ModelEvent> eventList ) {
        this.context = context;
        this.eventList = eventList;
        glide = Glide.with(context);
    }

    @NonNull
    @Override
    public ViewHolderSearch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event,parent,false);
        AdapterSearch.ViewHolderSearch viewHolderSearch = new AdapterSearch.ViewHolderSearch(view);
        return viewHolderSearch;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSearch holder, int position) {

        final ModelEvent event = eventList.get(position);

        holder.tv_book_title.setText(event.getTitle());
        holder.img_book_thumbnail.setImageResource(event.getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,DetailEvent.class);

                intent.putExtra("Title",event.getTitle());
                intent.putExtra("Description",event.getDescription());
                intent.putExtra("Thumbnail",event.getThumbnail());
                // start the activity
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return eventList.size();
    }

    public class ViewHolderSearch extends  RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView;

        public ViewHolderSearch(@NonNull View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }

}
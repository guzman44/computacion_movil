package co.movil.computacion.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import co.movil.computacion.R;
import co.movil.computacion.assets.utilidades.ViewComponent;
import co.movil.computacion.dtos.Evento.EventDTO;


public class AdapterSearch  extends RecyclerView.Adapter<AdapterSearch.ViewHolderSearch>  implements Filterable  {

    Context context;
    List<EventDTO> eventList;
    List<EventDTO> allList;
    RequestManager glide;
    ViewComponent vc;


    public AdapterSearch(Context context, List<EventDTO> eventList , ViewComponent viewComponent) {
        this.context = context;
        this.eventList = eventList;
        glide = Glide.with(context);
        allList = new ArrayList<>();
        allList.addAll(eventList);
        this.vc = viewComponent;

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

        final EventDTO event = eventList.get(position);

        holder.tv_book_title.setText(event.getNombre());
        byte[] decodedString = Base64.decode(event.getImagenMiniatura(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.img_book_thumbnail.setImageBitmap(bitmap);
        //holder.img_book_thumbnail.setImageResource(event.getImagenMiniatura());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,DetailEvent.class);

                intent.putExtra("Title",event.getNombre());
                intent.putExtra("Description",event.getDescripcion());
                intent.putExtra("Thumbnail", decodedString);
                intent.putExtras(vc.getUserBuble());
                //intent.putExtras(vc.getUserBuble());
                // start the activity
                context.startActivity(intent);

            }
        });
    }

    @Override
    public Filter getFilter() {

        return myFilter;
    }
    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<EventDTO> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(allList);
            } else {
                for (EventDTO event: allList) {
                    if (event.getNombre().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(event);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            eventList.clear();
            eventList.addAll((List<EventDTO>)filterResults.values);
            notifyDataSetChanged();
        }
    };


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

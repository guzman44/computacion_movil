package co.movil.computacion.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import co.movil.computacion.controller.Event;

public class ModelEvent implements  Serializable {

    private String Title;
    private String Category;
    private String Description;
    private int Thumbnail;
    private Calendar EventDate;
    private LatLng Location;

    public ModelEvent(String title, String category, String description, int thumbnail, Calendar date, LatLng location) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
        EventDate = date;
        Location = location;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public Calendar getEventDate()
    {
        return EventDate;
    }

    public void setEventDate( Calendar date )
    {
        EventDate = date;
    }

    public LatLng getLocation()
    {
        return Location;
    }

    public void setLocation( LatLng location)
    {
        Location = location;
    }
}

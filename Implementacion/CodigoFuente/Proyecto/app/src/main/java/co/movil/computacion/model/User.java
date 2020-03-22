package co.movil.computacion.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String name;
    private String lastName;
    private String email;
    private Drawable profilePic;

    public User() {

    }

    public User ( String username, String name, String lastName, String email, Drawable profilePic )
    {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Drawable getProfilePic() {
        return profilePic;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public void setProfilePic( Drawable profilePic )
    {
        this.profilePic = profilePic;
    }
}

package co.movil.computacion.model;

public class ModelFeed {
    int id,likes,comments,propic,postpic;
    String name,time, status;
    Boolean clicked = false;

    public ModelFeed(int id, int likes, int comments, int propic, int postpic, String name, String time, String status) {
        this.id = id;
        this.likes = likes;
        this.comments = comments;
        this.propic = propic;
        this.postpic = postpic;
        this.name = name;
        this.time = time;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getPropic() {
        return propic;
    }

    public void setPropic(int propic) {
        this.propic = propic;
    }

    public int getPostpic() {
        return postpic;
    }

    public void setPostpic(int postpic) {
        this.postpic = postpic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isClicked()
    {
        return this.clicked;
    }

    public void setClicked( boolean clicked )
    {
        this.clicked = clicked;
    }

    public void addLike()
    {
        this.likes+=1;
    }

    public void removeLike()
    {
        this.likes-=1;
    }
}

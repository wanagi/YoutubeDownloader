package hackerspace.invento.youtubedownloader;

public class Song {

    String title,artist,imgUrl,url;

    public Song () {

    }

    public Song (String title, String artist, String imgUrl, String url) {
        this.title = title;
        this.artist = artist;
        this.imgUrl = imgUrl;
        this.url = url;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setURL(String url) {
        this.url = url;
    }

}

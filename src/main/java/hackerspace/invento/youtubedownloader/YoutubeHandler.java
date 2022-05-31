package hackerspace.invento.youtubedownloader;

import android.content.Context;
import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by invento on 28/5/15.
 */
public class YoutubeHandler {


    private YouTube youtube;
    private YouTube.Search.List query;

    public static final String KEY = "AIzaSyDgWzLARD5rt5gfud0lylXOQxKkNIOe_eQ";

    public YoutubeHandler(Context context) {

        youtube = new YouTube.Builder(new NetHttpTransport(),new JacksonFactory(),new HttpRequestInitializer(){
             @Override
             public void initialize(HttpRequest hr) throws IOException{}
         }).setApplicationName(context.getString(R.string.app_name)).build();

        try{
            query = youtube.search().list("id,snippet");
            query.setKey(KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/channel,snippet/thumbnails/default/url)");
        }catch(IOException e){
            Log.d("YH", "Could not initialize: " + e);
        }
    }

    public List<Song> Search(String word){
        query.setQ(word);
        try{
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();
            List<Song> items = new ArrayList<>();
            for(SearchResult result:results){
                Song item = new Song();
                item.setTitle(result.getSnippet().getTitle());
                item.setArtist(result.getSnippet().getChannelTitle());
                item.setImgUrl(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setURL(result.getId().getVideoId());
                items.add(item);
            }
            return items;
        }catch(IOException e){
            Log.d("YC", "Could not search: "+e);
            return null;
        }
    }

}

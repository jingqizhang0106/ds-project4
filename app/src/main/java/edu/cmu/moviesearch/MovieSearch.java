package edu.cmu.moviesearch;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
public class MovieSearch extends AppCompatActivity {
    MovieSearch ms = this;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * The click listener will need a reference to this object, so that upon successfully finding a picture from Flickr, it
         * can callback to this object with the resulting picture Bitmap.  The "this" of the OnClick will be the OnClickListener, not
         * this InterestingPicture.
         */
        final MovieSearch ma = this;

        /*
         * Find the "submit" button, and add a listener to it
         */
        Button submitButton = (Button)findViewById(R.id.submit);
        // Add a listener to the send button
        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View viewParam) {
                String searchTerm = ((EditText)findViewById(R.id.searchTerm)).getText().toString();
                System.out.println("searchTerm = " + searchTerm);
                GetMovie gp = new GetMovie();
                gp.search(searchTerm, ms, ma); // Done asynchronously in another thread.  It calls ip.pictureReady() in this thread when complete.
            }
        });

    }
    /*
     * This is called by the GetPicture object when the picture is ready.  This allows for passing back the Bitmap picture for updating the ImageView
     */
    public void movieReady(Bitmap picture, String title, String des) {
        ImageView pictureView = (ImageView) findViewById(R.id.interestingPicture);
        TextView searchView = (EditText)findViewById(R.id.searchTerm);
        TextView b = (TextView)findViewById(R.id.resultString);
        b.setText(title+des);
        if (picture != null) {
            pictureView.setImageBitmap(picture);
            pictureView.setVisibility(View.VISIBLE);
            b.setText("Here is the movie " + searchView.getText());
        } else {
            pictureView.setImageResource(R.mipmap.ic_launcher);
            pictureView.setVisibility(View.INVISIBLE);
            b.setText("Sorry, I could not find a movie of a " + searchView.getText());
        }
        searchView.setText("");
        pictureView.invalidate();
    }
}

package infs3634.pokedex;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import infs3634.pokedex.apiClient.PokemonRestClient;
import infs3634.pokedex.dao.PokemonDAO;
import infs3634.pokedex.dto.Pokemon;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.loopj.android.http.*;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;

import cz.msebera.android.httpclient.Header;

/**
 * A fragment representing a single Pokemon detail screen.
 * This fragment is either contained in a {@link PokemonListActivity}
 * in two-pane mode (on tablets) or a {@link PokemonDetailActivity}
 * on handsets.
 */
public class PokemonDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Pokemon pokemon;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PokemonDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate");
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            pokemon = PokemonDAO.getPokemonById(getArguments().getInt(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(pokemon.getIdStr() + " - " + pokemon.getIdentifier());
            }
        }

    }

    /**
     * Rest API
     */
    private void loadPokemonDetail(View rootView) {

        if (pokemon.getJsonObject() != null) {
            // load api only once
            updateAjaxView(rootView);
            return;
        }

        PokemonRestClient.get(pokemon.getURL(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                pokemon.setJsonObject(response);
                updateAjaxView(null);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String response, Throwable e) {
                System.out.println(response);
                e.printStackTrace();
            }

        });
    }

    private void updateAjaxView(View rootView) {

        if (rootView == null) {
            rootView = getView();
        }

        if (this.pokemon != null && this.pokemon.getJsonObject() != null) {
            ((TextView) rootView.findViewById(R.id.pd_abilities)).setText(pokemon.getAbilitiesStr());
            ((TextView) rootView.findViewById(R.id.pd_moves)).setText(pokemon.getMovesStr());

            rootView.findViewById(R.id.pd_pinner).setVisibility(View.GONE);
            rootView.findViewById(R.id.pd_ability_card).setVisibility(View.VISIBLE);
            rootView.findViewById(R.id.pd_images_wrapper).setVisibility(View.VISIBLE);

//            rootView.findViewById(R.id.pd_moves_card).setVisibility(View.VISIBLE);
            rootView.findViewById(R.id.pd_webview).setVisibility(View.VISIBLE);


            // set images

            new DownloadImageTask((ImageView) rootView.findViewById(R.id.pd_img_back_female)).execute(pokemon.getBack_female());
            new DownloadImageTask((ImageView) rootView.findViewById(R.id.pd_img_back_shiny_female)).execute(pokemon.getBack_shiny_female());
            new DownloadImageTask((ImageView) rootView.findViewById(R.id.pd_img_back_default)).execute(pokemon.getBack_default());
            new DownloadImageTask((ImageView) rootView.findViewById(R.id.pd_img_front_female)).execute(pokemon.getFront_female());
            new DownloadImageTask((ImageView) rootView.findViewById(R.id.pd_img_front_shiny_female)).execute(pokemon.getFront_shiny_female());
            new DownloadImageTask((ImageView) rootView.findViewById(R.id.pd_img_back_shiny)).execute(pokemon.getBack_shiny());
            new DownloadImageTask((ImageView) rootView.findViewById(R.id.pd_img_front_default)).execute(pokemon.getFront_default());
            new DownloadImageTask((ImageView) rootView.findViewById(R.id.pd_img_front_shiny)).execute(pokemon.getFront_shiny());

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("onCreteView");
        View rootView = inflater.inflate(R.layout.pokemon_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (pokemon != null) {
            ((TextView) rootView.findViewById(R.id.pd_height)).setText(String.format("Height:\t\t\t\t\t\t%dcm", pokemon.getHeight() * 10));
            ((TextView) rootView.findViewById(R.id.pd_weight)).setText(String.format("Weight:\t\t\t\t\t%dkg", pokemon.getWeight() / 10));
            ((TextView) rootView.findViewById(R.id.pd_exp)).setText(String.format("Base Exp:\t\t\t%d", pokemon.getHeight() * 10));

            loadPokemonDetail(rootView);

            ImageView imageView = (ImageView) rootView.findViewById(R.id.pd_image);
            Glide.with(rootView.getContext())
                    .load(String.format("file:///android_asset/gifs/%s.gif", pokemon.getIdentifier()))
                    .asGif()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
//            try {
//                // get input stream
//                InputStream ims = getResources().getAssets().open(pokemon.getPortraitFileName());
//                // load image as Drawable
//                Drawable d = Drawable.createFromStream(ims, null);
//                ((ImageView) rootView.findViewById(R.id.pd_image)).setImageDrawable(d);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            WebView webView = (WebView) rootView.findViewById(R.id.pd_webview);
            // Enable Javascript
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setVerticalScrollBarEnabled(true);

            // Add a WebViewClient
            webView.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageFinished(WebView view, String url) {
                    injectCSSFile(view, "css/override.css");
                    super.onPageFinished(view, url);
                    view.setVisibility(View.VISIBLE);
                }

                // disable url redirect
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return true;
                }

                private void injectCSSFile(WebView view, String scriptFile) {
                    try {
                        InputStream inputStream = getResources().getAssets().open(scriptFile);
                        byte[] buffer = new byte[inputStream.available()];
                        inputStream.read(buffer);
                        inputStream.close();
                        String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
                        view.loadUrl("javascript:(function() {" +
                                "var parent = document.getElementsByTagName('head').item(0);" +
                                "var style = document.createElement('style');" +
                                "style.type = 'text/css';" +
                                // Tell the browser to BASE64-decode the string into your script !!!
                                "style.innerHTML = window.atob('" + encoded + "');" +
                                "parent.appendChild(style)" +
                                "})()");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
                }
            });


            webView.loadUrl(String.format("http://www.pokemon.com/us/pokedex/%s", this.pokemon.getIdentifier()));
        }

        return rootView;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            if (result == null) {
                bmImage.setVisibility(View.GONE);
            } else {
                bmImage.setImageBitmap(result);
            }
        }
    }
}

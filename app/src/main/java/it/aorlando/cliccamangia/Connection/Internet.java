package it.aorlando.cliccamangia.Connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by fagottino on 18/05/17.
 */

public class Internet {

    private Context context;

    public Internet(Context pContext) {
        this.context = pContext;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public static class IsInternetAvailable extends AsyncTask<Void, Integer, Boolean> {

        private IsInternetAvailableListener mListener;

        @Override
        final protected void onPreExecute() {
            if (mListener != null)
                mListener.onPreExecuteConcluded();
        }

        protected Boolean doInBackground(Void... params) {
            try {
                int timeout = 2000;
                URL myUrl = new URL("http://www.google.com");
                URLConnection connection = myUrl.openConnection();
                connection.setConnectTimeout(timeout);
                connection.connect();
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        protected void onPostExecute(Boolean result) {
            if (mListener != null)
                mListener.onPostExecuteConcluded(result);
        }

        public interface IsInternetAvailableListener {
            void onPreExecuteConcluded();
            void onPostExecuteConcluded(boolean result);
        }

        final public void setListener(IsInternetAvailableListener listener) {
            mListener = listener;
        }
    }
}

package it.aorlando.cliccamangia;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.aorlando.cliccamangia.Model.Item;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String URL_DOMAIN = "http://www.orlandoantonio.it/01/cm/";

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ArrayList<Item> listItems = new ArrayList<Item>();
    private ProgressBar progressBar;
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        progressBar = (ProgressBar) findViewById(R.id.progressBarSomeItemMain);
        new PrBar().execute();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void menuOfTheDayActivity(View pView) {
        Intent menuOfTheDay = new Intent(this, MenuOfTheDay.class);
        startActivity(menuOfTheDay);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_plates) {
            // Handle the camera action
        } else if (id == R.id.nav_drinks) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class PrBar extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), R.string.loading, Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Void... params) {

            for (int i = 1; i < 60; i++){
                publishProgress(i);
                try {
                    Thread.sleep(25);
                }
                catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url = MainActivity.URL_DOMAIN + "webservice/public/index.php/getRandomItems";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    initList(response);
                    for (int i = 60; i < 100; i++){
                        publishProgress(i);
                        try {
                            Thread.sleep(50);
                        }
                        catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }

                    GridView gridView = (GridView) findViewById(R.id.grdViewMain);
                    GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), listItems);
                    gridView.setAdapter(gridAdapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ItemsView itemsView = new ItemsView();
                            Bundle bundle = new Bundle();
                            //bundle.putParcelableArrayList("data", listItems.get(position));
                            itemsView.setArguments(bundle);
                            itemsView.show((MainActivity.this).getSupportFragmentManager(),"Image Dialog");
                            //itemsView.show(fm, "Dialog Fragment");
                            Toast.makeText(getApplicationContext(), "Clicked " + listItems.get(position).getDescription(), Toast.LENGTH_SHORT).show();
                            /*final Dialog custom = new Dialog(MainActivity.this);
                            custom.setContentView(R.layout.item_view);
                            TextView name = (TextView) custom.findViewById(R.id.txtName);
                            TextView description = (TextView) custom.findViewById(R.id.txtDescription);
                            ImageView image = (ImageView) custom.findViewById(R.id.imageItem);
                            TextView price = (TextView) custom.findViewById(R.id.txtPrice);
                            TextView cl = (TextView) custom.findViewById(R.id.txtCl);
                            custom.setTitle("Prova");
                            name.setText(listItems.get(position).getName());
                            description.setText(listItems.get(position).getDescription());

                            price.setText((Double.toString(listItems.get(position).getPrice())));
                            if (listItems.get(position).getCl() > 0) {
                                cl.setText(listItems.get(position).getCl());
                            }

                            Button btnAddToBasket = (Button) custom.findViewById(R.id.btnAddBasket);
                            btnAddToBasket.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            });

                            Button btnClose = (Button) custom.findViewById(R.id.btnClose);
                            btnClose.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    custom.dismiss();
                                }
                            });
                            custom.show();*/
                        }
                    });
                    progressBar.setVisibility(View.GONE);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    for (int i = 60; i > 0; i--){
                        publishProgress(i);
                        try {
                            Thread.sleep(20);
                        }
                        catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                    }
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
            return null;
        }

        private void initList(String pResponse) {
            try {
                JSONObject jsonResponse = new JSONObject(pResponse);
                JSONArray jsonMainNode = jsonResponse.optJSONArray("items");
                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    String name = jsonChildNode.optString("name");
                    String description = jsonChildNode.optString("description");
                    String image = jsonChildNode.optString("image");
                    double price = jsonChildNode.optDouble("price");
                    listItems.add(new Item(name, description, image, price));
                }
            } catch (JSONException ex) {
                Toast.makeText(getApplicationContext(), "Error " + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Toast.makeText(getApplicationContext(), R.string.loadingComplete, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
    }
}

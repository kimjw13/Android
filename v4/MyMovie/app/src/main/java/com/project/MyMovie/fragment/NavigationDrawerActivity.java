package com.project.MyMovie.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.project.MyMovie.R;
import com.project.MyMovie.data.MovieInformation;
import com.project.MyMovie.data.MovieInformationList;
import com.project.MyMovie.data.ResponseInfo;
import com.project.MyMovie.util.AppHelper;

import java.util.ArrayList;
import java.util.Collections;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MovieListFragmentPager movieListFragmentPager;
    MovieDetailFragment movieDetailFragment;
    ArrayList<MovieInformation> movieInformationList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_activity);

        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        requestMovieList();
        init();

    }

    public void requestMovieList(){

        movieListFragmentPager = new MovieListFragmentPager();

        movieDetailFragment = new MovieDetailFragment();

        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";
        url += "?" + "type=1";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);

    }

    public void init(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"action_setting",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //drawerLayout
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_movie_list) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, movieListFragmentPager).commit();
            requestMovieList();
        } else if (id == R.id.nav_movie_api) {

        } else if (id == R.id.nav_movie_book) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onChangeFragment(int id, String title){
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.MOVIE_HEADER), new MovieInformation(id, title));
        movieDetailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, movieDetailFragment).commit();
    }



    public void processResponse(String response){

        Gson gson = new Gson();
        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);
        if(info.code == 200){
            MovieInformationList list = gson.fromJson(response, MovieInformationList.class);
            movieInformationList = new ArrayList<>();

            for(MovieInformation movieInfo : list.result) {
                Collections.addAll(movieInformationList, movieInfo);
            }

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(getString(R.string.MOVIE_LIST), movieInformationList);
            movieListFragmentPager.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, movieListFragmentPager).commit();
        }
    }
}

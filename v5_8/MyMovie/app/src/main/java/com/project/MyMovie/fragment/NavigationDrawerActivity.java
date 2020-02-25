package com.project.MyMovie.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import java.util.Stack;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public interface OnBackKeyPressedListener {
        void onBack();
    }

    public void pushOnBackKeyPressedListener(OnBackKeyPressedListener listener) {
        mFragmentBackStack.push(listener);
    }

    public Stack<OnBackKeyPressedListener> mFragmentBackStack = new Stack<>();


    MovieListFragmentPager movieListFragmentPager;
    MovieDetailFragment movieDetailFragment;
    ArrayList<MovieInformation> movieInformationList;

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_activity);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        requestMovieList();
        drawerInit();


    }

    public void drawerInit(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(!mFragmentBackStack.isEmpty()){
            mFragmentBackStack.pop().onBack();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, movieListFragmentPager).commit();
            requestMovieList();
            toolbar.setTitle("영화 목록");
            Log.e("back stack", "뭔가 동작되는중");
        }else{
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);}
             else {
                super.onBackPressed();
            }
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
            toolbar.setTitle("영화 목록");
        } else if (id == R.id.nav_movie_api) {

        } else if (id == R.id.nav_movie_book) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onChangeFragment(int id, String title, int grade){
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.movieHeader), new MovieInformation(id, title, grade));
        movieDetailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, movieDetailFragment).commit();
        toolbar.setTitle("영화 상세");
    }

    public void requestMovieList(){

        movieListFragmentPager = new MovieListFragmentPager();
        movieDetailFragment = new MovieDetailFragment();

        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";
        url += "?" + "type=1";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("nav_drawer_activity","메인 화면 로딩 완료");
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("nav_drawer_activity",error.getMessage());
                    }
                }
        );


        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);

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
            bundle.putParcelableArrayList(getString(R.string.movieList), movieInformationList);
            Log.e("Pager 확인", "pager 정상 로딩 완료");
            movieListFragmentPager.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, movieListFragmentPager).commit();
        }
    }

}

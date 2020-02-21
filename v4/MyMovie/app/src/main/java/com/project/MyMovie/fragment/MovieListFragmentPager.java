package com.project.MyMovie.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.project.MyMovie.R;
import com.project.MyMovie.data.MovieInformation;

import java.util.ArrayList;

public class MovieListFragmentPager extends Fragment {
    NavigationDrawerActivity activity;
    ArrayList<MovieInformation> list;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (NavigationDrawerActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.movie_list_fragment_pager,container,false);

        Bundle bundle = getArguments();

        if(bundle != null){
            list = bundle.getParcelableArrayList(getString(R.string.movieList));
        }else{
            Log.e("bundle is null","Bundle is null");
        }

        ViewPager pager = rootView.findViewById(R.id.pager);

        MoviePagerAdapter adapter = new MoviePagerAdapter(activity.getSupportFragmentManager());
        for(int i = 0; i < list.size(); i++){
            MovieFragment fragment = new MovieFragment(list.get(i).id,
                    list.get(i).image,
                    list.get(i).title,
                    list.get(i).reservation_rate,
                    list.get(i).grade);

            adapter.addItem(fragment);
        }
        pager.setAdapter(adapter);
        return rootView;
    }

    public class MoviePagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MoviePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }


}

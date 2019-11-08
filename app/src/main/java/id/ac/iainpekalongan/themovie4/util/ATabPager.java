package id.ac.iainpekalongan.themovie4.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.ac.iainpekalongan.themovie4.feature.MoviesFragment;
import id.ac.iainpekalongan.themovie4.feature.TVFragment;
import id.ac.iainpekalongan.themovie4.feature.favorite.FavoriteFragment;


public class ATabPager extends FragmentPagerAdapter {

    private static final int NUM_ITEMS = 3;

    public ATabPager(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MoviesFragment();

            case 1:
                return new TVFragment();

            case 2:
                return new FavoriteFragment();

            default:
                return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}

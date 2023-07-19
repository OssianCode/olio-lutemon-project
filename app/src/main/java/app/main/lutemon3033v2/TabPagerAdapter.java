package app.main.lutemon3033v2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import app.main.lutemon3033v2.fragments.FragmentBattle;
import app.main.lutemon3033v2.fragments.FragmentGallery;
import app.main.lutemon3033v2.fragments.FragmentHome;
import app.main.lutemon3033v2.fragments.FragmentTrain;

public class TabPagerAdapter extends FragmentStateAdapter {
    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new FragmentGallery();
            case 1:
                return new FragmentHome();
            case 2:
                return new FragmentTrain();
            case 3:
                return new FragmentBattle();
            default:
                return new FragmentGallery();
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

package com.example.upangpocketproject.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.upangpocketproject.Fragments.AccountFragment;
import com.example.upangpocketproject.Fragments.HomeFragment;
import com.example.upangpocketproject.Fragments.PaymentFragment;
import com.example.upangpocketproject.Fragments.RefundFragment;
import com.example.upangpocketproject.Fragments.SettingsFragment;

public class TabFragmentAdapter extends FragmentStateAdapter {
    public TabFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new PaymentFragment();
            case 2:
                return new RefundFragment();
            case 3:
                return new AccountFragment();
            case 4:
                return new SettingsFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

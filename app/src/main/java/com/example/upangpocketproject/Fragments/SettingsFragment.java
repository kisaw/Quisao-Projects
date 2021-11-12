package com.example.upangpocketproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.upangpocketproject.Activities.AboutUsActivity;
import com.example.upangpocketproject.Activities.LogInActivity;
import com.example.upangpocketproject.Activities.HowToUseActivity;
import com.example.upangpocketproject.R;
import com.example.upangpocketproject.Activities.ReportIssueActivity;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Intent intent = getActivity().getIntent();
        String studentID = intent.getStringExtra("studentID");

        // Array of strings...
        String[] settingsArray = {"Report an issue", "How to Use", "About Us", "Log Out"};

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                R.layout.settings_listview_layout, settingsArray);

        ListView listView = view.findViewById(R.id.setting_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent intent = new Intent(getContext(), ReportIssueActivity.class);
                    intent.putExtra("studentID", studentID);
                    startActivity(intent);
                }
                else if(i == 1){
                    Intent intent = new Intent(getContext(), HowToUseActivity.class);
                    startActivity(intent);
                }
                else if(i == 2){
                    Intent intent = new Intent(getContext(), AboutUsActivity.class);
                    startActivity(intent);
                }
                else if(i == 3){
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();

                    Intent intent = new Intent(getContext(), LogInActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        return view;
    }

}
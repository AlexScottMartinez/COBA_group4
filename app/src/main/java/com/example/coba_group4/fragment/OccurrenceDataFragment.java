package com.example.coba_group4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coba_group4.R;
import com.example.coba_group4.occurence.OccurrenceData;

public class OccurrenceDataFragment extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Intent intent = new Intent(getActivity(), OccurrenceData.class);
        startActivity(intent);
        return inflater.inflate(R.layout.activity_occurrence_data, container, false);

    }
}
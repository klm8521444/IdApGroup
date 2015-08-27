package com.gmail.s8521444.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.s8521444.R;

public class SimpleFragment extends Fragment {
    private static final String ARG_NUMBER = "number";
    private int mNumber;

    public static SimpleFragment newInstance(int number) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    public SimpleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNumber = getArguments().getInt(ARG_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        ((TextView) view.findViewById(R.id.number)).setText(String.valueOf(mNumber));
        return view;
    }

}

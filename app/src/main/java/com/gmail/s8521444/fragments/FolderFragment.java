package com.gmail.s8521444.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gmail.s8521444.Models.Cat;
import com.gmail.s8521444.R;
import com.gmail.s8521444.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class FolderFragment extends Fragment implements View.OnClickListener{

    String LOG_TAG = "myLogs";

    private RecyclerView recyclerView;

    private static final String ARG_NUMBER = "number";
    private int mNumber;

    public static FolderFragment newInstance(int number) {



        FolderFragment fragment = new FolderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    public FolderFragment() {
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

        Log.d(LOG_TAG, "onCreateView 1");
        View view = inflater.inflate(R.layout.fragment_folder, container, false);
        Log.d(LOG_TAG, "onCreateView 11");


        List<Cat> catList = new ArrayList<>();
        Log.d(LOG_TAG, "onCreateView 13");
        Cat cat1 = new Cat("Кот1", R.drawable.write32);
        catList.add(cat1);
        Cat cat2 = new Cat("Кот2", R.drawable.tv32);
        catList.add(cat2);
        Cat cat3 = new Cat("Кот3", R.drawable.top32);
        catList.add(cat3);
        Cat cat4 = new Cat("Кот4", R.drawable.time32);
        catList.add(cat4);
        Cat cat5 = new Cat("Кот5", R.drawable.technorati32);
        catList.add(cat5);
        Cat cat6 = new Cat("Кот6", R.drawable.target32);
        catList.add(cat6);
        Cat cat7 = new Cat("Кот7", R.drawable.digg32);
        catList.add(cat7);
        Cat cat8 = new Cat("Кот8", R.drawable.delete32);
        catList.add(cat8);
        Cat cat9 = new Cat("Кот9", R.drawable.write32);
        catList.add(cat9);
        Cat cat10 = new Cat("Кот10", R.drawable.connect32);
        catList.add(cat10);
        /* еще 30 котов
        populateRecords(catList);*/

        Log.d(LOG_TAG, "onCreateView 2");

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(catList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        Log.d(LOG_TAG, "onCreateView 3");

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true); // необязательно
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // необязательно
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(itemAnimator);

        Log.d(LOG_TAG, "onCreateView 4");

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recyclerview_item:
                // Тут view соответствует recyclerview_item.xml
                final int position = recyclerView.getChildLayoutPosition(view);
                Toast.makeText(getActivity(), "Вы щёлкнули на позиции " + position,
                        Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}

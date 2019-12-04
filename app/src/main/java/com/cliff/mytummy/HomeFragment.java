package com.cliff.mytummy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ArrayList<Fitur> mFiturData;
    private RecyclerView mRecyclerView;
    private adapterFitur mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view =  inflater.inflate(R.layout.activity_home_fragment,container,false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFiturData = new ArrayList<>();
        mAdapter = new adapterFitur(getContext(),mFiturData);
        mRecyclerView.setAdapter(mAdapter);

        Initialize();




//        CardView cardView1 = view.findViewById(R.id.card_diagnosis);
//        cardView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast toast = Toast.makeText(getActivity(),"ini Menu diagnosis penyakit perut",
//                        Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        });
        return view;
    }

    public void Initialize(){
        String[] fiturList = getResources()
                .getStringArray(R.array.judul_card);
        String[] fiturInfo = getResources()
                .getStringArray(R.array.penjelasan_card);
        TypedArray fiturImageResources = getResources()
                .obtainTypedArray(R.array.image_card);

        // Clear the existing data (to avoid duplication).
        mFiturData.clear();

        // Create the ArrayList of Sports objects with the titles and
        // information about each sport
        for (int i = 0; i < fiturList.length; i++) {
            mFiturData.add(new Fitur(fiturList[i], fiturInfo[i],
                    fiturImageResources.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        fiturImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    /**
     * onClick method for th FAB that resets the data.
     *
     * @param view The button view that was clicked.
     */

}

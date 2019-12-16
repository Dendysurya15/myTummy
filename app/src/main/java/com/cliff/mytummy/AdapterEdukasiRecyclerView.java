package com.cliff.mytummy;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterEdukasiRecyclerView extends ArrayAdapter<ambilData> {
    private static final String TAG = "AdaoterEdukasiRecyclerView";

    private Context context;

    public AdapterEdukasiRecyclerView(@NonNull Context context, int resource, @NonNull ArrayList<ambilData> objects, Context context1) {
        super(context, resource, objects);
        this.context = context1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}

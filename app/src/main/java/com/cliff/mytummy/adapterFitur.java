package com.cliff.mytummy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adapterFitur extends RecyclerView.Adapter<adapterFitur.ViewHolder> {
    private ArrayList<Fitur> mFiturData;
    private Context mcontext;


    adapterFitur(Context context, ArrayList<Fitur> fitur_data) {
        this.mFiturData = fitur_data;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public adapterFitur.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.list_fitur, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull adapterFitur.ViewHolder holder, int position) {
        Fitur currentFitur = mFiturData.get(position);
        holder.bindTo(currentFitur);

    }

    @Override
    public int getItemCount() {
        return mFiturData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mJudultext;
        private TextView mPenjelasanText;
        private ImageView mFiturImage;

        ViewHolder(View item) {
            super(item);

            mJudultext = item.findViewById(R.id.title_fitur);
            mPenjelasanText = item.findViewById(R.id.subTitle_fitur);
            mFiturImage = item.findViewById(R.id.image_fitur);
            // Set the OnClickListener to the entire view.
            item.setOnClickListener(this);
        }

        void bindTo(Fitur currentFitur) {
            // Populate the textviews with data.
            mJudultext.setText(currentFitur.getTitle());
            mPenjelasanText.setText(currentFitur.getInfo());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mcontext).load(
                    currentFitur.getImageResource()).into(mFiturImage);
        }

        @Override
        public void onClick(View view) {

            Fitur currentFitur = mFiturData.get(getAdapterPosition());
            currentFitur.setPosition(getAdapterPosition());


//
//            Intent detailIntent = new Intent(mcontext, Fitur_Diagnosis.class);
//////            detailIntent.putExtra("title", currentFitur.getTitle());
//////            detailIntent.putExtra("image_resource",
//////                    currentFitur.getImageResource());
//////            mcontext.startActivity(detailIntent);
//            String posisi = currentFitur.toString();
//            Log.e( "onClick: ",""+posisi );

            Integer posisi = (int) (long) currentFitur.getPosition();

            switch (posisi) {
                case 0:
                    Intent intent = new Intent(mcontext, Fitur_Diagnosis.class);
                    mcontext.startActivity(intent);
                    break;
                case 1:
                    Intent intent2 = new Intent(mcontext, Fitur_Tindak_lanjut.class);
                    mcontext.startActivity(intent2);
                    break;
                case 2:
                    Intent intent3 = new Intent(mcontext, Fitur_Rekomendasi_Obat_Baru.class);
                    mcontext.startActivity(intent3);
                    break;
                case 3:
                    Intent intent4 = new Intent(mcontext, Fitur_Edukasi.class);
                    mcontext.startActivity(intent4);
                    break;
                default:
            }

        }
    }
}

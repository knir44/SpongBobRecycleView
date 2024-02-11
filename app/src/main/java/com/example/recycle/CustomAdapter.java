package com.example.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<DataModel> dataSet;
    public CustomAdapter(ArrayList<DataModel> dataSet) {
        this.dataSet = dataSet;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        ImageView imageView;
        public MyViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.characterName);
            description = itemView.findViewById(R.id.characterDescription);
            imageView = itemView.findViewById(R.id.characterImage);

            itemView.setOnClickListener(v -> {
                String itemName = name.getText().toString();
                String snackbarMessage = "You've selected: " + itemName + "! Enjoy exploring further!";
                Snackbar snackbar = Snackbar.make(v, snackbarMessage, Snackbar.LENGTH_INDEFINITE);

                // Customizing the appearance
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.snackbarBackgroundColor)); // Change the background color

                // Accessing the Snackbar's text view to customize text color and size
                TextView textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
                textView.setTextColor(ContextCompat.getColor(v.getContext(), R.color.snackbarTextColor)); // Change the text color
                textView.setTextSize(16); // Set the text size

                // Customizing the action button text color and size
                Button actionButton = snackBarView.findViewById(com.google.android.material.R.id.snackbar_action);
                actionButton.setTextColor(ContextCompat.getColor(v.getContext(), R.color.snackbarActionTextColor)); // Change the action button text color
                actionButton.setTextSize(16); // Set the action button text size

                // Set the action to close the Snackbar
                snackbar.setAction("Close", view -> snackbar.dismiss());
                snackbar.show();
            });

        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
            TextView textViewName = holder.name;
            TextView textViewVersion = holder.description;
            ImageView imageView = holder.imageView;

            textViewName.setText(dataSet.get(position).getName());
            textViewVersion.setText(dataSet.get(position).getDescription());
            imageView.setImageResource(dataSet.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}

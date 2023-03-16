package com.example.howareu.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.howareu.R;
import com.example.howareu.model.SimpleActivityModel;

import org.w3c.dom.Text;

import java.util.List;

public class HomeActivityAdapter extends RecyclerView.Adapter<HomeActivityAdapter.ViewHolder> {

    private List<SimpleActivityModel> simpleActivityModel;
    Context context;

    private OnDeleteActivityClickListener onDeleteActivityClickListener;
    private OnActivityMoodRateClickListener onMoodRateClickListener;
    private OnTextChangeListener onTextChangeListener;

    public HomeActivityAdapter(List<SimpleActivityModel> simpleActivityModel, Context context, OnDeleteActivityClickListener onDeleteActivityClickListener, OnActivityMoodRateClickListener onMoodRateClickListener, OnTextChangeListener onTextChangeListener) {
        this.simpleActivityModel = simpleActivityModel;
        this.context = context;
        this.onDeleteActivityClickListener = onDeleteActivityClickListener;
        this.onMoodRateClickListener = onMoodRateClickListener;
        this.onTextChangeListener = onTextChangeListener;
    }

    @NonNull
    @Override
    public HomeActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activities,parent,false);
        ViewHolder vh= new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeActivityAdapter.ViewHolder holder, int position) {


        holder.editTextActivityRate.setFocusable(false);
        holder.editTextActivityRate.setClickable(false);
        holder.editTextActivity.setText(simpleActivityModel.get(position).getActivityName());
        holder.editTextActivityRate.setText(simpleActivityModel.get(position).getMoodrate()+"");
        int x= holder.getAdapterPosition();
        holder.editTextActivity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){

                    onTextChangeListener.onTextChanged(holder.editTextActivity.getText().toString(), x);
                }
            }
        });


        holder.btnDeleteMoodActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(simpleActivityModel.size()>1){

                    onDeleteActivityClickListener.onDeleteActivityClicked(holder.getAdapterPosition());
                }

            }
        });

        holder.btnRateMoodActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onMoodRateClickListener.onMoodRateClicked(holder.editTextActivity.getText().toString(), holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {

        return simpleActivityModel.size();
    }






    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText editTextActivity, editTextActivityRate;
        private Button btnRateMoodActivity, btnDeleteMoodActivity;
        private CardView activity_CardView;
        private Button addActivity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            activity_CardView= itemView.findViewById(R.id.activity_CardView);

            editTextActivity= itemView.findViewById(R.id.editTextActivity);
            editTextActivityRate= itemView.findViewById(R.id.editTextActivityRate);

            addActivity = itemView.findViewById(R.id.btnAddActivity);

            btnRateMoodActivity= itemView.findViewById(R.id.btnRateMoodActivity);
            btnDeleteMoodActivity= itemView.findViewById(R.id.btnDeleteMoodActivity);




        }
    }

    public interface OnDeleteActivityClickListener {
        void onDeleteActivityClicked(int position);
    }



    public void setOnDeleteItemClickListener(OnDeleteActivityClickListener listener) {
        this.onDeleteActivityClickListener = listener;
    }



    public interface OnActivityMoodRateClickListener {
        void onMoodRateClicked(String name,int position);
    }



    public void setOnMoodRateClickListener(OnActivityMoodRateClickListener listener) {
        this.onMoodRateClickListener = listener;
    }



    public interface OnTextChangeListener {
        void onTextChanged(String name,int position);
    }



    public void setOnTextChangeListener(OnTextChangeListener listener) {
        this.onTextChangeListener = listener;
    }

}
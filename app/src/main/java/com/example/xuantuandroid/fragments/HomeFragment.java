package com.example.xuantuandroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xuantuandroid.R;
import com.example.xuantuandroid.adapters.CategoryAdapter;
import com.example.xuantuandroid.models.CategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    RecyclerView cateRecylerview;

    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    public HomeFragment() {
        // Required empty public constructor
    }

    FirebaseFirestore db;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);

        cateRecylerview = root.findViewById(R.id.rec_category);


        db = FirebaseFirestore.getInstance();

        /// image slider
/*
        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels= new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner1,"Discount", ImageView.ScaleType.CENTER_CROP));

        imageSlider.setImageList(slideModels);*/

        cateRecylerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        categoryModelList = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(getContext(),categoryModelList);

        cateRecylerview.setAdapter(categoryAdapter);


        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {

                        }
                    }
                });





        return root;




    }
}
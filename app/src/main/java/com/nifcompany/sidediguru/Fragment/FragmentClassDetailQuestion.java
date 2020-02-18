package com.nifcompany.sidediguru.Fragment;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nifcompany.sidediguru.Adapter.AdapterRvClassDetailQuestion;
import com.nifcompany.sidediguru.ClassDetail;
import com.nifcompany.sidediguru.Get.GetSpesificClass.Meta;
import com.nifcompany.sidediguru.Get.GetSpesificClass.QuestionsItem;
import com.nifcompany.sidediguru.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentClassDetailQuestion extends Fragment {
    private List<QuestionsItem> listQuestion = new ArrayList<>();
    private Meta meta;
    ClassDetail detailClass;


    private RecyclerView rvClassDetailQuestion;

    AdapterRvClassDetailQuestion adapterRvClassDetailQuestion;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_class_detail_question, container, false);
        rvClassDetailQuestion = rootView.findViewById(R.id.RvClassDetailQuestion);

        Activity activity = getActivity();
        if(activity instanceof ClassDetail){
            ClassDetail detailClass = (ClassDetail) activity;
            listQuestion = detailClass.getListQuestionItem();
        }

        Log.d("RESPON : ", "FragmentQuestion " + listQuestion);
        showRecyclerList();

        return rootView;
    }

    private void showRecyclerList() {
        Log.d("RESPON Fragment 2", "onResponse: " + listQuestion);

        LinearLayoutManager classDetailQuestion = new LinearLayoutManager(getContext());

        adapterRvClassDetailQuestion = new AdapterRvClassDetailQuestion(listQuestion);

        rvClassDetailQuestion.setLayoutManager(classDetailQuestion);

        rvClassDetailQuestion.setAdapter( adapterRvClassDetailQuestion );

//        adapterRvClassDetailStudent.setOnItemClickCallBack(new OnItemClickCallBack() {
//            @Override
//            public void onItemClicked(ItemsItem data) {
//
//            }
//
//        });

        rvClassDetailQuestion.setHasFixedSize(true);
    }



}

package com.nifcompany.sidediguru.Fragment;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nifcompany.sidediguru.Adapter.AdapterRvClassDetailStudent;
import com.nifcompany.sidediguru.ClassDetail;
import com.nifcompany.sidediguru.Get.GetSpesificClass.StudentsItem;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBackStudentProfile;
import com.nifcompany.sidediguru.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentClassDetailStudent extends Fragment {
    private List<StudentsItem> listStudent = new ArrayList<>();
    private RecyclerView rvClassDetailStudent;
    AdapterRvClassDetailStudent adapterRvClassDetailStudent;
    Dialog dialogStudentProfile;
    ClassDetail detailClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_class_detail_student, container, false);
        rvClassDetailStudent = rootView.findViewById(R.id.RvClassDetailStudent);

        Activity activity = getActivity();
        if(activity instanceof ClassDetail){
            ClassDetail detailClass = (ClassDetail) activity;
            listStudent = detailClass.getListStudentDetail();
        }

        showRecyclerList();

        Log.d("RESPON : ", "FragmentStudent " + listStudent);

        return rootView;
    }

    private void showRecyclerList() {
        LinearLayoutManager classDetailStudent = new LinearLayoutManager(getContext());

        adapterRvClassDetailStudent = new AdapterRvClassDetailStudent(listStudent);

        rvClassDetailStudent.setLayoutManager(classDetailStudent);

        rvClassDetailStudent.setAdapter( adapterRvClassDetailStudent );

        adapterRvClassDetailStudent.setOnItemClickCallBackStudentProfile(new OnItemClickCallBackStudentProfile() {
            @Override
            public void onItemClicked(StudentsItem studentsItem) {
                dialogStudentProfile = new Dialog(getContext());
                ShowPopup(studentsItem);
            }

        });

        rvClassDetailStudent.setHasFixedSize(true);
    }




    public void ShowPopup(StudentsItem studentsItem) {

        TextView tvName, tvNIS, tvJk, tvUmur, tvAgama, tvJoinAt;
        String vName, vNIS, vJK, vAgama, vJoinAt;
        Integer vUmur;

        dialogStudentProfile.setContentView(R.layout.pop_up_student_profile);

        tvName = dialogStudentProfile.findViewById(R.id.TvStudentDetailName);
        tvNIS = dialogStudentProfile.findViewById(R.id.TvStudentDetailNIS);
        tvJk = dialogStudentProfile.findViewById(R.id.TvStudentDetailJenisKelamin);
        tvUmur = dialogStudentProfile.findViewById(R.id.TvStudentDetailUmur);
        tvAgama = dialogStudentProfile.findViewById(R.id.TvStudentDetailAgama);
        tvJoinAt = dialogStudentProfile.findViewById(R.id.TvStudentDetailJoinAt);

        vName = studentsItem.getStudentName();
        vNIS = studentsItem.getNISN();
        vJK = studentsItem.getGender();
        vUmur = studentsItem.getAge();
        vAgama = studentsItem.getReligion();
        vJoinAt = studentsItem.getJoinAt();

        tvName.setText(vName);
        tvNIS.setText(vNIS);
        tvJk.setText(vJK);
        tvUmur.setText(""+vUmur);
        tvAgama.setText(vAgama);
        tvJoinAt.setText(vJoinAt);

        dialogStudentProfile.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogStudentProfile.show();
    }
}

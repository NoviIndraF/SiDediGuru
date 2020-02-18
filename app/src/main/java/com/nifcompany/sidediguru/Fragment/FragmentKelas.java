package com.nifcompany.sidediguru.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nifcompany.sidediguru.Adapter.AdapterClassData;
import com.nifcompany.sidediguru.ClassDetail;
import com.nifcompany.sidediguru.Get.GetAllClass.ItemsItem;
import com.nifcompany.sidediguru.MainActivity;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBackRecentClass;
import com.nifcompany.sidediguru.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentKelas extends Fragment {

    private List<ItemsItem> list = new ArrayList<>();
    private RecyclerView rvClassData;
    AdapterClassData listRecentAdapter;

    String vClassName, vCodeRef, vDateCreated;
    Integer vStudent, vId;
    Context mContex;

    public FragmentKelas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kelas, container, false);
        rvClassData = rootView.findViewById(R.id.RvClassMenu);
        setHasOptionsMenu(true);

        list = ((MainActivity)getActivity()).getListDataClass();

        showRecyclerList();
        rvClassData.setHasFixedSize(true);

        SearchView searchView  = rootView.findViewById(R.id.SearchView);
        searchView.setQueryHint("Cari Sesuatu....");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<ItemsItem> dataFilter = new ArrayList<>();
                for(ItemsItem data : list){
                    String nama = data.getClassName().toLowerCase();
                    if(nama.contains(newText)){
                        dataFilter.add(data);
                    }
                }
                listRecentAdapter.setFilter(dataFilter);
                return true;
            }
        });

        return rootView;
    }

    private void showRecyclerList() {
        LinearLayoutManager llmV = new LinearLayoutManager(getContext());
        listRecentAdapter = new AdapterClassData(list);
        rvClassData.setLayoutManager(llmV);
        rvClassData.setAdapter( listRecentAdapter );
        listRecentAdapter.setOnItemClickCallBack(new OnItemClickCallBackRecentClass() {
            @Override
            public void onItemClicked(ItemsItem data) {
                pindahData(data);
            }
        });
    }

    private void pindahData(ItemsItem data) {
        vId             = data.getClassId();
        vClassName      = data.getClassName();
        vCodeRef        = data.getCodeRefferal();
        vDateCreated    = data.getCreatedAt();
        vStudent        = data.getStudents();

        Intent intent = new Intent(getContext(), ClassDetail.class);

        intent.putExtra("classIdDetailClass", vId);
        intent.putExtra("className", vClassName);
        intent.putExtra("codeRef", vCodeRef);
        intent.putExtra("dateCreated", vDateCreated);
        intent.putExtra("students", vStudent);
        startActivity(intent);
    }
}

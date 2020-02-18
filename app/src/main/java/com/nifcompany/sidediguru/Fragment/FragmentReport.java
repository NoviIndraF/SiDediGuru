package com.nifcompany.sidediguru.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nifcompany.sidediguru.Adapter.AdapterReportData;
import com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem;
import com.nifcompany.sidediguru.MainActivity;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBack;
import com.nifcompany.sidediguru.R;
import com.nifcompany.sidediguru.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReport extends Fragment {
    private List<ItemsItem> listReport = new ArrayList<>();
    private RecyclerView rvReportData;

    public FragmentReport() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_laporan, container, false);
        rvReportData = rootView.findViewById(R.id.RvReport);

        listReport = ((MainActivity)getActivity()).getListDataReport();

        showRecyclerList();
        rvReportData.setHasFixedSize(true);
        return rootView;
    }

    private void showRecyclerList() {
        LinearLayoutManager llmV = new LinearLayoutManager(getContext());
        AdapterReportData listRecentAdapter = new AdapterReportData(listReport);

        rvReportData.setLayoutManager(llmV);

        rvReportData.setAdapter( listRecentAdapter );

        listRecentAdapter.setOnItemClickCallBack(new OnItemClickCallBack() {
            @Override
            public void onItemClicked(com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem itemsItem) {
                Intent intent = new Intent(getContext(), Report.class);
                intent.putExtra("reportClassId", itemsItem.getClassId());
                startActivity(intent);
            }
        });
    }
}

package com.nifcompany.sidediguru.Fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nifcompany.sidediguru.Adapter.AdapterRvLatesData;
import com.nifcompany.sidediguru.Adapter.AdapterRvRecentClass;
import com.nifcompany.sidediguru.ClassDetail;
import com.nifcompany.sidediguru.Get.GetAllClass.ItemsItem;
import com.nifcompany.sidediguru.MainActivity;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBack;
import com.nifcompany.sidediguru.OnItemClickCallBack.OnItemClickCallBackRecentClass;
import com.nifcompany.sidediguru.Profile;
import com.nifcompany.sidediguru.R;
import com.nifcompany.sidediguru.Report;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    private List<ItemsItem> list = new ArrayList<>();
    private List<com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem> listReport = new ArrayList<>();

    private RecyclerView rvRecentData,rvLatesData;

    private CircleImageView civImage;
    com.nifcompany.sidediguru.Get.GetProfileGuru.Data dataProfile;

    AdapterRvRecentClass listRecentAdapter;
    AdapterRvLatesData listLatesAdapter;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rvRecentData = rootView.findViewById(R.id.RvRecentMenu);
        rvLatesData = rootView.findViewById(R.id.RvLatesMenu);
        civImage    = rootView.findViewById(R.id.CivImagePofile);

        civImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowProfile();
            }
        });



        list = ((MainActivity)getActivity()).getListDataClass();
        dataProfile = ((MainActivity)getActivity()).getDataProfile();
        listReport = ((MainActivity)getActivity()).getListDataReport();

        Glide.with(civImage)
                .load(dataProfile.getBiodata().getImageProfile())
                .apply(new RequestOptions().override(300,300))
                .into(civImage);

        showRecyclerListRecentClass(list);
        showRecyclerListLatesReport(listReport);
        return rootView;
    }

    private void showRecyclerListRecentClass(List<ItemsItem> listRv) {
        LinearLayoutManager llmH = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        listRecentAdapter = new AdapterRvRecentClass(listRv);
        rvRecentData.setLayoutManager(llmH);
        rvRecentData.setAdapter( listRecentAdapter );

        listRecentAdapter.setOnItemClickCallBackRecentClass(new OnItemClickCallBackRecentClass() {
            @Override
            public void onItemClicked(ItemsItem itemsItem) {
                Intent intent = new Intent(getContext(), ClassDetail.class);
                intent.putExtra("classIdDetailClass", itemsItem.getClassId());
                startActivity(intent);
            }
        });



        rvRecentData.setHasFixedSize(true);

    }
    private void showRecyclerListLatesReport(List<com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem> listRv){
        LinearLayoutManager llmV = new LinearLayoutManager(getContext());
        listLatesAdapter = new AdapterRvLatesData(listRv);
        rvLatesData.setLayoutManager(llmV);
        rvLatesData.setAdapter( listLatesAdapter );

        listLatesAdapter.setOnItemClickCallBack(new OnItemClickCallBack() {
            @Override
            public void onItemClicked(com.nifcompany.sidediguru.Get.GetAllReport.ItemsItem data) {
                Intent intent = new Intent(getContext(), Report.class);
                intent.putExtra("reportClassId", data.getClassId());
                startActivity(intent);
            }
        });

        rvLatesData.setHasFixedSize(true);
    }


    public void ShowProfile(){
        Intent intent = new Intent(getContext(), Profile.class);
        startActivity(intent);
    }

    public void ShowPopupNoConnection() {

        Button btnNoConnectionSuccsess;

        final Dialog dialogNoConnection = new Dialog(getContext());
        dialogNoConnection.setContentView(R.layout.pop_up_no_connection);

        btnNoConnectionSuccsess   = dialogNoConnection.findViewById(R.id.BtnNoConnectionSuccess);


        dialogNoConnection.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogNoConnection.show();

        btnNoConnectionSuccsess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogNoConnection.dismiss();
            }
        });
    }
}

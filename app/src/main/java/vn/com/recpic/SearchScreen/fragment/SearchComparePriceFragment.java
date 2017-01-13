package vn.com.recpic.SearchScreen.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.com.recpic.R;
import vn.com.recpic.SearchScreen.activity.SearchLocationActivity;

/**
 * Created by Administrator on 1/12/2017.
 */

public class SearchComparePriceFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_compare_price, container, false);
        init(view);
        return view;
    }

    private void init(View view){
        TextView txtSearchLocation = (TextView) view.findViewById(R.id.txtSearchLocation);
        txtSearchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchLocationActivity.class);
                startActivity(intent);
            }
        });
    }
}

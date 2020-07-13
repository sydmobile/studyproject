package com.study.study_module.fragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.R;
import com.study.userful.util.L;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2020/6/2 12:13
 *
 * @author syd
 * @version 1.0
 */
public class FragmentOne extends Fragment {

    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.tv_close)
    TextView tvClose;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        L.e("one","onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgament_one, container, false);
        ButterKnife.bind(this, view);
        L.e("one", "onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.e("one", "onViewCreated");
        tvAdd.setOnClickListener((view1) -> {
            Log.e("add", "add");
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fl, new FragmentTwo());
            transaction.hide(FragmentOne.this);
            transaction.addToBackStack(null);
            transaction.commit();
        });
        tvClose.setOnClickListener(view1 -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.hide(FragmentOne.this);
            transaction.commit();

        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e("one", "onCreate");
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        L.e("one","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        L.e("one","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        L.e("one","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        L.e("one", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        L.e("one", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.e("one","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e("one", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        L.e("one","onDetach()");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        L.e("two",hidden+"");
    }

}

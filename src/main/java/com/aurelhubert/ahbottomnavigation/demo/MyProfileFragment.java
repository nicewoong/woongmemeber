package com.aurelhubert.ahbottomnavigation.demo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aurelhubert.ahbottomnavigation.demo.group.GroupFragment;


/**
 * 내 프로필 화면을 다루는 프래그먼트
 */
public class MyProfileFragment extends Fragment {

    /**
     * Create a new instance of the fragment
     */
    public static MyProfileFragment newInstance() {

        // 여기서 index 가 0,1,2,3,4 까지에 따라서 Fragment 설정해주면 되겠다.
        MyProfileFragment fragment = new MyProfileFragment(); // Fragment 를 새로 생성해서 반환해주는구나
        Bundle b = new Bundle();
        b.putInt("index", 4); // 4 번째 index of 탭에 들어갈 프래그먼트 ( 내 프로필 화면)
        fragment.setArguments(b); // Fragment 에 index 설정하고 반환해준다
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 나머지 1,2,3,4 페이지
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        return view;

    }



}

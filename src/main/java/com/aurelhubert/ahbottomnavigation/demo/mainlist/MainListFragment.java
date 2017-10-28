package com.aurelhubert.ahbottomnavigation.demo.mainlist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.aurelhubert.ahbottomnavigation.demo.R;
import com.aurelhubert.ahbottomnavigation.demo.lounge.LoungeFragment;

import java.util.ArrayList;


/**
 * 첫 번째 화면인 내 명함첩 프래그먼트
 */
public class MainListFragment extends Fragment {


    private FrameLayout fragmentContainer;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    /**
     * Create a new instance of the fragment
     */
    public static MainListFragment newInstance() {

        // 여기서 index 가 0,1,2,3,4 까지에 따라서 Fragment 설정해주면 되겠다.
        MainListFragment fragment = new MainListFragment(); // Fragment 를 새로 생성해서 반환해주는구나
        Bundle b = new Bundle();
        b.putInt("index", 0); // 첫 번째 탭에 위치할 프래그먼트
        fragment.setArguments(b); // Fragment 에 index 설정하고 반환해준다
        return fragment;
    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 나머지 1,2,3,4 페이지
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);
        initDemoList(view);
        return view;

    }

    /**
     * Init the fragment
     * 리스트뷰 프레그먼트 처리
     */
    private void initDemoList(View view) {


        fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_main_list_container);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_main_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        // 샘플 데이터 생성
        ArrayList<String> itemsData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            itemsData.add("Fragment " + getArguments().getInt("index", -1) + " / Item " + i);
        }

        MainListRecyclerAdapter adapter = new MainListRecyclerAdapter(makeSampleBusinessCard());
        recyclerView.setAdapter(adapter);

    }


    /**
     * 명함 리스트에 보여줄 Sample 데이터를 생성해서 반환한다
     * @return
     */
    private ArrayList<BusinessCardProfile> makeSampleBusinessCard () {

        ArrayList<BusinessCardProfile> arrayOfUsers = new ArrayList<BusinessCardProfile>();
        arrayOfUsers.add(new BusinessCardProfile("한웅제", "학생 ", "(경북대학교 컴퓨터학부)", "business_card_1") );
        arrayOfUsers.add(new BusinessCardProfile("김말숙", "대리 ", "(한국감정원 정보전산실/ 정보개발)", "business_card_2"));
        arrayOfUsers.add(new BusinessCardProfile("김정태", "대리", "(신용보증기금 기획실)", "business_card_3"));
        arrayOfUsers.add(new BusinessCardProfile("신상보", "대표 ", "(동복 해녀 회국수)", "business_card_4"));
        arrayOfUsers.add(new BusinessCardProfile("정도현", "사회복지사 ", "(대구장애인종합복지관)", "business_card_1"));
        arrayOfUsers.add(new BusinessCardProfile("James", "Associate Director ", "(Charles W.Davidson Corp.)", "business_card_2"));
        arrayOfUsers.add(new BusinessCardProfile("Amy", "Senior Data Engineer ","(NETFLIX data Engineering Center)", "business_card_3"));


        return arrayOfUsers;


    }

    /**
     * Refresh
     */
    public void refresh() {
        if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }
}

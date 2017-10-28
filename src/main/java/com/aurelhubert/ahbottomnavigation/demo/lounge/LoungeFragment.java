package com.aurelhubert.ahbottomnavigation.demo.lounge;

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

import java.util.ArrayList;

/**
 *
 */
public class LoungeFragment extends Fragment {

	private FrameLayout fragmentContainer;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;

	/**
	 * Create a new instance of the fragment
	 */
	public static LoungeFragment newInstance(int index) {

		// 여기서 index 가 0,1,2,3,4 까지에 따라서 Fragment 설정해주면 되겠다.
		LoungeFragment fragment = new LoungeFragment(); // Fragment 를 새로 생성해서 반환해주는구나
		Bundle b = new Bundle();
		b.putInt("index", index);
		fragment.setArguments(b); // Fragment 에 index 설정하고 반환해준다
		return fragment;
	}





	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			// 나머지 1,2,3,4 페이지
			View view = inflater.inflate(R.layout.fragment_lounge, container, false);
			initDemoList(view);
			return view;

	}

	/**
	 * Init the fragment
	 * 리스트뷰 프레그먼트 처리
	 */
	private void initDemoList(View view) {

		fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_container);
		recyclerView = (RecyclerView) view.findViewById(R.id.fragment_lounge_recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);


		// 샘플 데이터 생성
		ArrayList<String> itemsData = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			itemsData.add("Fragment " + getArguments().getInt("index", -1) + " / Item " + i);
		}

		LoungeRecyclerAdapter adapter = new LoungeRecyclerAdapter(itemsData);
		recyclerView.setAdapter(adapter);
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

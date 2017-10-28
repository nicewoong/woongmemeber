package com.aurelhubert.ahbottomnavigation.demo.mainlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.aurelhubert.ahbottomnavigation.demo.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Duration;

import java.io.File;
import java.util.ArrayList;

/**
 * 메인 명함리스트 (MainListFragment) 에서 리스트의 조작을 담당할 recyclerViewAdapter 이다
 * Apache License 오픈소스 라이브러리인 MaterialStyledDialogs 를 사용하였다
 * Copyright 2016 Javier Santos

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 MaterialStyledDialogs includes code from material-dialogs, which is
 licensed under the MIT license. You may obtain a copy at

 see https://github.com/afollestad/material-dialogs/blob/master/LICENSE.txt

 */
public class MainListRecyclerAdapter extends RecyclerView.Adapter<MainListRecyclerAdapter.ViewHolder> {

	private ArrayList<BusinessCardProfile> businessCardProfiles = new ArrayList<BusinessCardProfile>();

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView nameTextView;
		public TextView roleTextView;
		public TextView companyTextView;
		public ImageView businessCardImageView;



        /**
		 * 뷰 홀더에서 아이템 내의 뷰들을 인스턴스화 시켜주고
		 * @param v
         */
		public ViewHolder(final View v) {
			super(v);
			nameTextView = (TextView) v.findViewById(R.id.name);
			roleTextView = (TextView) v.findViewById(R.id.role);
			companyTextView = (TextView) v.findViewById(R.id.company);
			businessCardImageView = (ImageView) v.findViewById(R.id.business_card_image);

		}
	}

	public MainListRecyclerAdapter(ArrayList<BusinessCardProfile> dataset) {
		businessCardProfiles.clear();
		businessCardProfiles.addAll(dataset);
	}

	@Override
	public MainListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_mainlist, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}


	// 여기서 데이터를 셋 해줍시다..
	// 그리고 여기서 아이템 순서에 있는 데이터가 바인더 되니까 이곳에서 온클릭리스너를 구현해주면 되겠다
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final BusinessCardProfile currentCard = businessCardProfiles.get(position);
		holder.nameTextView.setText(businessCardProfiles.get(position).name); //이름 지정
		holder.roleTextView.setText(businessCardProfiles.get(position).role); // 직책 지정
		holder.companyTextView.setText(businessCardProfiles.get(position).company); // 회사 이름 지정
		holder.businessCardImageView.setImageResource(businessCardProfiles.get(position).imageDrawableID);

		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// 명함 리스트에서 아이템 클릭하면 다이얼로그를 형성해준다
				new MaterialStyledDialog.Builder(view.getContext())
						.setTitle(currentCard.name)
						.setDescription(currentCard.role + "\n" + currentCard.company )
						.setHeaderDrawable(currentCard.imageDrawableID)
						.withDarkerOverlay(true) // 배경화면 오버레이
						.withDialogAnimation(true, Duration.SLOW) // 다이얼로그 나타나고 사라질 때 애니메이션
						.setCancelable(true) // 바깥 눌렀을 때 다이얼로그 삭제
						.setPositiveText("명함보기")
						.onPositive( new MaterialDialog.SingleButtonCallback() {
							@Override
							public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

							}
						})
						.setNeutralText("대화하기")
						.onNeutral( new MaterialDialog.SingleButtonCallback() {
							@Override
							public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

							}
						})
						.setNegativeText("이 명함 전달")
						.onNegative( new MaterialDialog.SingleButtonCallback() {
							@Override
							public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

							}
						})
						.show();



			}
		});

	}

	@Override
	public int getItemCount() {
		return businessCardProfiles.size();
	}

}

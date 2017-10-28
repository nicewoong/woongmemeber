package com.aurelhubert.ahbottomnavigation.demo.mainlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.demo.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import java.io.File;
import java.util.ArrayList;

/**
 *
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
		 * 클릭리스너도 달아주면 된다
		 * @param v
         */
		public ViewHolder(final View v) {
			super(v);
			nameTextView = (TextView) v.findViewById(R.id.name);
			roleTextView = (TextView) v.findViewById(R.id.role);
			companyTextView = (TextView) v.findViewById(R.id.company);
			businessCardImageView = (ImageView) v.findViewById(R.id.business_card_image);

			v.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					// 명함 리스트에서 아이템 클릭하면 다이얼로그를 형성해준다
					new MaterialStyledDialog.Builder(v.getContext())
							.setTitle("Awesome!")
							.setDescription("What can we improve? Your feedback is always welcome.")
							.setHeaderDrawable(R.drawable.business_card_3)
							.withDarkerOverlay(true)
							.show();
				}
			});

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
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.nameTextView.setText(businessCardProfiles.get(position).name); //이름 지정
		holder.roleTextView.setText(businessCardProfiles.get(position).role); // 직책 지정
		holder.companyTextView.setText(businessCardProfiles.get(position).company); // 회사 이름 지정

		String path = Environment.getExternalStorageDirectory()+ "/drawable/"+ businessCardProfiles.get(position).image +".png";

		File imgFile = new File(path);
		if(imgFile.exists())
		{
			Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			holder.businessCardImageView.setImageBitmap(myBitmap);
		}


	}

	@Override
	public int getItemCount() {
		return businessCardProfiles.size();
	}

}

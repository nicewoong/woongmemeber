package com.aurelhubert.ahbottomnavigation.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.aurelhubert.ahbottomnavigation.demo.group.GroupFragment;
import com.aurelhubert.ahbottomnavigation.demo.group.MessageFragment;
import com.aurelhubert.ahbottomnavigation.demo.lounge.LoungeFragment;

import java.util.ArrayList;

/**
 *
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> fragments = new ArrayList<>();
	private Fragment currentFragment;

	public MainViewPagerAdapter(FragmentManager fm) {
		super(fm);

		fragments.clear();
		fragments.add(LoungeFragment.newInstance(0));
		fragments.add(GroupFragment.newInstance()); // 1번 index 탭에 들어갈 프래그먼트 (Group 프래그먼트)
		fragments.add(MessageFragment.newInstance()); // 2번 index 탭에 들어갈 프래그먼트  (message 프래그먼트)
		fragments.add(LoungeFragment.newInstance(3));
		fragments.add(LoungeFragment.newInstance(4));
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		if (getCurrentFragment() != object) {
			if(position==1) {
				currentFragment = ((GroupFragment) object);

			} else if(position==2){
				currentFragment = ((MessageFragment) object);

			}else {
				currentFragment = ((LoungeFragment) object);
			}

		}
		super.setPrimaryItem(container, position, object);
	}

	/**
	 * Get the current fragment
	 */
	public Fragment getCurrentFragment() {
		return currentFragment;
	}
}
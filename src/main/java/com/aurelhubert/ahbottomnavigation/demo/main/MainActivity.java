package com.aurelhubert.ahbottomnavigation.demo.main;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.demo.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

	private Fragment currentFragment;
	private MainViewPagerAdapter adapter;
	private AHBottomNavigationAdapter navigationAdapter;
	private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
	private boolean useMenuResource = true;
	private int[] tabColors;
	private Handler handler = new Handler();

	// UI
	private AHBottomNavigationViewPager viewPager;
	private AHBottomNavigation bottomNavigation;
	private FloatingActionButton floatingActionButton;

	private Drawer drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		boolean enabledTranslucentNavigation = getSharedPreferences("shared", Context.MODE_PRIVATE)
				.getBoolean("translucentNavigation", false);
		setTheme(enabledTranslucentNavigation ? R.style.AppTheme_TranslucentNavigation : R.style.AppTheme);
		setContentView(R.layout.activity_home);
		initUI();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacksAndMessages(null);
	}


	/**
	 * Init UI
	 */
	private void initUI() {
		
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
		}
		
		bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
		viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);
		floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);

		tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
		navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_5); // 메뉴 다섯개
		navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);



		bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
		bottomNavigation.setTranslucentNavigationEnabled(true);


		//Bottom Navigation 의 탭을 눌러서 화면이 변할때 호출되는 리스너 이다
		bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
			@Override
			public boolean onTabSelected(int position, boolean wasSelected) { // Position 값으로 탭 된 인덱스를 확인할 수 있다

				viewPager.setCurrentItem(position, false); // 뷰 페이저의 프레그먼트를 교체해줍니다. 현재 포지션에 맞게!

				return true;
			}
		});


		viewPager.setOffscreenPageLimit(4);
		adapter = new MainViewPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);

		currentFragment = adapter.getCurrentFragment();


		// Actionbar 에다가 아이콘 넣기
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_50);

		// Navigation Drawer (슬라이드 메뉴) 를 추가해주자 (라이브러리 이용)
		addNavigationDrawer();


	} // End of initUI()


	/**
	 * 액션바에 있는 옵션 버튼이 눌렸을 때 작동하는 리스너 이다
	 * @param item
	 * @return
     */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int itemId = item.getItemId();
		switch (itemId) {
			case android.R.id.home: // 액션바에 있는 메뉴버튼 눌렸을 때 ! (홈버튼을 메뉴 버튼으로 지정해놨음)
				drawer.openDrawer();
				return true;
			default:
				return super.onOptionsItemSelected(item);

		}

	}

	/**
	 * navigation drawer 를 생성한다
	 * 메뉴의 내용은 샘플 데이터로 채운다
	 */
	public void addNavigationDrawer() {
		//if you want to update the items at a later time it is recommended to keep it in a variable
		PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("item1").withSelectable(false);
		SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("item2");

		//create the drawer and remember the `Drawer` result object
		drawer = new DrawerBuilder()
				.withActivity(this)
				.withTranslucentStatusBar(false)
				.withActionBarDrawerToggle(false)
				.withActionBarDrawerToggleAnimated(true)
				.addDrawerItems(
						item1,
						new DividerDrawerItem().withEnabled(true),
						new PrimaryDrawerItem().withName("선물하기"),
						new DividerDrawerItem().withEnabled(true), // 이건 구분선
						new PrimaryDrawerItem().withName("스캔대행 신청"),
						new PrimaryDrawerItem().withName("연락처 지인찾기"),
						new PrimaryDrawerItem().withName("명함 교환방"),
						new DividerDrawerItem().withEnabled(true), // 이건 구분선
						new PrimaryDrawerItem().withName("대기 중인 명함"),
						new PrimaryDrawerItem().withName("보류된 명함"),
						new DividerDrawerItem().withEnabled(true), // 이건 구분선
						new PrimaryDrawerItem().withName("파일로 보내기"),
						new PrimaryDrawerItem().withName("구글 주소록에 자동 저장"),
						new DividerDrawerItem().withEnabled(true), // 이건 구분선
						new PrimaryDrawerItem().withName("지인에게 추천하기"),
						new PrimaryDrawerItem().withName("공지사항"),
						new PrimaryDrawerItem().withName("FAQ"),
						new PrimaryDrawerItem().withName("문의하기"),
						new PrimaryDrawerItem().withName("설정")



				)
				.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
					@Override
					public boolean onItemClick(android.view.View view, int i, IDrawerItem iDrawerItem) {
						// do something with the clicked item :D
						return false;
					}

				})
				.build();


	}

}

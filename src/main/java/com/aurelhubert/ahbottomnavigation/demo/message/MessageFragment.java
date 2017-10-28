package com.aurelhubert.ahbottomnavigation.demo.message;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.demo.R;
import com.aurelhubert.ahbottomnavigation.demo.lounge.LoungeRecyclerAdapter;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.ArrayList;

/**
 * 세 번째 탭인 메시지 탭에 대한 프래그먼트
 */
public class MessageFragment extends Fragment {

    /**
     * Create a new instance of the fragment
     */
    public static MessageFragment newInstance() {

        // 여기서 index 가 0,1,2,3,4 까지에 따라서 Fragment 설정해주면 되겠다.
        MessageFragment fragment = new MessageFragment(); // Fragment 를 새로 생성해서 반환해주는구나
        Bundle b = new Bundle();
        b.putInt("index", 2); // 2 번째 index of 탭에 들어갈 프래그먼트
        fragment.setArguments(b); // Fragment 에 index 설정하고 반환해준다
        return fragment;

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 나머지 1,2,3,4 페이지
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        initView(view);
        return view;

    }


    public void initView(View view) {
        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        arrayOfUsers.add(new User("KCJ","111111111"));
        arrayOfUsers.add(new User("HWJ","222222222"));

// Create the adapter to convert the array to views
        UsersAdapter adapter = new UsersAdapter(this.getContext(), arrayOfUsers);
// Attach the adapter to a ListView
        SlideAndDragListView slideAndDragListView = (SlideAndDragListView) view.findViewById(R.id.slide_and_drag_list_view);
        slideAndDragListView.setMenu(createMenu());
        slideAndDragListView.setAdapter(adapter);
        addMenuItemClickListener(slideAndDragListView);
    }


    /**
     * 리스트 아이템을 좌우로 슬라이드 했을 때 메뉴 나타나기 기능
     * 반환되는 객체를 slide list 에 추가할 것이다
     * @return
     */
    public Menu createMenu() {

        Menu menu = new Menu(true, 0);//the first parameter is whether can slide over

        // 오른쪽으로 슬라이드 했을 때 즐겨찾기 아이콘 추가
        menu.addItem(new MenuItem.Builder().setWidth(120)//set Width
                .setBackground(new ColorDrawable(Color.WHITE))// set background
                .setTextColor(Color.GRAY)//set text color
                .setTextSize(20)//set text size
                .setIcon(getResources().getDrawable(R.drawable.favourite_50))// set icon
                .build());

        // 왼쪽으로 슬라이드 했을 때 삭제하기 아이콘 추가
        menu.addItem(new MenuItem.Builder().setWidth(120)
                .setBackground(new ColorDrawable(Color.YELLOW))
                .setDirection(MenuItem.DIRECTION_RIGHT)//set direction (default DIRECTION_LEFT)
                .setIcon(getResources().getDrawable(R.drawable.ic_delete_50))// set icon
                .build());
        return menu;

    }


    /**
     * 리스트 아이템을 좌우로 슬라이드 했을 때 나타나는 메뉴를 클릭했을 때의 리스너를 구현
     */
    public void addMenuItemClickListener(SlideAndDragListView slideAndDragListView) {
        slideAndDragListView.setOnMenuItemClickListener(new SlideAndDragListView.OnMenuItemClickListener() {
            @Override
            public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
                switch (direction) {
                    case MenuItem.DIRECTION_LEFT: // 슬라이드 해서 왼쪽 메뉴 아이콘 눌렀을 때
                        switch (buttonPosition) {
                            case 0://One
                                Toast.makeText(getActivity(), "즐겨찾기 합니다.", Toast.LENGTH_SHORT).show();
                                return Menu.ITEM_SCROLL_BACK;
                        }
                        break;
                    case MenuItem.DIRECTION_RIGHT:
                        switch (buttonPosition) { // 슬라이드 해서 오른쪽 메뉴 아이콘 눌렀을 때
                            case 0://icon
                                Toast.makeText(getActivity(), "삭제합니다.", Toast.LENGTH_SHORT).show();
                                return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;
                        }
                        break;
                    default :
                        return Menu.ITEM_NOTHING;
                }
                return Menu.ITEM_NOTHING;
            }
        });
    }



    //========== Adapter ============== //
    private class UsersAdapter extends ArrayAdapter<User> {


        public UsersAdapter(Context context, ArrayList<User> users) {
            super(context, 0, users);
        }


        /**
         * 리스트에서 보일 아이템 뷰를 생성하고 해당 뷰에 데이터를 집어넣는다
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            User user = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_message, parent, false);
            }
            // Lookup view for data population
            TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
            TextView roleTextVeiw = (TextView) convertView.findViewById(R.id.role);
            // Populate the data into the template view using the data object
            nameTextView.setText(user.name);
            roleTextVeiw.setText(user.role);
            // Return the completed view to render on screen
            return convertView;
        }
    }


    /**
     * 리스트 아이템의 데이터를 넣을 용도로 사용되는 User 객체
     * user 정보를 담고 있다
     */
    private class User {
        public String name;
        public String role;

        public User(String name, String role) {
            this.name = name;
            this.role = role;
        }


    }
}

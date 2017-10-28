package com.aurelhubert.ahbottomnavigation.demo.message;

import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.aurelhubert.ahbottomnavigation.demo.R;

import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.ArrayList;

/**
 * 세 번째 탭인 메시지 탭에 대한 프래그먼트
 * SlideAndDragListView 라는 라이브러리를 사용하여
 * 리스트 아이템의 좌우 슬라이드시 메뉴 아이콘 기능을 구현하였다.
 * see : https://github.com/yydcdut/SlideAndDragListView
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

        // List 를 관리할 Adapter 를 생성한다
        UsersAdapter adapter = new UsersAdapter(this.getContext(), getSampleUsersData());
        // Attach the adapter to a ListView
        SlideAndDragListView slideAndDragListView = (SlideAndDragListView) view.findViewById(R.id.slide_and_drag_list_view);
        slideAndDragListView.setMenu(createMenu());
        slideAndDragListView.setAdapter(adapter); // 생성한 ArrayAdapter<User> 를 이용해서 리스트뷰에 표시하기 위해 어뎁터를 지정한다
        addMenuItemClickListener(slideAndDragListView); // 리스트 아이템에 슬라이드 메뉴를 추가한다

        // 리스트 아이템이 클릭되었을 때 호출되는 리스너 -> 새로운 채팅 Acitvity 를 열어주자
        slideAndDragListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("name",String.valueOf( ((TextView)view.findViewById(R.id.name)).getText()));
                startActivity(intent);
            }
        });
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


    /**
     * 현재 official 한 데이터가 없는 관계로
     * 채팅하고 있는 상대방 사용자 샘플 데이터 리스트를 구성하여 반환한다.
     * @return 샘플 User데이터 (이름과 직책 정보를 담는다.)
     */
    public ArrayList<User> getSampleUsersData() {

        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        arrayOfUsers.add(new User("한웅제", "학생 (경북대학교 컴퓨터학부)"));
        arrayOfUsers.add(new User("김말숙", "대리 (한국감정원 정보전산실/ 정보개발)"));
        arrayOfUsers.add(new User("김정태", "대리 (신용보증기금 기획실)"));
        arrayOfUsers.add(new User("신상보", "대표 (동복 해녀 회국수)"));
        arrayOfUsers.add(new User("정도현", "사회복지사 (대구장애인종합복지관)"));
        arrayOfUsers.add(new User("James", "Associate Director (Charles W.Davidson Corp.)"));
        arrayOfUsers.add(new User("Amy", "Senior Data Engineer (NETFLIX data Engineering Center)"));


        return arrayOfUsers;

    }


    //============================== Adapter ============================== //

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
     * 초 간단한 객체이므로 내부에 위치하였다. 추후 객체가 복잡하고 커지면 외부 클래스로 두는 것으로 하자
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

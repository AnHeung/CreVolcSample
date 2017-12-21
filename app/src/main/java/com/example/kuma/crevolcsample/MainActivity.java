package com.example.kuma.crevolcsample;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kuma.crevolcsample.view.adapater.MainPagerAdapter;
import com.example.kuma.crevolcsample.view.contract.MainContract;
import com.example.kuma.crevolcsample.view.fragment.MainBTCFragment;
import com.example.kuma.crevolcsample.view.fragment.MainETCFragment;
import com.example.kuma.crevolcsample.view.fragment.MainFavoriteFragment;
import com.example.kuma.crevolcsample.view.fragment.MainKRWFragment;
import com.example.kuma.crevolcsample.view.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.main_drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.navigation_view_layout)
    LinearLayout navigationLayout;


    @BindView(R.id.main_notify_btn)
    ImageButton notiBtn;
    @BindView(R.id.main_chat_btn)
    ImageButton chatBtn;
    @BindView(R.id.main_search_btn)
    ImageButton searchBtn;
    @BindView(R.id.main_vp)
    ViewPager mainViewPager;
    @BindView(R.id.main_trade_market_btn)
    ImageButton tradeMarketBtn;
    @BindView(R.id.main_investment_list_btn)
    ImageButton investmentListBtn;
    @BindView(R.id.main_investment_status_btn)
    ImageButton investmentStatusBtn;
    @BindView(R.id.main_wallet_btn)
    ImageButton walletBtn;
    @BindView(R.id.main_myinfo_btn)
    ImageButton myInfoBtn;

    @BindView(R.id.navigation_view_notify_setting)
    View navigationViewNotify;
    @BindView(R.id.navigation_view_coin_status)
    View navigationViewCoin;
    @BindView(R.id.navigation_view_terms)
    View navigationViewTerms;
    @BindView(R.id.navigation_view_customer_center)
    View navigationViewCustomer;
    @BindView(R.id.navigation_view_setting)
    View navigationViewSetting;

    MainPagerAdapter pagerAdapter;
    List<Fragment> fragmentsList;
    MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //시작하면서 뷰 바인딩 작업
        initUi();
    }


    ////뷰 바인딩 시작
    @Override
    public void initUi() {

        //툴바 설정 (액션바를 사용하지 않기 때문에 해당작업을 해줘야함)
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //DrawableLayout 세팅 작업
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.mainHeadColor));


        //탭레이아웃 글자색과 넣을 문구 설정
        tabLayout.addTab(tabLayout.newTab().setText("즐겨찾기"));
        tabLayout.addTab(tabLayout.newTab().setText("KRW"));
        tabLayout.addTab(tabLayout.newTab().setText("BTC"));
        tabLayout.addTab(tabLayout.newTab().setText("ETC"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //PagerAdapter 클래스에 바인딩할 프래그먼트생성
        fragmentsList = new ArrayList<>();
        fragmentsList.add(new MainFavoriteFragment());
        fragmentsList.add(new MainKRWFragment());
        fragmentsList.add(new MainBTCFragment());
        fragmentsList.add(new MainETCFragment());

        //PagerAdapter 생성자 부분에 Fragment리스트를 담고 ViewPager에 붙일 어댑터 설정
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragmentsList);
        mainViewPager.setAdapter(pagerAdapter);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

                for(int i = 0 ; i < navigationLayout.getChildCount();  i++){
                    navigationLayout.getChildAt(i).setBackgroundResource(R.color.naviBottomColor);
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        /**
         * Add a listener that will be invoked whenever the page changes or is incrementally
         * scrolled. See {@link ViewPager.OnPageChangeListener}.
         *
         * <p>Components that add a listener should take care to remove it when finished.
         * Other components that take ownership of a view may call {@link #clearOnPageChangeListeners()}
         * to remove all attached listeners.</p>
         *
         * @param listener listener to add
         */

        mainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //탭레이아웃 이벤트 정의
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

//    private void initUI() {
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
//        viewPager.setAdapter(new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return 5;
//            }
//
//            @Override
//            public boolean isViewFromObject(final View view, final Object object) {
//                return view.equals(object);
//            }
//
//            @Override
//            public void destroyItem(final View container, final int position, final Object object) {
//                ((ViewPager) container).removeView((View) object);
//            }
//
//            @Override
//            public Object instantiateItem(final ViewGroup container, final int position) {
//                final View view = LayoutInflater.from(
//                        getBaseContext()).inflate(R.layout.item_vp, null, false);
//
//                final TextView txtPage = (TextView) view.findViewById(R.id.txt_vp_item_page);
//                txtPage.setText(String.format("Page #%d", position));
//
//                container.addView(view);
//                return view;
//            }
//        });
//
//        final String[] colors = getResources().getStringArray(R.array.default_preview);
//
//        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
//        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_first),
//                        Color.parseColor(colors[0]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_sixth))
//                        .title("Heart")
//                        .badgeTitle("NTB")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_second),
//                        Color.parseColor(colors[1]))
////                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
//                        .title("Cup")
//                        .badgeTitle("with")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_third),
//                        Color.parseColor(colors[2]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_seventh))
//                        .title("Diploma")
//                        .badgeTitle("state")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_fourth),
//                        Color.parseColor(colors[3]))
////                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
//                        .title("Flag")
//                        .badgeTitle("icon")
//                        .build()
//        );
//        models.add(
//                new NavigationTabBar.Model.Builder(
//                        getResources().getDrawable(R.drawable.ic_fifth),
//                        Color.parseColor(colors[4]))
//                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
//                        .title("Medal")
//                        .badgeTitle("777")
//                        .build()
//        );
//
//        navigationTabBar.setModels(models);
//        navigationTabBar.setViewPager(viewPager, 2);
//        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(final int position) {
//                navigationTabBar.getModels().get(position).hideBadge();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(final int state) {
//                Toast.makeText(getApplicationContext(), "뷰페이저 스크롤", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        navigationTabBar.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
//                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
//                    navigationTabBar.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            model.showBadge();
//                        }
//                    }, i * 100);
//                }
//            }
//        }, 500);
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mainPresenter.detachView();
    }

    //////////하단 네비게이션 온클릭
    @OnClick(R.id.main_trade_market_btn)
    public void onClickTradeMarket() {
        Toast.makeText(this, "거래소", Toast.LENGTH_SHORT).show();
        changeView(0);
    }

    @OnClick(R.id.main_investment_list_btn)
    public void onClickInvestmentList() {
        Toast.makeText(this, "거래내역", Toast.LENGTH_SHORT).show();
        changeView(1);
    }

    @OnClick(R.id.main_investment_status_btn)
    public void onClickInvestmentStatus() {
        Toast.makeText(this, "투자현황", Toast.LENGTH_SHORT).show();
        changeView(2);
    }

    @OnClick(R.id.main_wallet_btn)
    public void onClickMyWallet() {
        Toast.makeText(this, "지갑", Toast.LENGTH_SHORT).show();
        changeView(3);
    }

    @OnClick(R.id.main_myinfo_btn)
    public void onClickMyInfo() {
        Toast.makeText(this, "내정보", Toast.LENGTH_SHORT).show();
        changeView(4);
    }


    /////////네비게이션뷰 온클릭
    @OnClick(R.id.navigation_view_notify_setting)
    public void onClickNotifySetting(){
        setNavigationViewHighlight(0);
    }

    @OnClick(R.id.navigation_view_coin_status)
    public void onClickCoinStatus(){
        setNavigationViewHighlight(1);
    }

    @OnClick(R.id.navigation_view_terms)
    public void onClickTerms(){
        setNavigationViewHighlight(2);
    }

    @OnClick(R.id.navigation_view_customer_center)
    public void onClickCustomer(){
        setNavigationViewHighlight(3);
    }

    @OnClick(R.id.navigation_view_setting)
    public void onClickSetting(){
        setNavigationViewHighlight(4);
    }

    ////////////상단 아이콘 온클릭
    @OnClick(R.id.main_notify_btn)
    public void onClickNotifyBtn() {
        Toast.makeText(this, "알림", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.main_chat_btn)
    public void onClickChatBtn() {
        Toast.makeText(this, "채팅", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.main_search_btn)
    public void onClickSearchBtn() {
        Toast.makeText(this, "검색", Toast.LENGTH_SHORT).show();
    }





    //Bottom Navi onClick 이벤트
    @Override
    public void changeView(int resource) {

        switch (resource) {

            case 0:
                tradeMarketBtn.setBackgroundResource(R.drawable.bottom_navi_trade_market_selected);
                investmentListBtn.setBackgroundResource(R.drawable.bottom_navi_investment_list_unselected);
                investmentStatusBtn.setBackgroundResource(R.drawable.bottom_navi_chart_unselected);
                walletBtn.setBackgroundResource(R.drawable.bottom_navi_wallet_unselected);
                myInfoBtn.setBackgroundResource(R.drawable.bottom_navi_myinfo_unselected);

                break;

            case 1:
                tradeMarketBtn.setBackgroundResource(R.drawable.bottom_navi_trade_market_unselected);
                investmentListBtn.setBackgroundResource(R.drawable.bottom_navi_investment_list_selected);
                investmentStatusBtn.setBackgroundResource(R.drawable.bottom_navi_chart_unselected);
                walletBtn.setBackgroundResource(R.drawable.bottom_navi_wallet_unselected);
                myInfoBtn.setBackgroundResource(R.drawable.bottom_navi_myinfo_unselected);

                break;

            case 2:
                tradeMarketBtn.setBackgroundResource(R.drawable.bottom_navi_trade_market_unselected);
                investmentListBtn.setBackgroundResource(R.drawable.bottom_navi_investment_list_unselected);
                investmentStatusBtn.setBackgroundResource(R.drawable.bottom_navi_chart_selected);
                walletBtn.setBackgroundResource(R.drawable.bottom_navi_wallet_unselected);
                myInfoBtn.setBackgroundResource(R.drawable.bottom_navi_myinfo_unselected);

                break;
            case 3:
                tradeMarketBtn.setBackgroundResource(R.drawable.bottom_navi_trade_market_unselected);
                investmentListBtn.setBackgroundResource(R.drawable.bottom_navi_investment_list_unselected);
                investmentStatusBtn.setBackgroundResource(R.drawable.bottom_navi_chart_unselected);
                walletBtn.setBackgroundResource(R.drawable.bottom_navi_wallet_selected);
                myInfoBtn.setBackgroundResource(R.drawable.bottom_navi_myinfo_unselected);

                break;
            case 4:
                tradeMarketBtn.setBackgroundResource(R.drawable.bottom_navi_trade_market_unselected);
                investmentListBtn.setBackgroundResource(R.drawable.bottom_navi_investment_list_unselected);
                investmentStatusBtn.setBackgroundResource(R.drawable.bottom_navi_chart_unselected);
                walletBtn.setBackgroundResource(R.drawable.bottom_navi_wallet_unselected);
                myInfoBtn.setBackgroundResource(R.drawable.bottom_navi_myinfo_selected);

                break;
        }
    }


    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


    //네비게이션 뷰에서 하이라이트 부분 처리
    private void setNavigationViewHighlight(int childNum){

        for(int i = 0 ; i < navigationLayout.getChildCount(); i++){
            if(childNum == i) {
                navigationLayout.getChildAt(i).setBackgroundResource(R.color.naviViewHighlight);
            }else {
                navigationLayout.getChildAt(i).setBackgroundResource(R.color.naviBottomColor);
            }
        }
    }
}

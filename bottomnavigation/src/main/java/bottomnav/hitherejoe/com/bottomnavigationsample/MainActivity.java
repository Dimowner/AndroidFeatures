package bottomnav.hitherejoe.com.bottomnavigationsample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener  {

	private static final int ITEM_FAVORITES = 0;
	private static final int ITEM_SCHEDULES = 1;
	private static final int ITEM_MUSIC = 2;

	private BottomNavigationView bottomNavigationView;
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		List<Fragment> fragments = new ArrayList<>();
		fragments.add(MyFragment.newInstance("Title 1"));
		fragments.add(MyFragment.newInstance("Title 2"));
		fragments.add(MyFragment.newInstance("Title 3"));
		MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), fragments);

		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);
		pager.addOnPageChangeListener(this);

		bottomNavigationView = (BottomNavigationView)
				findViewById(R.id.bottom_navigation);

		bottomNavigationView.setOnNavigationItemSelectedListener(
				new BottomNavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(@NonNull MenuItem item) {
						item.setChecked(true);
						switch (item.getItemId()) {
							case R.id.action_favorites:
								pager.setCurrentItem(ITEM_FAVORITES);
								break;
							case R.id.action_schedules:
								pager.setCurrentItem(ITEM_SCHEDULES);
								break;
							case R.id.action_music:
								pager.setCurrentItem(ITEM_MUSIC);
								break;
						}
						return false;
					}
				});

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		View view;
		switch (position) {
			case ITEM_FAVORITES:
				view = bottomNavigationView.findViewById(R.id.action_favorites);
				view.performClick();
				break;
			case ITEM_SCHEDULES:
				view = bottomNavigationView.findViewById(R.id.action_schedules);
				view.performClick();
				break;
			case ITEM_MUSIC:
				view = bottomNavigationView.findViewById(R.id.action_music);
				view.performClick();
				break;
			default:
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}


	private static class MyAdapter extends FragmentPagerAdapter {

		MyAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		private List<Fragment> fragments;
	}


	public static class MyFragment extends Fragment {

		public static final String ARG_KEY_TITLE = "key_title";

		public static MyFragment newInstance(String title) {
			Bundle args = new Bundle();
			args.putString(ARG_KEY_TITLE, title);
			MyFragment fragment = new MyFragment();
			fragment.setArguments(args);
			return fragment;
		}

		@Nullable
		@Override
		public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			return inflater.inflate(R.layout.my_fragment, container, false);
		}

		@Override
		public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);
			TextView tvText = (TextView) view.findViewById(R.id.tv_text);
			String title = "Empty";
			if (getArguments().containsKey(ARG_KEY_TITLE)) {
				title = getArguments().getString(ARG_KEY_TITLE);
			}
			tvText.setText(title);
		}
	}
}

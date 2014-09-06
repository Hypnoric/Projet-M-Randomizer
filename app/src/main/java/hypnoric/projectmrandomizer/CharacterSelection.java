package hypnoric.projectmrandomizer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CharacterSelection extends Activity {

    GridView charGrid;
    Random rand = new Random();
    Integer[] ImagesIds = {
            R.drawable.wario_full, R.drawable.mario_full,
            R.drawable.luigi_full, R.drawable.peach_full,
            R.drawable.bowser_full, R.drawable.yoshi_full,
            R.drawable.donkey_full, R.drawable.diddy_full,
            R.drawable.falcon_full, R.drawable.wolf_full,
            R.drawable.fox_full, R.drawable.falco_full,
            R.drawable.iceclimber_full, R.drawable.zelda_full,
            R.drawable.sheik_full, R.drawable.link_full,
            R.drawable.toonlink_full, R.drawable.ganondorf_full,
            R.drawable.mewtwo_full, R.drawable.lucario_full,
            R.drawable.pikachu_full, R.drawable.jigglypuff_full,
            R.drawable.squirtle_full, R.drawable.ivysaur_full,
            R.drawable.charizard_full, R.drawable.samus_full,
            R.drawable.zerosuit_full, R.drawable.lucas_full,
            R.drawable.ness_full, R.drawable.pit_full,
            R.drawable.kirby_full, R.drawable.metaknight_full,
            R.drawable.dedede_full, R.drawable.ike_full,
            R.drawable.marth_full, R.drawable.roy_full,
            R.drawable.olimar_full, R.drawable.rob_full,
            R.drawable.gdub_full, R.drawable.snake_full,
            R.drawable.sonic_full
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        charGrid = (GridView) findViewById(R.id.charGridView);
        charGrid.setAdapter(new ImageAdapter(this));

        final ImageButton selectAllButton = (ImageButton) findViewById(R.id.selectAllBtn);
        selectAllButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < charGrid.getAdapter().getCount(); i++) {
                    charGrid.setItemChecked(i, true);
                }
            }
        });

        final ImageButton resetButton = (ImageButton) findViewById(R.id.SelectNoneBtn);
        resetButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < charGrid.getAdapter().getCount(); i++) {
                    charGrid.setItemChecked(i, false);
                }
            }
        });

        final ImageButton randomButton = (ImageButton) findViewById(R.id.randomizeBtn);
        randomButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checked = charGrid.getCheckedItemPositions();
                List<Integer> checkedImagesIds = new ArrayList<Integer>();
                //Toast.makeText(CharacterSelection.this, "" + checkedImagesIds.size(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < charGrid.getAdapter().getCount(); ++i) {
                    if (checked.get(i)) {
                        checkedImagesIds.add(i);
                    }
                }
                if(checkedImagesIds.size() > 0) {
                    ImageView image;
                    image = new ImageView(v.getContext());

                    int randomChar = rand.nextInt(checkedImagesIds.size());
                    image.setImageDrawable(v.getResources().getDrawable(ImagesIds[checkedImagesIds.get(randomChar)]));
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(image);
                    toast.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.character_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            CheckableLayout layout;
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                layout = new CharacterSelection.CheckableLayout(CharacterSelection.this);
                layout.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT,
                        GridView.LayoutParams.WRAP_CONTENT));
                layout.addView(imageView);
            } else {
                layout = (CheckableLayout) convertView;
                imageView = (ImageView) layout.getChildAt(0);
            }

            imageView.setImageResource(mImagesIds[position]);
            return layout;
        }

        public int getCount() {
            return mImagesIds.length;
        }

        public Object getItem(int position) {
            return charGrid.getChildAt(position);
        }

        public long getItemId(int position) {
            return position;
        }

        // references to our images
        private Integer[] mImagesIds = {
                R.drawable.wario_, R.drawable.mario_,
                R.drawable.luigi_, R.drawable.peach_,
                R.drawable.bowser_, R.drawable.yoshi_,
                R.drawable.donkey_, R.drawable.diddy_,
                R.drawable.falcon_, R.drawable.wolf_,
                R.drawable.fox_, R.drawable.falco_,
                R.drawable.iceclimber_, R.drawable.zelda_,
                R.drawable.sheik_, R.drawable.link_,
                R.drawable.toonlink_, R.drawable.ganon_,
                R.drawable.mewtwo_, R.drawable.lucario_,
                R.drawable.pikachu_, R.drawable.jigglypuff_,
                R.drawable.squirtle_, R.drawable.ivysaur_,
                R.drawable.charizard_, R.drawable.samus_,
                R.drawable.zerosuit_, R.drawable.lucas_,
                R.drawable.ness_, R.drawable.pit_,
                R.drawable.kirby_, R.drawable.metaknight_,
                R.drawable.dedede_, R.drawable.ike_,
                R.drawable.marth_, R.drawable.roy_,
                R.drawable.olimar_, R.drawable.rob_,
                R.drawable.gdub_, R.drawable.snake_,
                R.drawable.sonic_
        };
    }

    public class CheckableLayout extends FrameLayout implements Checkable {
        private boolean isChecked;

        public CheckableLayout(Context context) {
            super(context);
        }

        @Override
        public void setChecked(boolean checked) {
            isChecked = checked;
            if(checked)
                setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
            else
                setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }

        @Override
        public boolean isChecked() {
            return isChecked;
        }

        @Override
        public void toggle() {
            setChecked(!isChecked);
        }

    }
}

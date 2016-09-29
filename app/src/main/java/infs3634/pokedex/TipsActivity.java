package infs3634.pokedex;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class TipsActivity extends MenuActivity {

    private static String tips[] = {"Dratini and Dragonair are in the begging of the Safari Zone in the water, you will need a SuperRod to catch them.",
            "Concentrate between filling out your Pokédex and training your Pokémon, don’t put emphasis on one over the other.",
            "The best way to catch Legendaries is to lower the Legendary Pokemon to barely any, but try not to make it faint, make it go to sleep and then use Ultra balls repeatedly.",
            "To get past the guards at cycling road without a bike go to the entrance to cycling road and then when you get to the guard, hold down across and keep pressing B until you get past the guard.",
            "To get a Pikablu take your Lvl 99 Raichu to the day care center and leave it there for 10 hrs. When you return to the day care center the Day Care man will say \"Your Raichu ran away, here have this Pikablu.\"",
            "Don’t waste your Masterball on any other Pokémon but Mewtwo",
            "If you are a beginner, choose Bulbasaur as your starter",
            "An intermediate player should choose Squirtle",
            "An advanced player should choose Charmander",
            "You don't need to trade your Golbat for a Crobat! All you need to do is get your Golbat to love you as much as you can and level it up!",
            "Instead of wasting your time to find a Male and a Female Pokémon to breed, just put the Pokémon along with Ditto at the Day Care Centre.",
            "To create more time in the Safari Zone, when standing on any grassy area, simply tap left or right. This should cause you to turn in that direction, without actually moving in that direction. However even though you stood in place, you will still fight random Pokémon! What’s better, your time will not have decreased since you didn’t actually take a step!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        TextView mTip1 = (TextView) this.findViewById(R.id.tip_1);
        TextView mTip2 = (TextView) this.findViewById(R.id.tip_2);
        TextView mTip3 = (TextView) this.findViewById(R.id.tip_3);

        Collections.shuffle(Arrays.asList(tips));

        mTip1.setText(tips[0]);
        mTip2.setText(tips[1]);
        mTip3.setText(tips[2]);

    }

}

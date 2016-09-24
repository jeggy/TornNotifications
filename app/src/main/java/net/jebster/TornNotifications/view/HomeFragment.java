package net.jebster.TornNotifications.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.jebster.TornNotifications.R;
import net.jebster.TornNotifications.model.TornGlobals;
import net.jebster.TornNotifications.model.FlightInfo;
import net.jebster.TornNotifications.model.TornUser;

/**
 * Created by jeggy on 9/24/16.
 */

public class HomeFragment extends Fragment implements TornInfoUpdateInterface{

    private final String TAG = "HomeFragment";

    private ProgressBar energyBar;
    private ProgressBar happyBar;
    private ProgressBar nerveBar;
    private ProgressBar lifeBar;
    private ProgressBar travelBar;
    private TextView energyBarText;
    private TextView happyBarText;
    private TextView nerveBarText;
    private TextView lifeBarText;
    private TextView travelBarText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        if (view != null) {
            energyBar = (ProgressBar) view.findViewById(R.id.energy_bar);
            happyBar = (ProgressBar) view.findViewById(R.id.happy_bar);
            nerveBar = (ProgressBar) view.findViewById(R.id.nerve_bar);
            lifeBar = (ProgressBar) view.findViewById(R.id.life_bar);
            travelBar = (ProgressBar) view.findViewById(R.id.travel_bar);

            energyBarText = (TextView) view.findViewById(R.id.energy_bar_text);
            happyBarText = (TextView) view.findViewById(R.id.happy_bar_text);
            nerveBarText = (TextView) view.findViewById(R.id.nerve_bar_text);
            lifeBarText = (TextView) view.findViewById(R.id.life_bar_text);
            travelBarText = (TextView) view.findViewById(R.id.travel_bar_text);
        }

        return view;
    }

    @Override
    public void tornUser(TornUser user) {
        if(energyBar != null && energyBarText != null && happyBar != null && happyBarText != null &&
                nerveBar != null && nerveBarText != null && lifeBar != null && lifeBarText != null) {

            energyBar.setMax(user.getMaximumEnergy());
            energyBar.setProgress(user.getEnergy());
            // energyBar.setSecondaryProgress(user.); // TODO: getEnergy() + energy.increment
            energyBarText.setText(user.getEnergy() + "/" + user.getMaximumEnergy());

            happyBar.setMax(user.getMaximumHappy());
            happyBar.setProgress(user.getHappy());
            happyBarText.setText(user.getHappy() + "/" + user.getMaximumHappy());

            nerveBar.setMax(user.getMaximumNerve());
            nerveBar.setProgress(user.getNerve());
            nerveBarText.setText(user.getNerve() + "/" + user.getMaximumNerve());

            lifeBar.setMax(user.getMaximumLife());
            lifeBar.setProgress(user.getLife());
            lifeBarText.setText(user.getLife()+"/"+user.getMaximumLife());


            if(user.getTravelTimeLeft() > 0) {
                travelBar.setVisibility(View.VISIBLE);
                travelBarText.setVisibility(View.VISIBLE);
                travelBar.setMax(user.getTravelTime());
                travelBar.setProgress(user.getTravelTimeLeft());
                travelBarText.setText(user.getTravelDestination() + ": " +user.getTravelTimeLeft() + "/" + user.getTravelTime() +"s");
            }else{
                travelBar.setVisibility(View.INVISIBLE);
                travelBarText.setVisibility(View.INVISIBLE);
            }
        }
    }
}

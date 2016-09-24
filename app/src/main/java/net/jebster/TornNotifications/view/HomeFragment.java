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

import java.util.Objects;

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
                nerveBar != null && nerveBarText != null && lifeBar != null && lifeBarText != null &&
                user.getErrorText() == null) {

            set(energyBar, user.getEnergy().getMaximum(), user.getEnergy().getCurrent());
            // energyBar.setSecondaryProgress(user.); // TODO: getEnergy() + energy.increment
            energyBarText.setText(user.getEnergy().getCurrent() + "/" + user.getEnergy().getMaximum());

            set(happyBar, user.getHappy().getMaximum(), user.getHappy().getCurrent());
            happyBarText.setText(user.getHappy().getCurrent() + "/" + user.getHappy().getMaximum());

            set(nerveBar, user.getNerve().getMaximum(), user.getNerve().getCurrent());
            nerveBarText.setText(user.getNerve().getCurrent() + "/" + user.getNerve().getMaximum());

            set(lifeBar, user.getLife().getMaximum(), user.getLife().getCurrent());
            lifeBarText.setText(user.getLife().getCurrent()+"/"+user.getLife().getMaximum());


            if(user.getTravel().getTime_left() > 0) {
                travelBar.setVisibility(View.VISIBLE);
                travelBarText.setVisibility(View.VISIBLE);
                set(travelBar, ((int) user.getTravel().getTravelTime()), ((int) (user.getTravel().getTravelTime() - user.getTravel().getTime_left())));
                travelBarText.setText(user.getTravel().getDestination() + ": " +user.getTravel().getTime_left() + "/" + user.getTravel().getTravelTime() +"s");
            }else{
                travelBar.setVisibility(View.INVISIBLE);
                travelBarText.setVisibility(View.INVISIBLE);
            }
        }else{
            set(energyBar, 0, 0);
            set(happyBar, 0, 0);
            set(nerveBar, 0, 0);
            set(lifeBar, 0, 0);
            set(travelBar, 0, 0);

            energyBarText.setText("0/0");
            happyBarText.setText("0/0");
            nerveBarText.setText("0/0");
            lifeBarText.setText("0/0");
            travelBarText.setText("0/0s");
        }
    }

    private void set(ProgressBar bar, int max, int progress){
        bar.setMax(max);
        bar.setProgress(progress);
    }
}

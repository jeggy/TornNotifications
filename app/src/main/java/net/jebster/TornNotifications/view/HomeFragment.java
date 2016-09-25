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
import java.util.concurrent.ThreadFactory;

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
    private ProgressBar energyBarTime;
    private ProgressBar happyBarTime;
    private ProgressBar nerveBarTime;
    private ProgressBar lifeBarTime;
    private ProgressBar travelBarTime;
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

            energyBarTime = (ProgressBar) view.findViewById(R.id.energy_bar_time);
            happyBarTime = (ProgressBar) view.findViewById(R.id.happy_bar_time);
            nerveBarTime = (ProgressBar) view.findViewById(R.id.nerve_bar_time);
            lifeBarTime = (ProgressBar) view.findViewById(R.id.life_bar_time);
            travelBarTime = (ProgressBar) view.findViewById(R.id.travel_bar_time);

            energyBarText = (TextView) view.findViewById(R.id.energy_bar_text);
            happyBarText = (TextView) view.findViewById(R.id.happy_bar_text);
            nerveBarText = (TextView) view.findViewById(R.id.nerve_bar_text);
            lifeBarText = (TextView) view.findViewById(R.id.life_bar_text);
            travelBarText = (TextView) view.findViewById(R.id.travel_bar_text);
        }

        return view;
    }

    private void show(ProgressBar pb, ProgressBar pbt, TextView tv, TornUser.Bar bar){
        set(pb, bar.getMaximum(), bar.getCurrent(), bar.getCurrent()+bar.getIncrement());
        set(pbt, bar.getInterval(), bar.getInterval()-bar.getTicktime(), 0);
        tv.setText(bar.getCurrent() + "/" + bar.getMaximum());
    }

    @Override
    public void tornUser(TornUser user) {
        if(energyBar != null && energyBarText != null && happyBar != null && happyBarText != null &&
                nerveBar != null && nerveBarText != null && lifeBar != null && lifeBarText != null &&
                user.getErrorText() == null) {

            show(energyBar, energyBarTime, energyBarText, user.getEnergy());
            show(happyBar, happyBarTime, happyBarText, user.getHappy());
            show(nerveBar, nerveBarTime, nerveBarText, user.getNerve());
            show(lifeBar, lifeBarTime, lifeBarText, user.getLife());

            if(user.getTravel().getTime_left() > 0) {
                travelBar.setVisibility(View.VISIBLE);
                travelBarTime.setVisibility(View.VISIBLE);
                travelBarText.setVisibility(View.VISIBLE);
                set(travelBar,
                        ((int) user.getTravel().getTravelTime()),
                        ((int) (user.getTravel().getTravelTime() - user.getTravel().getTime_left())),
                        0);
                travelBarText.setText(user.getTravel().getDestination() + ": " +user.getTravel().getTime_left() + "/" + user.getTravel().getTravelTime() +"s");
            }else{
                travelBar.setVisibility(View.INVISIBLE);
                travelBarTime.setVisibility(View.INVISIBLE);
                travelBarText.setVisibility(View.INVISIBLE);
            }
        }else{
            set(energyBar, 0, 0, 0);
            set(happyBar, 0, 0, 0);
            set(nerveBar, 0, 0, 0);
            set(lifeBar, 0, 0, 0);
            set(travelBar, 0, 0, 0);

            energyBarText.setText("0/0");
            happyBarText.setText("0/0");
            nerveBarText.setText("0/0");
            lifeBarText.setText("0/0");
            travelBarText.setText("0/0s");
        }
    }

    private void set(ProgressBar bar, int max, int progress, int secondary){
        if(bar != null) {
            bar.setMax(max);
            bar.setProgress(progress);
            bar.setSecondaryProgress(secondary);
        }
    }
}

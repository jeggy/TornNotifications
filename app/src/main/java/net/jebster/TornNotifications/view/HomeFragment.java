package net.jebster.TornNotifications.view;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.jebster.TornNotifications.R;
import net.jebster.TornNotifications.model.Globals;
import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.tools.Observer;
import net.jebster.TornNotifications.tools.TimeUtils;

import java.util.ArrayList;

/**
 * Created by jeggy on 9/24/16.
 */

public class HomeFragment extends Fragment implements Observer {

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
    private TextView energyBarText;
    private TextView happyBarText;
    private TextView nerveBarText;
    private TextView lifeBarText;
    private TextView travelBarText;
    private TextView cooldownsText;

    private TornUser user;

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

            energyBarText = (TextView) view.findViewById(R.id.energy_bar_text);
            happyBarText = (TextView) view.findViewById(R.id.happy_bar_text);
            nerveBarText = (TextView) view.findViewById(R.id.nerve_bar_text);
            lifeBarText = (TextView) view.findViewById(R.id.life_bar_text);
            travelBarText = (TextView) view.findViewById(R.id.travel_bar_text);

            cooldownsText = (TextView) view.findViewById(R.id.cooldowns_text);
        }
        Globals.User().addObserver(TAG, this);
        updateView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        updateView();
    }

    public void updateView(){
        user = Globals.User();
        if(user != null && user.getErrorText() == null){
            show(energyBar, energyBarTime, energyBarText, user.getEnergy());
            show(happyBar, happyBarTime, happyBarText, user.getHappy());
            show(nerveBar, nerveBarTime, nerveBarText, user.getNerve());
            show(lifeBar, lifeBarTime, lifeBarText, user.getLife());
            showCooldowns();
            showTravel();
        } else {
            reset();
        }
    }

    private void show(ProgressBar pb, ProgressBar pbt, TextView tv, TornUser.Bar bar){
        set(pb, bar.getMaximum(), bar.getCurrent(), bar.getCurrent()+bar.getIncrement());
        set(pbt, bar.getInterval(), bar.getInterval()-bar.getTicktime(), true);
        tv.setText(bar.getCurrent() + "/" + bar.getMaximum());
    }

    private void reset() {
        set(energyBar, 0, 0, true);
        set(happyBar, 0, 0, true);
        set(nerveBar, 0, 0, true);
        set(lifeBar, 0, 0, true);
        set(travelBar, 0, 0, true);


        energyBarText.setText("0/0");
        happyBarText.setText("0/0");
        nerveBarText.setText("0/0");
        lifeBarText.setText("0/0");
        travelBarText.setText("0/0s");
        cooldownsText.setText("");
    }



    private void showTravel() {
        if(user.getTravel().getTime_left() > 0) {
            travelBar.setVisibility(View.VISIBLE);
            travelBarText.setVisibility(View.VISIBLE);
            set(travelBar,
                    ((int) user.getTravel().getTravelTime()),
                    ((int) (user.getTravel().getTravelTime() - user.getTravel().getTime_left())), false);
            travelBarText.setText("In "+user.travel.getDestination()+": "+user.getTravel().getTimeTravelLeft()); //user.getTravel().getDestination() + ": " + user.getTravel().getTime_left() + "/" + user.getTravel().getTravelTime() +"s");
        }else{
            travelBar.setVisibility(View.INVISIBLE);
            travelBarText.setVisibility(View.INVISIBLE);
        }
    }

    private void showCooldowns(){
        String text = "";

        ArrayList<String> coolDowns = new ArrayList<>();

        if(user.cooldowns.getDrug() > 0)
            coolDowns.add("drugs["+ TimeUtils.getStringTime(user.cooldowns.getDrug())+"]");
        if(user.cooldowns.getMedical() > 0)
            coolDowns.add("medical["+TimeUtils.getStringTime(user.cooldowns.getMedical())+"]");
        if(user.cooldowns.getBooster() > 0)
            coolDowns.add("boosters["+TimeUtils.getStringTime(user.cooldowns.getBooster())+"]");

        if(coolDowns.size() > 0){
            text += "Cooldowns: ";
            for(int i = 0; i < coolDowns.size(); i++){
                if(i>0){
                    text += i == coolDowns.size()-1 ? " and " : ", ";
                }
                text += coolDowns.get(i);
            }
        }

        cooldownsText.setText(text);
    }

    private void set(ProgressBar bar, int max, int progress, int secondary){
        if(bar != null) {
            bar.setVisibility(View.VISIBLE);
            bar.setMax(max);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                bar.setProgress(progress, true);
            else
                bar.setProgress(progress);

            bar.setSecondaryProgress(secondary);
        }


        // TODO: Some smarter way.
        _lastMax = max;
        _lastProgress = progress;
    }

    private int _lastMax = 0;
    private int _lastProgress = 0;
    private void set(ProgressBar bar, int max, int progress, boolean check){
        if(_lastProgress != _lastMax || !check) {
            bar.setVisibility(View.VISIBLE);
            set(bar, max, progress, 0);
        }else {
            bar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void update() {
        Log.d(TAG, "update");
        if(getActivity()!=null)
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateView();
                }
            });
    }
}

package com.ninegame.bird.framework;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninegame.bird.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements View.OnClickListener, EnvironmentCallback {
    private Bundle mArgs = new Bundle();
    private Environment env;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getEnvironment() == null) {
            setEnvironment(FrameworkFacade.getInstance().getEnvironment());
        }
        getEnvironment().setCurrentActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }


    public void setBundleArguments(Bundle args) {
        this.mArgs = args;
    }

    public Bundle getBundleArguments() {
        return mArgs;
    }

    public void startFragment(Class var1) {
        this.startFragment(var1, (Bundle) null);
    }

    public void startFragment(Class classObj, Bundle bundle) {
        if (this.env != null) {
            this.env.startFragment(classObj.getName(), bundle);
        }

    }

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;

    }

    @Override
    public Environment getEnvironment() {
        return this.env;
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 处理返回事件
     *
     * @return true表示在fragment内部已经完成了对返回事件的处理，外部不需要再处理;
     * false表示外部需要对返回事件继续处理
     */
    public boolean goBack() {
        return false;
    }
}

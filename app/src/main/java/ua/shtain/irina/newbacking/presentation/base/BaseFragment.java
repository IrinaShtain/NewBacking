package ua.shtain.irina.newbacking.presentation.base;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

@EFragment
public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    @SystemService
    protected InputMethodManager inputMethodManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
        setHasOptionsMenu(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        hideKeyboard();
    }

    protected void hideKeyboard() {
        if (getView() != null) {
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }
}

package ua.shtain.irina.newbacking.presentation.screens.main.filter.add_author_dialog;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.concurrent.TimeUnit;

import ua.shtain.irina.newbacking.R;
import ua.shtain.irina.newbacking.presentation.utils.Constants;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
@EFragment(R.layout.fragment_dialog_author_picker)
public class AddAuthorDialogFragment extends DialogFragment implements AddAuthorContract.View{
    private AddAuthorContract.Presenter presenter;
    @ViewById
    protected Button btnCancel_FDAA;
    @ViewById
    protected Button btnAdd_FDAA;
    @ViewById
    protected EditText etAuthor_FDAA;
    @ViewById
    protected TextInputLayout tilAuthor_FDAA;

    @StringRes(R.string.msg_input_author)
    protected String errMsgInputName;
    @Override
    public int getTheme() {
        return R.style.DialogTheme;
    }

    @AfterInject
    @Override
    public void initPresenter() {
        new AddAuthorPresenter(this);
    }

    @Override
    public void setPresenter(AddAuthorContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initUI() {
        RxView.clicks(btnCancel_FDAA)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe(o -> dismiss());
        RxView.clicks(btnAdd_FDAA)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe(o -> presenter.addAuthor(etAuthor_FDAA.getText().toString()));

    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }


    @Override
    public void showErrorMsg() {
        tilAuthor_FDAA.setError(errMsgInputName);
    }

    @Override
    public void closeDialog(String authorName) {
        if (authorName != null) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(Constants.KEY_AUTHOR_NAME, authorName);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
        }
        dismiss();
    }
}

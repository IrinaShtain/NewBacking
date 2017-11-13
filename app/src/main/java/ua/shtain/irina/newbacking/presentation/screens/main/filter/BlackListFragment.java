package ua.shtain.irina.newbacking.presentation.screens.main.filter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import ua.shtain.irina.newbacking.R;
import ua.shtain.irina.newbacking.domain.AuthorRepository;
import ua.shtain.irina.newbacking.presentation.base.BasePresenter;
import ua.shtain.irina.newbacking.presentation.base.content.ContentFragment;
import ua.shtain.irina.newbacking.presentation.screens.main.filter.adapter.AuthorAdapter;
import ua.shtain.irina.newbacking.presentation.screens.main.filter.adapter.AuthorDH;
import ua.shtain.irina.newbacking.presentation.screens.main.filter.add_author_dialog.AddAuthorDialogFragment;
import ua.shtain.irina.newbacking.presentation.screens.main.filter.add_author_dialog.AddAuthorDialogFragment_;
import ua.shtain.irina.newbacking.presentation.utils.Constants;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
@EFragment
public class BlackListFragment extends ContentFragment implements BlackListContract.View {

    private BlackListContract.Presenter presenter;
    private AddAuthorDialogFragment addAuthorDialogFragment;
    @ViewById
    protected RecyclerView rvListItems_FBL;
    @ViewById
    protected FloatingActionButton btnAddUser_FBL;

    @ViewById
    protected TextView tvPlaceholderMessage_FBL;
    @ViewById
    protected ImageView ivPlaceholderImage_FBL;

    @Bean
    protected AuthorAdapter adapter;
    @Bean
    protected AuthorRepository repository;

    @AfterInject
    @Override
    public void initPresenter() {
        new BlackListPresenter(this, repository);
    }

    @Override
    public void setPresenter(BlackListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_black_list;
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @AfterViews
    public void initUI() {
        mActivity.getToolbarManager().setTitle(R.string.title_black_list);
        RxView.clicks(btnAddUser_FBL)
                .throttleFirst(Constants.CLICK_DELAY, TimeUnit.MILLISECONDS)
                .subscribe(o -> presenter.showAddingDialog());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        rvListItems_FBL.setAdapter(adapter);
        rvListItems_FBL.setLayoutManager(layoutManager);
        setupSwipeToRemove();

        presenter.subscribe();
    }

    @Override
    public void setAuthor(ArrayList<AuthorDH> blackListedAuthors) {
        ivPlaceholderImage_FBL.setVisibility(View.GONE);
        tvPlaceholderMessage_FBL.setVisibility(View.GONE);
        adapter.setListDH(blackListedAuthors);
    }

    @Override
    public void openDialog() {
        addAuthorDialogFragment = AddAuthorDialogFragment_.builder()
                .build();
        addAuthorDialogFragment.setTargetFragment(this, Constants.REQUEST_CODE_ADD_AUTHOR);
        addAuthorDialogFragment.show(mActivity.getSupportFragmentManager(), "Add_Author");
    }

    @Override
    public void addAuthor(AuthorDH author) {
        ivPlaceholderImage_FBL.setVisibility(View.GONE);
        tvPlaceholderMessage_FBL.setVisibility(View.GONE);
        adapter.insertItem(author, adapter.getItemCount());

    }

    @Override
    public void removeItem(int position) {
        adapter.removeItem(position);
    }

    @Override
    public void showNoUsersPlaceholder() {
        ivPlaceholderImage_FBL.setVisibility(View.VISIBLE);
        tvPlaceholderMessage_FBL.setVisibility(View.VISIBLE);
        ivPlaceholderImage_FBL.setImageResource(R.drawable.ic_no_users);
        tvPlaceholderMessage_FBL.setText(R.string.err_msg_no_users_has_been_added);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (addAuthorDialogFragment != null) addAuthorDialogFragment.dismiss();
    }

    @OnActivityResult(Constants.REQUEST_CODE_ADD_AUTHOR)
    public void onAmountDialogResult(int resultCode,
                                     @OnActivityResult.Extra(value = Constants.KEY_AUTHOR_NAME) String authorName) {
        if (resultCode == Activity.RESULT_OK) {
            presenter.addAuthor(authorName);
        }
    }

    private void setupSwipeToRemove() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                presenter.removeUser(adapter.getItem(pos), pos);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Bitmap icon = getVectorBitmap(R.drawable.ic_delete_white);
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    Paint p = new Paint();

                    if (dX < 0) {
                        p.setColor(Color.parseColor("#f44336"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        c.clipRect(background);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvListItems_FBL);
    }

    private Bitmap getVectorBitmap(@DrawableRes int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(mActivity, drawableId);
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}

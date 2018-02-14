package com.error_found.kotdroid.cgscopy.views.adapters;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.error_found.kotdroid.cgscopy.R;
import com.error_found.kotdroid.cgscopy.models.pojos.PojoLogin;
import com.error_found.kotdroid.cgscopy.views.fragments.GuestsFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user12 on 14/2/18.
 */

public class GuestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Fragment fragment;
    private List<PojoLogin> pojoLoginList;
    private LayoutInflater mLayoutInflater;
    private int guestType;
    private Context mContext;
    private GuestActionCallBack mGuestActionCallBack;


    public GuestAdapter(Fragment fragment, int guestType) {
        this.fragment = fragment;
        mGuestActionCallBack = (GuestActionCallBack) fragment;
        pojoLoginList = new ArrayList<>();
        mContext = fragment.getActivity();
        mLayoutInflater = LayoutInflater.from(mContext);
        this.guestType = guestType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(mLayoutInflater.inflate(R.layout.guest_row_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder) holder;
        PojoLogin pojoLogin = pojoLoginList.get(position);


        itemHolder.tvGender.setText(pojoLogin.gender);
        itemHolder.tvName.setText(pojoLogin.name);
        itemHolder.sdvProfile.setImageURI(Uri.parse(pojoLogin.image));
        if (null != (pojoLogin.date)) {
            itemHolder.tvDate.setText(pojoLogin.date);
        }
    }

    @Override
    public int getItemCount() {
        return pojoLoginList.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_gender)
        TextView tvGender;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.sdv_profile)
        SimpleDraweeView sdvProfile;
        @BindView(R.id.iv_more_option)
        ImageView ivMoreOption;


        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.iv_more_option)
        public void clickOnMoreOption(View view) {
            PopupMenu popupMenu = new PopupMenu(mContext, view);
            switch (guestType) {
                case GuestsFragment.GUEST_TYPE_BLOCKED:
                    popupMenu.inflate(R.menu.menu_popup_blocked);
                    break;
                case GuestsFragment.GUEST_TYPE_PERMANENT:
                    popupMenu.inflate(R.menu.menu_popup_permanent);
                    break;
                case GuestsFragment.GUEST_TYPE_EXPECTED:
                    popupMenu.inflate(R.menu.menu_popup_expected);
                    break;
            }


            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    int adapterPosition = getAdapterPosition();
                    int guestId = pojoLoginList.get(adapterPosition).guest_id;
                    switch (menuItem.getItemId()) {
                        case R.id.menu_block:
                            mGuestActionCallBack.changeGuestType(guestId, adapterPosition,
                                    GuestsFragment.GUEST_TYPE_BLOCKED);
                            break;
                        case R.id.menu_delete:
                            mGuestActionCallBack.changeGuestType(guestId, adapterPosition,
                                    GuestsFragment.GUEST_TYPE_DELETE);
                            break;
                        case R.id.menu_move_to_permanent:
                            mGuestActionCallBack.changeGuestType(guestId, adapterPosition,
                                    GuestsFragment.GUEST_TYPE_PERMANENT);
                            break;
                        case R.id.menu_edit:

                            break;
                    }

                    return false;
                }
            });

            popupMenu.show();
        }

    }

    public void updateList(PojoLogin login) {
        pojoLoginList.add(login);
        notifyDataSetChanged();

    }

    public interface GuestActionCallBack {
        void changeGuestType(int guestId, int adapterPosition, int guestType);
    }

    public void removeGuest(int adapterPosition) {
        pojoLoginList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);

    }
}

package br.com.monthbalancecontrolandroidapp.ui.balance_timeline;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.monthbalancecontrolandroidapp.R;
import br.com.monthbalancecontrolandroidapp.databinding.FragmentBalanceTimelineBinding;
import br.com.monthbalancecontrolandroidapp.model.TimeLineModel;

public class BalanceTimelineFragment extends Fragment {

    private FragmentBalanceTimelineBinding binding;
    ArrayList<TimeLineModel> rvList = new ArrayList<>();
    TimeLineAdapter timeLineAdapter;
    RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        SlideshowViewModel slideshowViewModel =
//                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentBalanceTimelineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rv = binding.rv;


        initLayout();
        initData();
//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initData() {
        TimeLineModel timeLineDummmy1 = new TimeLineModel(1,R.drawable.ic_launcher_foreground,"BlackBerry", "Rs 25000.00","JAN","2018");
        rvList.add(timeLineDummmy1);
        TimeLineModel timeLineDummmy2 = new TimeLineModel(2,R.drawable.ic_launcher_foreground,"Samsung", "Rs 65000.00","OCt","2017");
        rvList.add(timeLineDummmy2);
        TimeLineModel timeLineDummmy3 = new TimeLineModel(3,R.drawable.ic_launcher_foreground,"One Plus", "Rs 45000.00","AUG","2018");
        rvList.add(timeLineDummmy3);
        TimeLineModel timeLineDummmy4 = new TimeLineModel(2,R.drawable.ic_launcher_foreground,"Iphone", "Rs 88500.00","NOV","2016");
        rvList.add(timeLineDummmy4);
        TimeLineModel timeLineDummmy5 = new TimeLineModel(2,R.drawable.ic_launcher_foreground,"Samsung", "Rs 65000.00","MAR","2015");
        rvList.add(timeLineDummmy5);
        TimeLineModel timeLineDummmy6 = new TimeLineModel(2,R.drawable.ic_launcher_foreground,"One Plus", "Rs 45000.00","FEB","2018");
        rvList.add(timeLineDummmy6);
        TimeLineModel timeLineDummmy7 = new TimeLineModel(3,R.drawable.ic_launcher_foreground,"Iphone", "Rs 88500.00","MAY","2006");
        rvList.add(timeLineDummmy7);
        timeLineAdapter.notifyDataSetChanged();
    }

    private void initLayout() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
        timeLineAdapter = new TimeLineAdapter(getContext(), rvList);
        rv.setAdapter(timeLineAdapter);
    }

    public static class TimeLineAdapter extends RecyclerView.Adapter {
        private static final int VIEW_TYPE_DEAL_FIRST_ITEM = 1;
        private static final int VIEW_TYPE_DEAL_MIDDLE_ITEM = 2;
        private static final int VIEW_TYPE_DEAL_LAST_ITEM = 3;

        private Context mContext;
        private ArrayList<TimeLineModel> mDealsList;

        public TimeLineAdapter(Context context, ArrayList<TimeLineModel> mDealsListData) {
            mContext = context;
            mDealsList = mDealsListData;
        }

        @Override
        public int getItemCount() {
            return mDealsList.size();
        }

        @Override
        public int getItemViewType(int position) {
            TimeLineModel deal = mDealsList.get(position);
            if (deal.getType() == 1) {
                return VIEW_TYPE_DEAL_FIRST_ITEM;
            } else if (deal.getType() == 2) {
                return VIEW_TYPE_DEAL_MIDDLE_ITEM;
            } else {
                return VIEW_TYPE_DEAL_LAST_ITEM;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if (viewType == VIEW_TYPE_DEAL_FIRST_ITEM) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_card_type_start, parent, false);
                return new FirstDealHolder(view);
            } else if (viewType == VIEW_TYPE_DEAL_MIDDLE_ITEM) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item__card_type_middle, parent, false);
                return new MiddleDealHolder(view);
            } else if (viewType == VIEW_TYPE_DEAL_LAST_ITEM) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_card_end, parent, false);
                return new LastDealHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TimeLineModel dealsModel = mDealsList.get(position);
            switch (holder.getItemViewType()) {
                case VIEW_TYPE_DEAL_FIRST_ITEM:
                    ((FirstDealHolder) holder).bind(dealsModel);
                    break;
                case VIEW_TYPE_DEAL_MIDDLE_ITEM:
                    ((MiddleDealHolder) holder).bind(dealsModel);
                    break;
                case VIEW_TYPE_DEAL_LAST_ITEM:
                    ((LastDealHolder) holder).bind(dealsModel);
                    break;
            }
        }

        public static class FirstDealHolder extends RecyclerView.ViewHolder {
            TextView monthPurchased, yearPurchased, productName, productPrice;
            ImageView dealProductImage;

            public FirstDealHolder(View itemView) {
                super(itemView);
                monthPurchased = itemView.findViewById(R.id.monthPurchased);
                yearPurchased = itemView.findViewById(R.id.yearPurchased);
                dealProductImage = itemView.findViewById(R.id.dealProductImage);
                productName = itemView.findViewById(R.id.textView32);
                productPrice = itemView.findViewById(R.id.productPrice);
            }

            public void bind(TimeLineModel dealsModel) {
                monthPurchased.setText(dealsModel.month);
                yearPurchased.setText(dealsModel.year);
                productName.setText(dealsModel.productName);
                productPrice.setText(dealsModel.productPrice);
            }
        }

        public static class MiddleDealHolder extends RecyclerView.ViewHolder {
            TextView monthPurchased, yearPurchased, productName, productPrice;
            ImageView dealProductImage;

            public MiddleDealHolder(View itemView) {
                super(itemView);
                monthPurchased = itemView.findViewById(R.id.monthPurchased);
                yearPurchased = itemView.findViewById(R.id.yearPurchased);
                dealProductImage = itemView.findViewById(R.id.dealProductImage);
                productName = itemView.findViewById(R.id.textView32);
                productPrice = itemView.findViewById(R.id.productPrice);
            }

            public void bind(TimeLineModel dealsModel) {
                monthPurchased.setText(dealsModel.month);
                yearPurchased.setText(dealsModel.year);
                productName.setText(dealsModel.productName);
                productPrice.setText(dealsModel.productPrice);
            }
        }

        public static class LastDealHolder extends RecyclerView.ViewHolder {
            TextView monthPurchased, yearPurchased, productName, productPrice;
            ImageView dealProductImage;

            public LastDealHolder(View itemView) {
                super(itemView);
                monthPurchased = itemView.findViewById(R.id.monthPurchased);
                yearPurchased = itemView.findViewById(R.id.yearPurchased);
                dealProductImage = itemView.findViewById(R.id.dealProductImage);
                productName = itemView.findViewById(R.id.textView32);
                productPrice = itemView.findViewById(R.id.productPrice);
            }

            public void bind(TimeLineModel dealsModel) {
                monthPurchased.setText(dealsModel.month);
                yearPurchased.setText(dealsModel.year);
                productName.setText(dealsModel.productName);
                productPrice.setText(dealsModel.productPrice);
            }
        }
    }
}
package com.example.naumov.illia.illianaumov.main.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.naumov.illia.illianaumov.R;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.ExchangeRate;
import com.example.naumov.illia.illianaumov.main.mvp.model.entities.UiCurrency;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by illia_naumov.
 */

public class CurrencyRatesAdapter extends RecyclerView.Adapter<CurrencyRatesAdapter.CurrencyViewHolder>{

    private List<UiCurrency> currencyList;
    private LayoutInflater inflater;

    public CurrencyRatesAdapter(Context mContext) {
        inflater = LayoutInflater.from(mContext);
    }

    public void setCurrencyList(List<UiCurrency> currencyList) {
        this.currencyList = currencyList;


    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_currency_rate, parent, false);

        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        holder.tvCurrencyName.setText(currencyList.get(position).getCurrencyName());
        holder.tvSellPrice.setText(String.format(Locale.US, "%1$,.2f", currencyList.get(position).getCurrencySaleRate()));
        holder.tvPurchasePrice.setText(String.format(Locale.US, "%1$,.2f", currencyList.get(position).getCurrencyBuyRate()));
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_currency_name)
        TextView tvCurrencyName;
        @BindView(R.id.txt_sell_price)
        TextView tvSellPrice;
        @BindView(R.id.txt_purchase_price)
        TextView tvPurchasePrice;

        public CurrencyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }


    }
}

package com.example.practicafactuzone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {

    private List<Invoice> invoiceList;

    public InvoiceAdapter(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        Invoice invoice = invoiceList.get(position);
        holder.tvInvoiceDate.setText("Fecha: " + invoice.getDate());
        holder.tvInvoiceAmount.setText("Monto: $" + invoice.getAmount());
    }

    @Override
    public int getItemCount() {
        return invoiceList.size();
    }

    public static class InvoiceViewHolder extends RecyclerView.ViewHolder {
        TextView tvInvoiceDate;
        TextView tvInvoiceAmount;

        public InvoiceViewHolder(View itemView) {
            super(itemView);
            tvInvoiceDate = itemView.findViewById(R.id.tvInvoiceDate);
            tvInvoiceAmount = itemView.findViewById(R.id.tvInvoicePrice);
        }
    }
}

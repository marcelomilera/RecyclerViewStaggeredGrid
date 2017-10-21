package com.milera.marcelo.recyclerviewstaggeredgrid.ui.events;

import android.view.View;

/**
 * Created by MARCELO on 21/10/2017.
 */

public interface ClickListener{
    public void onClick(View view, int position);
    public void onLongClick(View view, int position);
}
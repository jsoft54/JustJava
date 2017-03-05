package com.example.jeanf.justjava;

import java.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    int quantity=0;
    public void submitOrder(View view) {
        int price= calculatePrice();
        displayMessage(createOrderSummary(price));
    }
    public String createOrderSummary(int totalPrice)
    {
        return "Name: JF" + "\n" + "Quantity:" + quantity + "\n" + "Total: "
            + NumberFormat.getCurrencyInstance().format(totalPrice) +"\nTank You!!" ;
    }
    public void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    public void increment(View view)
    {
        quantity=quantity+1;
        displayQuantity(quantity);
      }
    public void decrement(View view)
    {
        quantity=quantity-1;
        displayQuantity(quantity);
        // displayPrice(quantity*5);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffes) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffes);
    }
/**
        * Calculates the price of the order.
            *
 */
    private int calculatePrice() {
        return  quantity * 5;
    }
}

package com.example.jeanf.justjava;

import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    int quantity=1;
    public void submitOrder(View view) {
        boolean creamChecked = false;
        creamChecked = ((CheckBox) findViewById(R.id.creamCheck)).isChecked();
        boolean chocolateChecked = false;
        chocolateChecked = ((CheckBox) findViewById(R.id.chocolateCheck)).isChecked();
        int price= calculatePrice(creamChecked,chocolateChecked);
       displayMessage(createOrderSummary(price,creamChecked,chocolateChecked));
        composeEmail("jsoft54@outlook.com","Order Summary",createOrderSummary(price,creamChecked,chocolateChecked));
    }
    public String createOrderSummary(int totalPrice , boolean creamChecked, boolean chocolateChecked)
    {

        String nameOrder = ((EditText) findViewById(R.id.nameText)).getText().toString();
      /*  return "Name:" + nameOrder + "\n"
        +  "Add whipped cream? " + creamChecked + "\n"
                +  "Add chocolate? " + chocolateChecked + "\n"
                + "Quantity:" + quantity + "\n"
                + "Total: "      + NumberFormat.getCurrencyInstance().format(totalPrice)
                +"\nTank You!!" ;*/
        return getString(R.string.order_summary_name,nameOrder) +"\n"+
                getString(R.string.order_summary_whipped_cream,creamChecked)+"\n"+
                getString(R.string.order_summary_chocolate,chocolateChecked) +"\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+
                getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(totalPrice)) +"\n"+
                getString(R.string.thank_you);
    }
    public void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    public void increment(View view)
    {
        if (quantity==100) {
            Toast.makeText(this,"You cannot have more than 100 coffes", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        displayQuantity(quantity);
      }
    public void decrement(View view)
    {
        if (quantity==1) {
            Toast.makeText(this,"You cannot have less than 1 coffe", Toast.LENGTH_SHORT).show();
            return;
        }
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
    private int calculatePrice(boolean creamChecked, boolean chocolateChecked) {
        int basePrice=5;
        if (creamChecked)
            basePrice=basePrice+1;
        if (chocolateChecked)
            basePrice=basePrice+2;
        return  quantity * basePrice ;
    }
    public void composeEmail(String address, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
       // intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
          //  startActivity(intent);
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        }
    }
}

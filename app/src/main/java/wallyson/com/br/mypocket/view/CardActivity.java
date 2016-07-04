package wallyson.com.br.mypocket.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import wallyson.com.br.mypocket.R;
import wallyson.com.br.mypocket.presenter.CardActivityPresenter;
import wallyson.com.br.mypocket.presenter.CardInterface;

public class CardActivity extends AppCompatActivity implements CardInterface {
    private EditText cardName, credit, maturity;
    private Spinner bankName;
    private Button btnClean, btnSubmit;
    private CardActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        cardName = (EditText) findViewById(R.id.edtCardName);
        credit = (EditText) findViewById(R.id.edtCredit);
        maturity = (EditText) findViewById(R.id.edtMaturity);
        bankName = (Spinner) findViewById(R.id.spnBankName);
        btnClean = (Button) findViewById(R.id.btnClean);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        mPresenter = new CardActivityPresenter(this, this.getApplicationContext() );

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.cardRegistration();
            }
        });
        addElementSpinner();
    }

    public void addElementSpinner() {
        String[] arrayAccountName = mPresenter.getAllAccountName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayAccountName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankName.setAdapter(adapter);
    }

    public void clean() {
        cardName.setText(null);
        credit.setText(null);
        maturity.setText(null);
        bankName.setSelection(0);
    }

    public String getCardName() {
        return cardName.getText().toString();
    }

    public Double getCredit() {
        return Double.parseDouble(credit.getText().toString());
    }

    public String getMaturity() {
        return maturity.getText().toString();
    }

    public String getBankName() {
        return bankName.getSelectedItem().toString();
    }

    public void registrationError() {
        Toast.makeText(CardActivity.this, "@String/registrationError", Toast.LENGTH_SHORT).show();
    }

    public void successfullyInserted() {
        Toast.makeText(CardActivity.this, "@String/successfullyRegistration", Toast.LENGTH_SHORT).show();
    }

    public void databaseInsertError() {
        Toast.makeText(CardActivity.this, "@String/DatabaseInsertError", Toast.LENGTH_SHORT).show();
    }
}

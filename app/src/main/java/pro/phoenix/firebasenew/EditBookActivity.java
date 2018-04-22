package pro.phoenix.firebasenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity implements View.OnClickListener{

    EditText titleEt , authorEt;
    Button saveBtn , delBtn;
    String mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        saveBtn = findViewById(R.id.saveBtn);
        delBtn=findViewById(R.id.deleteBtn);
        saveBtn.setOnClickListener(this);
        delBtn.setOnClickListener(this);

        titleEt=findViewById(R.id.titleText);
        authorEt=findViewById(R.id.authorText);

        Intent i = getIntent();
        titleEt.setText(i.getStringExtra("title"));
        authorEt.setText(i.getStringExtra("author"));
        mRef=i.getStringExtra("ref");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.deleteBtn:
                BookListActivity.myRef.child(mRef).removeValue();
                finish();
                break;
            case R.id.saveBtn:
                Books book=new Books(titleEt.getText().toString() , authorEt.getText().toString());
                BookListActivity.myRef.child(mRef).setValue(book);
                finish();
                break;
        }

    }
}

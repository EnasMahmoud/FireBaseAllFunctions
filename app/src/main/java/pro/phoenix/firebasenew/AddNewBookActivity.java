package pro.phoenix.firebasenew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewBookActivity extends AppCompatActivity {

  private   EditText titleETxt , authorETxt;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_book);

        titleETxt=findViewById(R.id.titleEditeText);
        authorETxt=findViewById(R.id.authorEditeText);

        addBtn= findViewById(R.id.addNewBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Books newBook = new Books(titleETxt.getText().toString() ,
                        authorETxt.getText().toString() );
                String ID= BookListActivity.myRef.push().getKey();
                /*BookListActivity.myRef.child(ID).child("Title").setValue(titleETxt.getText().toString());
                BookListActivity.myRef.child(ID).child("Author").setValue(authorETxt.getText().toString());*/

                BookListActivity.myRef.child(ID).setValue(newBook);

                titleETxt.setText("");
                authorETxt.setText("");
                titleETxt.requestFocus();
            }
        });
    }
}

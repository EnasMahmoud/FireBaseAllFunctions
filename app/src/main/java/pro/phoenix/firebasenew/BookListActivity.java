package pro.phoenix.firebasenew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BookListActivity extends AppCompatActivity implements View.OnClickListener{

    public static FirebaseDatabase database ;
    public static DatabaseReference myRef;

    Button deleteAllBtn , addNewBtn ;
    ListView bookListV;

    FirebaseListAdapter<Books> mListAdapter;
    Intent editBookItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        bookListV = findViewById(R.id.bookListV);
        deleteAllBtn = findViewById(R.id.deleteAllBtn);
        addNewBtn = findViewById(R.id.addNewBtn);

        deleteAllBtn.setOnClickListener(this);
        addNewBtn.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        myRef=database.getReference("Books");
        myRef.keepSynced(true);

        mListAdapter = new FirebaseListAdapter<Books>(this,Books.class,R.layout.item_book,myRef) {
            @Override
            protected void populateView(View v, Books model, int position) {
                TextView titleView = v.findViewById(R.id.titleTV);
                TextView authorView = v.findViewById(R.id.authorTV);
                titleView.setText(model.getmTitle());
                authorView.setText(model.getmAuthor());
            }
        };
        bookListV.setAdapter(mListAdapter);

        editBookItem = new Intent(this , EditBookActivity.class);

        bookListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseReference itemRef = mListAdapter.getRef(position);
                TextView titleView = view.findViewById(R.id.titleTV);
                TextView authorView = view.findViewById(R.id.authorTV);

                editBookItem.putExtra("ref", itemRef.getKey());
                editBookItem.putExtra("title",titleView.getText().toString());
                editBookItem.putExtra("author",authorView.getText().toString());
                startActivity(editBookItem);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addNewBtn:
                Intent iAdd = new Intent(BookListActivity.this , AddNewBookActivity.class);
                startActivity(iAdd);
                break;
            case R.id.deleteAllBtn:
                myRef.removeValue();
                break;

        }

    }


}

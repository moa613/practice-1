LookupActivity
//이 액티비티는 의약품 정보를 이름 또는 형태 기준으로 검색한 뒤, 상세 페이지에 약 이름, 설명, 이미지 등을 표시하는 화면입니다.
//데이터를 전달한 액티비티의 타입(name/form)에 따라 이미지 표시 방식이 다르며, Intent를 통해 전달된 데이터를 기반으로 UI 요소에 표시합니다.

//1.onCreate() 메서드에서 레이아웃 설정 및 TextView, ImageView 객체 초기화.
//2.이전 액티비티(NameMyAdapter 또는 FormMyAdapter)에서 Intent로 전달한 값들을 받아옴.
//3.sort 값(name/form)에 따라 분기:
//4.이름 검색(name)인 경우: 이미지 데이터를 byte[]로 받아 Bitmap으로 디코딩.
//5.형태 검색(form)인 경우: 이미지 URL을 받아 Glide를 사용해 이미지 로딩.
//6.받아온 정보(약 이름, 설명, 이미지)를 화면에 출력.
//7.사용자가 뒤로가기 버튼을 누르면 액티비티 종료.

package com.example.androidlogin;

// Intent를 통해 다른 액티비티로부터 값 전달받기 위해 필요
import android.content.Intent;

// 이미지 데이터를 Bitmap으로 디코딩하기 위해 필요
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

// Bundle은 onCreate에서 전달받은 데이터 포함 객체
import android.os.Bundle;

// Log.e()로 로그 출력에 사용
import android.util.Log;

// 이미지 및 텍스트 표시 위한 위젯
import android.widget.ImageView;
import android.widget.TextView;

// Glide는 URL 기반 이미지 로딩을 위한 외부 라이브러리
import com.bumptech.glide.Glide;

// 의약품 이름 기반으로 검색 시 사용하는 액티비티를 상속
public class LookupActivity extends NameMainActivity {

    // 약 이름 표시용 텍스트뷰
    TextView textView;

    // 약 설명 표시용 텍스트뷰
    TextView detailStr;

    // 약 이미지 표시용 이미지뷰
    ImageView imageView;

    // 넘겨받은 약 이름과 상세 설명 저장용 변수
    String drugString;
    String str_detailStr;

    // Glide 또는 Bitmap으로 보여줄 이미지 주소
    String image;

    // 어느 어댑터(name/form)에서 넘어온 데이터인지 확인하기 위한 변수
    String sort = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_lookup.xml 레이아웃 설정
        setContentView(R.layout.activity_lookup);

        // xml의 View ID를 연결
        textView = findViewById(R.id.textView);
        detailStr = findViewById(R.id.detailStr);
        imageView = findViewById(R.id.image);

        // NameMyAdapter 또는 FormMyAdapter에서 넘겨준 intent 데이터 추출
        drugString = getIntent().getStringExtra("Drug"); // 약 이름
        str_detailStr = getIntent().getStringExtra("data"); // 약 설명
        sort = getIntent().getStringExtra("sort"); // 어떤 어댑터에서 왔는지 구분
        Log.e("form/sort??", sort); // 디버깅용 로그 출력

        // [1] 이름으로 검색한 경우 -> byte 배열로 전달된 이미지 처리
        if (sort.equals("name")) {
            // byte 배열로 받은 이미지 데이터를 Bitmap으로 디코딩
            byte[] b = getIntent().getByteArrayExtra("image");
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

            // View에 데이터 세팅
            textView.setText(drugString);         // 약 이름 표시
            detailStr.setText(str_detailStr);     // 약 설명 표시
            imageView.setImageBitmap(bitmap);     // 이미지 표시

        } else if (sort.equals("form")) {
            // [2] 형태로 검색한 경우 -> URL로 전달된 이미지 처리
            image = getIntent().getStringExtra("image");

            // Glide 라이브러리로 이미지 URL 로딩하여 이미지뷰에 표시
            Glide.with(this)
                .load(image)
                .into(imageView);

            // View에 데이터 세팅
            textView.setText(drugString);         // 약 이름 표시
            detailStr.setText(str_detailStr);     // 약 설명 표시
        }
    }

    // 뒤로가기 버튼 클릭 시 액티비티 종료
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}



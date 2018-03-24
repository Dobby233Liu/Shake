package com.l.shake;

import android.app.*;
import android.os.*;
import android.hardware.*;
import es.dmoral.toasty.*;
import android.widget.*;
import android.support.annotation.*;
import com.example.sunnick.shake.*;
import android.view.View.*;
import android.view.*;

public class MainActivity extends Activity/** implements SensorListener**/ implements Shakeable
{
	private SensorManager manager;

    //传感器监听器
    private MySensorEventListener listener;

    //是否可摇一摇，如果某些页面不想处理摇一摇，则设置为false
   // private boolean isShakeable = true;
	
    int Shak=0;
	@Override
	public void onShake(Object[] objs)
	{
	Shak++;
	((TextView)findViewById(R.id.Cishu)).setText(Integer.toString(Shak));
	}


		@Override
    protected void onCreate(@NonNull Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		manager = (SensorManager) this.getSystemService(Service.SENSOR_SERVICE);
        listener = new MySensorEventListener(this);
        setContentView(R.layout.main);
		//Toasty.custom(this,"hi",null,Toast.LENGTH_SHORT,false).show();
		((Button)findViewById(R.id.wow)).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View p1)
			{	
			Toasty.custom(MainActivity.this,"哇，你单身"+Shak*7+"年了耶！",null,Toast.LENGTH_SHORT,false).show();
			}
		});
    }
	protected void onResume(){
	super.onResume();
	manager.registerListener(listener,
	manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	SensorManager.SENSOR_DELAY_NORMAL);

    }
}

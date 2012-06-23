package android.CarSercurity.findLocation;


import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;

public class CarSercurityActivity extends Activity
{
	// ����LocationManager����
	LocationManager locManager;
	// �����������е�EditText���
	EditText show;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ��������ϵ�EditText��		show = (EditText) findViewById(R.id.show);
		// ����LocationManager����
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); 
		// ��GPS��ȡ���������Ķ�λ��Ϣ
		Location location = locManager.getLastKnownLocation(
			LocationManager.GPS_PROVIDER);
		// ʹ��location����EditText����ʾ
		updateView(location);
		// ����ÿ3���ȡһ��GPS�Ķ�λ��Ϣ
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER 
			, 3000, 8, new LocationListener()
		{
			@Override
			public void onLocationChanged(Location location)
			{
				// ��GPS��λ��Ϣ�����ı�ʱ������λ��
				updateView(location);
			}

			@Override
			public void onProviderDisabled(String provider)
			{
				updateView(null);				
			}

			@Override
			public void onProviderEnabled(String provider)
			{
				// ��GPS LocationProvider����ʱ������λ��
				updateView(locManager
					.getLastKnownLocation(provider));				
			}

			@Override
			public void onStatusChanged(String provider, int status,
				Bundle extras)
			{
			}
		}); 
	}	

	// ����EditText����ʾ������
	public void updateView(Location newLocation)
	{
		if (newLocation != null)
		{
			StringBuilder sb = new StringBuilder();
			sb.append("ʵʱ��λ����Ϣ��\n");
			sb.append("���ȣ�");
			sb.append(newLocation.getLongitude());
			sb.append("\nγ�ȣ�");
			sb.append(newLocation.getLatitude());
			sb.append("\n�߶ȣ�");
			sb.append(newLocation.getAltitude());
			sb.append("\n�ٶȣ�");
			sb.append(newLocation.getSpeed());
			sb.append("\n����");
			sb.append(newLocation.getBearing());			
			show.setText(sb.toString());
		}
		else
		{ 
			// ��������Location����������EditText
			show.setText("");
		}
	}
}
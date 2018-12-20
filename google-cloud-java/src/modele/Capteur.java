package modele;

import com.google.cloud.Timestamp;

public class Capteur {
	Timestamp date; 
	float latitude; 
	float longitude;
	float valeur;
	
	public Capteur(Timestamp date, float latitude, float longitude, float valeur)
	{
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.valeur = valeur;
	}
	
	public void setDate(Timestamp date)
	{
		this.date = date;
	}
	public void setLatitude(float latitude)
	{
		this.latitude = latitude;
	}
	public void setLongitude(float longitude)
	{
		this.longitude = longitude;
	}
	public void setValeur(float valeur)
	{
		this.valeur = valeur;
	}
	public Timestamp getDate()
	{
		return date;
	}
	public float getLatitude()
	{
		return latitude;
	}
	public float getLongitude()
	{
		return longitude;
	}
	public float getValeur()
	{
		return valeur;
	}

}

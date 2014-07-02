package org.geekulcha.Util;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Weeks;

public class TimeUtil {
	
	public static String getTimeString(Date mydate)
	{
		Date date=new Date();
		String returnString="";
		date.getTime();
		Date date2=mydate;
		int days=Days.daysBetween(new DateTime(date2), new DateTime(date)).getDays();
		int horas=Hours.hoursBetween(new DateTime(date2), new DateTime(date)).getHours();
		int minutos=Minutes.minutesBetween(new DateTime(date2), new DateTime(date)).getMinutes();
		int segundos=Seconds.secondsBetween(new DateTime(date2), new DateTime(date)).getSeconds();
		int weeks=Weeks.weeksBetween(new DateTime(date2), new DateTime(date)).getWeeks();
		int months=Months.monthsBetween(new DateTime(date2), new DateTime(date)).getMonths();
		System.out.println(months);
		/*System.out.println(days);
		System.out.println(horas);
		System.out.println(minutos);
		System.out.println(segundos);
		System.out.println(weeks);*/
		
		if(segundos>0)
		{
			if(segundos==1)
			returnString= segundos+" second Ago";
			else if(segundos>1&&segundos<60)
			{
				returnString= segundos+" seconds Ago";
			}
			else if(segundos>=60)
			{
				if(minutos==1)
				{
					returnString= minutos+" minute ago";
				}
				else if(minutos>1&&minutos<60)
				{
					returnString=minutos+" minutes ago";
				}
				else if(minutos>=60)
				{
					if(horas==1)
					{
						returnString=horas+" hour ago";
					}
					else if(horas>1&&horas<24)
					{
						returnString=horas+" hours ago";
					}
					else if(horas>=24)
					{
						if(days==1)
						{
							returnString=days+" day ago";
						}
						else if(days>1&&days<7)
						{
							returnString=days+" days ago";
						}
					}
					else if(days>=7)
					{
						if(weeks==1)
						{
							returnString= weeks+" week ago";
						}
						else if(weeks>1&&weeks<=4)
						{
							returnString=weeks+" weeks ago";
						}
						else
						{
							
							if(months==1)
							{
								returnString=months+" month ago";
							}
							else
							{
								returnString=months+" months ago";
							}
						}
					}
						
				}
			}
		}
		
		return returnString;
		
	}


}

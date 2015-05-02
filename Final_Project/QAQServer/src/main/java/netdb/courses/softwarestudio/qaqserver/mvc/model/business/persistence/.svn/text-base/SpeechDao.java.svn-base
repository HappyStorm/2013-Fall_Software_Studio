package netdb.courses.softwarestudio.qaqserver.mvc.model.business.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.qaqserver.mvc.ModelAwareServlet;
import netdb.courses.softwarestudio.qaqserver.mvc.model.domain.Speech;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

/**
 * Provides a way to access the {@link Speech} domain objects and hides the
 * implementation details specific to the underlying datastore.
 */
@SuppressWarnings("serial")
public class SpeechDao extends ModelAwareServlet<Speech> 
{
	private final double EARTH_RADIUS = 6378137.0;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		String pathInfoLow = req.getPathInfo();
		String dePathInfoLow = pathInfoLow.toLowerCase();
		String strSort = req.getParameter("sort");	
		String strList = req.getParameter("list");	
		String strLati = req.getParameter("lati");
		String strLongi = req.getParameter("longi");
		int sort = Integer.parseInt(strSort);
		int list = Integer.parseInt(strList);
		double lati = Double.parseDouble(strLati);
		double longi = Double.parseDouble(strLongi);
		
		List<Speech> speList = new ArrayList<Speech>();
		
		if(dePathInfoLow != null)
			{ 
				//System.out.println("Run here");
				if(sort==1)
					{
						List<Speech> tempList = ObjectifyService.ofy().
												load().type(Speech.class).
												list();
						List<Double> distanceList = new ArrayList<Double>();
						
						//System.out.printf("User now at \n\t Latitude: %f \n\t Longitude: %f \n",lati, longi);
						for(int i=0 ; i<tempList.size() ; i++)
							{
								Speech tempSpe = tempList.get(i);
								double tempLati = tempSpe.getLatitude();
								double tempLongi = tempSpe.getLongitude();
								//System.out.printf("No.%d Speech: \n\t Latitude: %f \n\t Longitude: %f \n",i, tempLati, tempLongi);
								distanceList.add(this.gpsDistance(lati, longi, tempLati, tempLongi));
							}
						//System.out.printf("%d\n",distanceList.size());
						
						/*for(int i=0 ; i<distanceList.size() ; i++)
							System.out.println(distanceList.get(i));*/
						
						for(int i=0 ; i<tempList.size() ; i++)
							{
								double bigDistance = 1000.0;
								int flag = 0;
								for(int j=0 ; j<distanceList.size() ; j++)
									{
										if(distanceList.get(j)<bigDistance
											|| distanceList.get(j).equals(0))
											{
												bigDistance = distanceList.get(j);
												flag = j;
											}
									}
								Speech temp = tempList.get(flag);
								//System.out.printf("No.%d Speech, Topic: %s\n", i, tempList.get(i).getTopic());
								speList.add(temp);
								//System.out.printf("No.%d Speech, Topic: %s\n", i, speList.get(i).getTopic());
								distanceList.set(flag, 99999999.0);
							}
						//for(int i=0 ; i<distanceList.size() ; i++)
							//System.out.println(distanceList.get(i));
						//System.out.println("Run here");
						//System.out.printf("Run here");
						if(list==1)
							{
								
								//for(int i=0 ; i<speList.size() ; i++)
									//System.out.printf("No.%d Speech, Topic: %s", i, speList.get(i).getTopic());
								setModel(req, speList);
								
							}							
						else
							{
								List<Speech> descList = new ArrayList<Speech>();
								for(int i=0 ; i<speList.size() ; i++)
									descList.add(speList.get(speList.size()-i-1));
								setModel(req, descList);
							}
					}
				else if(sort==2)
					{
						speList = ObjectifyService.ofy().
								load().type(Speech.class).
								list();
						if(list==1)
							{
								Collections.sort(speList, new Comparator<Speech>()
											{
												@Override
												public int compare(Speech que1, Speech que2) 
													{
														return (int) (que1.getStamp()-que2.getStamp());
										            }
											});
								setModel(req, speList);
							}
						else
							{
								Collections.sort(speList, new Comparator<Speech>()
											{
												@Override
												public int compare(Speech que1, Speech que2) 
													{
														return (int) (que2.getStamp()-que1.getStamp());
										            }
											});
								setModel(req, speList);
							}
					}
				else if(sort==3) 
					{
						//System.out.println("Run here.");
						speList = ObjectifyService.ofy().
								load().type(Speech.class).
								list();
						/*String Topic = req.getParameter("topic").toLowerCase();
						Query<Speech> speechQuery = ObjectifyService.ofy().load()
								  .type(Speech.class).
								  filter("topic", Topic);
						speList = speechQuery.list();*/
						if(list==1)
							{
								Collections.sort(speList, new Comparator<Speech>()
											{
												@Override
												public int compare(Speech que1, Speech que2) 
													{
														return que1.getTopic().compareTo(que2.getTopic());
										            }
											});
							}
						else
							{
								Collections.sort(speList, new Comparator<Speech>()
											{
												@Override
												public int compare(Speech que1, Speech que2) 
													{
														return  que2.getTopic().compareTo(que1.getTopic());
										            }
											});
							}
						setModel(req, speList);
					}
				else if(sort==0)
					{
						String Topic = req.getParameter("topic").toLowerCase();
						Query<Speech> speechQuery = ObjectifyService.ofy().load()
								  .type(Speech.class).
								  filter("topic", Topic);
						speList = speechQuery.list();
						Collections.sort(speList, new Comparator<Speech>()
									{
										@Override
										public int compare(Speech que1, Speech que2) 
											{
												return  que1.getTopic().compareTo(que2.getTopic());
								            }
									});
						setModel(req, speList);
					}
			} 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		Speech spe = getModel(req);
		Speech newSpeech = new Speech();
		//String enTopic = URLEncoder.encode(spe.getTopic().toLowerCase(), "UTF-8");
		//String enSpeaker = URLEncoder.encode(spe.getSpeaker().toLowerCase(), "UTF-8");
		//String enDescription = URLEncoder.encode(spe.getDescription().toLowerCase(), "UTF-8");
		long stamp = spe.getStamp();
		String topic = spe.getTopic().toLowerCase();
		String speaker = spe.getSpeaker().toLowerCase();
		String description = spe.getDescription().toLowerCase();
		//System.out.println("Latitude=" + spe.getLatitude());
		//System.out.println("Longitude=" + spe.getLongitude());
		System.out.println("Topic= " + topic);
		System.out.println("Speaker= " + speaker);
		System.out.println("Description=  " + description);
		newSpeech.setTopic(topic);
		newSpeech.setDescription(description);
		newSpeech.setSpeaker(speaker);
		newSpeech.setLatitude(spe.getLatitude());
		newSpeech.setLongitude(spe.getLongitude());
		newSpeech.setStamp(stamp);	
		//newSpeech.setDate("");
		ObjectifyService.ofy().save().entity(newSpeech).now();
		//ObjectifyService.ofy().save().entity(spe).now();
	}
	
	private double gpsDistance(double lat_a, double long_a, double lat_b, double long_b) 
		{
			double radlat_a = (lat_a * Math.PI / 180.0);
			double radlat_b = (lat_b * Math.PI / 180.0);
			double dif_lat = radlat_a - radlat_b;
			double dif_long = (long_a - long_b) * Math.PI / 180.0;
			double dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(dif_lat / 2), 2)+ Math.cos(radlat_a) * Math.cos(radlat_b)
			* Math.pow(Math.sin(dif_long / 2), 2)));
			dis = dis * EARTH_RADIUS;
			dis = Math.round(dis * 10000) / 10000;
			return dis;
		}
}
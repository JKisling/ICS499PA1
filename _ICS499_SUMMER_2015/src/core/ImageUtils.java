package core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageUtils {

	/**
	 * Gets the image based on the file path passed
	 */
	public static Image getImage(String filePath)
	{
		Image image;
		
		//Check if the filePath starts with http, treats it as a URL if it does, otherwise treats it as a local file
		if(filePath.indexOf("http") != -1)
		{
			try {
				//create the URL
				final URL url = new URL(filePath);
				//Open the connection to the URL, try loading the image if the proper response code is received, 
				//load the default image otherwise
				HttpURLConnection huc = (HttpURLConnection) url.openConnection();
				int responseCode = huc.getResponseCode();
				if (responseCode == 200)
				{
					try {
						
						URL imageURL = new URL (filePath);
						image = ImageIO.read(imageURL);
						image = image.getScaledInstance(471, 408, 1);
						return image;


					} catch(Exception e) {
						try{
							image = getDefaultImage();
						}catch (Exception e1) {
							System.out.println("Cannot display default image.");
						}
					}
				}
				else
				{
					try{
						image = getDefaultImage();
						return image;
					}catch (Exception e1) {
						System.out.println("Cannot display default image.");
					}
				}
			} catch(Exception e) {
				try{
					image = getDefaultImage();
					return image;
				}catch (Exception e1) {
					System.out.println("Cannot display default image.");
				}
			}
		} //End if
		
		//filePath was not a URL so treat it as a local file
		else 
		{
			try
			{					
				image = ImageIO.read(new File(Config.IMAGE_DIR + "\\"+filePath.trim()));
				image = image.getScaledInstance(453, 418, 1);
				return image;

			}catch(IOException e)
			{
				
				try {
					image = getDefaultImage();
					return image;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Cannot display default image.");
				}
			}
		} //End else
		
		return null;
	}
	
	/**
	 * Gets a default image file and returns it
	 * @return
	 */
	public static Image getDefaultImage()
	{
		
		try{
			Image image;
			image = ImageIO.read(new File(Config.NOT_FOUND_IMAGE));
			image = image.getScaledInstance(471, 408, 1);
			return image;
			
		}catch (Exception e1) {
			System.out.println("Cannot display default image.");
		}
		
		return null;
	}

	
		

}

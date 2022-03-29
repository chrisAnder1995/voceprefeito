package com.devidea.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;


public class PhotoUtil {
	public static final String DEFAULT_USER_IMG = "iVBORw0KGgoAAAANSUhEUgAAAc4AAAHOCAMAAAAmOBmCAAAAM1BMVEUyicg9j8pJlcxUm85godBrp9J3rdSCs9aNudiZv9ukxd2wy9+70eHH1+PS3eXe4+fp6elALxDWAAAMG0lEQVR42u3da7qkKgyF4aiUhYrA/Ed7fvT96e7Te3sjJN+aQfkWEKKiVGIowiWAk8BJ4CRwwkngJHASOAmccBI4CZwETgInnAROAieBk8AJJ4GTwEngJHDCSeAkcBI4CZxwEjgJnAROAiecBE4CJ4ETTgIngZPASeCEk8BJ4CRwEjjhJHASOAmcBE44CZwETgIngRNOAieBk8BJvHCmtMY4h18zx7ikVODsyHGNYZL/zxTiYlHVFGdJ8TXKxzOGuBU4NSav8yRHMs5rhlNVtvcoZzLORkapGBiWL7kiYclwtl4tl0muy9S9aNecF43LX8boWuBskX0e5I4M8w7n4wMzyH2Zuh2iXXKWOMq9GWKG86FS9j3IA5kznA9gzvJUOgQVMC2BdsVZ4iAPZy5w3pTlcUwRGWKB84akUdpk3OC8fNF8SbuEDGf/8+xPiXBe2M+bpHXGBOdFiaIh7wKnjaH5dYDucHa/ava1girnLC/RlFDgPDPRjqIrQ4LzcFbRlwjnwcyiMa8C55FlcxKdmXY4+182f1pAdzg/mTSI4qxwdl8E/ZwFzs/0DkR7Zjg7L2l78BQ0LXkKmpY6foLm8Q1ogdOOpsgLTkOaCtdPQdOSpy7OVQRPM5z9aWrr92ni3EXwNMO5D11yqrq/oodT7f3Nf3pmOH9PkF6jqJ2ghvMt/WaG00BRq/D2pxLOTsug79nhtFAGfS+HCpw2Fk5V3XgVnJv0nwXOb1PtYIBTRzdBA2cQC5ngrLX28NjexxLhrLXmwQinht1Ke86XFU0N021zzk3sZHHPWQZDnO3vrbTmfIulvJxzZrGV5JszGOMcXXMmsZbVM+dojrPxrZWmnKvYS/TLORrkbDs8W3JGEYanGU5THQQlw1MYnJaGp7ByWhqe7ThXEYanHc7RLGfD4dmMcxO7Wf1xBsOcozvOLJazeeOcTXO+nHEabSF8T/bFudrWbLZXacQ5GeccXXFmsZ7NE+fbPOfsiXM0zzk44txFmG3tcL4dcM5+OEcHnIMbTg9zbaPZtgVndME5e+GcXHCOTjiL+Mjug3N1wrn44JydcAYfnKMTTnHBmb1otnjZU1g6Ld30FJZOS4vn85yTG87BA6f4yW6fMzniXO1zLo44o33O2RFnsM8ZHHEO9jnFU4p1zuyKM1nnTK44F+uc0RVnhNNSXtY5gyvOACc7lY44B1ecYp3Tl+bjr+0+zFmccSbbnAlOOLlFBqfLPsLDnAucljgjnHDCCaeKzHDStIUTTjjhhBNOOOGEE0444aSNACeccFrj3ODkfieccKqI8YdLdjgtcVY44ew31TjnBKclTl/vqEzWOWdXnObfIPPVFnpb51xdcZp/+9pXH2Gzzulrp7Kb5xw9cVbznJ52KpN9Tk+l7Wyf09MdTwcnZno6xS3Z5/R0dkl1wOmnFpo8cPqphWYPnH76QqsHTj99oeyC08vi2eIDng04I0unJU4vT/NtPji97DyLE04fD5i0+BprE04fTyQsXjh9nIKavXDWFx0+S5wrc60lzsJca4nTwWzbZq5txLky11ritN9JKK44rXcSXtUVp/W+7eaL0/h7nmN1xmm7GIreOG0XQ9kdp+Wb2HN1x2n58endH6fhvUqoDjntDs/kkdNs47bh4GzJmRicljiNPnDbcnA25dwZnJY4TRa3TQdnW87M4LTEWd/cGbPEWcx1brNnTnMfT35X15zGTo0ainNOW72EtTrnNNXqC9U9p6VqKMNp6DGTWOG007qdKpy11mxkut3hNDTdKphqdXCaqG5DhfNbddt/M2HIcBpqJmwVzh/p/anbucJpZ7cyVTjtLJ9KFk5FnHXvePeZKpx2dp9LhdNOOTRXOP+UPh/sCxXOP6fHV7KnAuffytv+PAdVmro4+7uXPewVTjPbFW2a2jj78lSnqY6zK8+twmnHc61wmvEcUoXTjKe+dVMrZ91HNA1x6u8njDo1lXLWovt2tq7Onn5O3f34l1ZNvZyKX/2c1V4zxZx1U1rgrhXOQwXuRElriLMWfY/Hh1LhNLOARt2XSzunro6Cyr5eV5y1zOxPDHGqqXCHRf+l6oFTR0UUcoXzqgHaegUd1i6uUyectbQ9vW8uFc5rS9x2Tfkp9XKR+uGsdR0pgQxx1hIb1LixVDitLKFz7ur6dMZZa57BNMT5IGh/mD1yPrOGDj1i9slZa7m5yh37KoB656y1pvs6f2Hr9aL0y1lrXsZbBmbu95L0zFlr3d/DxStm6vp6dM5Za93m8TLLrfeL0T9nrXWP558Rm97JwJUwwVlrLeuJQTrOa7ZxGaxw1lpr3t7hwKg0Q2mM88vEu8bwsXE6hPeajP16c5xfN6VbjOEvrEMI77glk7/bKOePCTj9mmz751rn9BY44SRwEg+c+T1uJq7kDmddJ5H236S9IouMb99fCMzf74eE0jnm1/cuXsktZ5q7etXu/yfa7x2LcXXJmUJnL8L+/0T78z+z5YMpogNTRKZeOza/nYHUEFSUYPb29sCP/On102agz3Pm0PPrzb8NzVnVmy2i5Od/vQi9bUHT32/GNdlOP8z5zweeX9nA0Py2/dptc6YP3FjuaAX996EN72KX86NnkIS9C8z8kSdZnl49RNF/ud2f+sB/86NfTHv2iIynOD93+oj6kyU+cfbGo6uHqBuaHZxH8MlzGh4coKJvaGo/yOfzL5g+t4I+wZkOPtGs8hXLcugzo0+dZPMA54nPrKqriQ6/KfzQtwBu5zx3SP+g6r3ZU699LxY4T3/iRs9b7Wff4X9iwr2Z85Lvk6sAzfPpV0kfuAV4L+dVh4yE1tuWdMkvuf+Zizs5r/y2zbg2XETXy74xsPbLefGHFoa5TS83X/rC/twr5w3f+ZseH6Jlvfqgzntv0d/GucotefT8gjTfcB7Vrd8vk740HzxdZH/fdBTVnR2Fmzij3Jnx9jF6m6XIrR9Kuofz/lMQh/m2dbRs881n/t3nKX1qflmF4vXT7r48cUj5bRtQ6VfzS6F4Iem+vB47znrthvPxzxKF95ZPT7Dx4U8HrJ1wtvnI1BDiemxF2rePHl3TgafY0Py2moa4pI+q5rTGV8NPhK4dcKr4ANwQwjvGlP4gm1NKS4whtP+w2R31rRjU7CU3eF7LGTFq63kp54rQJz2zYk40m/fjL+TcB3haewqabTPr5CwTNO09L+NEU0M74SpONpzHk9RxLqCo2K5cw5kw0VHeXsKZKWrP5aWKkzLobBZFnJRBasqhCzjp7V1RDhUlnHSDLknQwUk36KJEFZwsnJqWz7OcGwyals+TnOw4de0+T3IGEFTtPs9x0qpV1rw9xbkjcHHztiknexRtu5UznDyGeX32ZpxMtfqmW2GqtTTdClWtsuQmnDQQbkpowkkDQWMz4SgnvVqVvduDnGXkst+W+XHONxf9xqSHOTOXXOfmU6iDLFVDQh1kqRo6xEkdpLUaOsJJ6/3+7I9xFvpBantDBzh5du+JbA9xskl5JONDnC8u9SNZH+HkVU7NmxWhg6A18QFOBqfq4SkMTkvDUxicloanMDgtDU9hcFoansLgtDQ8hcFpaXgKg9PS8BQGp6XhKQxOS8PzE5zcSmkxPG/j5D5ni6w3cTI4m2S8iZMnhNpku4WTJ4QaJdzCyUGKrbLfwcmzta0y38BJC6FdyvWcPPDVRStB2KVY2qsIuxRLexWhENKf18WcvAHYNvlaTgqhToohoRCyVAx9iJMDvnophoRCyFIx9BFOjsbspjP0EU7uW7fPch0nt8baZ7qMk01nP1tPYdPZR94XcRYuZT9bz39z8hiCjuzXcDLXdjTbCnOtpdlWmGstzbbCXGtptv0nJ5exp9lW6CFYmm2Ffm03iec56dfqyXSak3tjmpLPcvJApqasZzn5CqCmvE5y0hJSleEkJy0hXUnnONmmdNYYEh7hs7RVEbYpPaWc4eRxaW3ZznByN6W3xVPo8FlaPIWl09LiKew6LS2ewq7T0uIp7Dq7SjjMScNWYw5zcjKUxqSjnNzr1JjlKCdNBI2Zj3JSCfXXSBAqIUu1kFAJWaqFhErIUi0k9IQs9YWEj+BY6gsJ7xp1luEQJ+fwaU05wklh22NpKzwn1FvWI5zsU7QmHuGkY6s1ryOc7FN63Kn8B8kqVqwX0jroAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAABJRU5ErkJggg==";

	public static String getPhotoPath(){
		StringBuilder path = new StringBuilder();
		path.append(System.getProperty("jboss.server.base.dir"));

		// /deployments/content/voceprefeito_data/photo
		path.append(File.separator);
		path.append("voceprefeito_data");
		path.append(File.separator);
		path.append("photo");
		return path.toString();
	}
	public static String getDataPath(){
		StringBuilder path = new StringBuilder();
		path.append(System.getProperty("jboss.server.base.dir"));

		// /deployments/content/voceprefeito_data
		path.append(File.separator);
		path.append("voceprefeito_data");
		return path.toString();
	}

	public static String resizeBase64(String photoName, int size) {
		try {
			String base64 = getEncodedPhoto(photoName);
			byte[] imageByte = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);

			BufferedImage image = null;
			image = ImageIO.read(bis);
			bis.close();

			int width = image.getWidth();
			int height = image.getHeight();

			if (width > height) {
				width = size;
				height = height * size / image.getWidth();
			} else {
				height = size;
				width = width * size / image.getHeight();
			}

			image = resizeImage(image, width, height);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( image, "jpg", baos );
			baos.flush();
			imageByte = baos.toByteArray();
			baos.close();

			return Base64.getEncoder().encodeToString(imageByte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static BufferedImage resizeImage(String photoName, int size) {
		try {
			String base64 = getEncodedPhoto(photoName);
			byte[] imageByte = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);

			BufferedImage image = null;
			image = ImageIO.read(bis);
			bis.close();

			int width = image.getWidth();
			int height = image.getHeight();

			if (width > height) {
				width = size;
				height = height * size / image.getWidth();
			} else {
				height = size;
				width = width * size / image.getHeight();
			}

			image = resizeImage(image, width, height);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( image, "jpg", baos );
			baos.flush();
			imageByte = baos.toByteArray();
			baos.close();
			
			InputStream in = new ByteArrayInputStream(imageByte);
			BufferedImage bImageFromConvert = ImageIO.read(in);

			return bImageFromConvert;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static BufferedImage resizeImage(BufferedImage image, Integer imgWidth, Integer imgHeight) throws IOException {  
		Double newImgLenght = (double) image.getWidth();  
		Double newImgHeight = (double) image.getHeight();  

		Double imgProportion = null;  
		if (newImgLenght >= imgWidth) {  
			imgProportion = (newImgHeight / newImgLenght);  
			newImgLenght = (double) imgWidth;  
			newImgHeight = (newImgLenght * imgProportion);  
			while (newImgHeight > imgHeight) {  
				newImgLenght = (double) (--imgWidth);  
				newImgHeight = (newImgLenght * imgProportion);  
			}  
		} else if (newImgHeight >= imgHeight) {  
			imgProportion = (newImgLenght / newImgHeight);  
			newImgHeight = (double) imgHeight;  
			while (newImgLenght > imgWidth) {  
				newImgHeight = (double) (--imgHeight);  
				newImgLenght = (newImgHeight * imgProportion);  
			}  
		}  

		BufferedImage newImage = new BufferedImage(newImgLenght.intValue(), newImgHeight.intValue(), BufferedImage.TYPE_INT_RGB);  
		Graphics g = newImage.getGraphics();  
		g.drawImage(image.getScaledInstance(newImgLenght.intValue(), newImgHeight.intValue(), 10000), 0, 0, null);  
		g.dispose();

		return newImage;
	}

	public static BufferedImage resizeImage(String imgPath, Integer imgWidth, Integer imgHeight) throws IOException {
		File file = new File(imgPath);
		//long size = file.length();
		//System.out.println("Size: " + size + " - " + imgPath);
		BufferedImage image = ImageIO.read(file);  

		return resizeImage(image, imgWidth, imgHeight);
	}

	public static String getSystemPhotoName(String originalNamePhoto, String type){
		Date actualDate = new Date();
		Random random = new Random();
		
		if(originalNamePhoto == null) {
			Integer randomNumber = random.nextInt();
			originalNamePhoto =  randomNumber.toString();
		} else if(originalNamePhoto.isEmpty()) {
			Integer randomNumber = random.nextInt();
			originalNamePhoto =  randomNumber.toString();
		}
		
		if(type == null) {
			type = "png";
		} else if(type.isEmpty()) {
			type = "png";
		}
		
		StringBuilder imageNameMedia = new StringBuilder();
		imageNameMedia.append(SHA.bytesToHex(SHA.hash256(originalNamePhoto + actualDate.toString())));
		imageNameMedia.append(".");
		imageNameMedia.append(type);
		return imageNameMedia.toString();
	}

	public static String savePhoto(String base64, String photoName, String type) throws IOException {
		String path = getPhotoPath();
		File targetFolder = new File(path);
		targetFolder.mkdirs();

		byte[] imageByte = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64);

		photoName = getSystemPhotoName(photoName, type);

		File newPhoto = new File(targetFolder, photoName);
		OutputStream output = new FileOutputStream(newPhoto);

		output.write(imageByte);
		output.flush();
		output.close();
		
		return photoName;
	}

	public static String getEncodedPhoto(String photoName) throws IOException {
		String path = getPhotoPath();
		File file = new File(path + File.separator + photoName);
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] photoBytes = new byte[(int)file.length()];
		fileInputStreamReader.read(photoBytes);
		fileInputStreamReader.close();

		return Base64.getEncoder().encodeToString(photoBytes);
	}
	
	public static String getBase64FullPath(String path) throws IOException {
		File file = new File(path);
		FileInputStream fileInputStreamReader = new FileInputStream(file);
		byte[] photoBytes = new byte[(int)file.length()];
		fileInputStreamReader.read(photoBytes);
		fileInputStreamReader.close();
		
		return Base64.getEncoder().encodeToString(photoBytes);
	}
}

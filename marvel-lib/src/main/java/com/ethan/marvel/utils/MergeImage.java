/**
 * 
 */
package com.ethan.marvel.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.ethan.marvel.mffdata.DataLoader;

/**
 * @author GBTW0011
 * created date: 2015年7月20日
 */
public class MergeImage {

    /**
     * 
     */
    public MergeImage() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        MergeImage m = new MergeImage();
//        m.run();
//        m.generateAllFromMFFDATA();
        m.runCard();

        
//        m.runmushroom();
    }
    
    
    
    public void generateAllFromMFFDATA() {
        try {
            DataLoader herodl = new DataLoader();
            herodl.loadData(new File("/data/marvel/MFF Data/HERO_LIST.csv.txt.decode.txt"), "UTF-8", "\t", 0);
            
            int heroCounter = 0;
            List<String> heroCostumeList = new ArrayList<String>();
            String lastHero="";
            while(herodl.next()) {
                if("0".equals(herodl.getData("VISIBLE"))) {
                    continue;
                }
                String thisHero = herodl.getData("PREFAB");
                if(lastHero.equals(thisHero)) {
                    continue;
                }
                
                heroCounter++;
                heroCostumeList.add(thisHero);
                lastHero=thisHero;
            }
            
            int fixPx = 64;
            int w = fixPx * 10;
            int h = ((int) Math.ceil(heroCounter/10d))*fixPx;
            
            BufferedImage all = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = all.createGraphics();
            for(int i=1;i<=heroCounter;i++) {
                System.out.println(heroCostumeList.get(i-1));
                BufferedImage img = ImageIO.read(new File("/data/marvel/Convert_Png/"+heroCostumeList.get(i-1)+".png"));
                int x = (i-1) % 10;
                int y = (int) Math.floor((i-1)/10);
                g.drawImage(img,x*fixPx, y*fixPx, null);
                System.out.println("i: "+i+", x: "+x*fixPx+", y: "+y*fixPx);
                
            }
            ImageIO.write(all, "png", new File("/data/temp/allhead"+fixPx+".png"));
            
            

            
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    
    public void runmushroom() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/data/temp/mushroom.txt"));
            String line = null;
            
            while((line=br.readLine())!=null) {
                for(int i=0,size=line.length();i<size;i++) {
                    System.out.print(line.charAt(i)+" ");
                }
                System.out.println();
            }
            
            br.close();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    public void run() {
        try {
            int fixPx = 36; // modify here!!!
            int lastImageIndex = 157; // modify here!!!
            
            
            int w = fixPx * 10;
            int h = ((int) Math.ceil(lastImageIndex/10d))*fixPx;
            
            BufferedImage all = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = all.createGraphics();
            for(int i=1;i<=lastImageIndex;i++) {
                BufferedImage img = ImageIO.read(new File("/data/temp/imghero"+fixPx+"/"+i+".png"));
                int x = (i-1) % 10;
                int y = (int) Math.floor((i-1)/10);
                g.drawImage(img,x*fixPx, y*fixPx, null);
                System.out.println("i: "+i+", x: "+x*fixPx+", y: "+y*fixPx);
                
            }
            ImageIO.write(all, "png", new File("/data/temp/allheroes"+fixPx+".png"));
            
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void runISO() {
        try {
            int fixPx = 36;
            int lastImageIndex = 8; // modify here!!!
            
            
            int w = fixPx;
            int h = fixPx*(lastImageIndex+1);
            
            BufferedImage all = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = all.createGraphics();
            for(int i=0;i<=lastImageIndex;i++) {
                BufferedImage img = ImageIO.read(new File("/data/marvel/ISO_png/"+i+".png"));
                int x = 0;
                int y = i*fixPx;
                g.drawImage(img,x, y, null);
                //System.out.println("i: "+i+", x: "+x*fixPx+", y: "+y*fixPx);
                
            }
            ImageIO.write(all, "png", new File("/data/temp/alliso8_36.png"));
            
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    private String formatNumber(int num, int digits) {
        String s = Integer.toString(num);
        StringBuilder sb = new StringBuilder();
        for(int i=0,count=digits-s.length();i<count;i++) {
            sb.append('0');
            
        }
        sb.append(s);
        return sb.toString();
    }
    
    public void runCard() {
        try {
            int lastCardIndex = 79; // modify here!!!
            

          
            DataLoader imageloader = new DataLoader();
            imageloader.loadData(new File("/data/marvel/MFF Data/IMAGE_PACK.csv.txt.decode.txt"), "UTF-8", "\t", 0);


            for( int i=79;i<=lastCardIndex;i++) {
                String imgName = imageloader.getData("cc_"+formatNumber(i,4), "DEST");
                float ox = Float.parseFloat(imageloader.getData("cc_"+formatNumber(i,4), "X"));
                float oy = Float.parseFloat(imageloader.getData("cc_"+formatNumber(i,4), "Y"));
                float ow = Float.parseFloat(imageloader.getData("cc_"+formatNumber(i,4), "W"));
                float oh = Float.parseFloat(imageloader.getData("cc_"+formatNumber(i,4), "H"));
                
                BufferedImage img = ImageIO.read(new File("/data/marvel/cards/"+imgName+".png"));
                
                int w = img.getWidth();
                int h = img.getHeight();
                
                int targetW = (int) (w*ow);
                int targetH = (int) (h*oh);
                
                BufferedImage imgnew = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_ARGB);
                Graphics2D gnew = imgnew.createGraphics();
                gnew.drawImage(img,0, 0, targetW, targetH, (int)(w*ox), (oy==0f?h/2:0), (int)(w*ox)+targetW, (oy==0f?h/2:0)+targetH, null);
                
//                Card 79, it's a single image  =.=" not 2X3
//                gnew.drawImage(img,0, 0, targetW, targetH, (int)(w*ox), 0, (int)(w*ox)+targetW, targetH, null);
                
                gnew.dispose();
                gnew.setComposite(AlphaComposite.Src);

                ImageIO.write(imgnew, "png", new File("/data/temp/ncc_"+i+".png"));
                
            }

        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }
    

}

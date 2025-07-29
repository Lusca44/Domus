package br.com.domus.imagem.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageOptimizationService {

    @Value("${app.image.width}")
    private int targetWidth;
    
    @Value("${app.image.height}")
    private int targetHeight;
    
    @Value("${app.image.quality}")
    private float quality;
    
    public byte[] optimizeImage(byte[] originalImage) throws IOException {
        BufferedImage srcImage = ImageIO.read(new ByteArrayInputStream(originalImage));
        
        BufferedImage resized = Scalr.resize(
            srcImage, 
            Scalr.Method.QUALITY,
            Scalr.Mode.AUTOMATIC,
            targetWidth,
            targetHeight
        );
        
        return compressToJpeg(resized);
    }

    private byte[] compressToJpeg(BufferedImage image) throws IOException {
        ByteArrayOutputStream compressed = new ByteArrayOutputStream();
        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        
        try {
            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);
            
            writer.setOutput(ImageIO.createImageOutputStream(compressed));
            writer.write(null, new IIOImage(image, null, null), param);
        } finally {
            writer.dispose();
        }
        
        return compressed.toByteArray();
    }
}
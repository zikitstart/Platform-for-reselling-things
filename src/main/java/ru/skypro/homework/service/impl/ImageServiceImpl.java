package ru.skypro.homework.service.impl;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image upload(MultipartFile imageFile) throws IOException {
        BufferedImage originalBufferedImage = ImageIO.read(imageFile.getInputStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Tika tika = new Tika();
        String detectedType = tika.detect(imageFile.getInputStream());
        String fileExtension = MimeTypeUtils.parseMimeType(detectedType).getSubtype();
        ImageIO.write(originalBufferedImage,fileExtension , byteArrayOutputStream);
        byteArrayOutputStream.flush();
        Image imageModel = new Image();
        imageModel.setImage(byteArrayOutputStream.toByteArray());
        //imageModel.setSize((long) byteArrayOutputStream.size());
        byteArrayOutputStream.close();
        imageModel.setMediaType(detectedType);
        return imageModel;
    }

    @Override
    public Image save(Image image) {
        return imageRepository.saveAndFlush(image);
    }


    @Override
    public Image read(long id) {
        return imageRepository.findById(id)
                .orElse(null);
    }
}

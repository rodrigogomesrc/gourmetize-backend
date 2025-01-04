package br.ufrn.imd.gourmetize_backend.service;

import io.minio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

@Service
public class ImageService {

    private final MinioClient minioClient;
    private final String bucketName;

    public ImageService(
            @Value("${minio.endpoint}") String endpoint,
            @Value("${minio.accessKey}") String accessKey,
            @Value("${minio.secretKey}") String secretKey,
            @Value("${minio.bucketName}") String bucketName
    ) {
        this.minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
        this.bucketName = bucketName;

        try {
            boolean bucketExists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );
            if (!bucketExists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(bucketName).build()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao configurar o MinIO: " + e.getMessage(), e);
        }
    }

    public String uploadImage(MultipartFile file, Long userId) throws IOException {

        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("image/jpeg") && !contentType.equals("image/png"))) {
            throw new IllegalArgumentException("Apenas imagens (JPG, JPEG, PNG) são permitidas.");
        }

        String extension = contentType.equals("image/png") ? "png" : "jpg";

        String fileName = userId + "_" + Instant.now().toEpochMilli() + "." + extension;

        try (ByteArrayOutputStream compressedImageStream = compressImage(file)) {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(new ByteArrayInputStream(compressedImageStream.toByteArray()), compressedImageStream.size(), -1)
                    .contentType(contentType)
                    .build());
        } catch (Exception e) {
            throw new IOException("Erro ao enviar a imagem para o MinIO: " + e.getMessage(), e);
        }

        return fileName;
    }

    public InputStream getImage(String fileName) throws IOException {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build());
        } catch (Exception e) {
            throw new IOException("Erro ao buscar a imagem no MinIO: " + e.getMessage(), e);
        }
    }

    private ByteArrayOutputStream compressImage(MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());

        ByteArrayOutputStream compressedStream = new ByteArrayOutputStream();
        try (ImageOutputStream ios = ImageIO.createImageOutputStream(compressedStream)) {
            ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.7f); // Ajuste o nível de compressão (0.0 a 1.0)

            writer.write(null, new IIOImage(image, null, null), param);
            writer.dispose();
        }

        return compressedStream;
    }
}
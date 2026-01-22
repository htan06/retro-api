package com.retro.api.service.impl;

import com.cloudinary.Cloudinary;
import com.retro.api.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;
    private final String EXTENSION_IMAGE_REGEX = "^image/(svg|png|jpg|jpeg|jfif|pjpeg|pjp)$";

    @Override
    public List<String> uploadImages(List<MultipartFile> files) {
        return files
                .stream()
                .map(this::uploadImage)
                .toList();
        // Upload then map to url
    }

    @Override
    public String uploadImage(MultipartFile file) {
        if (!Objects.requireNonNull(file.getContentType()).matches(EXTENSION_IMAGE_REGEX)) {
            throw new IllegalArgumentException("File %s not a image".formatted(file.getName()));
        }

        try {
            Map uploadResponse = cloudinary.uploader().uploadLarge(file.getInputStream(), Map.of());
            return uploadResponse.get("secure_url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Co loi xay ra trong qua trinh upload anh");
        }
    }
}

package com.retro.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CloudinaryService {
    List<String> uploadImages(List<MultipartFile> files);

    String uploadImage(MultipartFile file);
}

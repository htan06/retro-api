package com.retro.api.service;

import com.retro.api.dto.brand.request.CreateBrandDTO;
import com.retro.api.dto.brand.request.UpdateBrandInfoDTO;
import com.retro.api.dto.brand.response.BrandDTO;

import java.util.List;

public interface BrandService {
    List<BrandDTO> getListBrand();

    BrandDTO createBrand(CreateBrandDTO createBrand);

    BrandDTO updateBrand(UpdateBrandInfoDTO updateBrandInfo);
}

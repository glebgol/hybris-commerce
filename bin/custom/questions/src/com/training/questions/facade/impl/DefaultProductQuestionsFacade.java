package com.training.questions.facade.impl;

import com.training.questions.facade.ProductQuestionsFacade;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

public class DefaultProductQuestionsFacade implements ProductQuestionsFacade {
    @Resource
    private ProductService productService;

    @Resource
    private Converter<ProductModel, ProductData> productConverter;

    @Override
    public ProductData getProductDataForCode(String code) {
        ProductModel productModel = productService.getProductForCode(code);

        return productConverter.convert(productModel);
    }
}

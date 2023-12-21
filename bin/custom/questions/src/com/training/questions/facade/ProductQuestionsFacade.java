package com.training.questions.facade;

import de.hybris.platform.commercefacades.product.data.ProductData;

public interface ProductQuestionsFacade {
    ProductData getProductDataForCode(String code);
}

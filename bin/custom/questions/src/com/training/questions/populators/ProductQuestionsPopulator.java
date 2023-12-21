package com.training.questions.populators;

import com.training.questions.data.QuestionData;
import com.training.questions.model.QuestionModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class ProductQuestionsPopulator<SOURCE extends ProductModel, TARGET extends ProductData> implements Populator<SOURCE, TARGET> {
    private final Converter<QuestionModel, QuestionData> questionConverter;

    public ProductQuestionsPopulator(Converter<QuestionModel, QuestionData> questionConverter) {
        this.questionConverter = questionConverter;
    }

    @Override
    public void populate(SOURCE productModel, TARGET productData) throws ConversionException {
        productData.setQuestions(Converters.convertAll(productModel.getQuestions(), questionConverter));
    }
}

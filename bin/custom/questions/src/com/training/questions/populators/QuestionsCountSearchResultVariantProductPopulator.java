package com.training.questions.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

public class QuestionsCountSearchResultVariantProductPopulator extends SearchResultVariantProductPopulator {
    @Override
    public void populate(final SearchResultValueData source, final ProductData target) {
        super.populate(source, target);
        target.setQuestionsCount(Integer.parseInt(getValue(source, "questionsCount").toString()));
    }
}

package com.training.questions.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultVariantProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;

import java.util.Optional;

public class WarrantyYearsSearchResultVariantProductPopulator extends SearchResultVariantProductPopulator {
    @Override
    public void populate(final SearchResultValueData source, final ProductData target) {
        super.populate(source, target);
        Optional.ofNullable(getValue(source, "warrantyYears"))
                .ifPresent(value -> target.setWarrantyYears(Integer.parseInt(value.toString())));
    }
}

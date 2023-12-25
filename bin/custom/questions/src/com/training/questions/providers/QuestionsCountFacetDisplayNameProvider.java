package com.training.questions.providers;

import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;
import org.apache.commons.lang3.StringUtils;

public class QuestionsCountFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider {
    @Override
    public String getDisplayName(SearchQuery searchQuery, IndexedProperty indexedProperty, String s) {
        if (StringUtils.isNotBlank(s)) {
            return s + " questions";
        }
        return StringUtils.EMPTY;
    }
}

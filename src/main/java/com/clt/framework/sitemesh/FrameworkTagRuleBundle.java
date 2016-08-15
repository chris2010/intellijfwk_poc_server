package com.clt.framework.sitemesh;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * Created by liujinbang on 15/9/7.
 * <p>
 * 自定义framework
 */
public class FrameworkTagRuleBundle implements TagRuleBundle {

    @Override
    public void install(State defaultState, ContentProperty contentProperty,
                        SiteMeshContext siteMeshContext) {
        defaultState.addRule("frameHeader", new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("frameHeader"), false));
    }

    @Override
    public void cleanUp(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {

    }
}

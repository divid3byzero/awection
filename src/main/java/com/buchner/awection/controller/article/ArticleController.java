package com.buchner.awection.controller.article;

import com.buchner.awection.database.ArticleFacade;
import com.buchner.awection.model.article.ArticleView;
import com.buchner.awection.model.internal.entity.Article;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ArticleController {


    @Inject
    private ArticleFacade articleFacade;

    @Inject
    private ArticleView articleView;

    public ArticleController() {

    }

    public void saveArticle() {

        Article article = articleView.getArticle();
        articleFacade.createArticle(article);
    }

}

package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.database.AuctionFacade;
import com.buchner.auction.model.core.entity.Article;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;

@Named
@ApplicationScoped
public class ImageStreamerService {

    @Inject
    private AuctionFacade auctionFacade;

    protected ImageStreamerService() {

    }

    public StreamedContent getImageStream() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String articleId =
                (String) facesContext.getExternalContext().getRequestParameterMap().get("articleId");
            Article singleArticle = auctionFacade.getSingleArticle(Integer.parseInt(articleId));
            return new DefaultStreamedContent(new ByteArrayInputStream(singleArticle.getImage()), "image/png");
        }
    }
}

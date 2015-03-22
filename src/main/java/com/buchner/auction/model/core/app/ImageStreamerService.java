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

/**
 * The image streamer is responsible for rendering an img-tag if JSF is in the
 * render response phase, or send the bytes of an image in any other phase. This
 * enables the possibilty of loading images from DB.
 */
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

            // Will render an empty img tag during render response phase
            return new DefaultStreamedContent();
        } else {

            // Send bytes in any other phase.
            String articleId =
                facesContext.getExternalContext().getRequestParameterMap().get("articleId");
            Article singleArticle = auctionFacade.getSingleArticle(Integer.parseInt(articleId));
            return new DefaultStreamedContent(new ByteArrayInputStream(singleArticle.getImage()),
                "image/png");
        }
    }
}

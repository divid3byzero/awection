package com.buchner.auction.model.core.app;

import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.Locale;

public class LocalePhaseListener implements PhaseListener {

    @Override public void afterPhase(PhaseEvent event) {

    }

    @Override public void beforePhase(PhaseEvent phaseEvent) {

        if (phaseEvent.getPhaseId() != PhaseId.RESTORE_VIEW) {
            FacesContext context = phaseEvent.getFacesContext();
            UIViewRoot viewRoot = context.getViewRoot();
            ExternalContext externalContext = context.getExternalContext();
            viewRoot.setLocale(new Locale("en"));
        }

    }

    @Override public PhaseId getPhaseId() {

        return PhaseId.ANY_PHASE;
    }
}

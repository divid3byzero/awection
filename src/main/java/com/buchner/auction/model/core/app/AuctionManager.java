package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionManager {

    private EntityManager entityManager;
    private BigDecimal dutchAuctionSubtrahent;

    public AuctionManager(EntityManager entityManager) {

        this.entityManager = entityManager;
        dutchAuctionSubtrahent = new BigDecimal("2");
    }


    public void checkAuctions() {

        checkDutchAuctions();
        checkSecondPriceAuctions();
    }

    private void checkDutchAuctions() {
        TypedQuery<Auction> namedQuery = getAuctionByTypeQuery(AuctionType.DUTCH);

        List<Auction> resultList = namedQuery.getResultList();
        for (Auction auction : resultList) {

            List<Bidder> bidder = auction.getBidder();
            if (bidder.size() > 1
                    && auction.getPrice().subtract(dutchAuctionSubtrahent).intValue() > 0) {
                auction.setPrice(
                        new BigDecimal(
                                String.valueOf(auction.getPrice().subtract(dutchAuctionSubtrahent))));
            }
        }
    }

    private void checkSecondPriceAuctions() {

        TypedQuery<Auction> auctionByTypeQuery = getAuctionByTypeQuery(AuctionType.SECOND_PRICE);
        List<Auction> secondPriceResultList = auctionByTypeQuery.getResultList();

        DateTime now = new DateTime(DateTimeZone.forID("Europe/Berlin"));
        Date nowDate = now.toDate();

        List<Auction> timeoutSecondPriceAuctions = secondPriceResultList
                .stream()
                .filter(auction -> nowDate.compareTo(auction.getEndTime()) == 1 && auction.isRunning()).collect(Collectors.toList());

        if (timeoutSecondPriceAuctions.size() > 0) {

            for (Auction auction : timeoutSecondPriceAuctions) {

                auction.setRunning(false);
                List<Bid> auctionBids = new ArrayList<>();

                List<Bidder> bidder = auction.getBidder();

                if (bidder.size() > 0) {

                    for (Bidder bidderElem : bidder) {
                        auctionBids.addAll(bidderElem.getBids());
                    }

                    if (auctionBids.size() > 0) {

                        auctionBids.sort(new BidComperator());
                        Bid winningBid = auctionBids.get(0);

                        BigDecimal priceToPay = null;
                        if (auctionBids.size() == 1) {

                            priceToPay = auction.getPrice();
                        } else {

                            priceToPay = auctionBids.get(1).getAmount();
                        }

                        long winningUserId = winningBid.getBidder().getUserId();
                        try {

                            User userById = UserLocalServiceUtil.getUserById(winningUserId);
                            AuctionResult auctionResult = new AuctionResult();
                            auctionResult.setDescription(auction.getArticle().getShortDesc());
                            auctionResult.setPrice(priceToPay);
                            auctionResult.setFirstName(userById.getFirstName());
                            auctionResult.setSurname(userById.getLastName());
                            auctionResult.setMail(userById.getEmailAddress());
                            auctionResult.setAuctionType(auction.getAuctionType());
                            entityManager.persist(auctionResult);
                        } catch (Exception e) {

                            entityManager.getTransaction().rollback();
                        }
                    }
                }
            }
        }
    }

    private TypedQuery<Auction> getAuctionByTypeQuery(AuctionType auctionType) {

        TypedQuery<Auction> namedQuery = entityManager
                .createNamedQuery("Auction.getByType",
                        Auction.class);
        namedQuery.setParameter("auctionType", auctionType);
        return namedQuery;
    }

}

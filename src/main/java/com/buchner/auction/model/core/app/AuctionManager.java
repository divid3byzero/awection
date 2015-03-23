package com.buchner.auction.model.core.app;

import com.buchner.auction.model.core.entity.*;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The auction manager is responsible for reducing the prices
 * of dutch auctions and checks if second price auctions are due.
 * It basically plays the role of an auctioneer.
 * The manager is called by a Quartz scheduler job. Since CDI is not available in Quartz
 * schedulers, needed instances of e.g. the entity manager have to be created manually which
 * violates the DRY principle a bit.
 */
public class AuctionManager {

    private EntityManager entityManager;
    private BigDecimal dutchAuctionSubtrahent;

    public AuctionManager(EntityManager entityManager) {

        this.entityManager = entityManager;
        dutchAuctionSubtrahent = new BigDecimal("2");
    }

    /**
     * The only public method. Calls the checks on both auction types.
     */
    public void checkAuctions() {

        checkDutchAuctions();
        checkSecondPriceAuctions();
    }

    /**
     * Iterates over all Dutch auctions. If an auction has more than one bidder (others don't make sense to check)
     * the price is reduced.
     */
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

    /**
     * Checks if second price auctions have timed out.
     */
    private void checkSecondPriceAuctions() {

        // Get all auctions of according type.
        TypedQuery<Auction> auctionByTypeQuery = getAuctionByTypeQuery(AuctionType.SECOND_PRICE);
        List<Auction> secondPriceResultList = auctionByTypeQuery.getResultList();

        // Get current timestamp for comparison.
        DateTime now = new DateTime(DateTimeZone.forID("Europe/Berlin"));
        Date nowDate = now.toDate();

        // Filter all auctions that have timed out.
        List<Auction> timeoutSecondPriceAuctions = secondPriceResultList
                .stream()
                .filter(auction -> nowDate.compareTo(auction.getEndTime()) == 1 && auction.isRunning()).collect(Collectors.toList());

        // Check if there are any auction that have timed out.
        if (timeoutSecondPriceAuctions.size() > 0) {

            for (Auction auction : timeoutSecondPriceAuctions) {

                // Stop auction and get its bidders.
                auction.setRunning(false);
                List<Bid> auctionBids = new ArrayList<>();
                List<Bidder> bidder = auction.getBidder();

                // Continue only if there are any bidders.
                if (bidder.size() > 0) {

                    for (Bidder bidderElem : bidder) {
                        auctionBids.addAll(bidderElem.getBids());
                    }

                    // Find winner only if registered bidders have bidden.
                    if (auctionBids.size() > 0) {

                        // Sort bids ascending according to their value.
                        auctionBids.sort(new BidComperator());
                        Bid winningBid = auctionBids.get(0);

                        // If there is only one bid the bidder has to pay the price
                        // originally entered by the article owner, which is basically the second highest
                        // amount possible.
                        BigDecimal priceToPay = null;
                        if (auctionBids.size() == 1) {
                            priceToPay = auction.getPrice();
                        } else {
                            // If there is more than one bid, the winner is still the bidder with
                            // the highest bid, but the price he has to pay is the second highest bid.
                            priceToPay = auctionBids.get(1).getAmount();
                        }

                        // Get user from Liferay to have access to user related data such as name and mail address.
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

    @SuppressWarnings("JpaQueryApiInspection")
    private TypedQuery<Auction> getAuctionByTypeQuery(AuctionType auctionType) {

        TypedQuery<Auction> namedQuery = entityManager
                .createNamedQuery("Auction.getByType",
                    Auction.class);
        namedQuery.setParameter("auctionType", auctionType);
        return namedQuery;
    }

}

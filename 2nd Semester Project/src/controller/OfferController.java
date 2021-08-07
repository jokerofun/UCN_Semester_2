package controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import Db.OfferDb;
import model.Offer;
import model.OfferEntry;

public class OfferController {

	private OfferDb db;

	public OfferController() throws DataAccessException {
		db = new OfferDb();
	}

	public Offer createOffer(Date dueDate, BigDecimal materialPrice, List<OfferEntry> offerEntries)
			throws DataAccessException {
		BigDecimal totalPrice = materialPrice;

		for (OfferEntry offerEntry : offerEntries) {
			totalPrice = totalPrice.add(offerEntry.calculatePricePerHourPerProduct());
		}

		return db.create(dueDate, materialPrice, totalPrice, offerEntries);
	}

	public boolean updateOffer(Offer offer) throws DataAccessException {
		return db.update(offer);
	}

	public boolean deleteOffer(int offerNumber) throws DataAccessException {
		return db.delete(offerNumber);
	}

	public Offer findOfferByNumber(int offerNumber) throws DataAccessException {
		return db.findByNumber(offerNumber);
	}

	public List<Offer> getAllOffers() throws DataAccessException {
		return db.getAll();
	}
}

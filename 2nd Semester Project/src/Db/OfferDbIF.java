package Db;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import controller.DataAccessException;
import model.Offer;
import model.OfferEntry;

public interface OfferDbIF {

	Offer create(Date dueDate, BigDecimal materialPrice, BigDecimal totalPrice, List<OfferEntry> offerEntries)
			throws DataAccessException;

	Offer findByNumber(int offerNumber) throws DataAccessException;

	boolean update(Offer offer) throws DataAccessException;

	boolean delete(int offerNumber) throws DataAccessException;

	List<Offer> getAll() throws DataAccessException;
}

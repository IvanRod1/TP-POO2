package sa.users;

import java.util.ArrayList;
import java.util.List;

import sa.accomodationsite.AccomodationSite;
import sa.properties.Rankeable;
import sa.properties.Review;

public abstract class User implements Rankeable {
	protected String fullName;
	protected int telephone;
	protected String mail;
	protected List<Review> reviews;
	protected AccomodationSite registeredSite;
	
	public User(String fullName, int telephone, String mail,AccomodationSite site) {
		this.fullName = fullName;
		this.telephone = telephone;
		this.mail = mail;
		this.reviews = new ArrayList<Review>();
		this.registeredSite = site;
	}
	
	public List<Review> getReviews() {
		return this.reviews;
	}
	
	public abstract void summary();
	
	@Override
	public double getRank() {
		
		
		/**
		 * acá lo mismo que en property.
		 * 
		 * 
		 * */
		return this.getReviews().stream()
								.mapToInt(rewiew -> rewiew.getRating())
								.average()
								.orElse(0.0);

		
	}
}

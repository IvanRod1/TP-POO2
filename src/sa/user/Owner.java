package sa.user;

import java.util.ArrayList;
import java.util.List;

import sa.properies.Property;

public class Owner extends User {
	
	private List<Property> properties;
	
	public Owner(String fullName, int telephone, String mail) {
	
		super(fullName, telephone, mail);
		
		this.properties = new ArrayList<Property>();
	}
	

	@Override
	public void summary() {
		// TODO Auto-generated method stub
		/**
		 * aca tambien queda vacío
		 */
		
	}
	
	

}

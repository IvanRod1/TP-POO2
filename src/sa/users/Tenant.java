package sa.users;

import sa.booking.Reserve;

public class Tenant extends User {
	
	public Tenant(String fullName, int telephone, String mail) {
		
		super(fullName, telephone, mail);
	}

	@Override
	public void summary() {
		// TODO Auto-generated method stub
		/**
		 * 
		 * * hacer lo mismo que en property 
		 */
		System.out.println("Tenant Summary:");
	    System.out.println("name: " + this.fullName);
	    System.out.println("telephone: " + this.telephone);
	    System.out.println("mail: " + this.mail);
		
	}	

	public void reserveDeclined(Reserve reserve) {
		// recibe el rechazo de una reserva
		// TODO: El enunciado no dice nada respecto a los rechazos.
	}
}

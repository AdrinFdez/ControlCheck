
package acme.features.patron.creditCard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.banners.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/patron/credit-card/")
public class PatronCreditCardController extends AbstractController<Patron, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private PatronCreditCardShowService		showService;

	@Autowired
	private PatronCreditCardListService		listService;

	@Autowired
	private PatronCreditCardCreateService	createService;

	@Autowired
	private PatronCreditCardUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}

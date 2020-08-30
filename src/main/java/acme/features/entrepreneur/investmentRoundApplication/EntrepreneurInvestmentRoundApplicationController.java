
package acme.features.entrepreneur.investmentRoundApplication;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/investment-round-application/")
public class EntrepreneurInvestmentRoundApplicationController extends AbstractController<Entrepreneur, InvestmentRoundApplication> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurInvestmentRoundApplicationListService			listService;

	@Autowired
	private EntrepreneurInvestmentRoundApplicationShowService			showService;

	@Autowired
	private EntrepreneurInvestmentRoundApplicationListByTickerService	listTickerService;

	@Autowired
	private EntrepreneurInvestmentRoundApplicationListByCreationService	listCreationService;

	@Autowired
	private EntrepreneurInvestmentRoundApplicationAcceptService			acceptService;

	@Autowired
	private EntrepreneurInvestmentRoundApplicationRejectService			rejectService;

	@Autowired
	private EntrepreneurInvestmentRoundApplicationCheckService			checkedService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_TICKER, BasicCommand.LIST, this.listTickerService);
		super.addCustomCommand(CustomCommand.LIST_CREATIONMOMENT, BasicCommand.LIST, this.listCreationService);
		super.addCustomCommand(CustomCommand.ACCEPT, BasicCommand.UPDATE, this.acceptService);
		super.addCustomCommand(CustomCommand.REJECT, BasicCommand.UPDATE, this.rejectService);
		super.addCustomCommand(CustomCommand.CHECK, BasicCommand.SHOW, this.checkedService);
	}

}

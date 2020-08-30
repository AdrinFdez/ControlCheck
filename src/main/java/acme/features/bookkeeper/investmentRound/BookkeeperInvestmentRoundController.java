
package acme.features.bookkeeper.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/investment-round/")
public class BookkeeperInvestmentRoundController extends AbstractController<Bookkeeper, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BookkeeperInvestmentRoundShowService				showService;

	@Autowired
	private BookkeeperInvestmentRoundAccountingListService		listAccountingService;

	@Autowired
	private BookkeeperInvestmentRoundNonAccountingListService	listNonAccountingService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_ACCOUNTING_INVESTMENT_ROUND, BasicCommand.LIST, this.listAccountingService);
		super.addCustomCommand(CustomCommand.LIST_NON_ACCOUNTING_INVESTMENT_ROUND, BasicCommand.LIST, this.listNonAccountingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}


package acme.features.authenticated.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRounds.InvestmentRound;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/investment-round/")
public class AuthenticatedInvestmentRoundController extends AbstractController<Authenticated, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedInvestmentRoundListService		listService;

	@Autowired
	private AuthenticatedInvestmentRoundShowService		showService;

	@Autowired
	private AuthenticatedInvestmentRoundListMineService	listMineInvestorService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE_INVESTOR, BasicCommand.LIST, this.listMineInvestorService);
	}

}

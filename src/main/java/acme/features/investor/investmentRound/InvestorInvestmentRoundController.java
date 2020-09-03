
package acme.features.investor.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/investment-round/")
public class InvestorInvestmentRoundController extends AbstractController<Investor, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InvestorInvestmentRoundListService		listService;

	@Autowired
	private InvestorInvestmentRoundShowService		showService;

	@Autowired
	private InvestorInvestmentRoundListMineService	listMineService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
	}

}

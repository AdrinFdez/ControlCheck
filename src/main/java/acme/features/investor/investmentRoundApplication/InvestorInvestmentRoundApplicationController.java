
package acme.features.investor.investmentRoundApplication;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.roles.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/investment-round-application/")
public class InvestorInvestmentRoundApplicationController extends AbstractController<Investor, InvestmentRoundApplication> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private InvestorInvestmentRoundApplicationListMineService	listService;

	@Autowired
	private InvestorInvestmentRoundApplicationShowService		showService;

	@Autowired
	private InvestorInvestmentRoundApplicationCreateService		createService;

	@Autowired
	private InvestorInvestmentRoundApplicationDeleteService		deleteService;

	@Autowired
	private InvestorInvestmentRoundApplicationUpdateService		updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}

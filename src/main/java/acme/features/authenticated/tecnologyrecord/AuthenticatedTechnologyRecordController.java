
package acme.features.authenticated.tecnologyrecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/technology-record/")
public class AuthenticatedTechnologyRecordController extends AbstractController<Authenticated, TechnologyRecord> {

	// Internal state ---------------------------------------------------

	@Autowired
	private AuthenticatedTechnologyRecordListService			listService;

	@Autowired
	private AuthenticatedTechnologyRecordListBySectorService	listBySectorService;

	@Autowired
	private AuthenticatedTechnologyRecordListByRatingService	listByRatingService;

	@Autowired
	private AuthenticatedTechnologyRecordShowService			showService;


	// Constructors -----------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_SECTOR, BasicCommand.LIST, this.listBySectorService);
		super.addCustomCommand(CustomCommand.LIST_RATING, BasicCommand.LIST, this.listByRatingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}

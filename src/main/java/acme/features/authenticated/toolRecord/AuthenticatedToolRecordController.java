
package acme.features.authenticated.toolRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.toolRecords.ToolRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/tool-record/")
public class AuthenticatedToolRecordController extends AbstractController<Authenticated, ToolRecord> {

	// Internal state ---------------------------------------------------

	@Autowired
	private AuthenticatedToolRecordListService			listService;

	@Autowired
	private AuthenticatedToolRecordListBySectorService	listBySectorService;

	@Autowired
	private AuthenticatedToolRecordListByRatingService	listByRatingService;

	@Autowired
	private AuthenticatedToolRecordShowService			showService;


	// Constructors -----------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_SECTOR, BasicCommand.LIST, this.listBySectorService);
		super.addCustomCommand(CustomCommand.LIST_RATING, BasicCommand.LIST, this.listByRatingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}

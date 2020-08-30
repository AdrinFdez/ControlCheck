
package acme.features.anonymous.toolRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.toolRecords.ToolRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/tool-record/")
public class AnonymousToolRecordController extends AbstractController<Anonymous, ToolRecord> {

	// Internal state ---------------------------------------------------

	@Autowired
	private AnonymousToolRecordListService			listService;

	@Autowired
	private AnonymousToolRecordListBySectorService	listBySectorService;

	@Autowired
	private AnonymousToolRecordListByRatingService	listByRatingService;

	@Autowired
	private AnonymousToolRecordShowService			showService;


	// Constructors -----------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_SECTOR, BasicCommand.LIST, this.listBySectorService);
		super.addCustomCommand(CustomCommand.LIST_RATING, BasicCommand.LIST, this.listByRatingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}

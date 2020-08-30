
package acme.features.anonymous.technologyrecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/technology-record/")
public class AnonymousTechnologyRecordController extends AbstractController<Anonymous, TechnologyRecord> {

	// Internal state ---------------------------------------------------

	@Autowired
	private AnonymousTechnologyRecordListService			listService;

	@Autowired
	private AnonymousTechnologyRecordListBySectorService	listBySectorService;

	@Autowired
	private AnonymousTechnologyRecordListByRatingService	listByRatingService;

	@Autowired
	private AnonymousTechnologyRecordShowService			showService;


	// Constructors -----------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_SECTOR, BasicCommand.LIST, this.listBySectorService);
		super.addCustomCommand(CustomCommand.LIST_RATING, BasicCommand.LIST, this.listByRatingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}


package acme.features.administrator.customParams;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.customParams.CustomParams;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/custom-params/")
public class AdministratorCustomParamsController extends AbstractController<Administrator, CustomParams> {

	@Autowired
	AdministratorCustomParamsShowService	showService;

	@Autowired
	AdministratorCustomParamsUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}

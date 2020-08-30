
package acme.features.investor.investmentRoundApplication;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class InvestorInvestmentRoundApplicationListMineService implements AbstractListService<Investor, InvestmentRoundApplication> {

	@Autowired
	InvestorInvestmentRoundApplicationRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRoundApplication> request, final InvestmentRoundApplication entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "offer", "statement");
	}

	@Override
	public Collection<InvestmentRoundApplication> findMany(final Request<InvestmentRoundApplication> request) {
		assert request != null;
		Collection<InvestmentRoundApplication> res;
		int idUserAccount = request.getPrincipal().getAccountId();
		int idInvestor = this.repository.findInvestorId(idUserAccount);
		res = this.repository.findManyMine(idInvestor);
		return res;
	}

}

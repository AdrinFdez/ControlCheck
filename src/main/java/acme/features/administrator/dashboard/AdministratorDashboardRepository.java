
package acme.features.administrator.dashboard;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(s) from Notice s")
	Integer totalNumberOfNotices();
	@Query("select count (s) from TechnologyRecord s")
	Integer totalNumberOfTechnologyRecords();
	@Query("select count (s) from ToolRecord s")
	Integer totalNumberOfToolRecords();

	@Query("select min(s.moneyMin.amount),max(s.moneyMin.amount),avg(s.moneyMin.amount),stddev(s.moneyMin.amount) from Inquiry s where s.deadline > CURRENT_DATE ")
	Double[][] MinMaxAvgStdFromInquires1();

	@Query("select min(s.moneyMax.amount),max(s.moneyMax.amount),avg(s.moneyMax.amount),stddev(s.moneyMax.amount) from Inquiry s where s.deadline > CURRENT_DATE ")
	Double[][] MinMaxAvgStdFromInquires2();

	@Query("select min(s.moneyMin.amount),max(s.moneyMin.amount),avg(s.moneyMin.amount),stddev(s.moneyMin.amount) from Overture s where s.deadline > CURRENT_DATE ")
	Double[][] MinMaxAvgStdFromOvertures1();

	@Query("select min(s.moneyMax.amount),max(s.moneyMax.amount),avg(s.moneyMax.amount),stddev(s.moneyMax.amount) from Overture s where s.deadline > CURRENT_DATE ")
	Double[][] MinMaxAvgStdFromOvertures2();

	@Query("select s.openSource,100.0 * count(s) / (select count(b) from TechnologyRecord b) from TechnologyRecord s group by s.openSource")
	List<List<String>> getRatioOfTechnologiesPerSource();

	@Query("select s.sector,count(s) from TechnologyRecord s group by s.sector")
	List<List<String>> getNumberOfTechnologiesBySector();

	@Query("select s.openSource,100.0 * count(s) / (select count(b) from ToolRecord b) from ToolRecord s group by s.openSource")
	List<List<String>> getRatioOfToolsPerSource();

	@Query("select s.sector,count(s) from ToolRecord s group by s.sector")
	List<List<String>> getNumberOfToolsBySector();

	//list:
	//average number of investment rounds per Entrepreneur

	@Query("select avg(select count(i) from InvestmentRound i where i.entrepreneur.id = e.id) from Entrepreneur e")
	Double avgInvestmentRoundPerEntrepreneur();

	//average number of applications per Entrepreneur

	@Query("select avg(select count(i) from InvestmentRoundApplication i where i.investmentRound.entrepreneur.id = e.id) from Entrepreneur e")
	Double avgInvestmentRoundApplicationPerEntrepreneur();

	//Double avgApplicationsPerEntrepreneur();
	//average number of applications per investor

	@Query("select avg(select count(i) from InvestmentRoundApplication i where i.investor.id = v.id) from Investor v")
	Double avgInvestmentRoundApplicationPerInvestor();

	//Double avgApplicationsPerInvestor();

	//chart:
	//ratio of investment rounds grouped by kind

	@Query("select i.kind,100.0 * count(i) / (select count(k) from InvestmentRound k) from InvestmentRound i group by i.kind")
	List<List<String>> getRatioOfInvestmentRoundByKind();
	//ratio of applications grouped by status
	@Query("select i.statement,100.0 * count(i) / (select count(s) from InvestmentRoundApplication s) from InvestmentRoundApplication i group by i.statement")
	List<List<String>> getRatioOfInvestmentRoundApplicationByStatus();

	//Time Series
	@Query("select a from InvestmentRoundApplication a where a.updateMoment > ?1 and a.statement ='accepted' ")
	List<InvestmentRoundApplication> findAccepted(Date ldt);

	@Query("select a from InvestmentRoundApplication a where a.updateMoment > ?1 and a.statement ='rejected' ")
	List<InvestmentRoundApplication> findRejected(Date ldt);

	@Query("select a from InvestmentRoundApplication a")
	List<InvestmentRoundApplication> findPending();

	@Query("select 100.0 * count(i) / (select count(k) from InvestmentRound k) from InvestmentRound i where i.yomp != null and i.yomp != ''")
	Double getRatioOfInvestmentRoundByYomp();

	@Query("select 100.0 * count(i) / (select count(s) from InvestmentRoundApplication s) from InvestmentRoundApplication i where i.link != null and i.link != ''")
	Double getRatioOfInvestmentRoundApplicationByLink();

	@Query("select 100.0 * count(i) / (select count(s) from InvestmentRoundApplication s) from InvestmentRoundApplication i where i.pass != null and i.pass != ''")
	Double getRatioOfInvestmentRoundApplicationByPassword();

}

<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<!-- ANONYMOUS -->
		<!-- Anonymous Menu -->
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<!-- Notice -->
			<acme:menu-suboption code="master.menu.anonymous.list.notice" action="/anonymous/notice/list"/>
			<acme:menu-separator/> <!-- Technology Records -->
			<acme:menu-suboption code="master.menu.anonymous.list.technologyrecord" action="/anonymous/technology-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.list.technologyrecord.bysector" action="/anonymous/technology-record/list-sector"/>
			<acme:menu-suboption code="master.menu.anonymous.list.technologyrecord.byrating" action="/anonymous/technology-record/list-rating"/>
			<acme:menu-separator/> <!-- Tool Records -->
			<acme:menu-suboption code="master.menu.anonymous.list.toolrecord" action="/anonymous/tool-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.list.toolrecord.bysector" action="/anonymous/tool-record/list-sector"/>
			<acme:menu-suboption code="master.menu.anonymous.list.toolrecord.byrating" action="/anonymous/tool-record/list-rating"/>
		</acme:menu-option>
		<!-- Links -->
		<acme:menu-option code="master.menu.anonymous.links" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.fernandez-link" action="https://github.com/AdrinFdez"/>
			<acme:menu-suboption code="master.menu.anonymous.rueda-link" action="https://github.com/danruelop"/>
			<acme:menu-suboption code="master.menu.anonymous.cruz-link" action="https://github.com/CarlosXCruz"/>
			<acme:menu-suboption code="master.menu.anonymous.cadenas-link" action="https://github.com/marcadsan2"/>
			<acme:menu-suboption code="master.menu.anonymous.pinero-link" action="https://github.com/marpinpas"/>		
		</acme:menu-option>
		<!-- Bulletins -->
		<acme:menu-option code="master.menu.anonymous.bulletins" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.list.ruedabulletin" action="/anonymous/rueda-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.create.ruedabulletin" action="/anonymous/rueda-bulletin/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.list.fernandezbulletin" action="/anonymous/fernandez-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.create.fernandezbulletin" action="/anonymous/fernandez-bulletin/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.list.cadenasbulletin" action="/anonymous/cadenas-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.create.cadenasbulletin" action="/anonymous/cadenas-bulletin/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.list.cruzbulletin" action="/anonymous/cruz-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.create.cruzbulletin" action="/anonymous/cruz-bulletin/create"/>
    		<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.list.pinerobulletin" action="/anonymous/pinero-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.create.pinerobulletin" action="/anonymous/pinero-bulletin/create"/>
		</acme:menu-option>
		
		<!-- AUTHENTICATED && !(hasRole('Administrator'))-->
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated() ">
		<!-- Notice -->
			<acme:menu-suboption code="master.menu.authenticated.list.notice" action="/authenticated/notice/list"/>
		<acme:menu-separator/> <!-- Technology Records -->
			<acme:menu-suboption code="master.menu.authenticated.list.technologyrecord" action="/authenticated/technology-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.list.technologyrecord.bysector" action="/authenticated/technology-record/list-sector"/>
			<acme:menu-suboption code="master.menu.authenticated.list.technologyrecord.byrating" action="/authenticated/technology-record/list-rating"/>
		<acme:menu-separator/> <!-- Tool Records -->
			<acme:menu-suboption code="master.menu.authenticated.list.toolrecord" action="/authenticated/tool-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.list.toolrecord.bysector" action="/authenticated/tool-record/list-sector"/>
			<acme:menu-suboption code="master.menu.authenticated.list.toolrecord.byrating" action="/authenticated/tool-record/list-rating"/>
		<acme:menu-separator/> <!-- Inquiries -->
      		<acme:menu-suboption code="master.menu.authenticated.inquiry.list" action="/authenticated/inquiry/list"/>
		<acme:menu-separator/> <!-- Overture -->
      		<acme:menu-suboption code="master.menu.authenticated.overture.list" action="/authenticated/overture/list"/>
		<acme:menu-separator/> <!-- Challenge -->
			<acme:menu-suboption code="master.menu.authenticated.list.challenge" action="/authenticated/challenge/list"/>
		<acme:menu-separator/> <!-- InvestmentRound -->
			<acme:menu-suboption code="master.menu.authenticated.investmentRound.list" action="/authenticated/investment-round/list"/>
		<acme:menu-separator/> <!-- DiscussionForum involved-->
			<acme:menu-suboption code="master.menu.authenticated.discussionforum.listmine" action="/authenticated/discussion-forum/list-mine"/>
		</acme:menu-option>
		
		<!-- Entrepreneur -->
		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<acme:menu-suboption code="master.menu.entrepreneur.investmentRound.list" action="/entrepreneur/investment-round/list_mine"/>
			<acme:menu-suboption code="master.menu.entrepreneur.investmentRound.create" action="/entrepreneur/investment-round/create"/>
			<acme:menu-suboption code="master.menu.entrepreneur.investmentRound.listTicker" action="/entrepreneur/investment-round-application/list_ticker"/>
			<acme:menu-suboption code="master.menu.entrepreneur.investmentRound.listCreationMoment" action="/entrepreneur/investment-round-application/list_creationmoment"/>
		</acme:menu-option>
		
		<!-- INVESTOR -->
		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.investmentRound.list" action="/investor/investment-round/list"/>
			<acme:menu-suboption code="master.menu.investor.investmentRound.listmine" action="/authenticated/investment-round/list_mine_investor"/>
			<acme:menu-suboption code="master.menu.investor.investmentRoundApplication.list" action="/investor/investment-round-application/list_mine"/>
		</acme:menu-option>

		<!-- BOOKKEEPER -->
		<acme:menu-option code="master.menu.bookkeeper" access="hasRole('Bookkeeper')">
			<acme:menu-suboption code="master.menu.bookkeeper.listAccountingInvestment" action="/bookkeeper/investment-round/list_accounting_investment_round"/>
			<acme:menu-suboption code="master.menu.bookkeeper.listNonAccountingInvestment" action="/bookkeeper/investment-round/list_non_accounting_investment_round"/>
		</acme:menu-option>
				 
		
		<!-- PATRON -->
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.banner.list" action="/patron/banner/list"/>
			<acme:menu-suboption code="master.menu.patron.banner.create" action="/patron/banner/create"/>
			<acme:menu-suboption code="master.menu.patron.creditCard.list" action="/patron/credit-card/list"/>
			<acme:menu-suboption code="master.menu.patron.creditCard.create" action="/patron/credit-card/create"/>
		</acme:menu-option>
        
        
		<!-- ADMINISTRATOR -->
		<acme:menu-option code="master.menu.administrator.feature" access="hasRole('Administrator')">
			<!-- Notice -->
			<acme:menu-suboption code="master.menu.administrator.list.notice" action="/administrator/notice/list"/>
			<acme:menu-suboption code="master.menu.administrator.create.notice" action="/administrator/notice/create"/>
			<acme:menu-separator/> <!-- Technology Records -->
			<acme:menu-suboption code="master.menu.administrator.list.technologyrecord" action="/administrator/technology-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.create.technologyrecord" action="/administrator/technology-record/create"/>
			<acme:menu-separator/> <!-- Tool Records -->
			<acme:menu-suboption code="master.menu.administrator.list.toolrecord" action="/administrator/tool-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.create.toolrecord" action="/administrator/tool-record/create"/>
			<acme:menu-separator/> <!-- Inquiries -->
			<acme:menu-suboption code="master.menu.administrator.inquiry.list" action="/administrator/inquiry/list"/>
			<acme:menu-suboption code="master.menu.administrator.inquiry.create" action="/administrator/inquiry/create"/>
			<acme:menu-separator/> <!-- Overture -->
			<acme:menu-suboption code="master.menu.administrator.overture.list" action="/administrator/overture/list"/>
			<acme:menu-suboption code="master.menu.administrator.overture.create" action="/administrator/overture/create"/>
			<acme:menu-separator/> <!-- Challenge -->
			<acme:menu-suboption code="master.menu.administrator.challenge.list" action="/administrator/challenge/list"/>
 			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create"/>
			<acme:menu-separator/> <!-- CreditCard -->
			<acme:menu-suboption code="master.menu.administrator.creditCard.list" action="/administrator/credit-card/list"/>
			<acme:menu-suboption code="master.menu.administrator.creditCard.create" action="/administrator/credit-card/create"/>
			<acme:menu-separator/> <!-- Banners -->
			<acme:menu-suboption code="master.menu.administrator.banner.list" action="/administrator/banner/list"/>
			<acme:menu-suboption code="master.menu.administrator.banner.create" action="/administrator/banner/create"/>
			<acme:menu-separator/> <!-- Bookkeeper Request -->
			<acme:menu-suboption code="master.menu.administrator.bookkeeperRequest.list" action="/administrator/bookkeeper-request/list"/>
		</acme:menu-option>
		
		<!-- Admin Display -->
		<acme:menu-option code="master.menu.administrator.adminDisplay" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.customParams" action="/administrator/custom-params/show"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/list"/>
		</acme:menu-option>
		
	</acme:menu-left>




	<acme:menu-right>
		
		<!-- Admin Menu -->
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create" access="!hasRole('Investor')"/>
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update" access="hasRole('Investor')"/>
			
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create" access="!hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update" access="hasRole('Entrepreneur')"/>
			
			<acme:menu-suboption code="master.menu.user-account.become-bookkeeper" action="/authenticated/bookkeeper-request/create" access="!hasRole('Bookkeeper')"/>
			<acme:menu-suboption code="master.menu.user-account.bookkeeper" action="/authenticated/bookkeeper/update" access="hasRole('Bookkeeper')"/>
			
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>


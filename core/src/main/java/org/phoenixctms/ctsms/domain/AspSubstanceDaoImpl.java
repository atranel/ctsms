// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.

/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.phoenixctms.ctsms.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.phoenixctms.ctsms.query.CategoryCriterion;
import org.phoenixctms.ctsms.query.CriteriaUtil;
import org.phoenixctms.ctsms.util.DefaultSettings;
import org.phoenixctms.ctsms.util.SettingCodes;
import org.phoenixctms.ctsms.util.Settings;
import org.phoenixctms.ctsms.util.Settings.Bundle;
import org.phoenixctms.ctsms.vo.AspSubstanceVO;

/**
 * @see AspSubstance
 */
public class AspSubstanceDaoImpl
extends AspSubstanceDaoBase
{

	private final static boolean MATCH_ATC_CODE_CODE = true;
	private final static boolean MATCH_ASP_NAME = true;
	private final static boolean MATCH_ASP_REGISTRATION_NUMBER = true;

	private static void applyAspSubstanceNameCriterions(org.hibernate.Criteria aspSubstanceCriteria, String nameInfix) {
		String revision =  Settings.getString(SettingCodes.ASP_REVISION, Bundle.SETTINGS, DefaultSettings.ASP_REVISION);
		ArrayList<CategoryCriterion> criterions = new ArrayList<CategoryCriterion>();
		criterions.add(new CategoryCriterion(nameInfix, "name", MatchMode.ANYWHERE));
		if (MATCH_ASP_NAME || MATCH_ASP_REGISTRATION_NUMBER || MATCH_ATC_CODE_CODE) {
			org.hibernate.Criteria aspsCriteria = aspSubstanceCriteria.createCriteria("asps", "asps0", CriteriaSpecification.LEFT_JOIN);
			if (MATCH_ASP_NAME) {
				criterions.add(new CategoryCriterion(nameInfix, "asps0.name", MatchMode.ANYWHERE));
			}
			if (MATCH_ASP_REGISTRATION_NUMBER) {
				criterions.add(new CategoryCriterion(nameInfix, "asps0.registrationNumber", MatchMode.EXACT));
			}
			aspsCriteria.add(Restrictions.eq("revision", revision));
			if (MATCH_ATC_CODE_CODE) {
				org.hibernate.Criteria atcCodesCriteria = aspsCriteria.createCriteria("atcCodes", "atcCodes0", CriteriaSpecification.LEFT_JOIN);
				atcCodesCriteria.add(Restrictions.eq("revision", revision));
				criterions.add(new CategoryCriterion(nameInfix, "atcCodes0.code", MatchMode.EXACT));
			}
		}

		CategoryCriterion.applyOr(aspSubstanceCriteria, criterions);
		aspSubstanceCriteria.add(Restrictions.eq("revision", revision));

	}


	/**
	 * @inheritDoc
	 */
	public AspSubstance aspSubstanceVOToEntity(AspSubstanceVO aspSubstanceVO)
	{
		AspSubstance entity = this.loadAspSubstanceFromAspSubstanceVO(aspSubstanceVO);
		this.aspSubstanceVOToEntity(aspSubstanceVO, entity, true);
		return entity;
	}


	/**
	 * @inheritDoc
	 */
	public void aspSubstanceVOToEntity(
			AspSubstanceVO source,
			AspSubstance target,
			boolean copyIfNull)
	{
		super.aspSubstanceVOToEntity(source, target, copyIfNull);
	}


	private org.hibernate.Criteria createAspSubstanceCriteria(boolean cacheable) {
		org.hibernate.Criteria aspSubstanceCriteria = this.getSession().createCriteria(AspSubstance.class);
		if (cacheable) {
			aspSubstanceCriteria.setCacheable(true);
		}
		return aspSubstanceCriteria;
	}


	@Override
	protected Collection<String> handleFindAspSubstanceNames(String nameInfix, Integer limit) {
		org.hibernate.Criteria aspSubstanceCriteria = createAspSubstanceCriteria(true);
		applyAspSubstanceNameCriterions(aspSubstanceCriteria, nameInfix);
		aspSubstanceCriteria.add(Restrictions.not(Restrictions.or(Restrictions.eq("name", ""), Restrictions.isNull("name"))));
		aspSubstanceCriteria.addOrder(Order.asc("name"));
		aspSubstanceCriteria.setProjection(Projections.distinct(Projections.property("name")));
		CriteriaUtil.applyLimit(limit, Settings.getIntNullable(SettingCodes.ASP_SUBSTANCE_NAME_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT, Bundle.SETTINGS,
				DefaultSettings.ASP_SUBSTANCE_NAME_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT), aspSubstanceCriteria);
		return aspSubstanceCriteria.list();
	}

	@Override
	protected Collection<AspSubstance> handleFindAspSubstances(String nameInfix, Integer limit) throws Exception
	{
		org.hibernate.Criteria aspSubstanceCriteria = createAspSubstanceCriteria(true);
		applyAspSubstanceNameCriterions(aspSubstanceCriteria, nameInfix);
		aspSubstanceCriteria.addOrder(Order.asc("name"));
		CriteriaUtil.applyLimit(limit,
				Settings.getIntNullable(SettingCodes.ASP_SUBSTANCE_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT, Bundle.SETTINGS,
						DefaultSettings.ASP_SUBSTANCE_AUTOCOMPLETE_DEFAULT_RESULT_LIMIT),
						aspSubstanceCriteria);
		if (MATCH_ASP_NAME || MATCH_ASP_REGISTRATION_NUMBER || MATCH_ATC_CODE_CODE) {
			// ProjectionList projectionList = Projections.projectionList().add(Projections.id());
			// projectionList.add(Projections.property("name"));
			// List aspSubstances = aspSubstanceCriteria.setProjection(Projections.distinct(projectionList)).list();
			// Iterator it = aspSubstances.iterator();
			// ArrayList result = new ArrayList(aspSubstances.size());
			// while (it.hasNext()) {
			// result.add(this.load((Long) ((Object[]) it.next())[0]));
			// }
			// return result;
			return CriteriaUtil.listDistinctRoot(aspSubstanceCriteria, this, "name");
		} else {
			return aspSubstanceCriteria.list();
		}
	}

	@Override
	protected long handleGetMedicationCount(String revision) throws Exception
	{
		org.hibernate.Criteria aspSubstanceCriteria = createAspSubstanceCriteria(false);
		aspSubstanceCriteria.add(Restrictions.eq("revision", revision));
		aspSubstanceCriteria.createCriteria("medications", CriteriaSpecification.INNER_JOIN);
		return (Long) aspSubstanceCriteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private AspSubstance loadAspSubstanceFromAspSubstanceVO(AspSubstanceVO aspSubstanceVO)
	{
		AspSubstance aspSubstance = null;
		Long id = aspSubstanceVO.getId();
		if (id != null) {
			aspSubstance = this.load(id);
		}
		if (aspSubstance == null)
		{
			aspSubstance = AspSubstance.Factory.newInstance();
		}
		return aspSubstance;
	}

	/**
	 * @inheritDoc
	 */
	public AspSubstanceVO toAspSubstanceVO(final AspSubstance entity)
	{
		return super.toAspSubstanceVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	public void toAspSubstanceVO(
			AspSubstance source,
			AspSubstanceVO target)
	{
		super.toAspSubstanceVO(source, target);
	}
}
// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.phoenixctms.ctsms.domain;

import java.util.Iterator;

import org.phoenixctms.ctsms.util.CoreUtil;
import org.phoenixctms.ctsms.util.L10nUtil;
import org.phoenixctms.ctsms.util.L10nUtil.Locales;
import org.phoenixctms.ctsms.vo.NotificationTypeVO;

/**
 * @see NotificationType
 */
public class NotificationTypeDaoImpl
		extends NotificationTypeDaoBase
{

	/**
	 * Retrieves the entity object that is associated with the specified value object
	 * from the object store. If no such entity object exists in the object store,
	 * a new, blank entity is created
	 */
	private NotificationType loadNotificationTypeFromNotificationTypeVO(NotificationTypeVO notificationTypeVO)
	{
		// TODO implement loadNotificationTypeFromNotificationTypeVO
		throw new UnsupportedOperationException("org.phoenixctms.ctsms.domain.loadNotificationTypeFromNotificationTypeVO(NotificationTypeVO) not yet implemented.");
		// NotificationType notificationType = null;
		// Long id = notificationTypeVO.getId();
		// if ( id != null) {
		// notificationType = this.load(id);
		// }
		// if (notificationType == null)
		// {
		// notificationType = NotificationType.Factory.newInstance();
		// }
		// return notificationType;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public NotificationType notificationTypeVOToEntity(NotificationTypeVO notificationTypeVO)
	{
		NotificationType entity = this.loadNotificationTypeFromNotificationTypeVO(notificationTypeVO);
		this.notificationTypeVOToEntity(notificationTypeVO, entity, true);
		return entity;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void notificationTypeVOToEntity(
			NotificationTypeVO source,
			NotificationType target,
			boolean copyIfNull)
	{
		super.notificationTypeVOToEntity(source, target, copyIfNull);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public NotificationTypeVO toNotificationTypeVO(final NotificationType entity)
	{
		return super.toNotificationTypeVO(entity);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void toNotificationTypeVO(
			NotificationType source,
			NotificationTypeVO target)
	{
		super.toNotificationTypeVO(source, target);
		boolean sendToIdentityStaffCategory = false;
		User user = CoreUtil.getUser();
		if (user != null) {
			Staff identity = user.getIdentity();
			if (identity != null) {
				StaffCategory staffCategory = identity.getCategory();
				Iterator<StaffCategory> it = source.getSendDepartmentStaffCategories().iterator();
				while (it.hasNext()) {
					if (it.next().equals(staffCategory)) {
						sendToIdentityStaffCategory = true;
						break;
					}
				}
			}
		}
		target.setSendToIdentityStaffCategory(sendToIdentityStaffCategory);
		target.setName(L10nUtil.getNotificationTypeName(Locales.USER, source.getNameL10nKey()));
	}
}
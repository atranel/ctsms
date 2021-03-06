package org.phoenixctms.ctsms.web.model.trial;

import java.util.ArrayList;
import java.util.Collection;

import org.phoenixctms.ctsms.exception.AuthenticationException;
import org.phoenixctms.ctsms.exception.AuthorisationException;
import org.phoenixctms.ctsms.exception.ServiceException;
import org.phoenixctms.ctsms.vo.InventoryBookingOutVO;
import org.phoenixctms.ctsms.vo.PSFVO;
import org.phoenixctms.ctsms.web.model.LazyDataModelBase;
import org.phoenixctms.ctsms.web.util.WebUtil;

public class TrialInventoryBookingLazyModel extends LazyDataModelBase {

	private Long trialId;

	@Override
	protected Collection<InventoryBookingOutVO> getLazyResult(PSFVO psf) {
		if (trialId != null) {
			try {
				return WebUtil.getServiceLocator().getTrialService().getTrialInventoryBookingList(WebUtil.getAuthentication(), trialId, psf);
			} catch (ServiceException e) {
			} catch (AuthenticationException e) {
				WebUtil.publishException(e);
			} catch (AuthorisationException e) {
			} catch (IllegalArgumentException e) {
			}
		}
		return new ArrayList<InventoryBookingOutVO>();
	}

	@Override
	protected InventoryBookingOutVO getRowElement(Long id) {
		return WebUtil.getInventoryBooking(id);
	}

	public Long getTrialId() {
		return trialId;
	}

	public void setTrialId(Long trialId) {
		this.trialId = trialId;
	}
}

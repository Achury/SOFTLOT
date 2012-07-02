package com.lotrading.softlot.businessPartners.logic.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lotrading.softlot.businessPartners.dao.IPartnerContactDao;
import com.lotrading.softlot.businessPartners.entity.PartnerContact;
import com.lotrading.softlot.businessPartners.entity.Phone;
import com.lotrading.softlot.businessPartners.logic.IAddressLogic;
import com.lotrading.softlot.businessPartners.logic.IPartnerContactLogic;
import com.lotrading.softlot.businessPartners.logic.IPhoneLogic;


/**
 * @author JUAN DAVID URIBE
 * @version 1.0
 * @created 01-abr-2011 10:59:59 a.m.
 */
public class PartnerContactLogicImpl implements IPartnerContactLogic {

	private IPartnerContactDao partnerContactDao;	
	private IAddressLogic addressLogic;
	private IPhoneLogic phoneLogic;
	private Log log = LogFactory.getLog(PartnerContactLogicImpl.class);	
	

	public PartnerContactLogicImpl(){

	}

	/**
	 * 
	 * @param partnerContact
	 */
	public int savePartnerContact(PartnerContact partnerContact)throws Exception{
		int _tmpContactId = 0;
		try {
			if (partnerContact == null)
				return _tmpContactId;
			
			if (partnerContact.getAddress() != null){					
				partnerContact.getAddress().setAddressId( addressLogic.saveAddress(partnerContact.getAddress()));				
			}
			// get the Group or department information ID
			partnerContact.setPartnerDeptInfoId(partnerContactDao.getDeptInfoID(partnerContact));
						
			if (partnerContact.getContactId() <= 0) {				
				partnerContact.setEnteredDate(new Date());				
				_tmpContactId = partnerContactDao.createContact(partnerContact);
				
			} else if (partnerContact.getContactId() > 0) {				
				if (partnerContactDao.updatePartnerContact(partnerContact)){
					_tmpContactId = partnerContact.getContactId();
				}
				
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			e.printStackTrace();
			throw e;
		}
		return _tmpContactId;
	}
	
	public List<Phone> loadContactPhones(PartnerContact partnerContact) throws Exception{
		List<Phone> phones = null;
		try {
			if (partnerContact != null) {
				phones = new ArrayList<Phone>();
				phones = partnerContactDao.loadContactPhones(partnerContact);
			}
		} catch (Exception e) {
			log.error("An Exception has been thrown " + e);
			throw e;
		}
		return phones;
	}
	
	public List<Phone> saveContactPhones(PartnerContact partnerContact){
		
		
		try {
			if (partnerContact == null)
				return null;
			
			if (partnerContact.getContactId()<=0)
				return null;
			
			if (partnerContact.getPhones()== null)
				return null;
			
			for (Phone _tmpPhone : partnerContact.getPhones()) {
				//if the phone already exist
				if ( _tmpPhone.getPhoneId() > 0){
					phoneLogic.savePhone(_tmpPhone);
					
				//if Phone is New
				}else{
					_tmpPhone.setPhoneId( phoneLogic.savePhone(_tmpPhone));
					partnerContactDao.saveContactPhone(_tmpPhone.getPhoneId(),partnerContact.getContactId(),_tmpPhone.isMainPhone());
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return partnerContact.getPhones();
	}
	
	public IPartnerContactDao getPartnerContactDao(){
		return partnerContactDao;
	}

	
	public void setPartnerContactDao(IPartnerContactDao newVal){
		partnerContactDao = newVal;
	}
	
	public IAddressLogic getAddressLogic() {
		return addressLogic;
	}


	public void setAddressLogic(IAddressLogic addressLogic) {
		this.addressLogic = addressLogic;
	}

	public void setPhoneLogic(IPhoneLogic phoneLogic) {
		this.phoneLogic = phoneLogic;
	}

	public IPhoneLogic getPhoneLogic() {
		return phoneLogic;
	}

}
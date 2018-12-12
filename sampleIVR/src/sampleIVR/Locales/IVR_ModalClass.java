package sampleIVR.Locales;

public class IVR_ModalClass {

	
	protected String locales;
	protected String description;
	
	   public IVR_ModalClass(String locales, String description) {
	        this.locales = locales;
	        this.description=description;
	    }
	
	   public String getLocales() {
	        return locales;
	    }
	   public String getDescription() {
	        return description;
	    }
	   
	   public void setLocales(String locales ) {
		   this.locales = locales;
	    }
	   public void setDescription(String description) {
		   this.description = description;
	    }
}

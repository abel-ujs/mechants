package merchants.entity.canvassResource;

public enum Gender {
 MALE {
	@Override
	public String getGenderInfor() {
		return "男性";
	}
},
 FEMAL {
	@Override
	public String getGenderInfor() {
		return "女性";
	}
};
 public abstract  String getGenderInfor();
 
}

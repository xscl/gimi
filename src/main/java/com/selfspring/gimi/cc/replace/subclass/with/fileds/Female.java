package replace.subclass.with.fileds;

class Female extends Person {

	@Override
	char getCode() {
		return 'F';
	}

	@Override
	boolean isMail() {
		return false;
	}
}

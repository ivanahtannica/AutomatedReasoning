package pl.core;

import java.util.HashMap;
import java.util.Map;

public class ModelImpl implements Model {
	Map<Symbol, Boolean> model = new HashMap<>();

	@Override
	public void set(Symbol sym, boolean value) {
		model.put(sym, value);
	}

	@Override
	public boolean get(Symbol sym) {
		return model.get(sym);
	}

	@Override
	public boolean satisfies(KB kb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean satisfies(Sentence sentence) {
		// TODO Auto-generated method stub
		return sentence.isSatisfiedBy(this);
	}

	@Override
	public void dump() {
		// TODO Auto-generated method stub
		
	}

	public boolean compareToModel(ModelImpl otherModel) {
		if (model.size() > otherModel.model.size()) {
			for (Symbol sym : otherModel.model.keySet()) {
				if (!model.containsKey(sym))
					return false;
				else if (!otherModel.model.get(sym) == model.get(sym)) {
					return false;
				}
			}
		}
		return true;
	}
}

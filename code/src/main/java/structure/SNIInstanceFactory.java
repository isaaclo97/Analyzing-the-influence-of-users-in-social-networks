package structure;

import grafo.optilib.structure.InstanceFactory;

public class SNIInstanceFactory extends InstanceFactory<SNIInstance> {
    @Override
    public SNIInstance readInstance(String s) {
        return new SNIInstance(s);
    }
}

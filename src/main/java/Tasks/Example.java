package Tasks;

import Main.Framework.Task;

public class Example extends Task {

    public Example(String name) {
        super(name);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void execute() {

    }
}
